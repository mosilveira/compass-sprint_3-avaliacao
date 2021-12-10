package uol.compass.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uol.compass.avaliacao.dto.StateDTO;
import uol.compass.avaliacao.model.State;
import uol.compass.avaliacao.repository.StateRepository;

import java.util.List;

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
}
