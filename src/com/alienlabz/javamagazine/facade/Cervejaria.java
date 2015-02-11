package com.alienlabz.javamagazine.facade;

import com.alienlabz.javamagazine.entidade.Cerveja;
import com.alienlabz.javamagazine.entidade.Fabricante;
import com.alienlabz.javamagazine.entidade.Tipo;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

public class Cervejaria {
    private List<Fabricante> fabricantes = new ArrayList<>();
    private List<Tipo> tipos = new ArrayList<>();
    private List<Cerveja> cervejas = new ArrayList<Cerveja>();
    private static final Cervejaria instancia = new Cervejaria();


    private Cervejaria() {
    }

    public static Cervejaria getInstancia() {
        return instancia;
    }

    public void salvar(Cerveja cerveja) {
        cervejas.add(cerveja);
    }

    public void excluir(Long id) {
        cervejas.removeIf(c -> c.getId().equals(id));
    }

    public void excluirFabricante(Long id) {
        fabricantes.removeIf((f) -> (f.getId() != null && f.getId().equals(id)));
        cervejas.forEach((c -> {
            if (c.getFabricante().getId().equals(id)) {
                c.setFabricante(null);
            }
        }));
    }

    public void excluirTipo(Long id) {
        tipos.removeIf((t) -> (t.getId() != null && t.getId().equals(id)));
        cervejas.forEach((c -> {
            if (c.getTipo().getId().equals(id)) {
                c.setTipo(null);
            }
        }));
    }

    public void gerarCSV() {
        new Thread(() -> {

            try {
                final PrintWriter writer = new PrintWriter("cervejas.csv", "UTF-8");

                cervejas.forEach(c -> {
                    writer.println(c.getId() + ",");
                    writer.println(c.getNome() + ",");
                    writer.println(c.getFabricante().getNome() + ",");
                    writer.println(c.getTipo().getNome() + ",");
                    writer.println(c.getData() + "\n");
                });

                writer.close();
            } catch (FileNotFoundException e) {

            } catch (UnsupportedEncodingException e) {

            }

        }).start();
    }

    /**
     * Imprimir a lista de cervejas.
     */
    public void imprimir() {
        cervejas.forEach(System.out::println);
    }

    public <T> void processar(List<T> dados, Conversor<T> conversor) {
        dados
                .stream()
                .map(conversor::converter)
                .forEach(c -> {
                    cervejas.add(c);
                });
    }

    public void cadastrarTipos(List<String> nomes) {
        nomes.stream().map(Tipo::new).forEach((t) -> {
            tipos.add(t);
        });
    }

    public void cadastrarFabricantes(List<String> nomes) {
        nomes.stream().map(Fabricante::new).forEach((f) -> {
            fabricantes.add(f);
        });
    }

    /**
     * Obter todas as cervejas de um determinado tipo.
     *
     * @param id Identificador do tipo.
     * @return Lista de Cervejas do tipo informado.
     */
    public List<Cerveja> obterCervejasPorTipo(Long id) {
        return cervejas.stream().filter(c -> c.getTipo().getId().equals(id)).collect(Collectors.toList());
    }

    /**
     * Obter as cervejas
     *
     * @return
     */
    public List<Cerveja> obterMaisRecentes() {
        return cervejas
                .stream()
                .sorted((c1, c2) -> c1.getData().compareTo(c2.getData()))
                .limit(5)
                .collect(Collectors.toList());
    }

    public Double obterMediaNotaPorTipo(Long id) {
        Double result = 0D;

        IntSummaryStatistics stats =
                cervejas.stream()
                        .filter(c -> c.getTipo().getId().equals(id))
                        .mapToInt(c -> c.getNota())
                        .summaryStatistics();

        result = stats.getAverage();
        return result;
    }

    public List<Cerveja> obterCervejas() {
        return cervejas;
    }

    public static void main(String... args) {
        Cervejaria cervejaria = Cervejaria.getInstancia();

        List<JSONObject> objects = new ArrayList<>();
        JSONObject j1 = new JSONObject();
        j1.put("nome", "J1");
        objects.add(j1);

        cervejaria.processar(objects, (json) -> {
            Cerveja c = new Cerveja();
            c.setNome(json.getString("nome"));

            return c;
        });
    }

}
