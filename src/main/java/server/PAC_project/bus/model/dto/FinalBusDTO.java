package server.PAC_project.bus.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import server.PAC_project.bus.model.entity.BusEntity;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalBusDTO {

    @JsonProperty("ROUTE")
    private String ROUTENAME;

    @JsonProperty("ROUTE_ID")
    private String ROUTEID;

    private String INOUT_CODE;

    public BusEntity toEntity() {
        return BusEntity.toEn(this);
    }

}