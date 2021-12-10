package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import uol.compass.avaliacao.dto.StateDTO;
import uol.compass.avaliacao.model.State;
import uol.compass.avaliacao.repository.StateRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    @Transactional
    public ResponseEntity<StateDTO> create(@RequestBody @Valid StateDTO stateDTO, UriComponentsBuilder uriComponentsBuilder) {
        State state = stateDTO.toModel(stateDTO);
        stateRepository.save(state);

        URI uri = uriComponentsBuilder.path("/api/states/{id}").buildAndExpand(state.getId()).toUri();
        return ResponseEntity.created(uri).body(new StateDTO(state));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StateDTO> update(@PathVariable Long id, @RequestBody @Valid StateDTO stateDTO) {
        Optional<State> optional = stateRepository.findById(id);
        if (optional.isPresent()) {
            State state = stateDTO.update(id, stateRepository);
            return ResponseEntity.ok(new StateDTO(state));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<State> optional = stateRepository.findById(id);
        if (optional.isPresent()) {
            stateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}