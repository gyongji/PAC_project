package server.PAC_project.subway.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.PAC_project.bus.schedule.SeoulBusRoute;
import server.PAC_project.subway.SubwayRealTimeArrivalService;
import server.PAC_project.subway.SubwayService;
import server.PAC_project.subway.model.dto.AllStationDTO;
import server.PAC_project.subway.model.dto.AllStationListDTO;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import io.swagger.v3.oas.annotations.Operation;
import server.PAC_project.subway.schedule.SubwayLineD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subway")
public class SubwayController {

    private final SubwayService service;
    private final SubwayRealTimeArrivalService subwayRealTimeArrivalService;
    @Operation(summary = "지하철 검색 API", description = "해당 역의 정보를 출력한다.")
    @GetMapping("/searchingStation")
    public ResponseEntity<Map<String, List<SearchSubwayLineDTO>>> searchSubwayLine(@RequestParam("station") String subwayName) throws IOException {
        return ResponseEntity.ok(subwayRealTimeArrivalService.getData(subwayName));
    }

    @Operation(summary = "지하철 모든 역정보 검색 API", description = "모든 역의 정보를 출력한다. ( 중복값 X )")
    @GetMapping("/searchingNotDuplicationAllStation")
    public ResponseEntity<Map<String, List<AllStationDTO>>> searchAllMapSubwayStation() throws IOException {
        return ResponseEntity.ok(service.searchAllMapSubwayStation());
    }

    @Operation(summary = "지하철 모든 역정보 검색 API", description = "모든 역의 정보를 출력한다. ( 중복값 List )")
    @GetMapping("/searchingDuplicationAllStation")
    public ResponseEntity<Map<String, List<AllStationListDTO>>> searchAllListSubwayStation() throws IOException {
        return ResponseEntity.ok(service.searchAllListSubwayStation());
    }


}
