package server.PAC_project.subway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import server.PAC_project.subway.config.ResponseSubwayRealTimeArrival;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.repository.SubwayRepository;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SubwayRealTimeArrivalService {
    @Value("${open.api.key.subway-real-time-key}")
    private String subwayRealTimeLocationKey;

    @Value("${open.url.subway-arrival-endpoint}")
    private String subwayRealTimeArrivalEndPoint;

    @Value("${open.url.subway-arrival-url}")
    private String subwayRealTimeUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static final int START_PAGE_NUMBER = 1;
    private static final int END_PAGE_NUMBER = 10;

    private final SubwayRepository subwayRepository;

    public Map<String, List<SearchSubwayLineDTO>> getData(String subwayStationName) throws IOException {
        Map<String, List<SearchSubwayLineDTO>> resultDataList = new HashMap<>();
        resultDataList.put("subwayList" , parser(subwayStationName));
        return resultDataList;
    }

    private List<SearchSubwayLineDTO> parser(String subwayStationName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String endPoint = START_PAGE_NUMBER + "/" + END_PAGE_NUMBER + "/" + subwayStationName;
        String STATION_COORDINATES_URL = subwayRealTimeUrl + subwayRealTimeLocationKey + subwayRealTimeArrivalEndPoint + endPoint;
        System.out.println(STATION_COORDINATES_URL);

        JsonNode jsonNode1 = objectMapper.readTree(restTemplate.getForObject(STATION_COORDINATES_URL, String.class));
        jsonNode1 = jsonNode1.get("realtimeArrivalList");

        List<SearchSubwayLineDTO> subwayLines = objectMapper.readValue(
                filteringSubwayRealTime(jsonNode1, subwayStationName).toString(),
                new TypeReference<List<SearchSubwayLineDTO>>() {}
        );

        List<SearchSubwayLineDTO> sortedLines = subwayLines.stream()
                .sorted(Comparator.comparingInt(SearchSubwayLineDTO::returnBarvDt)) // Assume barvlDt is in seconds
                .toList();

        Map<String, SearchSubwayLineDTO> uniqueMap = new LinkedHashMap<>();
        for (SearchSubwayLineDTO line : sortedLines) {
            String key = line.getUpdnLine() + ":" + line.getSubwayId();
            if (!uniqueMap.containsKey(key)) {
                uniqueMap.put(key, line);
            }
        }

        return new ArrayList<>(uniqueMap.values());
    }

    private ArrayNode filteringSubwayRealTime(JsonNode jsonNode, String subwayStationName) {
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
        return subwayRepository.searchSubwayLine(subwayStationName, jsonNode.asText().replace("\"", ""));
    }
}
