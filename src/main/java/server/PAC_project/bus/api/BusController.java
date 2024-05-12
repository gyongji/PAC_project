package server.PAC_project.bus.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.service.BusService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    //서울특별시 한정 버스 노선 ID값 정보
    @GetMapping("/route/information")
    public ResponseEntity<List<ResponseBusRouteDTO>> getBusRouteInformation() throws IOException {
        return ResponseEntity.ok(busService.getBusRouteInformation());
    }

}