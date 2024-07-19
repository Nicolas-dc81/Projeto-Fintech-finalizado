package br.com.fiap.store.bean;

import java.util.Calendar;

public class Despesas {
	
	private int codigo;
	private String nome;
	private double valor;
	private String descricao;
	private Calendar data;
	private int cdUsuario;
	
	public Despesas() {
		super();
	}
		
		public Despesas(int codigo, String nome, double valor, String descricao, Calendar data) {
			super();
			this.codigo = codigo;
			this.nome = nome;
			this.valor = valor;
			this.descricao = descricao;
			this.data = data;	
		
	}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Calendar getData() {
			return data;
		}

		public void setData(Calendar data) {
			this.data = data;
		}
		
		public int getCdUsuario() {
	        return cdUsuario;
	    }

	    public void setCdUsuario(int cdUsuario) {
	        this.cdUsuario = cdUsuario;
		
		
	    }

		public int getCdUsuario1() {
			// TODO Auto-generated method stub
			return 0;
		}
}
