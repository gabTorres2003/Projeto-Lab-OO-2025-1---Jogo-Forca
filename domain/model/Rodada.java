package domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rodada {

    public static final int MAX_ERROS = 10;
    public static final int PONTOS_POR_ACERTO_FINAL = 100;
    public static final int PONTOS_POR_LETRA_ENCOBERTA = 15;

    private long id;
    private List<Palavra> palavras;
    private Jogador jogador;
    private Boneco boneco;

    private List<Letra> letrasTentadas;
    private Set<Letra> letrasEncobertas;
    
    private int numErros;
    private boolean arriscou;
    private boolean encerrada;

    private Rodada(long id, Jogador jogador, List<Palavra> palavras, Boneco boneco) {
        this.id = id;
        this.jogador = jogador;
        this.palavras = palavras;
        this.boneco = boneco;
        this.letrasTentadas = new ArrayList<>();
        this.letrasEncobertas = new HashSet<>();
        for (Palavra p : palavras) {
            letrasEncobertas.addAll(p.getLetras());
        }
        this.numErros = 0;
        this.arriscou = false;
        this.encerrada = false;
    }

    public static Rodada criar(long id, Jogador jogador, List<Palavra> palavras, Boneco boneco) {
        return new Rodada(id, jogador, palavras, boneco);
    }

    public long getId() { return id; }
    public Jogador getJogador() { return jogador; }
    public List<Palavra> getPalavras() { return palavras; }
    public int getNumErros() { return numErros; }
    public boolean isEncerrada() { return encerrada; }
    public List<Letra> getLetrasTentadas() { return letrasTentadas; }

    public boolean tentarLetra(Letra letraTentada) {
        if (encerrada || letrasTentadas.contains(letraTentada)) {
            return false;
        }

        letrasTentadas.add(letraTentada);

        boolean acertou = false;
        for (Palavra p : palavras) {
            if (p.contemLetra(letraTentada)) {
                acertou = true;
            }
        }

        if (acertou) {
            letrasEncobertas.remove(letraTentada);
            if (letrasEncobertas.isEmpty()) {
                encerrada = true;
            }
        } else {
            numErros++;
            if (numErros >= MAX_ERROS) {
                encerrada = true;
            }
        }
        return acertou;
    }

    public boolean arriscar(List<String> palpites) {
        if (encerrada || arriscou || palpites.size() != palavras.size()) {
            return false;
        }

        this.arriscou = true;
        this.encerrada = true;

        for (int i = 0; i < palavras.size(); i++) {
            if (!palavras.get(i).comparar(palpites.get(i))) {
                return false;
            }
        }

        letrasEncobertas.clear();
        return true;
    }

    public int calcularPontos() {
        if (descobriuTodasAsLetras()) {
            int letrasRestantesNaVitoria = letrasEncobertas.size(); // Será 0 se não arriscou
            return PONTOS_POR_ACERTO_FINAL + (letrasRestantesNaVitoria * PONTOS_POR_LETRA_ENCOBERTA);
        }
        return 0;
    }

    public boolean descobriuTodasAsLetras() {
        return letrasEncobertas.isEmpty();
    }
    
    public void exibirBoneco() {
        this.boneco.exibir(this.numErros);
    }
    
    public void exibirPalavras() {
        System.out.println("Palavra(s):");
        for (Palavra p : palavras) {
            for (Letra l : p.getLetras()) {
                if (!letrasEncobertas.contains(l)) {
                    System.out.print(l.getCodigo() + " ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.print("  ");
        }
        System.out.println("\n");
    }
    
    public void exibirLetrasTentadas() {
        System.out.print("Letras tentadas: ");
        for (Letra l : letrasTentadas) {
            System.out.print(l.getCodigo() + " ");
        }
        System.out.println("\n");
    }
}

