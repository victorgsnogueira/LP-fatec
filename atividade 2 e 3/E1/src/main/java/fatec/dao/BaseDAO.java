package fatec.dao;

import java.util.List;

public interface BaseDAO<T> {
    void inserir(T objeto) throws Exception;
    void atualizar(T objeto) throws Exception;
    void excluir(int id) throws Exception;
    T buscarPorId(int id) throws Exception;
    List<T> listarTodos() throws Exception;
}