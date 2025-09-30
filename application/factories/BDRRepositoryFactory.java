package application.factories;

import domain.repository.JogadorRepository;
import domain.repository.PalavraRepository;
import domain.repository.RodadaRepository;
import domain.repository.TemaRepository;

public class BDRRepositoryFactory implements RepositoryFactory {
    private static BDRRepositoryFactory soleInstance;

    public static BDRRepositoryFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BDRRepositoryFactory();
        }
        return soleInstance;
    }

    private BDRRepositoryFactory() {}

    @Override
    public JogadorRepository getJogadorRepository() {
        return null;
    }

    @Override
    public TemaRepository getTemaRepository() {
        return null;
    }

    @Override
    public PalavraRepository getPalavraRepository() {
        return null;
    }

    @Override
    public RodadaRepository getRodadaRepository() {
        return null;
    }
}

