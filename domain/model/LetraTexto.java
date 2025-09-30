package domain.model;

public class LetraTexto extends Letra {
    public LetraTexto(char codigo) {
        super(codigo);
    }
    
    @Override
    public void exibir() {
        System.out.print(this.codigo);
    }
}