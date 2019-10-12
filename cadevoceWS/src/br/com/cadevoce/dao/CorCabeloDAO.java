package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.CorCabeloVO;

public class CorCabeloDAO {

	public CorCabeloVO[] listarCorCabelo() {
		List<CorCabeloVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM CorCabelo";
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				CorCabeloVO corCabelo = new CorCabeloVO();
				corCabelo.setCodigo(rs.getInt(1));
				corCabelo.setDescricao(rs.getString(2));

				lista.add(corCabelo);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarCorCabelo!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método listarCorCabelo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return lista.toArray(new CorCabeloVO[lista.size()]);
	}

	public CorCabeloVO buscarCorCabeloPorId(int codigo) {
		CorCabeloVO corCabelo = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM CorCabelo WHERE IDCORCABELO = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				corCabelo = new CorCabeloVO();
				corCabelo.setCodigo(rs.getInt(1));
				corCabelo.setDescricao(rs.getString(2));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarCorCabeloPorId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarCorCabeloPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return corCabelo;
	}

	public int inserirCorCabelo(CorCabeloVO corCabelo) {
		int idGerado = 0;
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "INSERT INTO CorCabelo (DESCRICAO ) VALUES(?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, corCabelo.getDescricao());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirCorCabelo!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método inserirCorCabelo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idGerado;
	}

	public void editarCorCabelo(CorCabeloVO corCabelo, int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE CorCabelo SET DESCRICAO = ? WHERE IDCORCABELO = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, corCabelo.getDescricao());
			statement.setInt(2, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarCorCabelo!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método editarCorCabelo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void removerCorCabelo(int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM CorCabelo WHERE IDCORCABELO = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerCorCabelo!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método removerCorCabelo");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
