package uol.compass.avaliacao.dto;

import uol.compass.avaliacao.model.CountryRegion;
import uol.compass.avaliacao.model.State;
import uol.compass.avaliacao.repository.StateRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class FormStateDTO {

    @NotNull @NotEmpty
    private String nome;
    @NotNull
    private CountryRegion regiao;
    @NotNull
    private Integer populacao;
    @NotNull @NotEmpty
    private String capital;
    @NotNull
    private Double area;

    public FormStateDTO() {
    }

    public FormStateDTO(State state) {
        this.nome = state.getNome();
        this.regiao = state.getRegiao();
        this.populacao = state.getPopulacao();
        this.capital = state.getCapital();
        this.area = state.getArea();
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

    public static List<FormStateDTO> toDTO(List<State> states) {
        return states.stream().map(FormStateDTO::new).collect(Collectors.toList());
    }

    public State toModel(FormStateDTO stateDTO) {
        return new State(stateDTO.getNome(), stateDTO.getRegiao(), stateDTO.getPopulacao(), stateDTO.getCapital(), stateDTO.getArea());
    }

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
