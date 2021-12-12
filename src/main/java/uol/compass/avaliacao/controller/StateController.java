package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import uol.compass.avaliacao.dto.FormStateDTO;
import uol.compass.avaliacao.dto.StateDTO;
import uol.compass.avaliacao.model.CountryRegion;
import uol.compass.avaliacao.model.State;
import uol.compass.avaliacao.repository.StateRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    // GET - Retorna uma lista de estados
    // Parametro regiao usado apra filtrar a lista por regiao
    // Parametro sort usado para ordenar a lista por população ou area
    @GetMapping
    public List<StateDTO> findAll(@RequestParam(required = false) CountryRegion regiao,
                                  @RequestParam(required = false) String sort) {

        List<State> states;

        if (regiao == null) {
            states = stateRepository.findAll();
        } else {
            states = stateRepository.findAllByRegiao(regiao);
        }

        if (sort != null) {
            if (sort.equals("populacao")) {
                states.sort(comparing(State::getPopulacao, reverseOrder()));
            } else if (sort.equals("area")) {
                states.sort(comparing(State::getArea, reverseOrder()));
            }
        }
        return StateDTO.toDTO(states);
    }

    // GET - Retorna um estado pelo id
    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> findById(@PathVariable Long id) {
        Optional<State> optional = stateRepository.findById(id);
        return optional.map(state ->
                ResponseEntity.ok(new StateDTO(state))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Cria um novo estado no cadastro
    @PostMapping
    @Transactional
    public ResponseEntity<StateDTO> create(@RequestBody @Valid FormStateDTO formStateDTO, UriComponentsBuilder uriComponentsBuilder) {
        State state = formStateDTO.toModel(formStateDTO);
        stateRepository.save(state);

        URI uri = uriComponentsBuilder.path("/api/states/{id}").buildAndExpand(state.getId()).toUri();
        return ResponseEntity.created(uri).body(new StateDTO(state));
    }

    // PUT - Atualiza um estado de acordo com o id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StateDTO> update(@PathVariable Long id, @RequestBody @Valid FormStateDTO formStateDTO) {
        Optional<State> optional = stateRepository.findById(id);
        if (optional.isPresent()) {
            State state = formStateDTO.update(id, stateRepository);
            return ResponseEntity.ok(new StateDTO(state));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE - Exclui um estado de acordo com o id
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<State> optional = stateRepository.findById(id);
        if (optional.isPresent()) {
            stateRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}