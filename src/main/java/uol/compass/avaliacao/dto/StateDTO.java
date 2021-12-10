package uol.compass.avaliacao.dto;

import uol.compass.avaliacao.model.CountryRegion;
import uol.compass.avaliacao.model.State;

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

    public static List<StateDTO> toDTO(List<State> states) {
        return states.stream().map(StateDTO::new).collect(Collectors.toList());
    }

    public State toModel(StateDTO stateDTO) {
        return new State(stateDTO.getNome(), stateDTO.getRegiao(), stateDTO.getPopulacao(), stateDTO.getCapital(), stateDTO.getArea());
    }
}
