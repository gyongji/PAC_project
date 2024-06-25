package server.PAC_project.subway.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.PAC_project.subway.SubwayService;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.schedule.SubwayLineD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subway")
public class SubwayController {

    private final SubwayService service;

    //지하철 플랫폼 정보
    @GetMapping("/searchingStation")
    public List<SearchSubwayLineDTO> searchSubwayLine(@RequestParam("subwayName") String subwayName) throws IOException {
        return service.searchSubwayLine(subwayName);
    }

}
