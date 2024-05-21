package server.PAC_project.subway.service;

import server.PAC_project.subway.model.dto.ResponesSubwayLocationDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayArrivalDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayLineDTO;
import server.PAC_project.subway.model.dto.ResponseSubwayStationDTO;
import server.PAC_project.subway.parser.SubwayParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubwayService {

    private final SubwayParser<ResponseSubwayStationDTO> subwayStationDTOSubwayParser;
    private final SubwayParser<ResponesSubwayLocationDTO> subwayLocationDTOSubwayParser;
    private final SubwayParser<ResponseSubwayArrivalDTO> subwayArrivalDTOSubwayParser;
    private final SubwayParser<ResponseSubwayLineDTO> subwayLineParser;

    public List<ResponseSubwayStationDTO> getStationCoordinates() throws IOException {
        return subwayStationDTOSubwayParser.getData();
    }
    public List<ResponesSubwayLocationDTO> getSubwayRealTimeLocation(String startPageNumber, String endPageNumber, String subwayName) throws IOException {
        return subwayLocationDTOSubwayParser.getData(startPageNumber,endPageNumber,subwayName);
    }
    public List<ResponseSubwayArrivalDTO> getSubwayRealTimeArrival(String startPageNumber, String endPageNumber, String regionName) throws IOException {
        return subwayArrivalDTOSubwayParser.getData(startPageNumber,endPageNumber,regionName);
    }

    //---------------아래의 코드는 자동화 예정 코드 -----/////////

    // 테스트 코드 ( 추후에 자동화 예정 ) 
    public List<ResponseSubwayLineDTO> getSubwayStationName() throws IOException {
        return subwayLineParser.getData();
    }

}
