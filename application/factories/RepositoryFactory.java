package application.factories;

import domain.repository.JogadorRepository;
import domain.repository.PalavraRepository;
import domain.repository.RodadaRepository;
import domain.repository.TemaRepository;

public interface RepositoryFactory {
    JogadorRepository getJogadorRepository();
    TemaRepository getTemaRepository();
    PalavraRepository getPalavraRepository();
    RodadaRepository getRodadaRepository();
}

