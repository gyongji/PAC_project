package server.PAC_project.bus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import server.PAC_project.bus.model.entity.BusEntity;

@Data
public class ResponseBusRouteDTO {
    @JsonProperty("ROUTE")
    private String ROUTE_NAME;
    @JsonProperty("ROUTE_ID")
    private String ROUTE_ID;

    public BusEntity toEntity() {
        return BusEntity.toEn(this);
    }
}