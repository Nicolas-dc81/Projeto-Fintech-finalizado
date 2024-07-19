package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Despesas;
import br.com.fiap.store.dao.DespesasDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleDespesasDAO implements DespesasDAO {

    private Connection conexao;

    @Override
    public void cadastrar(Despesas despesa) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO T_DESPESAS1 (CD_DESPESAS, DS_DESPESAS, VL_DESPESAS, NM_CATEGORIA, DT_DESPESAS, CD_USUARIO) VALUES (SQ_T_DESPESAS1.NEXTVAL, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());
            stmt.setString(3, despesa.getNome());

            Calendar data = despesa.getData();
            if (data == null) {
                throw new DBException("Data não pode ser nula");
            }
            java.sql.Date sqlDate = new java.sql.Date(data.getTimeInMillis());
            stmt.setDate(4, sqlDate);

            stmt.setInt(5, despesa.getCdUsuario()); // Adicione esta linha

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Despesas despesa) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE T_DESPESAS1 SET DS_DESPESAS = ?, VL_DESPESAS = ?, NM_CATEGORIA = ?, DT_DESPESAS = ?, CD_USUARIO = ? WHERE CD_DESPESAS = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());
            stmt.setString(3, despesa.getNome());

            Calendar data = despesa.getData();
            if (data == null) {
                throw new DBException("Data não pode ser nula");
            }
            java.sql.Date sqlDate = new java.sql.Date(data.getTimeInMillis());
            stmt.setDate(4, sqlDate);
            stmt.setInt(5, despesa.getCdUsuario());
            stmt.setInt(6, despesa.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(int codigo) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM T_DESPESAS1 WHERE CD_DESPESAS = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Despesas buscar(int id) {
        Despesas despesa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            stmt = conexao.prepareStatement("SELECT * FROM T_DESPESAS1 WHERE CD_DESPESAS = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int codigo = rs.getInt("CD_DESPESAS");
                String descricao = rs.getString("DS_DESPESAS");
                double valor = rs.getDouble("VL_DESPESAS");
                String nome = rs.getString("NM_CATEGORIA");
                java.sql.Date date = rs.getDate("DT_DESPESAS");
                Calendar calendar = Calendar.getInstance();
                if (date != null) {
                    calendar.setTime(date);
                }
                

                despesa = new Despesas(codigo, descricao, valor, nome, calendar); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return despesa;
    }

    @Override
    public List<Despesas> listar() {
        List<Despesas> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            stmt = conexao.prepareStatement("SELECT * FROM T_DESPESAS1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("CD_DESPESAS");
                String descricao = rs.getString("DS_DESPESAS");
                double valor = rs.getDouble("VL_DESPESAS");
                String nome = rs.getString("NM_CATEGORIA");
                java.sql.Date date = rs.getDate("DT_DESPESAS");
                Calendar calendar = Calendar.getInstance();
                if (date != null) {
                    calendar.setTime(date);
                }
               

                Despesas despesa = new Despesas(codigo, descricao, valor, nome, calendar); 
                lista.add(despesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}

