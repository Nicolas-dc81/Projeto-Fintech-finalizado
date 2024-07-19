package br.com.fiap.store.factory;

import br.com.fiap.store.dao.DespesasDAO;
import br.com.fiap.store.dao.impl.OracleDespesasDAO;

public class DAOFactory {
	
	public static DespesasDAO getDespesasDAO() {
		return new OracleDespesasDAO();
	}

}
