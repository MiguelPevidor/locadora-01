package locadora.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Locacao {
    @Id
    private Long id;

    private LocalDate dtLocacao;

    private LocalDate dtDevolucaoPrevista;

    private LocalDate dtDevolucaoEfetiva;

    private Double valorCobrado;

    private Double multaCobrada;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
