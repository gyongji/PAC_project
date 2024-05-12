package server.PAC_project.bus.service;

import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;
import server.PAC_project.bus.parser.BusParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusParser<ResponseBusRouteDTO> busRouteBusParser;

    public List<ResponseBusRouteDTO> getBusRouteInformation() throws JsonProcessingException {
        return busRouteBusParser.getData();
    }

}
