package locadora.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String ano;

    private String sinopse;

    private String categoria;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "titulo_ator",
            joinColumns = @JoinColumn(name = "titulo_id"),
            inverseJoinColumns = @JoinColumn( name = "ator_id")
    )
    private List<Ator> atores = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @OneToMany(mappedBy = "titulo")
    private List<Item> itens = new ArrayList<>();

}
