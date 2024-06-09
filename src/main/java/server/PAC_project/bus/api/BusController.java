package server.PAC_project.bus.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.PAC_project.bus.schedule.SeoulBusRoute;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
public class BusController {

    private final SeoulBusRoute busService;

    //서울특별시 한정 버스 노선 ID값 정보
    @GetMapping("/route/information")
    public void getBusRouteInformation() throws IOException {
        busService.getData();
    }

}