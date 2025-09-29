import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Palavra {

    private long id;
    private String palavraOculta;
    private List<Letra> letras;
    private Tema tema;

    private Palavra(long id, String palavra, Tema tema) {
        this.id = id;
        this.palavraOculta = palavra.toUpperCase();
        this.tema = tema;
        this.letras = new ArrayList<>();
        
        for (char c : this.palavraOculta.toCharArray()) {
            this.letras.add(new LetraTexto(c)); 
        }
    }
    
    public static Palavra criar(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }
    
    public long getId() {
        return id;
    }

    public Tema getTema() {
        return tema;
    }

    public List<Letra> getLetras() {
        return letras;
    }
    
    public int getTamanho() {
        return this.palavraOculta.length();
    }
    
    public boolean comparar(String palpite) {
        return this.palavraOculta.equalsIgnoreCase(palpite);
    }

    public boolean contemLetra(Letra letra) {
        return this.palavraOculta.indexOf(letra.getCodigo()) >= 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palavra palavra = (Palavra) o;
        return id == palavra.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class LetraTexto extends Letra {
    public LetraTexto(char codigo) {
        super(codigo);
    }
    @Override
    public void exibir() {
        System.out.print(this.codigo);
    }
}

