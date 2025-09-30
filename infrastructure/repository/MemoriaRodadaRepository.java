package infrastructure.repository;

import domain.model.Jogador;
import domain.model.Rodada;
import domain.repository.RodadaRepository;
import infrastructure.RepositoryException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MemoriaRodadaRepository implements RodadaRepository {

    private static MemoriaRodadaRepository soleInstance;

    public static MemoriaRodadaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaRodadaRepository();
        }
        return soleInstance;
    }

    private final Map<Long, Rodada> pool;
    private final AtomicLong proximoId;

    private MemoriaRodadaRepository() {
        this.pool = new ConcurrentHashMap<>();
        this.proximoId = new AtomicLong(1);
    }

    @Override
    public long getProximoId() {
        return proximoId.getAndIncrement();
    }

    @Override
    public Rodada getPorId(long id) {
        return pool.get(id);
    }

    @Override
    public List<Rodada> getPorJogador(Jogador jogador) {
        return pool.values().stream()
                .filter(r -> r.getJogador().equals(jogador))
                .collect(Collectors.toList());
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
        if (pool.containsKey(rodada.getId())) {
            throw new RepositoryException("Rodada com ID " + rodada.getId() + " já existe.");
        }
        pool.put(rodada.getId(), rodada);
    }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {
        if (!pool.containsKey(rodada.getId())) {
            throw new RepositoryException("Rodada com ID " + rodada.getId() + " não encontrada para atualização.");
        }
        pool.put(rodada.getId(), rodada);
    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {
        if (pool.remove(rodada.getId()) == null) {
            throw new RepositoryException("Rodada com ID " + rodada.getId() + " não encontrada para remoção.");
        }
    }
}

