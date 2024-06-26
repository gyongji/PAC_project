package server.PAC_project.subway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.PAC_project.subway.model.entity.Line;

@Repository
public interface SubwayRepository extends JpaRepository<Line, Long> {

    @Query(value = "SELECT l.inoutCode FROM Line l where l.stationName = :subwayStationName and l.stationLineCode = :subwayLineCode")
    String searchSubwayLine(@Param("subwayStationName") String subwayStationName,
                                    @Param("subwayLineCode") String subwayLineCode);

}
