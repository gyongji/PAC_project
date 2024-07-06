package server.PAC_project.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.bus.model.dto.FinalBusDTO;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.util.BusMapto;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusSearchService busSearch;
    private final BusRepository busRepository;

    public ResponseBusRouteDTO busRouteSearch(String routeName) {
        return busSearch.searchBusRoute(routeName);
    }

    public Map<String, List<FinalBusDTO>> getBusDtos() {
        return BusMapto.mapBusToFinalBusDTO(busRepository.findAll());
    }

}