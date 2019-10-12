package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import br.com.cadevoce.AI.CadeVoceAI;
import br.com.cadevoce.vo.FacesVO;

public class FacesDAO {

	// Grava a primeira foto no banco de dados
	public static int inserirFaces1(String url, int codDesap, String resultFileNamePath) {
		int idGerado = 0;
		
		//corta a url e concatena com uma transformação do cloudinary para detectar e fazer uma foto somente do rosto com 300x300
		
		String[] parts = url.split("/");
		String part1 = parts[0];
		String part2 = parts[2];
		String part3 = parts[3];
		String part4 = parts[4];
		String part5 = parts[5];
		String part6 = parts[6];
		String part7 = parts[7];
		String part8 = parts[8];
		String part9 = parts[9];
		
		String urlFaces_03 = part1 + "//" + part2 + "/" + part3 + "/" + part4 + "/" + part5 + "/c_thumb,w_300,h_300,g_face/" + part7 + "/" + part8+ "/" + part9;
		
		System.out.println("Faces_03: " + urlFaces_03);
		
		
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "INSERT INTO FACES (IDFACES, FACE_01, FACE_02, FACE_03) VALUES(?, ?, ?, ?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, codDesap);
			statement.setString(2, url);
			statement.setString(3, resultFileNamePath);
			statement.setString(4, urlFaces_03);

			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			updateIdFaces(codDesap);

			if (rs.next()) {
				idGerado = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirFaces1!");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método inserirFaces1");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idGerado;
	}

	// Método que atualiza o idFaces tabela desaparecido
	public static void updateIdFaces(int codDesap) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE DESAPARECIDO SET IDFACES = ?  WHERE CODDESAP = ?";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, codDesap);
			statement.setInt(2, codDesap);

			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método updateIdFaces!");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método updateIdFaces");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public static FacesVO[] listarFaces() {
		List<FacesVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM FACES";
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				FacesVO faces = new FacesVO();
				faces.setIdFaces(rs.getInt(1));
				faces.setPersonId(rs.getString(2));
				faces.setFace_01(rs.getString(3));
				faces.setFace_02(rs.getString(4));
				faces.setFace_03(rs.getString(5));

				lista.add(faces);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarFaces!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método listarFaces");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new FacesVO[lista.size()]);
	}

	public static FacesVO buscarFacesPorId(String idFaces) {
		FacesVO faces = null;
		String personId = "";
		String face_01 = "";
		String face_02 = "";

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT * FROM FACES WHERE IDFACES = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, idFaces);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				faces = new FacesVO();
//				faces.setPersonId(rs.getString(2));
//				faces.setFace_01(rs.getString(3));
//				faces.setFace_02(rs.getString(4));
//				faces.setFace_03(rs.getString(5));
				
				personId = rs.getString(2);
				face_01 = rs.getString(3);
				face_02 = rs.getString(4);

			}
			//Deletar dados -> Foto na nuvem e dados no azure
			apagaFotoCloudinary(face_02);
			CadeVoceAI.apagarPersonAzure(personId);
	
		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarFacesPorId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarFacesPorId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return faces;
	}

	public static void editarFaces(FacesVO faces, int idFaces) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE FACES SET FACE_01 = ?, FACE_02 = ?, FACE_03 = ? WHERE IDFACES = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, faces.getFace_01());
			statement.setString(2, faces.getFace_02());
			statement.setString(3, faces.getFace_03());
			statement.setInt(4, idFaces);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarFaces!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método editarFaces");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void removerFaces(String idFaces) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM FACES WHERE IDFACES = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, idFaces);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerFaces!");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método removerFaces");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public static String salvarPersonId(String personId, int codDesap) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE FACES SET PERSONID = ?  WHERE IDFACES = ?";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, personId);
			statement.setInt(2, codDesap);

			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método salvarPersonId!");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método salvarPersonId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return personId;
	}

	public static String personId(int codDesap) {
		String personIdRetorno = "";
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT PERSONID FROM FACES WHERE IDFACES = ?";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, codDesap);

			statement.execute();
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				FacesVO faces = new FacesVO();
				faces.setPersonId(rs.getString("PERSONID"));
				personIdRetorno = rs.getString("PERSONID");
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método personId!");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método personId");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return personIdRetorno;
	}
	
	
	public static void apagaFotoCloudinary(String path) {
		
		
		try {
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "cadevoce", "api_key",
					"393111438232317", "api_secret", "SaRNGEWKvUsDGPGwbpml6tH2Jso"));
			

			Api api = cloudinary.api();
			
			api.deleteResources(Arrays.asList(path), ObjectUtils.emptyMap());
			
			System.out.println("Foto armazenada no Cloudinary apagada com sucesso");

		} catch (Exception e) {
			String msg = "Erro ao tentar apagar a foto no Cloudinary!";
			e.printStackTrace();
		}

	}
	

}
