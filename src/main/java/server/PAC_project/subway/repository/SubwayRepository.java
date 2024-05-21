package server.PAC_project.subway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import server.PAC_project.subway.model.entity.Line;

@Repository
public interface SubwayRepository extends JpaRepository<Line, Long> {

}
