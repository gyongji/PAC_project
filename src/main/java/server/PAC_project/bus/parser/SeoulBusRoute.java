package server.PAC_project.bus.parser;

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

import java.util.List;
@Service
@RequiredArgsConstructor
public class SeoulBusRoute implements BusParser<ResponseBusRouteDTO> {

    //시작 페이지
    private final int START_NUMBER = 1;
    
    //마지막 페이지
    private final int END_NUMBER = 100;


    @Value("${open.api.key.seoul-bus-route-key}")
    private String seoulBusRouteKey;

    @Value("${open.url.seoul-bus-route-url}")
    private String seoulBusRouteUrl;

    @Value("${open.url.seoul-bus-route-endpoint}")
    private String seoulBusRouteEndPoint;

    @Autowired
    private RestTemplate restTemplate;


    //서울특별시 한정 버스 노선 ID값 조회 서비스
    @Override
    public List<ResponseBusRouteDTO> getData() throws JsonProcessingException {
        return parser();
    }

    private List<ResponseBusRouteDTO> parser() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String STATION_COORDINATES_URL = seoulBusRouteUrl + "/"
                + seoulBusRouteKey + "/"
                + seoulBusRouteEndPoint + "/"
                + START_NUMBER + "/"
                + END_NUMBER + "/";
        System.out.println(STATION_COORDINATES_URL);

        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        System.out.println(dataList);
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        jsonNode1 = jsonNode1.get("busRoute");
        jsonNode1 = jsonNode1.get("row");
        System.out.println(jsonNode1);
        return objectMapper.readValue(jsonNode1.toString(), new TypeReference<>() {});
    }

}
