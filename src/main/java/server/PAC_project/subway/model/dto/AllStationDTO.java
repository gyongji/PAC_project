package server.PAC_project.subway.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import server.PAC_project.subway.config.SubwayRoute;
import server.PAC_project.subway.model.entity.Line;

import java.util.List;

@Data
public class AllStationDTO {

    @Schema(description = "지하철 역 명")
    private String stationName;

    @Schema(description = "승하차 코드")
    private String inoutCode;

    @Schema(description = "타입 코드")
    private String typeCode;

    @Schema(description = "지하철 도시 코드")
    private String localCode;

    @Schema(description = "지하철 라인 명")
    private List<String> stationLine;

    @Schema(description = "지하철 라인 코드")
    private List<String> stationLineCode;




    @Builder
    public AllStationDTO(String typeCode, String localCode, List<String> stationLine, List<String> stationLineCode, String stationName, String inoutCode) {
        this.typeCode = typeCode;
        this.localCode = localCode;
        this.stationLine = stationLine;
        this.stationLineCode = stationLineCode;
        this.stationName = stationName;
        this.inoutCode = inoutCode;
    }

}
