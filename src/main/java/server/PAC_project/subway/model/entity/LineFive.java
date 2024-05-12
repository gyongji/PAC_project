package server.PAC_project.subway.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class LineFive {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String typeCode;

    private String localCode;

    private String stationLine;

    private String stationName;

    private String stationCode;

    private String inoutCode;

    public LineFive(Integer id, String typeCode, String localCode, String stationLine, String stationName, String stationCode, String inoutCode) {
        this.id = id;
        this.typeCode = typeCode;
        this.localCode = localCode;
        this.stationLine = stationLine;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.inoutCode = inoutCode;
    }
}
