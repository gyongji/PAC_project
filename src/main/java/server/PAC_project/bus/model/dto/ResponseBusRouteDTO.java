package server.PAC_project.bus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseBusRouteDTO {
    @JsonProperty("ROUTE")
    private String ROUTE_NAME;
    @JsonProperty("ROUTE_ID")
    private String ROUTE_ID;
}
