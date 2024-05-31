package server.PAC_project.subway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.parser.SubwayParser;
import server.PAC_project.subway.schedule.SubwayLineD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubwayService {

    private final SubwayParser<SearchSubwayLineDTO> subwayArrivalDTOSubwayParser;
    private final SubwayLineD subwayLineD;


    public List<SearchSubwayLineDTO> searchSubwayLine(String regionName) throws IOException {
        return subwayArrivalDTOSubwayParser.getData(regionName);
    }
}
