package server.PAC_project.bus.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.PAC_project.bus.model.dto.FinalBusDTO;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "route_id")
    private String ROUTEID;

    @Column(name = "route_name")
    private String ROUTENAME;

    @Column(name = "inout")
    private String INOUT;

    public static BusEntity toEn(FinalBusDTO finalBusDTO) {
        return BusEntity.builder()
                .ROUTEID(finalBusDTO.getROUTEID())
                .ROUTENAME(finalBusDTO.getROUTENAME())
                .INOUT(finalBusDTO.getINOUT_CODE())
                .build();
    }

}