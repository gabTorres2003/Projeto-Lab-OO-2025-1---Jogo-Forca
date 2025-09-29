import java.util.Objects;

public abstract class Letra {
    
    protected final char codigo;

    protected Letra(char codigo) {
        this.codigo = Character.toUpperCase(codigo);
    }

    public char getCodigo() {
        return codigo;
    }

    public abstract void exibir();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letra letra = (Letra) o;
        return codigo == letra.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

