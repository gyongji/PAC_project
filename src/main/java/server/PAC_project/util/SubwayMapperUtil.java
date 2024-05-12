package server.PAC_project.util;

import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.entity.LineFive;
import server.PAC_project.subway.model.entity.LineTwo;

import java.util.List;
import java.util.stream.Collectors;

public class SubwayMapperUtil {
    @SuppressWarnings("unchecked")
    public static <T> List<T> mapLineToEntity(List<ResponseSubwayLineDTO> responseSubwayLineDTO, String lineName) {
        if (lineName.equals("2호선")) {
            return (List<T>) mapTwoLineToEntity(responseSubwayLineDTO);
        } else if (lineName.equals("5호선")) {
            return (List<T>) mapFiveLineToEntity(responseSubwayLineDTO);
        } else {
            return (List<T>) mapFiveLineToEntity(responseSubwayLineDTO);
        }
    }


    private static List<LineTwo> mapTwoLineToEntity(List<ResponseSubwayLineDTO> responseSubwayLineDTO) {
        return responseSubwayLineDTO.stream()
                .map(ResponseSubwayLineDTO::LineTwoToEntity) // 2호선에 해당하는 엔티티로 변환
                .collect(Collectors.toList());
    }

    private static List<LineFive> mapFiveLineToEntity(List<ResponseSubwayLineDTO> responseSubwayLineDTO) {
        return responseSubwayLineDTO.stream()
                .map(ResponseSubwayLineDTO::LineFiveToEntity) // 5호선에 해당하는 엔티티로 변환
                .collect(Collectors.toList());
    }
}