package domain.repository;

import domain.model.Jogador;
import domain.model.Rodada;
import infrastructure.RepositoryException;
import java.util.List;

public interface RodadaRepository extends Repository {
    Rodada getPorId(long id);
    List<Rodada> getPorJogador(Jogador jogador);
    void inserir(Rodada rodada) throws RepositoryException;
    void atualizar(Rodada rodada) throws RepositoryException;
    void remover(Rodada rodada) throws RepositoryException;
}