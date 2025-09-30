package application.factories;

import domain.repository.PalavraRepository;
import domain.repository.RodadaRepository;
import domain.repository.TemaRepository;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory {
    private TemaRepository temaRepository;
    private PalavraRepository palavraRepository;

    protected RodadaFactoryImpl(RodadaRepository rodadaRepo, TemaRepository temaRepo, PalavraRepository palavraRepo) {
        super(rodadaRepo);
        this.temaRepository = temaRepo;
        this.palavraRepository = palavraRepo;
    }
    
    protected TemaRepository getTemaRepository() {
        return this.temaRepository;
    }
    
    protected PalavraRepository getPalavraRepository() {
        return this.palavraRepository;
    }
}