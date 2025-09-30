package application.factories;

import domain.model.Letra;

public interface LetraFactory {
    Letra getLetra(char codigo);
    Letra getLetraEncoberta();
}

