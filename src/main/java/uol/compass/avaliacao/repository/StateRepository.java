package uol.compass.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.avaliacao.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
}
