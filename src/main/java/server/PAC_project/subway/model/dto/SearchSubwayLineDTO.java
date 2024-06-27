package server.PAC_project.subway.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchSubwayLineDTO {

    @Schema(description = "지하철 호선 ID", example = "1호선 : 1001, 2호선 1002")
    private String subwayId;
    @Schema(description = "상행선 하행선 구분", example = "상행/내선 : 0, 하행/외선 : 1")
    private String updnLine;
    @Schema(description = "도착지 방면", example = "성수행( 목적지역 ) - 구로디지털단지방면( 다음역 )")
    private String trainLineNm;
    @Schema(description = "이전 지하철 ID")
    private String statnFid;
    @Schema(description = "다음 지하철 ID")
    private String statnTid;
    @Schema(description = "지하철 역 명")
    private String statnNm;
    @Schema(description = "환승 노선 수")
    private String trnsitCo;
    @Schema(description = "열차 종류", example = "급행, ITX, 일반, 특급")
    private String btrainSttus;
    @Schema(description = "열차 도착 예정 시간", example = "초 단위")
    private String barvlDt;
    @Schema(description = "종착 지하철 역명")
    private String btrainNo;
    @Schema(description = "첫번쨰 도착 메세지", example = "도착, 줄발, 진입 등")
    private String arvlMsg2;
    @Schema(description = "두번째 도착 메세지", example = "종합 운동장 도착, 12분후 (광명사거리) 등")
    private String arvlMsg3;
    @Schema(description = "도착 코드", example = "0: 진입, 1: 도착, 3: 전역출발, 4: 전역 진입, 5: 전역 도착, 99: 운행중")
    private String arvlCd;
    @Schema(description = "승/하차 여부 코드")
    private String inoutCode;


}
