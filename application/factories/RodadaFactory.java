package application.factories;

import domain.model.Jogador;
import domain.model.Rodada;

public interface RodadaFactory {
    Rodada getRodada(Jogador jogador);
}