package application.factories;

import domain.model.Palavra;
import domain.model.Tema;
import domain.repository.PalavraRepository;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {
    private static PalavraFactoryImpl soleInstance;

    public static void createSoleInstance(PalavraRepository repository) {
        if (soleInstance == null) {
            soleInstance = new PalavraFactoryImpl(repository);
        }
    }

    public static PalavraFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private PalavraFactoryImpl(PalavraRepository repository) {
        super(repository);
    }

    @Override
    public Palavra getPalavra(String palavra, Tema tema) {
        return Palavra.criar(getProximoId(), palavra, tema);
    }
}

