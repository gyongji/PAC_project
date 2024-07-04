package server.PAC_project.bus.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.PAC_project.bus.BusService;
import server.PAC_project.bus.model.dto.FinalBusDTO;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.schedule.SeoulBusRoute;
import server.PAC_project.bus.util.BusMapto;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;
    private final BusRepository busRepository;

    private final SeoulBusRoute seoulBusRoute;

    @GetMapping("/test")
    public ResponseEntity<String> test() throws IOException {
        seoulBusRoute.getData();
        return ResponseEntity.ok("Test OK");
    }

    @GetMapping("/information")
    public ResponseEntity<ResponseBusRouteDTO> requestBusSearch(@RequestParam("busRouteName") String busRouteName) {
        return ResponseEntity.ok(busService.busRouteSearch(busRouteName));
    }

    @GetMapping("/routes")
    ResponseEntity<List<FinalBusDTO>> getAllBusRoutes() {
        List<BusEntity> busEntities = busRepository.findAll(); // BusEntity의 리스트를 가져오는 코드
        List<FinalBusDTO> busDtos = BusMapto.mapBusToFinalBusDTO(busEntities); // BusEntity를 FinalBusDTO로 매핑하는 코드
        return ResponseEntity.ok(busDtos);
    }
}