package server.PAC_project.subway.parser;

import server.PAC_project.subway.model.dto.ResponseSubwayArrivalDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayRealTimeArrival implements SubwayParser<ResponseSubwayArrivalDTO> {
    @Value("${open.api.key.subway-real-time-key}")
    private String subwayRealTimeLocationKey;

    @Value("${open.url.subway-real-time-url}")
    private String subwayRealTimeUrl;

    @Value("${open.url.subway-arrival-url}")
    private String subwayRealTimeArrivalEndPoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ResponseSubwayArrivalDTO> getData(String startPageNumber, String endPageNumber, String regionName) throws IOException {
        return parser(startPageNumber,endPageNumber,regionName);
    }
    private List<ResponseSubwayArrivalDTO> parser(String startPageNumber, String endPageNumber, String regionName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String endPoint = startPageNumber + "/" + endPageNumber + "/" + regionName;
        String STATION_COORDINATES_URL = subwayRealTimeUrl + subwayRealTimeLocationKey +subwayRealTimeArrivalEndPoint+ endPoint;
        System.out.println(STATION_COORDINATES_URL);

        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        JsonNode jsonNode2 = objectMapper.readTree(dataList);
        jsonNode2 = jsonNode1.get("errorMessage");
        jsonNode1 = jsonNode1.get("realtimeArrivalList");

        return objectMapper.readValue(jsonNode1.toString(), new TypeReference<>() {});
    }

}
