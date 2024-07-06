package server.PAC_project.subway.schedule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import server.PAC_project.subway.config.SubwayRoute;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.repository.SubwayRepository;
import server.PAC_project.util.SubwayMapperUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubwayLineD {

    @Value("${open.api.key.subway-real-time-key}")
    private String getSubwayStationNameKey;

    @Value("${open.url.subway-station-name-url}")
    private String getSubwayStationNameUrl;

    @Value("${open.url.subway-station-name-endpoint}")
    private String getSubwayStationNameEndpoint;

    private static final String endPageNumber = "500";
    private static final String startPageNumber = "0";

    private final RestTemplate restTemplate;
    private final SubwayRepository subwayRepository;

    //@Scheduled(cron = "${schedule.cron}")
    //매 월 25일 23시(오후 11시)에 자동으로 실행
    @Scheduled(cron = "0 0 22 06 07 ?")
    public void autoSubwayInformationSave() throws IOException {
        subwayRepository.deleteAll();
        List<ResponseSubwayLineDTO> parser = new ArrayList<>();
        for (SubwayRoute subwayName : SubwayRoute.values()) {
            parser.addAll(parser(subwayName.getLineName()));
        }
        subwayRepository.saveAll(SubwayMapperUtil.mapLineToEntity(parser));
    }

    // Excel Parsing
    private Map<String, String> parserXecel(String subwayName) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("xecel/v0.3_기후동행카드_이용노선도_정리_240513.xlsx");
        Map<String, String> subwayInformationList = new HashMap<>();
        if (inputStream == null) {
            throw new IOException("File ERROR 404");
        }

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheet(subwayName);
        if(sheet != null) {
            int rowindex;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (rowindex = 5; rowindex < physicalNumberOfRows; rowindex++) {

                XSSFRow row = sheet.getRow(rowindex);
                if (row != null) {
                    XSSFCell cell1 = row.getCell(11);
                    XSSFCell cell3 = row.getCell(13);
                    if (cell1 == null || cell3 == null) {
                        break;
                    }
                    if (row.getCell(11).getCellType() == CellType.BLANK) {
                        break;
                    }
                    subwayInformationList.put(cell1.toString(), cell3.toString());
                }
            }
        }
        return subwayInformationList;
    }

    // 플랫폼 실시간 정보 API 요청
    private List<ResponseSubwayLineDTO> parser(String subwayName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = parserXecel(subwayName);
        String STATION_COORDINATES_URL = getSubwayStationNameUrl
                + getSubwayStationNameKey
                +getSubwayStationNameEndpoint
                +"/"+ SubwayLineD.startPageNumber +"/"+ SubwayLineD.endPageNumber +"/%20/%20/"+subwayName;

        System.out.println(STATION_COORDINATES_URL);

        JsonNode jsonNode1 = objectMapper.readTree(restTemplate.getForObject(STATION_COORDINATES_URL, String.class));
        jsonNode1 = jsonNode1.get("SearchSTNBySubwayLineInfo").get("row");

        List<ResponseSubwayLineDTO> result = new ArrayList<>();
        for (JsonNode jsonNode : jsonNode1) {
            if (subwayName.equals(jsonNode.get("LINE_NUM").asText())) {
                ResponseSubwayLineDTO responseSubwayLineDTO = objectMapper.treeToValue(jsonNode, ResponseSubwayLineDTO.class);
                for (String s : map.keySet()) {
                    if(jsonNode.get("STATION_NM").asText().equals(s)) {
                        responseSubwayLineDTO.setInoutCode(map.get(s));
                        break;
                    }
                }
                result.add(responseSubwayLineDTO);
            }
        }
        return result;
    }
}
