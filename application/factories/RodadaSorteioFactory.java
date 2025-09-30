package application.factories;

import domain.model.Boneco;
import domain.model.Jogador;
import domain.model.Palavra;
import domain.model.Rodada;
import domain.model.Tema;
import domain.repository.PalavraRepository;
import domain.repository.RodadaRepository;
import domain.repository.TemaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RodadaSorteioFactory extends RodadaFactoryImpl {
    private static RodadaSorteioFactory soleInstance;

    public static void createSoleInstance(RodadaRepository rodadaRepo, TemaRepository temaRepo, PalavraRepository palavraRepo) {
        if (soleInstance == null) {
            soleInstance = new RodadaSorteioFactory(rodadaRepo, temaRepo, palavraRepo);
        }
    }

    public static RodadaSorteioFactory getSoleInstance() {
        return soleInstance;
    }

    private RodadaSorteioFactory(RodadaRepository rodadaRepo, TemaRepository temaRepo, PalavraRepository palavraRepo) {
        super(rodadaRepo, temaRepo, palavraRepo);
    }

    @Override
    public Rodada getRodada(Jogador jogador) {
        TemaRepository temaRepo = getTemaRepository();
        PalavraRepository palavraRepo = getPalavraRepository();

        List<Tema> temas = temaRepo.getTodos();
        if (temas.isEmpty()) {
            throw new RuntimeException("Não há temas cadastrados para iniciar uma rodada.");
        }

        Random random = new Random();
        Tema temaSorteado = temas.get(random.nextInt(temas.size()));

        List<Palavra> palavrasDoTema = palavraRepo.getPorTema(temaSorteado);
        if (palavrasDoTema.isEmpty()) {
            throw new RuntimeException("O tema sorteado '" + temaSorteado.getNome() + "' não possui palavras.");
        }
        
        Collections.shuffle(palavrasDoTema);
        
        int numPalavras = random.nextInt(3) + 1;
        List<Palavra> palavrasSorteadas = palavrasDoTema.subList(0, Math.min(numPalavras, palavrasDoTema.size()));
        
        if (palavrasSorteadas.isEmpty()) {
            throw new RuntimeException("Não foi possível sortear palavras para a rodada.");
        }
        
        Boneco boneco = ElementoGraficoTextoFactory.getSoleInstance().getBoneco();
        
        return Rodada.criar(getProximoId(), jogador, palavrasSorteadas, boneco);
    }
}