package server.PAC_project.subway.model.dto;

import lombok.Data;

@Data
public class ResponseSubwayArrivalDTO {
    private Integer beginRow;
    private Integer endRow;
    private Integer curPage;
    private Integer pageRow;
    private Integer totalCount;
    private Integer rowNum;
    private Integer selectedCount;
    private String subwayId;
    private String updnLine;
    private String trainLineNm;
    private String subwayNm;
    private String subwayHeading;
    private String statnFid;
    private String statnTid;
    private String statnId;
    private String statnNm;
    private String trainCo;
    private String trnsitCo;
    private String ordkey;
    private String subwayList;
    private String statnList;
    private String btrainSttus;
    private String barvlDt;
    private String btrainNo;
    private String bstatnId;
    private String bstatnNm;
    private String recptnDt;
    private String arvlMsg2;
    private String arvlMsg3;
    private String arvlCd;
}
