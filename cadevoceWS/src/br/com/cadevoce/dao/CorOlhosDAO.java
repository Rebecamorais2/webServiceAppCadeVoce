package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.CorOlhosVO;

public class CorOlhosDAO {

	public CorOlhosVO[] listarCorOlhos() {
		List<CorOlhosVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM CorOlhos";
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CorOlhosVO corOlhos = new CorOlhosVO();
				corOlhos.setCodigo(rs.getInt(1));
				corOlhos.setDescricao(rs.getString(2));

				lista.add(corOlhos);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarCorOlhos!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método listarCorOlhos");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new CorOlhosVO[lista.size()]);
	}

	public CorOlhosVO buscarCorOlhosPorId(int codigo) {
		CorOlhosVO corOlhos = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM CorOlhos WHERE IDCOROLHOS = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				corOlhos = new CorOlhosVO();
				corOlhos.setCodigo(rs.getInt(1));
				corOlhos.setDescricao(rs.getString(2));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarCorOlhosPorId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarCorOlhosPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return corOlhos;
	}

	public int inserirCorOlhos(CorOlhosVO corOlhos) {
		int idGerado = 0;
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "INSERT INTO CorOlhos (DESCRICAO ) VALUES(?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, corOlhos.getDescricao());

			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirCorOlhos!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados  -  método inserirCorOlhos");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idGerado;
	}

	public void editarCorOlhos(CorOlhosVO corOlhos, int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE CorOlhos SET DESCRICAO = ? WHERE IDCOROLHOS = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, corOlhos.getDescricao());
			statement.setInt(2, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarCorOlhos!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método editarCorOlhos");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void removerCorOlhos(int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM CorOlhos WHERE IDCOROLHOS = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerCorOlhos!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método removerCorOlhos");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
