package server.PAC_project.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.bus.model.dto.FinalBusDTO;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.service.BusSearch;
import server.PAC_project.bus.util.BusMapto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusSearch busSearch;
    private final BusRepository busRepository;

    public ResponseBusRouteDTO busRouteSearch(String routeName) {
        return busSearch.searchBusRoute(routeName);
    }

    public List<FinalBusDTO> getBusDtos() {
        List<BusEntity> busEntities = busRepository.findAll(); // BusEntity의 리스트를 가져오는 코드
        List<FinalBusDTO> busDtos = BusMapto.mapBusToFinalBusDTO(busEntities); // BusEntity를 FinalBusDTO로 매핑하는 코드
        return busDtos;
    }

}