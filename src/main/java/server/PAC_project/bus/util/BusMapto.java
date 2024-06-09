package server.PAC_project.bus.util;

import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BusMapto {
    public static List<BusEntity> mapBusToEtity(List<ResponseBusRouteDTO> responseBusRouteDTO) {
        return responseBusRouteDTO.stream()
                .map(ResponseBusRouteDTO::toEntity)
                .collect(Collectors.toList());
    }
}
