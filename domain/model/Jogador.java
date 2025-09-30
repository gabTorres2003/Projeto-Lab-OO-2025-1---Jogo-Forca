package domain.model;

import java.util.Objects;

public class Jogador {

    private long id;
    private String nome;
    private int pontuacao;

    private Jogador(long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.pontuacao = 0;
    }
    
    public static Jogador criar(long id, String nome) {
        return new Jogador(id, nome);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }
    
    public void atualizarPontuacao(int novosPontos) {
        if (novosPontos > 0) {
            this.pontuacao += novosPontos;
        }
    }

    @Override
    public String toString() {
        return "Jogador{" + "id=" + id + ", nome='" + nome + '\'' + ", pontuacao=" + pontuacao + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return id == jogador.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}