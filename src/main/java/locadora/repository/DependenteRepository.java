package locadora.repository;

import locadora.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    int countByResponsavel_IdAndEstahAtivoTrue(Long socioId);
}
