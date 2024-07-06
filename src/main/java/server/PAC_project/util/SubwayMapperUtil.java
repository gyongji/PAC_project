package server.PAC_project.util;

import server.PAC_project.subway.model.dto.AllStationDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.entity.Line;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubwayMapperUtil {
    public static List<Line> mapLineToEntity(List<ResponseSubwayLineDTO> responseSubwayLineDTO) {
        return responseSubwayLineDTO.stream()
                .map(ResponseSubwayLineDTO::toEntity) // ResponseSubwayLineDTO 에서 Line 객체 생성
                .collect(Collectors.toList());
    }

    public static Map<String, List<AllStationDTO>> mapLineToDto(List<Line> lines) {
        List<AllStationDTO> collect = lines.stream()
                .map(Line::toDto)
                .toList();
        return Map.of("dataList", collect);
    }

}