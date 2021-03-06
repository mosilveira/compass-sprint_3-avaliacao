package uol.compass.avaliacao.model;

import javax.persistence.*;

@Entity
public class State {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private CountryRegion regiao;
    private Integer populacao;
    private String capital;
    private Double area;

    public State() {
    }

    public State(String nome, CountryRegion regiao, Integer populacao, String capital, Double area) {
        this.nome = nome;
        this.regiao = regiao;
        this.populacao = populacao;
        this.capital = capital;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CountryRegion getRegiao() {
        return regiao;
    }

    public void setRegiao(CountryRegion regiao) {
        this.regiao = regiao;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}