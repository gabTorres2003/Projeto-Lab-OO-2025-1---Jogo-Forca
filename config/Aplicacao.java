package config;

import application.factories.BDRRepositoryFactory;
import application.factories.ElementoGraficoFactory;
import application.factories.ElementoGraficoTextoFactory;
import application.factories.JogadorFactory;
import application.factories.JogadorFactoryImpl;
import application.factories.MemoriaRepositoryFactory;
import application.factories.PalavraFactory;
import application.factories.PalavraFactoryImpl;
import application.factories.RepositoryFactory;
import application.factories.RodadaFactory;
import application.factories.RodadaSorteioFactory;
import application.factories.TemaFactory;
import application.factories.TemaFactoryImpl;
import application.services.PalavraAppService;
import application.services.RodadaAppService;
import domain.repository.JogadorRepository;
import domain.repository.PalavraRepository;
import domain.repository.RodadaRepository;
import domain.repository.TemaRepository;

public class Aplicacao {

    private static Aplicacao soleInstance;

    private final String[] TIPOS_REPOSITORY_FACTORY = {"memoria", "relacional"};
    private final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private final String[] TIPOS_RODADA_FACTORY = {"sorteio"};

    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    private Aplicacao() {}

    public static Aplicacao getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new Aplicacao();
        }
        return soleInstance;
    }

    public void configurar() {
        RepositoryFactory repoFactory = getRepositoryFactory();
        JogadorRepository jogadorRepo = repoFactory.getJogadorRepository();
        TemaRepository temaRepo = repoFactory.getTemaRepository();
        PalavraRepository palavraRepo = repoFactory.getPalavraRepository();
        RodadaRepository rodadaRepo = repoFactory.getRodadaRepository();

        JogadorFactoryImpl.createSoleInstance(jogadorRepo);
        TemaFactoryImpl.createSoleInstance(temaRepo);
        PalavraFactoryImpl.createSoleInstance(palavraRepo);
        RodadaSorteioFactory.createSoleInstance(rodadaRepo, temaRepo, palavraRepo);
        
        PalavraAppService.createSoleInstance(temaRepo, palavraRepo, getPalavraFactory());
        RodadaAppService.createSoleInstance(getRodadaFactory(), rodadaRepo, jogadorRepo);
    }

    public String[] getTiposRepositoryFactory() {
        return TIPOS_REPOSITORY_FACTORY;
    }

    public void setTipoRepositoryFactory(String tipo) {
        this.tipoRepositoryFactory = tipo;
    }

    public RepositoryFactory getRepositoryFactory() {
        if (tipoRepositoryFactory.equalsIgnoreCase("memoria")) {
            return MemoriaRepositoryFactory.getSoleInstance();
        } else if (tipoRepositoryFactory.equalsIgnoreCase("relacional")) {
            return BDRRepositoryFactory.getSoleInstance();
        }
        throw new RuntimeException("Tipo de Repository Factory desconhecido.");
    }
    
    public String[] getTiposElementoGraficoFactory() {
        return TIPOS_ELEMENTO_GRAFICO_FACTORY;
    }

    public void setTipoElementoGraficoFactory(String tipo) {
        this.tipoElementoGraficoFactory = tipo;
    }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        if (tipoElementoGraficoFactory.equalsIgnoreCase("texto")) {
            return ElementoGraficoTextoFactory.getSoleInstance();
        }
        throw new RuntimeException("Tipo de Elemento Gráfico Factory desconhecido.");
    }
    
    public ElementoGraficoFactory getBonecoFactory() {
        return this.getElementoGraficoFactory();
    }
    
    public ElementoGraficoFactory getLetraFactory() {
        return this.getElementoGraficoFactory();
    }
    
    public String[] getTiposRodadaFactory() {
        return TIPOS_RODADA_FACTORY;
    }
    
    public void setTipoRodadaFactory(String tipo) {
        this.tipoRodadaFactory = tipo;
    }
    
    public RodadaFactory getRodadaFactory() {
        if (tipoRodadaFactory.equalsIgnoreCase("sorteio")) {
            return RodadaSorteioFactory.getSoleInstance();
        }
        throw new RuntimeException("Tipo de Rodada Factory desconhecido.");
    }

    public TemaFactory getTemaFactory() {
        return TemaFactoryImpl.getSoleInstance();
    }

    public PalavraFactory getPalavraFactory() {
        return PalavraFactoryImpl.getSoleInstance();
    }

    public JogadorFactory getJogadorFactory() {
        return JogadorFactoryImpl.getSoleInstance();
    }
}