package domain.repository;

import domain.model.Tema;
import infrastructure.RepositoryException;
import java.util.List;

public interface TemaRepository extends Repository {
    Tema getPorId(long id);
    Tema getPorNome(String nome);
    List<Tema> getTodos();
    void inserir(Tema tema) throws RepositoryException;
    void atualizar(Tema tema) throws RepositoryException;
    void remover(Tema tema) throws RepositoryException;
}