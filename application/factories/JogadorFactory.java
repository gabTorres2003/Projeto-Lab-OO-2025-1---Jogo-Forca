package application.factories;

import domain.model.Jogador;

public interface JogadorFactory {
    Jogador getJogador(String nome);
}

