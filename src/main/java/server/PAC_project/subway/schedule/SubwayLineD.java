package server.PAC_project.subway.schedule;

import server.PAC_project.config.SubwayRoute;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.parser.SubwayParser;
import server.PAC_project.subway.repository.SubwayRepository;
import server.PAC_project.util.SubwayMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayLineD implements SubwayParser<ResponseSubwayLineDTO> {

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

    public List<ResponseSubwayLineDTO> getData() throws IOException {
        List<ResponseSubwayLineDTO> parser = new ArrayList<>();
        for (SubwayRoute subwayName : SubwayRoute.values()) {
            parser.addAll(parser(startPageNumber, endPageNumber, subwayName.getLineName()));
        }
        subwayRepository.saveAll(SubwayMapperUtil.mapLineToEntity(parser));
        return parser;
    }

    private List<ResponseSubwayLineDTO> parser(String startPageNumber, String endPageNumber, String subwayName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String STATION_COORDINATES_URL = getSubwayStationNameUrl
                + getSubwayStationNameKey
                +getSubwayStationNameEndpoint
                +"/"+startPageNumber+"/"+endPageNumber+"/%20/%20/"+subwayName;
        System.out.println(STATION_COORDINATES_URL);
        String dataList = restTemplate.getForObject(STATION_COORDINATES_URL, String.class);
        JsonNode jsonNode1 = objectMapper.readTree(dataList);
        jsonNode1 = jsonNode1.get("SearchSTNBySubwayLineInfo");
        jsonNode1 = jsonNode1.get("row");
        List<ResponseSubwayLineDTO> result = new ArrayList<>();
        for (JsonNode jsonNode : jsonNode1) {
            ResponseSubwayLineDTO responseSubwayLineDTO = objectMapper.treeToValue(jsonNode, ResponseSubwayLineDTO.class);
            result.add(responseSubwayLineDTO);
        }

        objectMapper.readValue(jsonNode1.toString(), new TypeReference<>() {});
        return result;
    }
}
