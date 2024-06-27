package server.PAC_project.subway.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.PAC_project.subway.SubwayService;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subway")
public class SubwayController {

    private final SubwayService service;

    //지하철 플랫폼 정보
    @Operation(summary = "지하철 검색 API", description = "해당 역의 정보를 출력한다.")
    @GetMapping("/searchingStation")
    public List<SearchSubwayLineDTO> searchSubwayLine(@RequestParam("station") String subwayName) throws IOException {
        return service.searchSubwayLine(subwayName);
    }

}
