package domain.model;

import java.util.Objects;

public class Tema {

    private long id;
    private String nome;

    private Tema(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static Tema criar(long id, String nome) {
        return new Tema(id, nome);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Tema{" + "id=" + id + ", nome='" + nome + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tema tema = (Tema) o;
        return id == tema.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}