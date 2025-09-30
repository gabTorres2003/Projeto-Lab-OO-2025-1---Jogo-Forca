package application.services;

public class JogadorNaoEncontradoException extends Exception {

    private final String jogador;

    public JogadorNaoEncontradoException(String jogador) {
        super("Jogador não encontrado: " + jogador);
        this.jogador = jogador;
    }

    public String getJogador() {
        return jogador;
    }
}

