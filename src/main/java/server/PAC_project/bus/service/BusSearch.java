package server.PAC_project.bus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;
import server.PAC_project.bus.repository.BusRepository;
import server.PAC_project.bus.util.BusMapto;

@Service
@RequiredArgsConstructor
public class BusSearch {

    private final BusRepository busRepository;

    public ResponseBusRouteDTO searchBusRoute(String routeName) {
        BusEntity byINOUT = busRepository.findBusEntityByROUTENAME(routeName);
        return BusMapto.mapBusToDTO(byINOUT);
    }

}