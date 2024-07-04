package server.PAC_project.bus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestBusRouteDTO {

    @JsonProperty("RTE_NM")
    private String ROUTE_NAME;

    @JsonProperty("RTE_ID")
    private String ROUTE_ID;

}