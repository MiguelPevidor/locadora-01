package locadora.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import locadora.domain.Cliente;
import locadora.domain.Diretor;
import locadora.domain.Item;
import locadora.domain.Locacao;
import locadora.domain.dto.locacao.LocacaoRequestDto;
import locadora.domain.dto.locacao.LocacaoResponseDto;
import locadora.handler.exceptions.BusinessException;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import locadora.mapper.LocacaoMapper;
import locadora.repository.LocacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class LocacaoService {

    private LocacaoRepository locacaoRepository;
    private ClienteService clienteService;
    private ItemService itemService;
    private LocacaoMapper mapper;

    public void salvar(LocacaoRequestDto dto) {
        Cliente cliente = clienteService.buscarPorId(dto.idCliente());
        Item item = itemService.buscarPorId(dto.idItem());

        validar(cliente,item);

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setItem(item);
        locacao.setValorCobrado(item.getTitulo().getClasse().getValor());
        locacao.setDtLocacao(LocalDate.now());
        locacao.setDtDevolucaoPrevista(locacao.getDtLocacao().plusDays(item.getTitulo().getClasse().getPrazoDevolucao()));
        locacaoRepository.save(locacao);
    }

    private void validar(Cliente cliente, Item item) {

        //se uma de suas locações não foi devolvida e ultrapaçou o prazo,
        //o cliente esta em débito
        for(Locacao locacao : cliente.getLocacoes()){
            if(locacao.getDtDevolucaoEfetiva() == null && (locacao.getDtDevolucaoPrevista().isBefore(LocalDate.now()))){
                throw new BusinessException("Cliente em Débito!");
            }
        }

        if (locacaoRepository.existsByItemAndDtDevolucaoEfetivaIsNull(item)) {
            throw new BusinessException("Este item já está alugado e não foi devolvido");
        }
    }

    public void atualizar(Long id, LocacaoRequestDto dto) {
        Locacao locacao = buscarPorId(id);

        if(dto.dtDevolucaoPrevista().isBefore(locacao.getDtLocacao())){
            throw new BusinessException("A data de devolução prevista tem de ser maior do que a data de locação");
        }

        mapper.updateEntity(dto, locacao);
        locacaoRepository.save(locacao);
    }

    public Locacao buscarPorId(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("locação não encontrado"));  // Lança uma exceção se não encontrar o ator
    }

    public void deletar(Long id) {
        Locacao locacao = buscarPorId(id);
        if(locacao.getDtDevolucaoEfetiva() == null){
            locacaoRepository.delete(locacao);
        }

        throw new BusinessException("Não é possível excluir uma locação já paga!");
    }

    @Transactional
    public Locacao efetuarDevolucao(String numSerie) {

        Item item = itemService.findByNumSerie(numSerie);


        Locacao locacao = locacaoRepository.findByItemAndDtDevolucaoEfetivaIsNull(item)
                .orElseThrow(() -> new BusinessException("Item informado não está locado no momento."));
        LocalDate dataDevolucaoEfetiva = LocalDate.now();

        if (dataDevolucaoEfetiva.isBefore(locacao.getDtLocacao())) {
            throw new BusinessException("Data de devolução não pode ser anterior à data da locação.");
        }

        double multa = 0.0;
        long diasAtraso = 0;

        if (dataDevolucaoEfetiva.isAfter(locacao.getDtDevolucaoPrevista())) {
            // calcular dias de atraso
            diasAtraso = ChronoUnit.DAYS.between(locacao.getDtDevolucaoPrevista(), dataDevolucaoEfetiva);

//            Caso esteja em atraso, informar a multa devida
            multa = calcularMulta(locacao.getValorCobrado(), diasAtraso);
        }

        locacao.setMultaCobrada(multa);
        locacao.setValorCobrado(locacao.getValorCobrado() + multa);
        locacao.setDtDevolucaoEfetiva(dataDevolucaoEfetiva);


        return locacaoRepository.save(locacao);
    }

    private double calcularMulta(Double valorOriginal, long diasAtraso) {
        // Exemplo: Multa de 100% do valor da locação por dia de atraso
        return valorOriginal * diasAtraso;
    }
}
