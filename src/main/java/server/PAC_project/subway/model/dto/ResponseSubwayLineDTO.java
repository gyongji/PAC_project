package server.PAC_project.subway.model.dto;

import server.PAC_project.subway.model.entity.LineFive;
import server.PAC_project.subway.model.entity.LineTwo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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


    public LineFive LineFiveToEntity() {
        System.out.println("5 Line Success");
        return LineFive.builder()
                .stationLine(this.stationLine)
                .stationName(this.stationName)
                .inoutCode(this.inoutCode)
                .stationCode(this.stationCode)
                .build();

    }

    public LineTwo LineTwoToEntity() {
        System.out.println("5 Line Success");
        return LineTwo.builder()
                .stationLine(this.stationLine)
                .stationName(this.stationName)
                .inoutCode(this.inoutCode)
                .stationCode(this.stationCode)
                .build();

    }

}
//        if (name.equals("5호선")) {
//                System.out.println("5 Line Success");
//                return new LineFive(this.typeCode, this.localCode, this.stationLine, this.stationName, this.stationCode, this.inoutCode);
//                } else if (name.equals("2호선")) {
//                System.out.println("2 Line Success");
//                return new LineTwo(this.typeCode, this.localCode, this.stationLine, this.stationName, this.stationCode, this.inoutCode);
//                }else{
//                System.out.println("ERROR");
//                return null;
//                }
