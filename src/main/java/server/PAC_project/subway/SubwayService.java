package server.PAC_project.subway;

import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.springframework.stereotype.Service;
import server.PAC_project.subway.config.SubwayRoute;
import server.PAC_project.subway.model.dto.AllStationDTO;
import server.PAC_project.subway.model.dto.SearchSubwayLineDTO;
import server.PAC_project.subway.model.entity.Line;
import server.PAC_project.subway.repository.SubwayRepository;
import server.PAC_project.util.SubwayMapperUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubwayService {
    private final SubwayRepository subwayRepository;

    public Map<String, List<AllStationDTO>> searchAllSubwayStation() {
        Map<String, List<AllStationDTO>> allStationMap = new HashMap<>();
        allStationMap.put("dataList", getSubwayStation());
        return allStationMap;
    }

    private List<AllStationDTO> getSubwayStation() {
        List<AllStationDTO> allStationDTOList = new ArrayList<>();
        for (SubwayRoute value : SubwayRoute.values()) {
            List<String> allDistinctStationName = subwayRepository.findAllDistinctStationName(value.getLineName());
            for (String s : allDistinctStationName) {
                List<String> stationLineList = new ArrayList<>();
                List<String> stationLineCodeList = new ArrayList<>();
                List<Line> byStationName = subwayRepository.findByStationName(s);
                AllStationDTO allStationDTO = SubwayMapperUtil.searchingAllSubwayStationDTO(byStationName.get(0));
                for (Line line : byStationName) {
                    stationLineList.add(line.getStationLine());
                    stationLineCodeList.add(line.getStationLineCode());
                }
                allStationDTO.setStationLine(stationLineList);
                allStationDTO.setStationLineCode(stationLineCodeList);
                allStationDTOList.add(allStationDTO);
            }

        }
        return allStationDTOList;
    }
}
