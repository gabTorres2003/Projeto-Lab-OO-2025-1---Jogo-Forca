package application.factories;

import domain.model.Jogador;
import domain.repository.JogadorRepository;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {
    private static JogadorFactoryImpl soleInstance;

    public static void createSoleInstance(JogadorRepository repository) {
        if (soleInstance == null) {
            soleInstance = new JogadorFactoryImpl(repository);
        }
    }

    public static JogadorFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private JogadorFactoryImpl(JogadorRepository repository) {
        super(repository);
    }

    @Override
    public Jogador getJogador(String nome) {
        return Jogador.criar(getProximoId(), nome);
    }
}

