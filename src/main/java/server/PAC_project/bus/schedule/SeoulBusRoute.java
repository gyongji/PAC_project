package server.PAC_project.bus.schedule;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.util.BusMapto;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private BusRepository busRepository;

    // 서울특별시 한정 버스 노선 ID값 조회 서비스
    @Scheduled(cron = "0 0 03 25 * ?")
    public void getData() throws JsonProcessingException {
       List<BusEntity> BusEntitys = BusMapto.mapBusToEtity(parser());
       busRepository.saveAll(BusEntitys);
    }

    // Exel Parsing
    public Map<String, String> parseBusExcel(String busRouteName) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("bus/v0.3_기후동행카드_이용노선도_정리_240513.xlsx");
        Map<String, String> busRouteInformationList = new HashMap<>();
        if (inputStream == null) {
            throw new IOException("File ERROR 404");
        }

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheet(busRouteName);
        if(sheet != null) {
            int rowIndex;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (rowIndex = 5; rowIndex < physicalNumberOfRows; rowIndex++) {

                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    XSSFCell cell1 = row.getCell(0); // Assuming the bus route ID is stored in the first column
                    XSSFCell cell3 = row.getCell(1); // Assuming the bus route name is stored in the second column
                    if (cell1 == null || cell3 == null) {
                        break;
                    }
                    if (row.getCell(0).getCellType() == CellType.BLANK) {
                        break;
                    }
                    busRouteInformationList.put(cell1.toString(), cell3.toString());
                }
            }
        }
        return busRouteInformationList;
    }

    // 정보 데려오는 부분
    private List<ResponseBusRouteDTO> parser() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String STATION_COORDINATES_URL = seoulBusRouteUrl
                + seoulBusRouteKey
                + seoulBusRouteEndPoint
                + START_NUMBER + "/"
                + END_NUMBER + "/";
        System.out.println(STATION_COORDINATES_URL);

        // URL에 요청 보내서 String.class(문자열) 형식으로 받아옴
        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        System.out.println(dataList);

        // JSON 데이터를 파싱하여 특정 필드를 추출
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        jsonNode1 = jsonNode1.get("busRoute");
        jsonNode1 = jsonNode1.get("row");
        System.out.println(jsonNode1);

        // JSON 데이터를 LIST<ResponseBusRouteDTO> 형태로 변환
        return objectMapper.readValue(jsonNode1.toString(), new TypeReference<>() {});
    }
}