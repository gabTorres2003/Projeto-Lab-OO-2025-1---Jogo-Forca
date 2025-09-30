package infrastructure.repository;

import domain.model.Jogador;
import domain.repository.JogadorRepository;
import infrastructure.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoriaJogadorRepository implements JogadorRepository {

    private static MemoriaJogadorRepository soleInstance;

    public static MemoriaJogadorRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaJogadorRepository();
        }
        return soleInstance;
    }

    private final Map<Long, Jogador> pool;
    private final AtomicLong proximoId;

    private MemoriaJogadorRepository() {
        this.pool = new ConcurrentHashMap<>();
        this.proximoId = new AtomicLong(1);
    }
    
    @Override
    public long getProximoId() {
        return proximoId.getAndIncrement();
    }

    @Override
    public Jogador getPorId(long id) {
        return pool.get(id);
    }

    @Override
    public Jogador getPorNome(String nome) {
        for (Jogador jogador : pool.values()) {
            if (jogador.getNome().equalsIgnoreCase(nome)) {
                return jogador;
            }
        }
        return null;
    }

    @Override
    public List<Jogador> getTodos() {
        return new ArrayList<>(pool.values());
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        if (pool.containsKey(jogador.getId())) {
            throw new RepositoryException("Jogador com ID " + jogador.getId() + " já existe.");
        }
        pool.put(jogador.getId(), jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        if (!pool.containsKey(jogador.getId())) {
            throw new RepositoryException("Jogador com ID " + jogador.getId() + " não encontrado para atualização.");
        }
        pool.put(jogador.getId(), jogador);
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        if (pool.remove(jogador.getId()) == null) {
            throw new RepositoryException("Jogador com ID " + jogador.getId() + " não encontrado para remoção.");
        }
    }
}

