package server.PAC_project.subway.parser;

import server.PAC_project.subway.model.dto.ResponseSubwayStationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Qualifier("subwayStationService")
public class SubwayStationLocationSubwayParser implements SubwayParser<ResponseSubwayStationDTO> {

    @Value("${open.api.key.subway-station-coordinates-key}")
    private String subwayStationCoordinatesKey;

    @Value("${open.url.subway-station-coordinates-url}")
    private String subwayStationCoordinatesUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ResponseSubwayStationDTO> getData() throws JsonProcessingException {
        return parser();
    }

    private List<ResponseSubwayStationDTO> parser() throws JsonProcessingException {
        String STATION_COORDINATES_URL = subwayStationCoordinatesUrl + subwayStationCoordinatesKey;
        ObjectMapper objectMapper = new ObjectMapper();
        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        return objectMapper.readValue(dataList, new TypeReference<>() {
        });
    }

//    private int ConnectionUrlCheck(String STATION_COORDINATES_URL) throws IOException {
//        HttpURLConnection urlConnection;
//        URL url = new URL(STATION_COORDINATES_URL);
//        urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setConnectTimeout(3000);
//        urlConnection.setReadTimeout(3000);
//        urlConnection.setRequestMethod("GET");
//        return urlConnection.getResponseCode();
//    }
}
