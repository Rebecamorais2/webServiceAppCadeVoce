package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.RacaDesapVO;

public class RacaDesapDAO {

	public RacaDesapVO[] listarRacaDesap() {
		List<RacaDesapVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM RacaDesap";
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				RacaDesapVO racaDesap = new RacaDesapVO();
				racaDesap.setCodigo(rs.getInt(1));
				racaDesap.setDescricao(rs.getString(2));

				lista.add(racaDesap);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarRacaDesap!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método listarRacaDesap");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new RacaDesapVO[lista.size()]);
	}

	public RacaDesapVO buscarRacaDesapPorId(int codigo) {
		RacaDesapVO racaDesap = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT * FROM RacaDesap WHERE idRaca = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				racaDesap = new RacaDesapVO();
				racaDesap.setCodigo(rs.getInt(1));
				racaDesap.setDescricao(rs.getString(2));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarRacaDesapPorId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarRacaDesapPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return racaDesap;
	}

	public int inserirRacaDesap(RacaDesapVO racaDesap) {
		int idGerado = 0;
		Connection conexao;
		try {

			conexao = BDConfig.getConnection();
			String sql = "INSERT INTO RacaDesap (DESCRICAO ) VALUES(?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, racaDesap.getDescricao());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirRacaDesap!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método inserirRacaDesap");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idGerado;
	}

	public void editarRacaDesap(RacaDesapVO racaDesap, int codigo) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE RacaDesap SET DESCRICAO = ? WHERE idRaca = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, racaDesap.getDescricao());
			statement.setInt(2, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarRacaDesap!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método editarRacaDesap");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void removerRacaDesap(int codigo) {
		Connection conexao;
		try {

			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM RacaDesap WHERE idRaca = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerRacaDesap!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no  Banco de dados - método removerRacaDesap");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
