package server.PAC_project.subway.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubwayRoute {

    LINE_1("1호선", "LC010", "1001"),
    LINE_2("2호선", "LC010", "1002"),
    LINE_3("3호선", "LC010", "1003"),
    LINE_4("4호선", "LC010", "1004"),
    LINE_5("5호선", "LC010", "1005"),
    LINE_6("6호선", "LC010", "1006"),
    LINE_7("7호선", "LC010", "1007"),
    LINE_8("8호선", "LC010", "1008"),
    LINE_9("9호선", "LC010", "1009"),
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
    YONGIN_LINE("용인경전철", "미정", "1075"),

    // 아래 호선들은 실시간 정보 제공 안됨.
    INCHEON_LINE("인천선", "LC030", "1063"),
    INCHEON_LINE_2("인천2호선", "LC030", "1063"),
    GIMPO_GOLD_LINE("김포도시철도", "미정", "1075"),
    SINLIM_LINE("신림선", "미정", "1002"),
    UIJEONGBU_LINE("의정부경전철", "미정", "1001");


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
