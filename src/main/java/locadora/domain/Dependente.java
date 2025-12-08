package locadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Getter@Setter
public class Dependente extends Cliente{

    @ManyToOne()
    @JoinColumn(name = "socio_id")
    private Socio responsavel;
}
