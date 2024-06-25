package server.PAC_project.bus.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.PAC_project.bus.BusService;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    @GetMapping("/information")
    public ResponseEntity<ResponseBusRouteDTO> requestBusSearch(@RequestParam("busRouteName") String busRouteName) {
        return ResponseEntity.ok(busService.busRouteSearch(busRouteName));
    }

}