package server.PAC_project.subway.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.PAC_project.subway.config.SubwayRoute;
import server.PAC_project.subway.model.dto.AllStationDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "line")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeCode;

    private String localCode;

    private String stationLine;

    private String stationLineCode;

    private String stationName;


    private String inoutCode;

    @Builder
    public Line(String typeCode, String localCode, String stationLine, String stationLineCode ,String stationName,  String inoutCode) {
        this.typeCode = typeCode;
        this.localCode = localCode;
        this.stationLine = stationLine;
        this.stationLineCode = stationLineCode;
        this.stationName = stationName;
        this.inoutCode = inoutCode;
    }

    public static Line toEn(ResponseSubwayLineDTO responseSubwayLineDTO) {
        String subwayLineCode = SubwayRoute.getSubwayLineCode(responseSubwayLineDTO.getStationLine());
        return Line.builder()
                .stationLine(responseSubwayLineDTO.getStationLine())
                .stationLineCode(subwayLineCode)
                .inoutCode(responseSubwayLineDTO.getInoutCode())
                .stationName(responseSubwayLineDTO.getStationName())
                .localCode(responseSubwayLineDTO.getLocalCode())
                .typeCode(responseSubwayLineDTO.getTypeCode())
                .build();
    }



}
