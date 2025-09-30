package ui.console;

import application.factories.JogadorFactory;
import application.factories.PalavraFactory;
import application.factories.TemaFactory;
import application.services.JogadorNaoEncontradoException;
import application.services.RodadaAppService;
import config.Aplicacao;
import domain.model.Jogador;
import domain.model.Letra;
import domain.model.Palavra;
import domain.model.Rodada;
import domain.model.Tema;
import domain.repository.JogadorRepository;
import domain.repository.PalavraRepository;
import domain.repository.TemaRepository;
import infrastructure.RepositoryException;

import java.util.Scanner;

public class Main {

    private static void init() throws RepositoryException {
        Aplicacao app = Aplicacao.getSoleInstance();
        app.setTipoRepositoryFactory("memoria");
        app.setTipoElementoGraficoFactory("texto");
        app.configurar();

        JogadorRepository jogadorRepo = app.getRepositoryFactory().getJogadorRepository();
        TemaRepository temaRepo = app.getRepositoryFactory().getTemaRepository();
        PalavraRepository palavraRepo = app.getRepositoryFactory().getPalavraRepository();

        JogadorFactory jogadorFactory = app.getJogadorFactory();
        TemaFactory temaFactory = app.getTemaFactory();
        PalavraFactory palavraFactory = app.getPalavraFactory();

        Jogador j1 = jogadorFactory.getJogador("ana");
        Jogador j2 = jogadorFactory.getJogador("bia");
        jogadorRepo.inserir(j1);
        jogadorRepo.inserir(j2);

        Tema t1 = temaFactory.getTema("Frutas");
        Tema t2 = temaFactory.getTema("Profissões");
        temaRepo.inserir(t1);
        temaRepo.inserir(t2);

        Palavra p1 = palavraFactory.getPalavra("MORANGO", t1);
        Palavra p2 = palavraFactory.getPalavra("BANANA", t1);
        Palavra p3 = palavraFactory.getPalavra("ABACAXI", t1);
        palavraRepo.inserir(p1);
        palavraRepo.inserir(p2);
        palavraRepo.inserir(p3);
        
        Palavra p4 = palavraFactory.getPalavra("MEDICO", t2);
        Palavra p5 = palavraFactory.getPalavra("PROFESSOR", t2);
        Palavra p6 = palavraFactory.getPalavra("ADVOGADO", t2);
        palavraRepo.inserir(p4);
        palavraRepo.inserir(p5);
        palavraRepo.inserir(p6);
    }

    public static void main(String[] args) {
        try {
            init();
            System.out.println("Sistema inicializado e dados carregados.");
        } catch (RepositoryException e) {
            System.err.println("Erro fatal ao inicializar repositórios: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Aplicacao app = Aplicacao.getSoleInstance();
        RodadaAppService rodadaService = RodadaAppService.getSoleInstance();

        System.out.println("\n--- BEM-VINDO AO JOGO DA FORCA ---");
        
        while (true) {
            System.out.print("Digite seu nome de jogador (ou 'sair' para terminar): ");
            String nomeJogador = scanner.nextLine();

            if (nomeJogador.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                Rodada rodada = rodadaService.novaRodada(nomeJogador);
                System.out.println("\nNova rodada iniciada para " + nomeJogador.toUpperCase() + "!");
                System.out.println("Tema: " + rodada.getPalavras().get(0).getTema().getNome());

                while (!rodada.isEncerrada()) {
                    System.out.println("\n------------------------------------");
                    rodada.exibirBoneco();
                    rodada.exibirPalavras();
                    rodada.exibirLetrasTentadas();

                    System.out.print("Digite uma letra: ");
                    String input = scanner.nextLine().toUpperCase();
                    
                    if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                        System.out.println("Entrada inválida. Por favor, digite uma única letra.");
                        continue;
                    }
                    
                    char letraChar = input.charAt(0);
                    Letra letra = (Letra) app.getLetraFactory().getLetra(letraChar);

                    if (rodada.getLetrasTentadas().contains(letra)) {
                        System.out.println("Letra já tentada!");
                        continue;
                    }

                    if(rodada.tentarLetra(letra)) {
                        System.out.println("BOA! Você acertou a letra!");
                    } else {
                        System.out.println("ERRADO! Essa letra não existe.");
                    }
                }

                System.out.println("\n--- FIM DA RODADA ---");
                if (rodada.descobriuTodasAsLetras()) {
                    System.out.println("PARABÉNS, VOCÊ VENCEU!");
                } else {
                    System.out.println("QUE PENA, VOCÊ PERDEU!");
                    rodada.exibirBoneco();
                    System.out.print("As palavras eram: ");
                    rodada.getPalavras().forEach(p -> System.out.print(p.toString().toUpperCase() + " "));
                    System.out.println();
                }

                rodadaService.salvarRodada(rodada);

            } catch (JogadorNaoEncontradoException e) {
                System.err.println("ERRO: Jogador '" + e.getJogador() + "' não cadastrado no sistema.");
            } catch (RuntimeException e) {
                System.err.println("ERRO ao criar a rodada: " + e.getMessage());
            }
            System.out.println("\n====================================\n");
        }
        
        System.out.println("Obrigado por jogar!");
        scanner.close();
    }
}