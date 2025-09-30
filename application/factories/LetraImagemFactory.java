package application.factories;

import domain.model.Letra;

public class LetraImagemFactory extends LetraFactoryImpl {
    private static LetraImagemFactory soleInstance;

    public static LetraImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraImagemFactory();
        }
        return soleInstance;
    }

    private LetraImagemFactory() {}

    @Override
    protected Letra criarLetra(char codigo) {
        return null;
    }

    @Override
    protected Letra criarLetraEncoberta() {
        return null;
    }
}