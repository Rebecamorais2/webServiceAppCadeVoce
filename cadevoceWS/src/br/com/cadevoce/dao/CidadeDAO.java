package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.CidadeVO;

public class CidadeDAO {

	public CidadeVO[] listarCidade() {
		List<CidadeVO> lista = new ArrayList<>();

		Connection conexao;

		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM CIDADE";
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CidadeVO cidade = new CidadeVO();
				cidade.setCodigo(rs.getInt(1));
				cidade.setDescricao(rs.getString(2));
				cidade.setCodEstado(rs.getInt(3));

				lista.add(cidade);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarCidade!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco - método listarCidade");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new CidadeVO[lista.size()]);

	}

	public CidadeVO buscarCidadePorId(int codigo) {
		CidadeVO cidade = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM CIDADE WHERE IDCIDADE = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				cidade = new CidadeVO();
				cidade.setCodigo(rs.getInt(1));
				cidade.setDescricao(rs.getString(2));
				cidade.setCodEstado(rs.getInt(3));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarCidadePorId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro Banco de dados- método buscarCidadePorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return cidade;
	}

	public int inserirCidade(CidadeVO cidade) {
		int idGerado = 0;
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "INSERT INTO CIDADE (DESCRICAO, CODESTADO ) VALUES(?, ?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cidade.getDescricao());
			statement.setInt(2, cidade.getCodEstado());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirCidade!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método inserirCidade");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return idGerado;
	}

	public void editarCidade(CidadeVO cidade, int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE CIDADE SET DESCRICAO = ?, CODESTADO = ? WHERE IDCIDADE = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, cidade.getDescricao());
			statement.setInt(2, cidade.getCodEstado());
			statement.setInt(3, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarCidade!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método editarCidade");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void removerCidade(int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM CIDADE WHERE IDCIDADE = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			statement.execute();
		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerCidade!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método removerCidade");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
