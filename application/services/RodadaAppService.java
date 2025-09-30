package application.services;

import application.factories.RodadaFactory;
import domain.model.Jogador;
import domain.model.Rodada;
import domain.repository.JogadorRepository;
import domain.repository.RodadaRepository;
import infrastructure.RepositoryException;

public class RodadaAppService {

    private static RodadaAppService soleInstance;
    private final RodadaFactory rodadaFactory;
    private final RodadaRepository rodadaRepository;
    private final JogadorRepository jogadorRepository;

    private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        this.rodadaFactory = rodadaFactory;
        this.rodadaRepository = rodadaRepository;
        this.jogadorRepository = jogadorRepository;
    }

    public static void createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        soleInstance = new RodadaAppService(rodadaFactory, rodadaRepository, jogadorRepository);
    }

    public static RodadaAppService getSoleInstance() {
        return soleInstance;
    }

    public Rodada novaRodada(long idJogador) {
        Jogador jogador = jogadorRepository.getPorId(idJogador);
        if (jogador == null) {
            return null;
        }
        return rodadaFactory.getRodada(jogador);
    }

    public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadorRepository.getPorNome(nomeJogador);

        if (jogador == null) {
            throw new JogadorNaoEncontradoException(nomeJogador);
        }

        return rodadaFactory.getRodada(jogador);
    }

    public boolean salvarRodada(Rodada rodada) {
        try {
            rodadaRepository.inserir(rodada);
            return true;
        } catch (RepositoryException e) {
            e.printStackTrace();
            return false;
        }
    }
}

