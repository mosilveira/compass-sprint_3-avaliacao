package uol.compass.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.avaliacao.model.CountryRegion;
import uol.compass.avaliacao.model.State;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    // Método para retornar uma lista de estados filtrados por região
    List<State> findAllByRegiao(CountryRegion region);
}
