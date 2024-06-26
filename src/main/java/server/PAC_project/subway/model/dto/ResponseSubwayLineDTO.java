package server.PAC_project.subway.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import server.PAC_project.subway.model.entity.Line;

@Data
//@JsonProperty :: ObjectMapper에서 추출한 데이터의 변수 이름과 매칭
//설정이 되어있지 않는 필드는 무시하고 진행시키는 매서드 ( ObjectMapper 메서드 설정? )
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSubwayLineDTO {

    private String typeCode = "CA100";

    private String localCode = "LC100";

    @JsonProperty("LINE_NUM")
    private String stationLine;

    @JsonProperty("STATION_NM")
    private String stationName;

    @JsonProperty("STATION_CD")
    private String stationCode;

    private String inoutCode;

    public Line toEntity() {
        return Line.toEn(this);
    }
}