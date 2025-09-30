package application.services;

import application.factories.PalavraFactory;
import domain.model.Palavra;
import domain.model.Tema;
import domain.repository.PalavraRepository;
import domain.repository.TemaRepository;
import infrastructure.RepositoryException;

public class PalavraAppService {

    private static PalavraAppService soleInstance;
    private final TemaRepository temaRepository;
    private final PalavraRepository palavraRepository;
    private final PalavraFactory palavraFactory;

    private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
        this.palavraFactory = palavraFactory;
    }

    public static void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
    }

    public static PalavraAppService getSoleInstance() {
        return soleInstance;
    }

    public boolean novaPalavra(String palavraStr, long idTema) {
        Tema tema = temaRepository.getPorId(idTema);
        if (tema == null) {
            return false;
        }

        if (palavraRepository.getPalavraPorPalavra(palavraStr) != null) {
            return true;
        }

        Palavra palavra = palavraFactory.getPalavra(palavraStr, tema);

        try {
            palavraRepository.inserir(palavra);
            return true;
        } catch (RepositoryException e) {
            e.printStackTrace();
            return false;
        }
    }
}

