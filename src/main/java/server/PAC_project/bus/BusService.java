package server.PAC_project.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.service.BusSearch;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusSearch busSearch;

    public ResponseBusRouteDTO busRouteSearch(String routeName) {
        return busSearch.searchBusRoute(routeName);
    }

}