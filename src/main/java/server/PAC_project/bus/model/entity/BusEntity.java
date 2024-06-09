package server.PAC_project.bus.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.PAC_project.bus.model.dto.ResponseBusRouteDTO;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String ROUTE_ID;

    private String ROUTE_NAME;

    public BusEntity(String ROUTE_ID, String ROUTE_NAME) {
        this.ROUTE_ID = ROUTE_ID;
        this.ROUTE_NAME = ROUTE_NAME;
    }

    public static BusEntity toEn(ResponseBusRouteDTO responseBusRouteDTO) {
        return BusEntity.builder()
                .ROUTE_ID(responseBusRouteDTO.getROUTE_ID())
                .ROUTE_NAME(responseBusRouteDTO.getROUTE_NAME())
                .build();
    }
}