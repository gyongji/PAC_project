package server.PAC_project.subway.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResponseSubwayRealTimeArrival {
        SUBWAY_ID("subwayId"),
        UPDN_LINE("updnLine"),
        TRAIN_LINE_NM("trainLineNm"),
        STATN_FID("statnFid"),
        STATN_TID("statnTid"),
        STATN_NM("statnNm"),
        TRNSIT_CO("trnsitCo"),
        BTRAIN_STTUS("btrainSttus"),
        BARVL_DT("barvlDt"),
        BTRAIN_NO("btrainNo"),
        ARVL_MSG2("arvlMsg2"),
        ARVL_MSG3("arvlMsg3"),
        ARVL_CD("arvlCd");

        private final String fieldName;


    public String getFieldName() {
            return fieldName;
    }
}
