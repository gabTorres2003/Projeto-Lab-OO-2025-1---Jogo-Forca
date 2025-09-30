package application.factories;

import domain.repository.JogadorRepository;
import domain.repository.PalavraRepository;
import domain.repository.RodadaRepository;
import domain.repository.TemaRepository;
import infrastructure.repository.MemoriaJogadorRepository;
import infrastructure.repository.MemoriaPalavraRepository;
import infrastructure.repository.MemoriaRodadaRepository;
import infrastructure.repository.MemoriaTemaRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory {
    private static MemoriaRepositoryFactory soleInstance;

    public static MemoriaRepositoryFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaRepositoryFactory();
        }
        return soleInstance;
    }

    private MemoriaRepositoryFactory() {}

    @Override
    public JogadorRepository getJogadorRepository() {
        return MemoriaJogadorRepository.getSoleInstance();
    }

    @Override
    public TemaRepository getTemaRepository() {
        return MemoriaTemaRepository.getSoleInstance();
    }

    @Override
    public PalavraRepository getPalavraRepository() {
        return MemoriaPalavraRepository.getSoleInstance();
    }

    @Override
    public RodadaRepository getRodadaRepository() {
        return MemoriaRodadaRepository.getSoleInstance();
    }
}

