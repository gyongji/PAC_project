package server.PAC_project.subway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.subway.model.dto.AllStationDTO;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.model.entity.Line;
import server.PAC_project.subway.repository.SubwayRepository;
import server.PAC_project.util.SubwayMapperUtil;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayService {
    private final SubwayRepository subwayRepository;

    public List<AllStationDTO> searchAllSubwayStation() throws IOException {
        List<Line> all = subwayRepository.findAll();
        return SubwayMapperUtil.mapLineToDto(all);
    }
}
