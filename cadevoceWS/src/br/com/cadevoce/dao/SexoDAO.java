package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.SexoVO;

public class SexoDAO {

	public SexoVO[] listarSexo() {
		List<SexoVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT * FROM SEXO";

			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SexoVO sexo = new SexoVO();
				sexo.setCodigo(rs.getInt(1));
				sexo.setDescricao(rs.getString(2));

				lista.add(sexo);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método listarSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new SexoVO[lista.size()]);
	}

	public SexoVO buscarSexo(int codigo) {
		SexoVO sexo = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT * FROM SEXO WHERE IDSEXO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				sexo = new SexoVO();
				sexo.setCodigo(rs.getInt(1));
				sexo.setDescricao(rs.getString(2));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return sexo;
	}

	public int inserirSexo(SexoVO sexo) {
		int idGerado = 0;
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "INSERT INTO SEXO (DESCRICAO ) VALUES(?)";

			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, sexo.getDescricao());

			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - inserirSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idGerado;
	}

	public void editarSexo(SexoVO sexo, int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "UPDATE SEXO SET DESCRICAO = ? WHERE IDSEXO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, sexo.getDescricao());
			statement.setInt(2, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - editarSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void removerSexo(int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "DELETE FROM SEXO WHERE IDSEXO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - removerSexo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
