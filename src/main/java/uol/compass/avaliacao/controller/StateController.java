package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uol.compass.avaliacao.dto.StateDTO;
import uol.compass.avaliacao.model.State;
import uol.compass.avaliacao.repository.StateRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<StateDTO> findAll() {
        List<State> states = stateRepository.findAll();
        return StateDTO.toDTO(states);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> findById(@PathVariable Long id) {
        Optional<State> optional = stateRepository.findById(id);
        return optional.map(state ->
                ResponseEntity.ok(new StateDTO(state))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}