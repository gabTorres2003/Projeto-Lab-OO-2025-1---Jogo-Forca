package application.factories;

import domain.model.Boneco;
import domain.model.Letra;

public interface ElementoGraficoFactory {
    Boneco getBoneco();
    Letra getLetra(char codigo);
    Letra getLetraEncoberta();
}