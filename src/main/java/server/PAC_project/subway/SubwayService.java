package server.PAC_project.subway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.parser.SubwayRealTimeArrival;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayService {

    private final SubwayRealTimeArrival subwayArrivalDTOSubwayParser;


    public List<SearchSubwayLineDTO> searchSubwayLine(String regionName) throws IOException {
        return subwayArrivalDTOSubwayParser.getData(regionName);
    }
}
