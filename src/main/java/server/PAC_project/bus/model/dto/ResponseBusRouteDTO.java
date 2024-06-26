package server.PAC_project.bus.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseBusRouteDTO {

    private String ROUTENAME;
    private String INOUT_CODE;

    @Builder
    public ResponseBusRouteDTO(String ROUTENAME, String INOUT_CODE) {
        this.ROUTENAME = ROUTENAME;
        this.INOUT_CODE = INOUT_CODE;
    }

}