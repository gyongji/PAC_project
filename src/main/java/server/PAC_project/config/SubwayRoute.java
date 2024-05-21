package server.PAC_project.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubwayRoute {

    LINE_1("1호선", "LC010"),
    LINE_2("2호선", "LC010"),
    LINE_3("3호선", "LC010"),
    LINE_4("4호선", "LC010"),
    LINE_5("5호선", "LC010"),
    LINE_6("6호선", "LC010"),
    LINE_7("7호선", "LC010"),
    LINE_8("8호선", "LC010"),
    LINE_9("9호선", "LC010"),
    INCHEON_LINE_1("인천", "LC030"),
    BUNDANG_LINE("분당선","미정"),
    GYEONGUI_LINE("경의선","미정"),
    AIRPORT_TRAIN_LINE("공항철도","미정"),
    GIMPO_GOLD_LINE("김포", "미정"),
    SEOHAE_LINE("서해", "미정"),
    SINLIM_LINE("신림", "미정"),
    GYEONGCHUN_LINE("경춘선","미정"),
    GYEONGGANG_LINE("경강", "미정"),
    UISINSEOL_LINE("우이신설", "미정"),
    UIJEONGBU_LINE("의정부", "미정"),
    SUIN_LINE("수인","미정");

    private final String lineName;
    private final String localCode;
}
