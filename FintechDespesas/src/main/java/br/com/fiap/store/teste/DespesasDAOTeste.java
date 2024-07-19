package br.com.fiap.store.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Despesas;
import br.com.fiap.store.dao.DespesasDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

public class DespesasDAOTeste {

    public static void main(String[] args) {
        DespesasDAO dao = DAOFactory.getDespesasDAO();
        
        
        Calendar data = Calendar.getInstance();
        data.set(2023, Calendar.JUNE, 1); 
        
        
        Despesas despesa = new Despesas();
        despesa.setDescricao("Prêmios");
        despesa.setValor(4500.0);
        despesa.setNome("Vendas");
        despesa.setData(data);
        
        try {
            dao.cadastrar(despesa);
            System.out.println("Despesa cadastrada.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        // Buscar uma despesa pelo código e atualizar
        despesa = dao.buscar(1);
        if (despesa != null) {
            despesa.setNome("Fixas");
            despesa.setValor(500);
            try {
                dao.atualizar(despesa);
                System.out.println("Despesa atualizada.");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Despesa não encontrada.");
        }

        // Listar as despesas
        List<Despesas> lista = dao.listar();
        for (Despesas item : lista) {
            System.out.println(item.getNome() + " " + item.getValor());
        }

        // Remover uma despesa
        try {
            dao.remover(1);
            System.out.println("Despesa removida.");
        } catch (DBException e) {
            e.printStackTrace();
        }    
    }    
}
