package locadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numSerie;

    private LocalDate dtAquisicao;

    private String tipoItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titulo_id")
    private Titulo titulo;
}
