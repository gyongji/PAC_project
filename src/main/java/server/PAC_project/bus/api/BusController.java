package server.PAC_project.bus.api;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.PAC_project.bus.BusService;
import server.PAC_project.bus.model.dto.FinalBusDTO;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.util.BusMapto;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    @GetMapping("/information")
    public ResponseEntity<ResponseBusRouteDTO> requestBusSearch(@RequestParam("busRouteName") String busRouteName) {
        return ResponseEntity.ok(busService.busRouteSearch(busRouteName));
    }

    @GetMapping("/routes")
    public ResponseEntity<Map<String, List<FinalBusDTO>>> getAllBusRoutes() {
        return ResponseEntity.ok(busService.getBusDtos());
    }

}