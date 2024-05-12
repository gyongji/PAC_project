package server.PAC_project.subway.api;

import server.PAC_project.subway.model.dto.ResponesSubwayLocationDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayArrivalDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayStationDTO;
import server.PAC_project.subway.service.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subway")
public class SubwayController {

    private final SubwayService service;

    @GetMapping("/subwayLineInputData")
    public ResponseEntity<List<ResponseSubwayLineDTO>> subwayLineInputDataTest(
            @RequestParam("startPageNumber") String startPageNumber,
            @RequestParam("endPageNumber") String endPageNumber,
            @RequestParam("subwayName") String subwayName
    ) throws IOException {
        return ResponseEntity.ok(service.getSubwayStationName(startPageNumber, endPageNumber, subwayName));
    }

    //----------------------------------------------------------------------//

    // 서울 지하철 노선 좌표 값 x, y
    @GetMapping("/station")
    public ResponseEntity<List<ResponseSubwayStationDTO>> getStation() throws IOException {
        return ResponseEntity.ok(service.getStationCoordinates());
    }

    //실시간 지하철 위치
    @GetMapping("/real/time/location")
    public ResponseEntity<List<ResponesSubwayLocationDTO>> getRealTimeSubwayLocation(
            @RequestParam("startPageNumber") String startPageNumber,
            @RequestParam("endPageNumber") String endPageNumber,
            @RequestParam("subwayName") String subwayName
    ) throws IOException {
        return ResponseEntity.ok(service.getSubwayRealTimeLocation(startPageNumber, endPageNumber, subwayName));
    }

    //실시간 지하철 도착 시간
    @GetMapping("/real/time/arrival")
    public ResponseEntity<List<ResponseSubwayArrivalDTO>> getRealTimeSubwayArrival(
            @RequestParam("startPageNumber") String startPageNumber,
            @RequestParam("endPageNumber") String endPageNumber,
            @RequestParam("subwayName") String regionName
    ) throws IOException {
        return ResponseEntity.ok(service.getSubwayRealTimeArrival(startPageNumber, endPageNumber, regionName));
    }
}
