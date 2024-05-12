package server.PAC_project.subway.config;

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
    INCHEON_LINE_1("인천1호선", "LC030"),
    BUNDANG_LINE("분당선","미정"),
    GYEONGUI_LINE("경희선","미정"),
    SHINBUNDANG_LINE("신분당선","미정"),
    AIRPORT_TRAIN_LINE("공항철도","미정"),
    CENTER_LINE("중앙선","미정"),
    GYEONGCHUN_LINE("경춘선","미정"),
    SUIN_LINE("수인선","미정");


    private final String lineName;
    private final String localCode;

}
