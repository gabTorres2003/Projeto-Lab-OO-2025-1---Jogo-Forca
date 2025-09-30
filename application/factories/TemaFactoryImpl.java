package application.factories;

import domain.model.Tema;
import domain.repository.TemaRepository;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {
    private static TemaFactoryImpl soleInstance;

    public static void createSoleInstance(TemaRepository repository) {
        if (soleInstance == null) {
            soleInstance = new TemaFactoryImpl(repository);
        }
    }

    public static TemaFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }

    @Override
    public Tema getTema(String nome) {
        return Tema.criar(getProximoId(), nome);
    }
}

