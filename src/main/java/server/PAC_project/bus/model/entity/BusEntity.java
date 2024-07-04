package server.PAC_project.bus.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.PAC_project.bus.model.dto.FinalBusDTO;


@Entity
@Getter
@NoArgsConstructor
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id")
    private String ROUTEID;

    @Column(name = "route_name")
    private String ROUTENAME;

    @Column(name = "inout")
    private String INOUT;

    @Builder
    public BusEntity(Long id,String ROUTEID, String ROUTENAME, String INOUT) {
        this.ROUTEID = ROUTEID;
        this.ROUTENAME = ROUTENAME;
        this.INOUT = INOUT;
    }

    public static BusEntity toEn(FinalBusDTO finalBusDTO) {
        return BusEntity.builder()
                .ROUTEID(finalBusDTO.getROUTEID())
                .ROUTENAME(finalBusDTO.getROUTENAME())
                .INOUT(finalBusDTO.getINOUT_CODE())
                .build();
    }

}