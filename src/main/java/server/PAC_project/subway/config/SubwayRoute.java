package server.PAC_project.subway.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubwayRoute {

    LINE_1("01호선", "LC010", "1001"),
    LINE_2("02호선", "LC010", "1002"),
    LINE_3("03호선", "LC010", "1003"),
    LINE_4("04호선", "LC010", "1004"),
    LINE_5("05호선", "LC010", "1005"),
    LINE_6("06호선", "LC010", "1006"),
    LINE_7("07호선", "LC010", "1007"),
    LINE_8("08호선", "LC010", "1008"),
    LINE_9("09호선", "LC010", "1009"),
    AIRPORT_TRAIN_LINE("공항철도", "미정", "1065"),
    GYEONGCHUN_LINE("경춘선", "미정", "1067"),
    UISINSEOL_LINE("우이신설경전철", "미정", "1092"),
    SEOHAE_LINE("서해선", "미정", "1093"),
    GYEONGGANG_LINE("경강선", "미정", "1081"),

    // BUNDANG_LINE("신분당선", "미정", "1075"),
// 경의중앙선 -> 경의선 변환기 필요
    GYEONGUI_LINE("경의선", "미정", "1063"),
    // 수의분당선 -> 수인분당선 변환기 필요
    BUNDANG_LINE("수인분당선", "미정", "1075"),
    YONGIN_LINE("용인경전철", "미정", "X"),
    SIN_BUNDANG_LINE("신분당선", "미정", "1077"),

    // 아래 호선들은 실시간 정보 제공 안됨.
    INCHEON_LINE("인천선", "LC030", "X"),
    INCHEON_LINE_2("인천2호선", "LC030", "X"),
    GIMPO_GOLD_LINE("김포도시철도", "미정", "X"),
    SINLIM_LINE("신림선", "미정", "X"),
    UIJEONGBU_LINE("의정부경전철", "미정", "X");


    private final String lineName;
    private final String localCode;
    private final String subwayLineCode;

    public String getName() {
        return this.lineName;
    }

    public static String getSubwayLineCode(String subwayLineName) {
        SubwayRoute subwayRoute = null;
        for (SubwayRoute s : SubwayRoute.values()) {
            if (subwayLineName.contains(s.getLineName())) {
                subwayRoute = s;
            }
        }
        if (subwayRoute == null) {
            return "11";
        } else {
            return subwayRoute.getSubwayLineCode();
        }

    }

}
