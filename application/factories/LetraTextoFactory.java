package application.factories;

import domain.model.Letra;
import domain.model.LetraTexto;

public class LetraTextoFactory extends LetraFactoryImpl {
    private static LetraTextoFactory soleInstance;

    public static LetraTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraTextoFactory();
        }
        return soleInstance;
    }

    private LetraTextoFactory() {}

    @Override
    protected Letra criarLetra(char codigo) {
        return new LetraTexto(codigo);
    }

    @Override
    protected Letra criarLetraEncoberta() {
        return new LetraTexto('_');
    }
}