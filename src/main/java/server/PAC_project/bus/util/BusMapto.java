package server.PAC_project.bus.util;

import server.PAC_project.bus.model.dto.FinalBusDTO;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.model.entity.BusEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusMapto {

    public static List<BusEntity> mapBusToEtity(List<FinalBusDTO> responseBusRouteDTO) {
        return responseBusRouteDTO.stream()
                .map(FinalBusDTO::toEntity)
                .collect(Collectors.toList());
    }

    public static ResponseBusRouteDTO mapBusToDTO(BusEntity busEntity) {
        return ResponseBusRouteDTO.builder()
                .ROUTENAME(busEntity.getROUTENAME())
                .INOUT_CODE(busEntity.getINOUT())
                .build();
    }

    public static List<FinalBusDTO> mapBusToFinalBusDTO(List<BusEntity> busEntities) {
        List<FinalBusDTO> finalBusDTOs = new ArrayList<>();
        for (BusEntity busEntity : busEntities) {
            FinalBusDTO finalBusDTO = new FinalBusDTO();
            finalBusDTO.setROUTEID(busEntity.getROUTEID());
            finalBusDTO.setROUTENAME(busEntity.getROUTENAME());
            finalBusDTO.setINOUT_CODE(busEntity.getINOUT());
            finalBusDTOs.add(finalBusDTO);
        }
        return finalBusDTOs;
    }

}