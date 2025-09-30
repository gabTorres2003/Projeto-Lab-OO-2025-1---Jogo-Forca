package domain.repository;

import domain.model.Jogador;
import infrastructure.RepositoryException;
import java.util.List;

public interface JogadorRepository extends Repository {
    Jogador getPorId(long id);
    Jogador getPorNome(String nome);
    List<Jogador> getTodos();
    void inserir(Jogador jogador) throws RepositoryException;
    void atualizar(Jogador jogador) throws RepositoryException;
    void remover(Jogador jogador) throws RepositoryException;
}