package application.factories;

import domain.model.Tema;

public interface TemaFactory {
    Tema getTema(String nome);
}