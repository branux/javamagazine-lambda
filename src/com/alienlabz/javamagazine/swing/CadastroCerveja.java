package com.alienlabz.javamagazine.swing;

import com.alienlabz.javamagazine.entidade.Cerveja;
import com.alienlabz.javamagazine.entidade.Fabricante;
import com.alienlabz.javamagazine.entidade.Tipo;
import com.alienlabz.javamagazine.facade.Cervejaria;

import javax.swing.*;
import java.util.Date;

public class CadastroCerveja extends JFrame {
    private JTextField nome;
    private JTextField id;
    private JTextField nota;
    private JTextField data;
    private JComboBox<Fabricante> fabricante;
    private JComboBox<Tipo> tipo;
    private JButton salvar;

    public CadastroCerveja() {
        id = new JTextField();
        nome = new JTextField();
        nota = new JTextField();
        data = new JTextField();
        fabricante = new JComboBox<>();
        tipo = new JComboBox<>();

        add(id);
        add(nome);
        add(nota);
        add(data);
        add(fabricante);
        add(tipo);
        add(salvar);

        salvar = new JButton("Salvar");
        salvar.addActionListener(e -> {
            Cerveja cerveja = new Cerveja();
            cerveja.setNome(nome.getText());
            cerveja.setNota(Integer.valueOf(nota.getText()));
            cerveja.setFabricante((Fabricante)fabricante.getModel().getSelectedItem());
            cerveja.setTipo((Tipo) tipo.getModel().getSelectedItem());

            Cervejaria.getInstancia().salvar(cerveja);
        });
    }

}
