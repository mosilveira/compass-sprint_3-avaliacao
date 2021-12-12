package uol.compass.avaliacao.dto;

import uol.compass.avaliacao.model.CountryRegion;
import uol.compass.avaliacao.model.State;
import uol.compass.avaliacao.repository.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StateDTO {

    private Long id;
    private String nome;
    private CountryRegion regiao;
    private Integer populacao;
    private String capital;
    private Double area;

    public StateDTO() {
    }

    public StateDTO(State state) {
        this.id = state.getId();
        this.nome = state.getNome();
        this.regiao = state.getRegiao();
        this.populacao = state.getPopulacao();
        this.capital = state.getCapital();
        this.area = state.getArea();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CountryRegion getRegiao() {
        return regiao;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public String getCapital() {
        return capital;
    }

    public Double getArea() {
        return area;
    }

    // Método para converter uma lista de estados em uma lista DTO
    public static List<StateDTO> toDTO(List<State> states) {
        return states.stream().map(StateDTO::new).collect(Collectors.toList());
    }

    // Método para atualizar um estado selecionado pelo id
    public State update(Long id, StateRepository stateRepository) {
        State state = stateRepository.getById(id);
        state.setNome(this.nome);
        state.setRegiao(this.regiao);
        state.setPopulacao(this.populacao);
        state.setCapital(this.capital);
        state.setArea(this.area);

        return state;
    }
}
