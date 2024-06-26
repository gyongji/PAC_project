package server.PAC_project.util;

import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.model.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SubwayMapperUtil {
    public static List<Line> mapLineToEntity(List<ResponseSubwayLineDTO> responseSubwayLineDTO) {
        return responseSubwayLineDTO.stream()
                .map(ResponseSubwayLineDTO::toEntity) // ResponseSubwayLineDTO 에서 Line 객체 생성
                .collect(Collectors.toList());
    }

}