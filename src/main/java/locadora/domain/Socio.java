package locadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Getter@Setter
@NoArgsConstructor
public class Socio extends Cliente{

    private String cpf;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependente> dependentes;





}
