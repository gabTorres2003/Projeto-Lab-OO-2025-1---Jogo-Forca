package infrastructure.repository;

import domain.model.Palavra;
import domain.model.Tema;
import domain.repository.PalavraRepository;
import infrastructure.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MemoriaPalavraRepository implements PalavraRepository {

    private static MemoriaPalavraRepository soleInstance;

    public static MemoriaPalavraRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaPalavraRepository();
        }
        return soleInstance;
    }

    private final Map<Long, Palavra> pool;
    private final AtomicLong proximoId;

    private MemoriaPalavraRepository() {
        this.pool = new ConcurrentHashMap<>();
        this.proximoId = new AtomicLong(1);
    }

    @Override
    public long getProximoId() {
        return proximoId.getAndIncrement();
    }

    @Override
    public Palavra getPorId(long id) {
        return pool.get(id);
    }

    @Override
    public List<Palavra> getPorTema(Tema tema) {
        return pool.values().stream()
                .filter(p -> p.getTema().equals(tema))
                .collect(Collectors.toList());
    }

    @Override
    public List<Palavra> getTodas() {
        return new ArrayList<>(pool.values());
    }

    @Override
    public Palavra getPalavraPorPalavra(String palavra) {
        for (Palavra p : pool.values()) {
            if (p.comparar(palavra)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        if (pool.containsKey(palavra.getId())) {
            throw new RepositoryException("Palavra com ID " + palavra.getId() + " já existe.");
        }
        pool.put(palavra.getId(), palavra);
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        if (!pool.containsKey(palavra.getId())) {
            throw new RepositoryException("Palavra com ID " + palavra.getId() + " não encontrada para atualização.");
        }
        pool.put(palavra.getId(), palavra);
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        if (pool.remove(palavra.getId()) == null) {
            throw new RepositoryException("Palavra com ID " + palavra.getId() + " não encontrada para remoção.");
        }
    }
}

