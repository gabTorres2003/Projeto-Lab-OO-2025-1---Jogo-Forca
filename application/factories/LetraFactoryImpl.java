package application.factories;

import domain.model.Letra;
import java.util.HashMap;
import java.util.Map;

public abstract class LetraFactoryImpl implements LetraFactory {
    private Map<Character, Letra> pool = new HashMap<>();
    private Letra encoberta = null;

    protected abstract Letra criarLetra(char codigo);
    protected abstract Letra criarLetraEncoberta();

    @Override
    public final Letra getLetra(char codigo) {
        char c = Character.toUpperCase(codigo);
        if (!pool.containsKey(c)) {
            pool.put(c, criarLetra(c));
        }
        return pool.get(c);
    }

    @Override
    public final Letra getLetraEncoberta() {
        if (encoberta == null) {
            encoberta = criarLetraEncoberta();
        }
        return encoberta;
    }
}

