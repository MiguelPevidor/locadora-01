package locadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numInscricao;

    private String nome;

    private LocalDate dataNascimento;

    private String sexo;

    private boolean estahAtivo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Locacao> locacoes;
}
