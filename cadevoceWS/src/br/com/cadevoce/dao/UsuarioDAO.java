package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.sun.org.apache.regexp.internal.RE;

import br.com.cadevoce.vo.DesaparecidoVO;
import br.com.cadevoce.vo.UsuarioVO;

public class UsuarioDAO {

	// private static final Logger LOG =
	// Logger.getLogger(UsuarioDAO.class.getName());
	Logger logger;

	public UsuarioVO[] listarUsuarios() {
		List<UsuarioVO> lista = new ArrayList<>();

		Connection conexao;
		try {

			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM USUARIO";

			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				UsuarioVO usuario = new UsuarioVO(0, "", "", "");
				usuario.setCodigo(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));

				lista.add(usuario);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarUsuarios");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no banco de dados -  método listarUsuarios");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new UsuarioVO[lista.size()]);
	}

	public UsuarioVO buscarUsuarioPorId(int codUser) {
		UsuarioVO usuario = null;

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM USUARIO WHERE CODUSER = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codUser);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				usuario = new UsuarioVO();
				usuario.setCodigo(rs.getInt("CODUSER"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarUsuarioPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarUsuarioPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return usuario;
	}

	public int inserirUsuario(UsuarioVO usuario) {
		int idGerado = 0;
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "INSERT INTO USUARIO (NOME, EMAIL, SENHA) " + "VALUES(?, ?, ?)";

			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());

			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirUsuario!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método inserirUsuario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return idGerado;
	}

	public void editarUsuario(UsuarioVO usuario, int codUser) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "UPDATE USUARIO SET NOME = ?, EMAIL = ?, SENHA = ? WHERE CODUSER = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			statement.setInt(4, codUser);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarUsuario!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método editarUsuario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void removerDependenciaUsuario(int codUser) {
		
		String[] array = null;
		String apagar = "";
		List<DesaparecidoVO> lista = new ArrayList<>();
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT CODDESAP FROM DESAPARECIDO  WHERE CODUSER = ?";
		
			PreparedStatement statement = conexao.prepareStatement(sql);
			
			statement.setInt(1, codUser);
			
			ResultSet rs = statement.executeQuery();
			
	
			while (rs.next()) {
				DesaparecidoVO desaparecido = new DesaparecidoVO();
				desaparecido.setCodDesap(rs.getInt("CODDESAP"));
	

				lista.add(desaparecido);
//				System.out.println("Lista para apagar: "+ desaparecido.getCodDesap());
				apagar = String.valueOf(desaparecido.getCodDesap());
				
				int codDesap = Integer.parseInt(apagar);
			
				System.out.println("apagar "+apagar);
				System.out.println("int "+codDesap);

				DesaparecidoDAO.removerDesaparecido(codDesap);
				
				String idDesap = String.valueOf(codDesap);
				
				FacesDAO.buscarFacesPorId(idDesap); //busca as informações e apaga os dados do cloudinary e cognitive services
				
				FacesDAO.removerFaces(idDesap);
				  
				
		    }

		
			
		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerUsuario!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método removerUsuario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	
	public void removerUsuario(int codUser) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "DELETE FROM USUARIO WHERE CODUSER = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codUser);
			statement.execute();
		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerUsuario!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método removerUsuario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public UsuarioVO validarUsuario(String email, String senha) {

		UsuarioVO usuario = new UsuarioVO();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				usuario.setCodigo(rs.getInt("CODUSER"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método validarUsuario!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método validarUsuario");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return usuario;

	}

	public String buscarSenha(String email) {
		UsuarioVO usuario = null;
		String senha = "false";

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT SENHA FROM USUARIO WHERE EMAIL = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				usuario = new UsuarioVO();
//				usuario.setCodigo(rs.getInt("CODUSER"));
//				usuario.setNome(rs.getString("NOME"));
//				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSenha(rs.getString("SENHA"));

				senha = usuario.getSenha();

			} else if (!rs.isBeforeFirst()) {
				senha = "false";
			}

			System.out.println("Conferindo : " + senha);

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarSenha");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarSenha");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Senha: " + senha);
		return senha;
	}

}