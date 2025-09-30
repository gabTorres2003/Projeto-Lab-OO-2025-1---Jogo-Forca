package application.factories;

import domain.model.Palavra;
import domain.model.Tema;

public interface PalavraFactory {
    Palavra getPalavra(String palavra, Tema tema);
}

