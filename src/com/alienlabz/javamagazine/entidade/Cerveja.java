package com.alienlabz.javamagazine.entidade;

import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

public class Cerveja {
    private Long id;
    private String nome;
    private Integer nota;
    private Fabricante fabricante;
    private Tipo tipo;
    private Date data;

    public Cerveja() {
    }

    public Cerveja(JSONObject jsonCerveja) {
        this.id = (Long) jsonCerveja.get("id");
        this.nome = (String) jsonCerveja.get("nome");
        this.nota = (Integer) jsonCerveja.get("nota");
        this.data = (Date) jsonCerveja.get("data");
        this.fabricante = (Fabricante) jsonCerveja.get("fabricante");
        this.tipo = (Tipo) jsonCerveja.get("tipo");
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
