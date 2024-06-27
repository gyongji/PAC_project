//package server.PAC_project;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import server.PAC_project.subway.SubwayService;
//import server.PAC_project.subway.model.dto.ResponseSubwayArrivalDTO;
//import server.PAC_project.subway.model.dto.SearchSubwayLine;
//import server.PAC_project.subway.model.entity.Line;
//import server.PAC_project.subway.repository.SubwayRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@SpringBootTest
//public class SubwayStationSearchTest {
//
//    @Autowired
//    private SubwayRepository subwayRepository;
//
//    @Autowired
//    private SubwayService service;
//
//    private static final String startNumber = "0";
//    private static final String endNumber = "20";
//
//    @Test
//    public void getSubwayStationList() throws Exception {
//        //given
//        String subwayLineName = "까치산";
//        //when
//        List<Line> lines = subwayRepository.searchSubwayLine(subwayLineName);
//        List<ResponseSubwayArrivalDTO> subwayRealTimeArrival = service.getSubwayRealTimeArrival(startNumber, endNumber, subwayLineName);
//
//        //then
//        List<SearchSubwayLine> searchSubwayLines = mapToDTO(lines, subwayRealTimeArrival);
//        for (Line line : lines) {
//            System.out.println(line.getStationName());
//        }
//        for (ResponseSubwayArrivalDTO responseSubwayArrivalDTO : subwayRealTimeArrival) {
//            System.out.println(responseSubwayArrivalDTO.getStatnNm() + " || " + responseSubwayArrivalDTO.getBarvlDt());
//        }
//        for (SearchSubwayLine searchSubwayLine : searchSubwayLines) {
//            System.out.println(searchSubwayLine.getSubwayLineName() +
//                    " || " + searchSubwayLine.getSubwayInOutCode() +
//                    " || " + searchSubwayLine.getTrainLineNm() +
//                    " || " + searchSubwayLine.getSubwayArrivalTime());
//        }
//
//    }
//
//    private List<SearchSubwayLine> mapToDTO(List<Line> lines, List<ResponseSubwayArrivalDTO> responseSubwayArrivalDTOs) {
//        return lines.stream()
//                .flatMap(lined -> {
//                    List<ResponseSubwayArrivalDTO> matchingDTOs = responseSubwayArrivalDTOs.stream()
//                            .filter(res -> res.getStatnNm().equals(lined.getStationName()))
//
//                            .toList();
//                    return matchingDTOs.stream()
//                            .map(responseSubwayArrivalDTO -> SearchSubwayLine.builder()
//                                    .trainLineNm(responseSubwayArrivalDTO.getTrainLineNm())
//                                    .subwayStationName(lined.getStationName())
//                                    .subwayLineName(lined.getStationLine())
//                                    .subwayInOutCode(lined.getInoutCode())
//                                    .subwayArrivalTime(responseSubwayArrivalDTO.getBarvlDt())
//                                    .build());
//                })
//                .collect(Collectors.toList());
//    }
//
//}
