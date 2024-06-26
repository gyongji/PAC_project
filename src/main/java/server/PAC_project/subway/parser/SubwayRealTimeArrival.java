package server.PAC_project.subway.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.common.Mutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import server.PAC_project.subway.config.ResponseSubwayRealTimeArrival;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.repository.SubwayRepository;

import javax.management.monitor.Monitor;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;

@Service
@RequiredArgsConstructor
public class SubwayRealTimeArrival implements SubwayParser<SearchSubwayLineDTO> {
    @Value("${open.api.key.subway-real-time-key}")
    private String subwayRealTimeLocationKey;

    @Value("${open.url.subway-real-time-url}")
    private String subwayRealTimeUrl;

    @Value("${open.url.subway-arrival-url}")
    private String subwayRealTimeArrivalEndPoint;

    @Autowired
    private RestTemplate restTemplate;

    private static final int START_PAGE_NUMBER = 1;
    private static final int END_PAGE_NUMBER = 10;

    private final SubwayRepository subwayRepository;

    @Override
    public List<SearchSubwayLineDTO> getData(String subwayStationName) throws IOException {
        return parser(subwayStationName);
    }

    private List<SearchSubwayLineDTO> parser(String subwayStationName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String endPoint = START_PAGE_NUMBER + "/" + END_PAGE_NUMBER + "/" + subwayStationName;
        String STATION_COORDINATES_URL = subwayRealTimeUrl + subwayRealTimeLocationKey +subwayRealTimeArrivalEndPoint+ endPoint;
        System.out.println(STATION_COORDINATES_URL);

        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        JsonNode jsonNode2 = objectMapper.readTree(dataList);
        jsonNode2 = jsonNode1.get("errorMessage");
        jsonNode1 = jsonNode1.get("realtimeArrivalList");

        return objectMapper.readValue(filteringSubwayRealTime(jsonNode1,subwayStationName).toString(), new TypeReference<>() {});
    }

    private ArrayNode filteringSubwayRealTime(JsonNode jsonNode,String subwayStationName) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (JsonNode node : jsonNode) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            for (ResponseSubwayRealTimeArrival value : ResponseSubwayRealTimeArrival.values()) {
                objectNode.put(value.getFieldName(), node.get(value.getFieldName()));
            }
            objectNode.put("inoutCode", searchSubwayLineInoutCode(subwayStationName, node.get("subwayId")));
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }

    private String searchSubwayLineInoutCode(String subwayStationName, JsonNode jsonNode) {
        String subwayLineCode = jsonNode.asText().replace("\"", "");
        return subwayRepository.searchSubwayLine(subwayStationName, subwayLineCode);
    }
}
