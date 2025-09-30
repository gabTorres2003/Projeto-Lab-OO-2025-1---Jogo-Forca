package application.factories;

import domain.model.Boneco;
import domain.model.Letra;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {
    private static ElementoGraficoImagemFactory soleInstance;

    public static ElementoGraficoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoImagemFactory();
        }
        return soleInstance;
    }

    private ElementoGraficoImagemFactory() {}
    
    @Override
    public Boneco getBoneco() {
        return null;
    }

    @Override
    public Letra getLetra(char codigo) {
        return null;
    }

    @Override
    public Letra getLetraEncoberta() {
        return null;
    }
}

