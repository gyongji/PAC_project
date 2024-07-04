package server.PAC_project.bus.schedule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import server.PAC_project.bus.model.dto.FinalBusDTO;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.util.BusMapto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeoulBusRoute {

    //시작 페이지
    private final int START_NUMBER = 1;

    //마지막 페이지
    private final int END_NUMBER = 900;


    @Value("${open.api.key.seoul-bus-route-key}")
    private String seoulBusRouteKey;

    @Value("${open.url.seoul-bus-route-url}")
    private String seoulBusRouteUrl;

    @Value("${open.url.seoul-bus-route-endpoint}")
    private String seoulBusRouteEndPoint;

    @Autowired
    private RestTemplate restTemplate;

    private final BusRepository busRepository;

    // 서울특별시 한정 버스 노선 ID값 조회 서비스
    @Scheduled(cron = "0 0 03 25 * ?")
    @Transactional
    public void getData() throws IOException {


        // 데이터베이스 초기화 (기존 데이터를 삭제)
        busRepository.deleteAll();
        // 파싱된 데이터를 BusEntity 객체로 매핑
        List<BusEntity> busEntities = BusMapto.mapBusToEtity(parser());

        // 매핑된 BusEntity 객체를 데이터베이스에 저장
        busRepository.saveAll(busEntities);
    }

    // Exel Parsing
    public Map<String, String> parseBusExcel() throws IOException {
        InputStream inputStream
                = Thread.currentThread().getContextClassLoader().getResourceAsStream("xecel/v0.3_기후동행카드_이용노선도_정리_240513.xlsx");
        Map<String, String> busRouteInformationList = new HashMap<>();
        if (inputStream == null) {
            throw new IOException("File ERROR 404");
        }

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // 엑셀 시트에서 버스 이름 가져오기
        XSSFSheet sheet = workbook.getSheetAt(1);
        if (sheet != null) {
            int rowIndex;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (rowIndex = 6; rowIndex < physicalNumberOfRows; rowIndex++) {

                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    XSSFCell cell1 = row.getCell(10); // Assuming the bus route ID is stored in the first column
                    XSSFCell cell3 = row.getCell(12); // Assuming the bus route name is stored in the second column
                    if (cell1 == null || cell3 == null) {
                        break;
                    }
                    if (row.getCell(10).getCellType() == CellType.BLANK) {
                        break;
                    }
                    busRouteInformationList.put(cell1.toString(), cell3.toString());
                }
            }
        }
        return busRouteInformationList;
    }

    // 정보 데려오는 부분
    private List<FinalBusDTO> parser() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> stringStringMap = parseBusExcel();
        List<FinalBusDTO> busEntities = new ArrayList<>();
        String STATION_COORDINATES_URL = seoulBusRouteUrl
                + seoulBusRouteKey
                + seoulBusRouteEndPoint
                + START_NUMBER + "/"
                + END_NUMBER + "/";

        // URL에 요청 보내서 String.class(문자열) 형식으로 받아옴
        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);

        // JSON 데이터를 파싱하여 특정 필드를 추출
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        jsonNode1 = jsonNode1.get("busRoute").get("row");

        // 현재 데이터베이스에서 이미 존재하는 ROUTE_ID 목록을 가져옴
        List<String> existingRouteIds = busRepository.findAll().stream()
                .map(BusEntity::getROUTEID)
                .toList();

        for (JsonNode jsonNode : jsonNode1) {
            FinalBusDTO finalBusDTO = objectMapper.treeToValue(jsonNode, FinalBusDTO.class);

            // 기본 값 RS900으로 설정 (RS900 = 기후동행카드 사용 불가능 버스)
            finalBusDTO.setINOUT_CODE("RS900");
            for (String s : stringStringMap.keySet()) {
                if (jsonNode.get("RTE_NM").asText().equals(s)) {
                    finalBusDTO.setINOUT_CODE(stringStringMap.get(s));
                    break;
                }
            }
            // 중복된 ROUTE_ID인지 확인하고 중복되지 않은 경우에만 추가
            if (!existingRouteIds.contains(finalBusDTO.getROUTEID())) {
                busEntities.add(finalBusDTO);
            }

        }
        System.out.println(jsonNode1);
        objectMapper.readValue(jsonNode1.toString(), new TypeReference<>() {
        });

        // JSON 데이터를 LIST<ResponseBusRouteDTO> 형태로 변환
        return busEntities;
    }

}