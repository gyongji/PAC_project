package server.PAC_project.subway.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import server.PAC_project.subway.model.entity.Line;

@Data
public class AllStationListDTO {

    @Schema(description = "지하철 역 명")
    private String stationName;

    @Schema(description = "승하차 코드")
    private String inoutCode;

    @Schema(description = "타입 코드")
    private String typeCode;

    @Schema(description = "지하철 도시 코드")
    private String localCode;

    @Schema(description = "지하철 라인 명")
    private String stationLine;

    @Schema(description = "지하철 라인 코드")
    private String stationLineCode;




    @Builder
    public AllStationListDTO(String typeCode, String localCode, String stationLine, String stationLineCode, String stationName, String inoutCode) {
        this.typeCode = typeCode;
        this.localCode = localCode;
        this.stationLine = stationLine;
        this.stationLineCode = stationLineCode;
        this.stationName = stationName;
        this.inoutCode = inoutCode;
    }

    public static AllStationListDTO toEnAll(Line line) {
        return AllStationListDTO.builder()
                .typeCode(line.getTypeCode())
                .localCode(line.getLocalCode())
                .stationLine(line.getStationLine())
                .stationLineCode(line.getStationLineCode())
                .stationName(line.getStationName())
                .inoutCode(line.getInoutCode())
                .build();

    }
}
