package application.factories;

import domain.model.Boneco;
import domain.model.Letra;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {
    private static ElementoGraficoTextoFactory soleInstance;

    public static ElementoGraficoTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoTextoFactory();
        }
        return soleInstance;
    }

    private ElementoGraficoTextoFactory() {}

    @Override
    public Boneco getBoneco() {
        return BonecoTextoFactory.getSoleInstance().getBoneco();
    }

    @Override
    public Letra getLetra(char codigo) {
        return LetraTextoFactory.getSoleInstance().getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return LetraTextoFactory.getSoleInstance().getLetraEncoberta();
    }
}

