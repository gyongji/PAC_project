package server.PAC_project.subway.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import server.PAC_project.subway.config.SubwayRoute;
import server.PAC_project.subway.model.entity.Line;

@Data
public class AllStationDTO {

    @Schema(description = "타입 코드")
    private String typeCode;

    @Schema(description = "지하철 도시 코드")
    private String localCode;

    @Schema(description = "지하철 라인 명")
    private String stationLine;

    @Schema(description = "지하철 라인 코드")
    private String stationLineCode;

    @Schema(description = "지하철 역 명")
    private String stationName;

    @Schema(description = "지하철 역 명 코드")
    private String stationCode;

    @Schema(description = "승하차 코드")
    private String inoutCode;


    @Builder
    public AllStationDTO(String typeCode, String localCode, String stationLine, String stationLineCode, String stationName, String stationCode, String inoutCode) {
        this.typeCode = typeCode;
        this.localCode = localCode;
        this.stationLine = stationLine;
        this.stationLineCode = stationLineCode;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.inoutCode = inoutCode;
    }

    public static AllStationDTO toEn(Line line) {
        return AllStationDTO.builder()
                .inoutCode(line.getInoutCode())
                .stationLine(line.getStationLine())
                .localCode(line.getLocalCode())
                .stationCode(line.getStationCode())
                .stationLineCode(line.getStationCode())
                .stationName(line.getStationName())
                .typeCode(line.getTypeCode())
                .build();
    }
}
