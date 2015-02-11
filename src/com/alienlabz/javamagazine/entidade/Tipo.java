package com.alienlabz.javamagazine.entidade;

public class Tipo {
    private Long id;
    private String nome;

    public Tipo() {}

    public Tipo(String nome) {
        this.nome = nome;
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
