package br.com.fiap.store.dao;

import java.util.List;
import br.com.fiap.store.bean.Despesas;
import br.com.fiap.store.exception.DBException;

public interface DespesasDAO {
	
	void cadastrar (Despesas despesa) throws DBException;
	void atualizar (Despesas despesa) throws DBException;
	void remover (int codigo) throws DBException;
	Despesas buscar (int id);
	List<Despesas> listar();

}
