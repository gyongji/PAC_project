package server.PAC_project.subway.parser;

import server.PAC_project.subway.model.dto.ResponesSubwayLocationDTO;
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
//@Qualifier("subwayLocationService")
public class SubwayRealTimeLocation implements SubwayParser<ResponesSubwayLocationDTO> {
    @Value("${open.api.key.subway-real-time-key}")
    private String subwayRealTimeLocationKey;

    @Value("${open.url.subway-real-time-url}")
    private String subwayRealTimeUrl;

    @Value("${open.url.subway-location-url}")
    private String subwayRealTimeLocationEndPoint;

    @Autowired
    private RestTemplate restTemplate;


    public List<ResponesSubwayLocationDTO> getData(String startPageNumber, String endPageNumber, String subwayName) throws IOException {
        return parser(startPageNumber,endPageNumber,subwayName);
    }

    private List<ResponesSubwayLocationDTO> parser(String startPageNumber, String endPageNumber, String subwayName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String endPoint = startPageNumber + "/" + endPageNumber + "/" + subwayName;
        String STATION_COORDINATES_URL = subwayRealTimeUrl + subwayRealTimeLocationKey +subwayRealTimeLocationEndPoint+ endPoint;


        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        JsonNode jsonNode2 = objectMapper.readTree(dataList);
        jsonNode2 = jsonNode1.get("errorMessage");
        jsonNode1 = jsonNode1.get("realtimePositionList");

        return objectMapper.readValue(jsonNode1.toString(), new TypeReference<>() {});
    }

}
