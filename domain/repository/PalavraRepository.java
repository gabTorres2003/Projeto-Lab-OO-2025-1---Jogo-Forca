package domain.repository;

import domain.model.Palavra;
import domain.model.Tema;
import infrastructure.RepositoryException;
import java.util.List;

public interface PalavraRepository extends Repository {
    Palavra getPorId(long id);
    List<Palavra> getPorTema(Tema tema);
    List<Palavra> getTodas();
    Palavra getPalavraPorPalavra(String palavra);
    void inserir(Palavra palavra) throws RepositoryException;
    void atualizar(Palavra palavra) throws RepositoryException;
    void remover(Palavra palavra) throws RepositoryException;
}