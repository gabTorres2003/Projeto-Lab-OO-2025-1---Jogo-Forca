package infrastructure.repository;

import domain.model.Tema;
import domain.repository.TemaRepository;
import infrastructure.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance;

    public static MemoriaTemaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaTemaRepository();
        }
        return soleInstance;
    }

    private final Map<Long, Tema> pool;
    private final AtomicLong proximoId;

    private MemoriaTemaRepository() {
        this.pool = new ConcurrentHashMap<>();
        this.proximoId = new AtomicLong(1);
    }

    @Override
    public long getProximoId() {
        return proximoId.getAndIncrement();
    }

    @Override
    public Tema getPorId(long id) {
        return pool.get(id);
    }

    @Override
    public Tema getPorNome(String nome) {
        for (Tema tema : pool.values()) {
            if (tema.getNome().equalsIgnoreCase(nome)) {
                return tema;
            }
        }
        return null;
    }

    @Override
    public List<Tema> getTodos() {
        return new ArrayList<>(pool.values());
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        if (pool.containsKey(tema.getId())) {
            throw new RepositoryException("Tema com ID " + tema.getId() + " já existe.");
        }
        pool.put(tema.getId(), tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        if (!pool.containsKey(tema.getId())) {
            throw new RepositoryException("Tema com ID " + tema.getId() + " não encontrado para atualização.");
        }
        pool.put(tema.getId(), tema);
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        if (pool.remove(tema.getId()) == null) {
            throw new RepositoryException("Tema com ID " + tema.getId() + " não encontrado para remoção.");
        }
    }
}

