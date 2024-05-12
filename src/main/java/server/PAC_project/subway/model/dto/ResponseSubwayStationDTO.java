package server.PAC_project.subway.model.dto;

import lombok.Data;

@Data
public class ResponseSubwayStationDTO {
    private String lineNm;
    private String convX;
    private String stnKrNm;
    private String convY;
    private String outStnNum;
}
