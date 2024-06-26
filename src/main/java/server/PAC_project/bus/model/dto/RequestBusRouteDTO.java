package server.PAC_project.bus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestBusRouteDTO {

    @JsonProperty("ROUTE")
    private String ROUTE_NAME;

    @JsonProperty("ROUTE_ID")
    private String ROUTE_ID;

}