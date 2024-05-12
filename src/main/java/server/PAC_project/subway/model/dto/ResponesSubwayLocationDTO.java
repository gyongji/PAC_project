package server.PAC_project.subway.model.dto;

import lombok.Data;

@Data
public class ResponesSubwayLocationDTO {

    private String beginRow;
    private String endRow;
    private String curPage;
    private String pageRow;
    private String totalCount;
    private String rowNum;
    private String selectedCount;
    private String subwayId;
    private String subwayNm;
    private String statnId;
    private String statnNm;
    private String trainNo;
    private String lastRecptnDt;
    private String recptnDt;
    private String updnLine;
    private String statnTid;
    private String statnTnm;
    private String trainSttus;
    private String directAt;
    private String lstcarAt;

}
