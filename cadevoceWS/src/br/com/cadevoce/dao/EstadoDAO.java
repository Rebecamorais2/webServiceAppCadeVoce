package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.EstadoVO;

public class EstadoDAO {

	public EstadoVO[] listarEstado() {
		List<EstadoVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM Estado";
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				EstadoVO estado = new EstadoVO();
				estado.setCodigo(rs.getInt(1));
				estado.setDescricao(rs.getString(2));

				lista.add(estado);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarEstado!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método listarEstado");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new EstadoVO[lista.size()]);

	}

	public EstadoVO buscarEstadoPorId(int codigo) {
		EstadoVO estado = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM Estado WHERE CODESTADO = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				estado = new EstadoVO();
				estado.setCodigo(rs.getInt(1));
				estado.setDescricao(rs.getString(2));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarEstadoPorId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método buscarEstadoPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return estado;
	}

	public int inserirEstado(EstadoVO estado) {
		int idGerado = 0;
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "INSERT INTO Estado (DESCRICAO ) VALUES(?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, estado.getDescricao());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirEstado!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método inserirEstado");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idGerado;
	}

	public void editarEstado(EstadoVO estado, int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE ESTADO SET DESCRICAO = ? WHERE CODESTADO = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, estado.getDescricao());
			statement.setInt(2, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarEstado!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método editarEstado");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void removerEstado(int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM Estado WHERE CODESTADO = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerEstado!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método removerEstado");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
