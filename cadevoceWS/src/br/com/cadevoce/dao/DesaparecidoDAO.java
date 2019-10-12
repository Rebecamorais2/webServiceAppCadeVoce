package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cadevoce.vo.DesaparecidoVO;

public class DesaparecidoDAO {

	DesaparecidoVO desaparecido = new DesaparecidoVO();

	public DesaparecidoVO[] listarDesaparecidos() {
		List<DesaparecidoVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT D.CODDESAP, D.NOME, D.IDADE, R.DESCRICAO, CO.DESCRICAO, CC.DESCRICAO, D.ALTURA, S.DESCRICAO, "
					+ "C.DESCRICAO, E.DESCRICAO, D.DATADESAPARECIMENTO, D.TELEFONECONTATO, D.DESCRICAO, D.DATACADASTRO, "
					+ "D.PARENTESCO, D.CODUSER, D.IDFACES, F.FACE_01, F.FACE_02, F.FACE_03, D.IDCIDADE,  D.IDRACA, "
					+ "D.IDCORCABELO, D.IDSEXO, D.IDCOROLHOS "
					+ "FROM DESAPARECIDO D, CIDADE C, COROLHOS CO, ESTADO E, RACADESAP R, CORCABELO CC, FACES F, SEXO S "
					+ "WHERE D.IDCIDADE = C.IDCIDADE " + "AND C.CODESTADO = E.CODESTADO "
					+ "AND D.IDCORCABELO = CC.IDCORCABELO " + "AND D.IDCOROLHOS = CO.IDCOROLHOS "
					+ "AND D.IDRACA = R.IDRACA " + "AND D.IDFACES = F.IDFACES " + "AND D.IDSEXO = S.IDSEXO";

			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				DesaparecidoVO desaparecido = new DesaparecidoVO();
				desaparecido.setCodDesap(rs.getInt("D.CODDESAP"));
				desaparecido.setNome(rs.getString("D.NOME"));
				desaparecido.setIdade(rs.getInt("D.IDADE"));
				desaparecido.setRacaDescricao(rs.getString("R.DESCRICAO"));
				desaparecido.setOlhosDescricao(rs.getString("CO.DESCRICAO"));
				desaparecido.setCabeloDescricao(rs.getString("CC.DESCRICAO"));
				desaparecido.setAltura(rs.getDouble("D.ALTURA"));
				desaparecido.setSexoDescricao(rs.getString("S.DESCRICAO"));
				desaparecido.setCidDescricao(rs.getString("C.DESCRICAO"));
				desaparecido.setEstDescricao(rs.getString("E.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql = rs.getString("D.DATADESAPARECIMENTO");
				String dia = datasql.substring(8);
				String mes = datasql.substring(5, 7);
				String ano = datasql.substring(0, 4);
				String datadesap = dia + "-" + mes + "-" + ano;

				desaparecido.setDataDesaparecimento(datadesap);

				desaparecido.setTelefoneContato(rs.getString("D.TELEFONECONTATO"));
				desaparecido.setDescricao(rs.getString("D.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql2 = rs.getString("D.DATACADASTRO");
				String dia2 = datasql2.substring(8, 10);
				String mes2 = datasql2.substring(5, 7);
				String ano2 = datasql2.substring(0, 4);
				String hora = datasql2.substring(11, 19);
				String datacadastroCompleta = dia2 + "-" + mes2 + "-" + ano2 + "-" + hora;
				String datacadastro = datacadastroCompleta.substring(0, 10);

				desaparecido.setDataCadastro(datacadastro);
				desaparecido.setParentesco(rs.getString("D.PARENTESCO"));
				desaparecido.setIdUsuario(rs.getInt("D.CODUSER"));
				desaparecido.setIdFaces(rs.getInt("D.IDFACES"));
				desaparecido.setFace_01(rs.getString("F.FACE_01"));
				desaparecido.setFace_02(rs.getString("F.FACE_02"));
				desaparecido.setFace_03(rs.getString("F.FACE_03"));
				desaparecido.setIdCidade(rs.getInt("D.IDCIDADE"));
				desaparecido.setIdRaca(rs.getInt("D.IDRACA"));
				desaparecido.setIdCorCabelo(rs.getInt("D.IDCORCABELO"));
				desaparecido.setIdSexo(rs.getInt("D.IDSEXO"));
				desaparecido.setIdCorOlhos(rs.getInt("D.IDCOROLHOS"));

				lista.add(desaparecido);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método listarDesaparecidos!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método listarDesaparecidos");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new DesaparecidoVO[lista.size()]);
	}

	public DesaparecidoVO[] buscarDesaparecidoPorNome(String nome, String estado, String cidade) {

		List<DesaparecidoVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT D.CODDESAP, D.NOME, D.IDADE, R.DESCRICAO, CO.DESCRICAO, CC.DESCRICAO, D.ALTURA, S.DESCRICAO, "
					+ "C.DESCRICAO, E.DESCRICAO, D.DATADESAPARECIMENTO, D.TELEFONECONTATO, D.DESCRICAO, D.DATACADASTRO, "
					+ "D.PARENTESCO, D.CODUSER, D.IDFACES, F.FACE_01, F.FACE_03, D.IDCIDADE, D.IDRACA, "
					+ "D.IDCORCABELO, D.IDSEXO, D.IDCOROLHOS "
					+ "FROM DESAPARECIDO D, CIDADE C, COROLHOS CO, ESTADO E, RACADESAP R, CORCABELO CC, FACES F, SEXO S "
					+ "WHERE D.IDCIDADE = C.IDCIDADE " + "AND C.CODESTADO = E.CODESTADO "
					+ "AND D.IDCORCABELO = CC.IDCORCABELO " + "AND D.IDCOROLHOS = CO.IDCOROLHOS "
					+ "AND D.IDRACA = R.IDRACA " + "AND D.IDFACES = F.IDFACES " + "AND D.IDSEXO = S.IDSEXO "
					+ "AND D.NOME LIKE ? " + "AND E.DESCRICAO = ? " + "AND C.DESCRICAO = ? ";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, "%"+nome+"%");
			statement.setString(2, estado);
			statement.setString(3, cidade);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				DesaparecidoVO desaparecido = new DesaparecidoVO();
				desaparecido.setCodDesap(rs.getInt("D.CODDESAP"));
				desaparecido.setNome(rs.getString("D.NOME")); 
				desaparecido.setIdade(rs.getInt("D.IDADE")); 
				desaparecido.setRacaDescricao(rs.getString("R.DESCRICAO"));
				desaparecido.setOlhosDescricao(rs.getString("CO.DESCRICAO"));
				desaparecido.setCabeloDescricao(rs.getString("CC.DESCRICAO"));
				desaparecido.setAltura(rs.getDouble("D.ALTURA"));
				desaparecido.setSexoDescricao(rs.getString("S.DESCRICAO"));
				desaparecido.setCidDescricao(rs.getString("C.DESCRICAO"));
				desaparecido.setEstDescricao(rs.getString("E.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql = rs.getString("D.DATADESAPARECIMENTO");
				String dia = datasql.substring(8);
				String mes = datasql.substring(5, 7);
				String ano = datasql.substring(0, 4);
				String datadesap = dia + "-" + mes + "-" + ano;

				desaparecido.setDataDesaparecimento(datadesap);
				desaparecido.setParentesco(rs.getString("D.PARENTESCO"));
				desaparecido.setIdUsuario(rs.getInt("D.CODUSER"));
				desaparecido.setTelefoneContato(rs.getString("D.TELEFONECONTATO"));
				desaparecido.setDescricao(rs.getString("D.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql2 = rs.getString("D.DATACADASTRO");
				String dia2 = datasql2.substring(8, 10);
				String mes2 = datasql2.substring(5, 7);
				String ano2 = datasql2.substring(0, 4);
				String hora = datasql2.substring(11, 19);
				String datacadastroCompleta = dia2 + "-" + mes2 + "-" + ano2 + "-" + hora;
				String datacadastro = datacadastroCompleta.substring(0, 10);

				desaparecido.setDataCadastro(datacadastro);

				desaparecido.setIdFaces(rs.getInt("D.IDFACES"));
				desaparecido.setFace_01(rs.getString("F.FACE_01"));
				desaparecido.setFace_03(rs.getString("F.FACE_03"));
				desaparecido.setIdCidade(rs.getInt("D.IDCIDADE"));
				desaparecido.setIdRaca(rs.getInt("D.IDRACA"));
				desaparecido.setIdCorCabelo(rs.getInt("D.IDCORCABELO"));
				desaparecido.setIdSexo(rs.getInt("D.IDSEXO"));
				desaparecido.setIdCorOlhos(rs.getInt("D.IDCOROLHOS"));

				lista.add(desaparecido);

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método buscarDesaparecidoPorNome!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método buscarDesaparecidoPorNome!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new DesaparecidoVO[lista.size()]);
	}

	public int inserirDesaparecido(DesaparecidoVO desaparecido) {
		int idGerado = 0;
		Connection conexao;

		int idRaca = racaId(desaparecido);
		int idCorOlhos = corDosOlhosId(desaparecido);
		int idCorCabelo = corCabeloId(desaparecido);
		int idCidade = cidadeId(desaparecido);
		int idSexo = sexoId(desaparecido);
		System.out.println(idSexo);

		try {
			conexao = BDConfig.getConnection();

			String sql = "INSERT INTO DESAPARECIDO (NOME, IDADE, ALTURA, IDSEXO, DATADESAPARECIMENTO, "
					+ "TELEFONECONTATO, DESCRICAO, IDCIDADE, IDCOROLHOS, IDRACA, "
					+ "IDCORCABELO, CODUSER, PARENTESCO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, desaparecido.getNome());
			statement.setInt(2, desaparecido.getIdade());
			statement.setDouble(3, desaparecido.getAltura());
			statement.setInt(4, idSexo);

			// ao inserir usar o formato dd-mm-aaaa
			String dia = desaparecido.getDataDesaparecimento().substring(0, 2);
			String mes = desaparecido.getDataDesaparecimento().substring(3, 5);
			String ano = desaparecido.getDataDesaparecimento().substring(6);
			String data = ano + "-" + mes + "-" + dia;

			statement.setString(5, data);
			statement.setString(6, desaparecido.getTelefoneContato());
			statement.setString(7, desaparecido.getDescricao());
			statement.setInt(8, idCidade);
			statement.setInt(9, idCorOlhos);
			statement.setInt(10, idRaca);
			statement.setInt(11, idCorCabelo);
			statement.setInt(12, desaparecido.getIdUsuario());
			statement.setString(13, desaparecido.getParentesco());

			statement.execute();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				idGerado = rs.getInt(1);
				String faceId = String.valueOf(idGerado);

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método inserirDesaparecido!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método inserirDesaparecido");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return idGerado;
	}

	public void editarDesaparecido(DesaparecidoVO desaparecido, int codDesap) {
		Connection conexao;
		
		int idRaca = racaId(desaparecido);
		int idCorOlhos = corDosOlhosId(desaparecido);
		int idCorCabelo = corCabeloId(desaparecido);
		int idCidade = cidadeId(desaparecido);
		int idSexo = sexoId(desaparecido);
		
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE DESAPARECIDO "
					+ "SET NOME = ?, IDADE = ?, IDRACA = ?, IDCOROLHOS = ?, "
					+ "IDCORCABELO = ?, ALTURA = ?, IDSEXO = ?, IDCIDADE = ?, "
					+ "DATADESAPARECIMENTO = ?, TELEFONECONTATO= ?, PARENTESCO = ?, DESCRICAO = ? "
					+ "WHERE CODDESAP = ? ";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, desaparecido.getNome());
			statement.setInt(2, desaparecido.getIdade());
			statement.setInt(3, idRaca);
			statement.setInt(4, idCorOlhos);
			statement.setInt(5, idCorCabelo);
			statement.setDouble(6, desaparecido.getAltura());
			statement.setInt(7, idSexo);
			statement.setInt(8, idCidade);

			// ao inserir usar o formato dd-mm-aaaa
			String dia = desaparecido.getDataDesaparecimento().substring(0, 2);
			String mes = desaparecido.getDataDesaparecimento().substring(3, 5);
			String ano = desaparecido.getDataDesaparecimento().substring(6);
			String data = ano + "-" + mes + "-" + dia;

			statement.setString(9, data);

			statement.setString(10, desaparecido.getTelefoneContato());
			statement.setString(11, desaparecido.getParentesco());
			statement.setString(12, desaparecido.getDescricao());
			statement.setInt(13, codDesap);
			statement.execute();

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método editarDesaparecido!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método editarDesaparecido!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// Remove o cadastro de um desaparecido passando o código do desaparecido como
	// parametro
	public static void removerDesaparecido(int codDesap) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "DELETE FROM DESAPARECIDO WHERE CODDESAP = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codDesap);
			statement.execute();
			//removerFotos

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método removerDesaparecido!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método removerDesaparecido");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public DesaparecidoVO[] exibirCadastrosRecentes() {
		List<DesaparecidoVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT D.NOME, D.CODDESAP, D.IDADE, R.DESCRICAO, CO.DESCRICAO, CC.DESCRICAO, D.ALTURA, S.DESCRICAO, "
					+ "C.DESCRICAO, E.DESCRICAO, D.DATADESAPARECIMENTO, D.TELEFONECONTATO, D.DESCRICAO, D.DATACADASTRO, "
					+ "D.PARENTESCO, D.CODUSER, D.IDFACES, F.FACE_01, F.FACE_02, F.FACE_03, D.IDCIDADE, D.IDRACA, "
					+ "D.IDCORCABELO, D.IDSEXO, D.IDCOROLHOS "
					+ "FROM DESAPARECIDO D, CIDADE C, COROLHOS CO, ESTADO E, RACADESAP R, CORCABELO CC, FACES F, SEXO S "
					+ "WHERE D.IDCIDADE = C.IDCIDADE " + "AND C.CODESTADO = E.CODESTADO "
					+ "AND D.IDCORCABELO = CC.IDCORCABELO " + "AND D.IDCOROLHOS = CO.IDCOROLHOS "
					+ "AND D.IDRACA = R.IDRACA " + "AND D.IDFACES = F.IDFACES " + "AND D.IDSEXO = S.IDSEXO "
					+ "AND D.DATACADASTRO BETWEEN DATE_ADD(CURDATE(), INTERVAL -1 MONTH) AND CURDATE()+1 "
					+ "ORDER BY D.DATACADASTRO DESC"; 

			// O SELECT ACIMA PEGA O PERÍODO ENTRE A DATA ATUAL E UM MÊS ANTES

			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				DesaparecidoVO desaparecido = new DesaparecidoVO();
				desaparecido.setCodDesap(rs.getInt("D.CODDESAP"));
				desaparecido.setNome(rs.getString("D.NOME"));
				desaparecido.setIdade(rs.getInt("D.IDADE"));
				desaparecido.setRacaDescricao(rs.getString("R.DESCRICAO"));
				desaparecido.setOlhosDescricao(rs.getString("CO.DESCRICAO"));
				desaparecido.setCabeloDescricao(rs.getString("CC.DESCRICAO"));
				desaparecido.setAltura(rs.getDouble("D.ALTURA"));
				desaparecido.setSexoDescricao(rs.getString("S.DESCRICAO"));
				desaparecido.setCidDescricao(rs.getString("C.DESCRICAO"));
				desaparecido.setEstDescricao(rs.getString("E.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql = rs.getString("D.DATADESAPARECIMENTO");
				String dia = datasql.substring(8);
				String mes = datasql.substring(5, 7);
				String ano = datasql.substring(0, 4);
				String datadesap = dia + "-" + mes + "-" + ano;

				desaparecido.setDataDesaparecimento(datadesap);
				desaparecido.setParentesco(rs.getString("D.PARENTESCO"));
				desaparecido.setIdUsuario(rs.getInt("D.CODUSER"));
				desaparecido.setTelefoneContato(rs.getString("D.TELEFONECONTATO"));
				desaparecido.setDescricao(rs.getString("D.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql2 = rs.getString("D.DATACADASTRO");
				String dia2 = datasql2.substring(8, 10);
				String mes2 = datasql2.substring(5, 7);
				String ano2 = datasql2.substring(0, 4);
				String hora = datasql2.substring(11, 19);
				String datacadastroCompleta = dia2 + "-" + mes2 + "-" + ano2 + "-" + hora;
				String datacadastro = datacadastroCompleta.substring(0, 10);

				desaparecido.setDataCadastro(datacadastro);

				desaparecido.setIdFaces(rs.getInt("D.IDFACES"));
				desaparecido.setFace_01(rs.getString("F.FACE_01"));
				desaparecido.setFace_02(rs.getString("F.FACE_02"));
				desaparecido.setFace_03(rs.getString("F.FACE_03"));
				desaparecido.setIdCidade(rs.getInt("D.IDCIDADE"));
				desaparecido.setIdRaca(rs.getInt("D.IDRACA"));
				desaparecido.setIdCorCabelo(rs.getInt("D.IDCORCABELO"));
				desaparecido.setIdSexo(rs.getInt("D.IDSEXO"));
				desaparecido.setIdCorOlhos(rs.getInt("D.IDCOROLHOS"));

				lista.add(desaparecido);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método exibirCadastrosRecentes!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método exibirCadastrosRecentes");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new DesaparecidoVO[lista.size()]);

	}

	// Lista os desaparecidos cadastrados por um usuário passando o código do
	// usuário como parametro
	public DesaparecidoVO[] meusCadastros(int codUser) {
		List<DesaparecidoVO> lista = new ArrayList<>();

		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "SELECT D.NOME, D.CODDESAP, D.IDADE, R.DESCRICAO, CO.DESCRICAO, CC.DESCRICAO, D.ALTURA, S.DESCRICAO, "
					+ "C.DESCRICAO, E.DESCRICAO, D.DATADESAPARECIMENTO, D.TELEFONECONTATO, D.DESCRICAO, D.DATACADASTRO, "
					+ "D.PARENTESCO, D.CODUSER, D.IDFACES, F.FACE_01, F.FACE_02, F.FACE_03, D.IDCIDADE, D.IDRACA, "
					+ "D.IDCORCABELO, D.IDSEXO, D.IDCOROLHOS "
					+ "FROM DESAPARECIDO D, CIDADE C, COROLHOS CO, ESTADO E, RACADESAP R, CORCABELO CC, FACES F, SEXO S "
					+ "WHERE D.IDCIDADE = C.IDCIDADE " + "AND C.CODESTADO = E.CODESTADO "
					+ "AND D.IDCORCABELO = CC.IDCORCABELO " + "AND D.IDCOROLHOS = CO.IDCOROLHOS "
					+ "AND D.IDRACA = R.IDRACA " + "AND D.IDFACES = F.IDFACES " + "AND D.IDSEXO = S.IDSEXO "
					+ "AND D.CODUSER = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, codUser);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				DesaparecidoVO desaparecido = new DesaparecidoVO();
				desaparecido.setCodDesap(rs.getInt("D.CODDESAP"));
				desaparecido.setNome(rs.getString("D.NOME"));
				desaparecido.setIdade(rs.getInt("D.IDADE"));
				desaparecido.setRacaDescricao(rs.getString("R.DESCRICAO"));
				desaparecido.setOlhosDescricao(rs.getString("CO.DESCRICAO"));
				desaparecido.setCabeloDescricao(rs.getString("CC.DESCRICAO"));
				desaparecido.setAltura(rs.getDouble("D.ALTURA"));
				desaparecido.setSexoDescricao(rs.getString("S.DESCRICAO"));
				desaparecido.setCidDescricao(rs.getString("C.DESCRICAO"));
				desaparecido.setEstDescricao(rs.getString("E.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql = rs.getString("D.DATADESAPARECIMENTO");
				String dia = datasql.substring(8);
				String mes = datasql.substring(5, 7);
				String ano = datasql.substring(0, 4);
				String datadesap = dia + "-" + mes + "-" + ano;

				desaparecido.setDataDesaparecimento(datadesap);
				desaparecido.setParentesco(rs.getString("D.PARENTESCO"));
				desaparecido.setIdUsuario(rs.getInt("D.CODUSER"));
				desaparecido.setTelefoneContato(rs.getString("D.TELEFONECONTATO"));
				desaparecido.setDescricao(rs.getString("D.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql2 = rs.getString("D.DATACADASTRO");
				String dia2 = datasql2.substring(8, 10);
				String mes2 = datasql2.substring(5, 7);
				String ano2 = datasql2.substring(0, 4);
				String hora = datasql2.substring(11, 19);
				String datacadastroCompleta = dia2 + "-" + mes2 + "-" + ano2 + "-" + hora;
				String datacadastro = datacadastroCompleta.substring(0, 10);

				desaparecido.setDataCadastro(datacadastro);

				desaparecido.setIdFaces(rs.getInt("D.IDFACES"));
				desaparecido.setFace_01(rs.getString("F.FACE_01"));
				desaparecido.setFace_02(rs.getString("F.FACE_02"));
				desaparecido.setFace_03(rs.getString("F.FACE_03"));
				desaparecido.setIdCidade(rs.getInt("D.IDCIDADE"));
				desaparecido.setIdRaca(rs.getInt("D.IDRACA"));
				desaparecido.setIdCorCabelo(rs.getInt("D.IDCORCABELO"));
				desaparecido.setIdSexo(rs.getInt("D.IDSEXO"));
				desaparecido.setIdCorOlhos(rs.getInt("D.IDCOROLHOS"));

				lista.add(desaparecido);
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método meusCadastros!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados -  método meusCadastros");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new DesaparecidoVO[lista.size()]);

	}

	public DesaparecidoVO[] resultadoBuscarPorFoto(String personId) {

		List<DesaparecidoVO> lista = new ArrayList<>();

		Connection conexao;
		try {

			System.out
					.println("Executando o método resultadoBuscarPorFoto, abrindo conexão com o BD passando o personId "
							+ personId);

			conexao = BDConfig.getConnection();
			String sql = "SELECT D.NOME, D.CODDESAP, D.IDADE, R.DESCRICAO, CO.DESCRICAO, CC.DESCRICAO, D.ALTURA, S.DESCRICAO, "
					+ "C.DESCRICAO, E.DESCRICAO, D.DATADESAPARECIMENTO, D.TELEFONECONTATO, D.DESCRICAO, D.DATACADASTRO, "
					+ "D.PARENTESCO, D.CODUSER, D.IDFACES, F.FACE_01, F.FACE_02, F.FACE_03, D.IDCIDADE, D.IDRACA, "
					+ "D.IDCORCABELO, D.IDSEXO, D.IDCOROLHOS "
					+ "FROM DESAPARECIDO D, CIDADE C, COROLHOS CO, ESTADO E, RACADESAP R, CORCABELO CC, FACES F, SEXO S "
					+ "WHERE D.IDCIDADE = C.IDCIDADE " + "AND C.CODESTADO = E.CODESTADO "
					+ "AND D.IDCORCABELO = CC.IDCORCABELO " + "AND D.IDCOROLHOS = CO.IDCOROLHOS "
					+ "AND D.IDRACA = R.IDRACA " + "AND D.IDFACES = F.IDFACES " + "AND D.IDSEXO = S.IDSEXO "
					+ "AND F.PERSONID = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, personId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				DesaparecidoVO desaparecido = new DesaparecidoVO();

				System.out.println("Cadastro encontrado:  " + rs.getString("D.NOME"));

				desaparecido.setNome(rs.getString("D.NOME"));
				desaparecido.setCodDesap(rs.getInt("D.CODDESAP"));
				desaparecido.setIdade(rs.getInt("D.IDADE"));
				desaparecido.setRacaDescricao(rs.getString("R.DESCRICAO"));
				desaparecido.setOlhosDescricao(rs.getString("CO.DESCRICAO"));
				desaparecido.setCabeloDescricao(rs.getString("CC.DESCRICAO"));
				desaparecido.setAltura(rs.getDouble("D.ALTURA"));
				desaparecido.setSexoDescricao(rs.getString("S.DESCRICAO"));
				desaparecido.setCidDescricao(rs.getString("C.DESCRICAO"));
				desaparecido.setEstDescricao(rs.getString("E.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql = rs.getString("D.DATADESAPARECIMENTO");
				String dia = datasql.substring(8);
				String mes = datasql.substring(5, 7);
				String ano = datasql.substring(0, 4);
				String datadesap = dia + "-" + mes + "-" + ano;

				desaparecido.setDataDesaparecimento(datadesap);
				desaparecido.setParentesco(rs.getString("D.PARENTESCO"));
				desaparecido.setIdUsuario(rs.getInt("D.CODUSER"));
				desaparecido.setTelefoneContato(rs.getString("D.TELEFONECONTATO"));
				desaparecido.setDescricao(rs.getString("D.DESCRICAO"));

				// para listar no formato dd-mm-aaaa
				String datasql2 = rs.getString("D.DATACADASTRO");
				String dia2 = datasql2.substring(8, 10);
				String mes2 = datasql2.substring(5, 7);
				String ano2 = datasql2.substring(0, 4);
				String hora = datasql2.substring(11, 19);
				String datacadastroCompleta = dia2 + "-" + mes2 + "-" + ano2 + "-" + hora;
				String datacadastro = datacadastroCompleta.substring(0, 10);

				desaparecido.setDataCadastro(datacadastro);

				desaparecido.setIdFaces(rs.getInt("D.IDFACES"));
				desaparecido.setFace_01(rs.getString("F.FACE_01"));
				desaparecido.setFace_02(rs.getString("F.FACE_02"));
				desaparecido.setFace_03(rs.getString("F.FACE_03"));
				desaparecido.setIdCidade(rs.getInt("D.IDCIDADE"));
				desaparecido.setIdRaca(rs.getInt("D.IDRACA"));
				desaparecido.setIdCorCabelo(rs.getInt("D.IDCORCABELO"));
				desaparecido.setIdSexo(rs.getInt("D.IDSEXO"));
				desaparecido.setIdCorOlhos(rs.getInt("D.IDCOROLHOS"));

				lista.add(desaparecido);

			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método resultadoBuscarPorFoto!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método resultadoBuscarPorFoto!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return lista.toArray(new DesaparecidoVO[lista.size()]);

	}

	// insere o idFaces na tabela desaparecido após inserido foto na tabela face
	public void atualizaFaceId(String FaceId) {
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();
			String sql = "UPDATE DESAPARECIDO (IDFACES) VALUES (\"" + FaceId + "\")";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, desaparecido.getIdFaces());
		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método atualizaFaceId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método atualizaFaceId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	// A partir daqui as funções estão gerando ids das informações vindas como
	// string pelos spinners
	// Recupera o id da raca selecionada
	public int racaId(DesaparecidoVO desaparecido) {
		int racaId = 0;
		String raca = desaparecido.getRacaDescricao();
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT IDRACA FROM RACADESAP WHERE DESCRICAO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, raca);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				desaparecido.setIdRaca(rs.getInt("IDRACA"));

				racaId = desaparecido.getIdRaca();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método racaId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método racaId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return racaId;
	}

	public int corDosOlhosId(DesaparecidoVO desaparecido) {
		int idOlhos = 0;
		String olhosDescricao = desaparecido.getOlhosDescricao();
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT IDCOROLHOS FROM COROLHOS WHERE DESCRICAO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, olhosDescricao);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				desaparecido.setIdCorOlhos(rs.getInt("IDCOROLHOS"));

				idOlhos = desaparecido.getIdCorOlhos();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método corDosOlhosId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método corDosOlhosId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idOlhos;
	}

	public int corCabeloId(DesaparecidoVO desaparecido) {
		int idcorCabelo = 0;
		String cabeloDescricao = desaparecido.getCabeloDescricao();
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT IDCORCABELO FROM CORCABELO WHERE DESCRICAO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, cabeloDescricao);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				desaparecido.setIdCorCabelo(rs.getInt("IDCORCABELO"));

				idcorCabelo = desaparecido.getIdCorCabelo();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método corCabeloId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método corCabeloId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idcorCabelo;
	}

	public int cidadeId(DesaparecidoVO desaparecido) {
		int idCidade = 0;
		String cidDescricao = desaparecido.getCidDescricao();
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT IDCIDADE FROM CIDADE WHERE DESCRICAO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, cidDescricao);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				desaparecido.setIdCidade(rs.getInt("IDCIDADE"));

				idCidade = desaparecido.getIdCidade();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método cidadeId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método cidadeId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idCidade;
	}

	public int sexoId(DesaparecidoVO desaparecido) {
		int idSexo = 0;
		String sexoDescricao = desaparecido.getSexoDescricao();
		Connection conexao;
		try {
			conexao = BDConfig.getConnection();

			String sql = "SELECT IDSEXO FROM SEXO WHERE DESCRICAO = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, sexoDescricao);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				desaparecido.setIdSexo(rs.getInt("IDSEXO"));

				idSexo = desaparecido.getIdSexo();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("Erro no método sexoId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erro no Banco de dados - método sexoId!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return idSexo;
	}

}