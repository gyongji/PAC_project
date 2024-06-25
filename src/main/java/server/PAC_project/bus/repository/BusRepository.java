package server.PAC_project.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.PAC_project.bus.model.entity.BusEntity;


public interface BusRepository extends JpaRepository<BusEntity, Long> {

    BusEntity findBusEntityByROUTENAME(String routeName);

}