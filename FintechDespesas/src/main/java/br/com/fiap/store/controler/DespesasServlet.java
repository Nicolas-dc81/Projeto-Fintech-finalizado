package br.com.fiap.store.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.fiap.store.bean.Despesas;
import br.com.fiap.store.dao.DespesasDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;


/**
 * Servlet implementation class DespesasServlet
 */
public class DespesasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private DespesasDAO dao; 

    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getDespesasDAO();
    }
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      try{
					String descricao = request.getParameter("descricao");
					double valor = Integer.parseInt(request.getParameter("valor"));
					String nome = request.getParameter(request.getParameter("nome"));
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(format.parse(request.getParameter("Data")));
					
					Despesas despesa = new Despesas(0, descricao, valor, nome, calendar); 
					dao.cadastrar(despesa);
					
					request.setAttribute("msg", "Despesa cadastrado!");
				}catch(DBException db) {
					db.printStackTrace();
					request.setAttribute("erro", "Erro ao cadastrar");
				}catch(Exception e){
					e.printStackTrace();
					request.setAttribute("erro","Por favor, valide os dados");
				}
				request.getRequestDispatcher("cadastro-despesas.jsp").forward(request, response);
			
			
	
	}

}
