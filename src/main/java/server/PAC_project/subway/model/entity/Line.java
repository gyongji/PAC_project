package server.PAC_project.subway.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String typeCode;

    private String localCode;

    private String stationLine;

    private String stationName;

    private String stationCode;

    private String inoutCode;

    public Line(String typeCode, String localCode, String stationLine, String stationName, String stationCode, String inoutCode) {
        this.typeCode = typeCode;
        this.localCode = localCode;
        this.stationLine = stationLine;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.inoutCode = inoutCode;
    }

    public static Line toEn(ResponseSubwayLineDTO responseSubwayLineDTO) {
        return Line.builder()
                .stationLine(responseSubwayLineDTO.getStationLine())
                .inoutCode(responseSubwayLineDTO.getInoutCode())
                .stationCode(responseSubwayLineDTO.getStationCode())
                .stationName(responseSubwayLineDTO.getStationName())
                .localCode(responseSubwayLineDTO.getLocalCode())
                .typeCode(responseSubwayLineDTO.getTypeCode())
                .build();
    }

}
