package locadora.repository;

import locadora.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio,Long> {
}
