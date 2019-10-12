package br.com.cadevoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;

import br.com.cadevoce.vo.EstadosCidadesVO;

public class EstadosCidadesDAO {

	JSONObject jsonObj = new JSONObject();

	JSONArray cidade = new JSONArray();
	JSONArray cidade2 = new JSONArray();
	JSONArray cidade3 = new JSONArray();
	JSONArray cidade4 = new JSONArray();
	JSONArray cidade5 = new JSONArray();
	JSONArray cidade6 = new JSONArray();
	JSONArray cidade7 = new JSONArray();
	JSONArray cidade8 = new JSONArray();
	JSONArray cidade9 = new JSONArray();
	JSONArray cidade10 = new JSONArray();
	JSONArray cidade11 = new JSONArray();
	JSONArray cidade12 = new JSONArray();
	JSONArray cidade13 = new JSONArray();
	JSONArray cidade14 = new JSONArray();
	JSONArray cidade15 = new JSONArray();
	JSONArray cidade16 = new JSONArray();
	JSONArray cidade17 = new JSONArray();
	JSONArray cidade18 = new JSONArray();
	JSONArray cidade19 = new JSONArray();
	JSONArray cidade20 = new JSONArray();
	JSONArray cidade21 = new JSONArray();
	JSONArray cidade22 = new JSONArray();
	JSONArray cidade23 = new JSONArray();
	JSONArray cidade24 = new JSONArray();
	JSONArray cidade25 = new JSONArray();
	JSONArray cidade26 = new JSONArray();
	JSONArray cidade27 = new JSONArray();

	String estAcre;
	String estAlagoas;
	String estAmapa;
	String estAmazonas;
	String estBahia;
	String estCeara;
	String estDistritoFederal;
	String estEspiritoSanto;
	String estGoias;
	String estMaranhao;
	String estMatoGrosso;
	String estMatoGrossoSul;
	String estMinasGerais;
	String estPara;
	String estParaiba;
	String estParana;
	String estPernambuco;
	String estPiaui;
	String estRiodeJaneiro;
	String estRioGrandeNorte;
	String estRioGrandeSul;
	String estRondonia;
	String estRoraima;
	String estSantaCatarina;
	String estSaoPaulo;
	String estSergipe;
	String estTocantins;

	JSONArray principal = new JSONArray();
	JSONObject estados = new JSONObject();

	// Cidades do Acre
	public String acCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 1";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade.put(estadosCidades.getCidade());

		}
		String cidAc = cidade.toString();
		return cidAc;

	}

	// Cidades de Alagoas
	public String alCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 2";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade2.put(estadosCidades.getCidade());

		}
		String cidAl = cidade2.toString();
		return cidAl;

	}

	// Cidades de Amapá
	public String apCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 3";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade3.put(estadosCidades.getCidade());

		}
		String cidAp = cidade3.toString();
		return cidAp;

	}

	// Cidades de Amazonas
	public String amCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 4";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade4.put(estadosCidades.getCidade());

		}
		String cidAm = cidade4.toString();
		return cidAm;

	}

	// Cidades de Bahia
	public String baCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 5";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade5.put(estadosCidades.getCidade());

		}
		String cidBa = cidade5.toString();
		return cidBa;

	}

	// Cidades de Ceara
	public String ceCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 6";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade6.put(estadosCidades.getCidade());

		}
		String cidCe = cidade6.toString();
		return cidCe;

	}

	// Cidades de Destrito Federal
	public String dfCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 7";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade7.put(estadosCidades.getCidade());

		}
		String cidDf = cidade7.toString();
		return cidDf;

	}

	// Cidades de Espirito Santo
	public String esCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 8";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade8.put(estadosCidades.getCidade());

		}
		String cidEs = cidade8.toString();
		return cidEs;

	}

	// Cidades de Goias
	public String goCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 9";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade9.put(estadosCidades.getCidade());

		}
		String cidGo = cidade9.toString();
		return cidGo;

	}

	// Cidades de Maranhao
	public String maCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 10";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade10.put(estadosCidades.getCidade());

		}
		String cidMa = cidade10.toString();
		return cidMa;

	}

	// Cidades de Mato Grosso
	public String mtCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 11";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade11.put(estadosCidades.getCidade());

		}
		String cidMt = cidade11.toString();
		return cidMt;

	}

	// Cidades de Mato Grosso do Sul
	public String msCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 12";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade12.put(estadosCidades.getCidade());

		}
		String cidMs = cidade12.toString();
		return cidMs;

	}

	// Cidades de Minas Gerais
	public String mgCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 13";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade13.put(estadosCidades.getCidade());

		}
		String cidMg = cidade13.toString();
		return cidMg;

	}

	// Cidades de Pará
	public String paCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 14";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade14.put(estadosCidades.getCidade());

		}
		String cidPa = cidade14.toString();
		return cidPa;

	}

	// Cidades de Paraiba
	public String pbCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 15";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade15.put(estadosCidades.getCidade());

		}
		String cidPb = cidade15.toString();
		return cidPb;

	}

	// Cidades de Parana
	public String prCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 16";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade16.put(estadosCidades.getCidade());

		}
		String cidPr = cidade16.toString();
		return cidPr;

	}

	// Cidades de Pernambuco
	public String peCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 17";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade17.put(estadosCidades.getCidade());

		}
		String cidPe = cidade17.toString();
		return cidPe;

	}

	// Cidades de Piaui
	public String piCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 18";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade18.put(estadosCidades.getCidade());

		}
		String cidPi = cidade18.toString();
		return cidPi;

	}

	// Cidades de RJ
	public String rjCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 19";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade19.put(estadosCidades.getCidade());

		}
		String cidRj = cidade19.toString();
		return cidRj;

	}

	// Cidades de Rio Grande do Norte
	public String rnCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 20";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade20.put(estadosCidades.getCidade());

		}
		String cidRn = cidade20.toString();
		return cidRn;

	}

	// Cidades de Rio Grande do Sul
	public String rsCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 21";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade21.put(estadosCidades.getCidade());

		}
		String cidRs = cidade21.toString();
		return cidRs;

	}

	// Cidades de Rondonia
	public String roCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 22";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade22.put(estadosCidades.getCidade());

		}
		String cidRo = cidade22.toString();
		return cidRo;

	}

	// Cidades de Roraima
	public String rrCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 23";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade23.put(estadosCidades.getCidade());

		}
		String cidRr = cidade23.toString();
		return cidRr;

	}

	// Cidades de Santa Catarina
	public String scCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 24";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade24.put(estadosCidades.getCidade());

		}
		String cidSc = cidade24.toString();
		return cidSc;

	}

	// Cidades de Sao Paulo
	public String spCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 25";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade25.put(estadosCidades.getCidade());

		}
		String cidSp = cidade25.toString();
		return cidSp;

	}

	// Cidades de Sergipe
	public String seCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 26";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade26.put(estadosCidades.getCidade());

		}
		String cidSe = cidade26.toString();
		return cidSe;

	}

	// Cidades de Tocantins
	public String toCidades() throws Exception {
		List<EstadosCidadesVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from cidade where codestado = 27";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setCidade(rs.getString("descricao"));

			lista.add(estadosCidades);
			cidade27.put(estadosCidades.getCidade());

		}
		String cidTo = cidade27.toString();
		return cidTo;

	}

	// Estados - Acre
	public String acEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 1";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estAcre = estadosCidades.getEstado();
		}
		return estAcre;
	}

	// Estados - Alagoas
	public String alEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 2";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estAlagoas = estadosCidades.getEstado();
		}
		return estAlagoas;
	}

	// Estados - Amapá
	public String apEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 3";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estAmapa = estadosCidades.getEstado();
		}
		return estAmapa;
	}

	// Estados - Amazonas
	public String amEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 4";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estAmazonas = estadosCidades.getEstado();
		}
		return estAmazonas;
	}

	// Estados - Bahia
	public String baEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 5";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estBahia = estadosCidades.getEstado();
		}
		return estBahia;
	}

	// Estados - Ceará
	public String ceEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 6";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estCeara = estadosCidades.getEstado();
		}
		return estCeara;
	}

	// Estados - Distrito Federal
	public String dfEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 7";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estDistritoFederal = estadosCidades.getEstado();
		}
		return estDistritoFederal;
	}

	// Estados - Espirito Santo
	public String esEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 8";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estEspiritoSanto = estadosCidades.getEstado();
		}
		return estEspiritoSanto;
	}

	// Estados - Goias
	public String goEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 9";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estGoias = estadosCidades.getEstado();
		}
		return estGoias;
	}

	// Estados - Maranhão
	public String maEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 10";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estMaranhao = estadosCidades.getEstado();
		}
		return estMaranhao;
	}

	// Estados - Mato Grosso
	public String mtEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 11";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estMatoGrosso = estadosCidades.getEstado();
		}
		return estMatoGrosso;
	}

	// Estados - Mato Grosso do Sul
	public String msEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 12";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estMatoGrossoSul = estadosCidades.getEstado();
		}
		return estMatoGrossoSul;
	}

	// Estados - Minas Gerais
	public String mgEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 13";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estMinasGerais = estadosCidades.getEstado();
		}
		return estMinasGerais;
	}

	// Estados - Pará
	public String paEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 14";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estPara = estadosCidades.getEstado();
		}
		return estPara;
	}

	// Estados - Paraiba
	public String pbEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 15";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estParaiba = estadosCidades.getEstado();
		}
		return estParaiba;
	}

	// Estados - Paraná
	public String prEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 16";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estParana = estadosCidades.getEstado();
		}
		return estParana;
	}

	// Estados - Pernambuco
	public String peEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 17";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estPernambuco = estadosCidades.getEstado();
		}
		return estPernambuco;
	}

	// Estados - Piauí
	public String piEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 18";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estPiaui = estadosCidades.getEstado();
		}
		return estPiaui;
	}

	// Estados - Rio de Janeiro
	public String rjEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 19";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estRiodeJaneiro = estadosCidades.getEstado();
		}
		return estRiodeJaneiro;
	}

	// Estados - Rio Grande do Norte
	public String rnEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 20";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estRioGrandeNorte = estadosCidades.getEstado();
		}
		return estRioGrandeNorte;
	}

	// Estados - Rio Grande do Sul
	public String rsEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 21";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estRioGrandeSul = estadosCidades.getEstado();
		}
		return estRioGrandeSul;
	}

	// Estados - Rondonia
	public String rdEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 22";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estRondonia = estadosCidades.getEstado();
		}
		return estRondonia;
	}

	// Estados - Roraima
	public String rrEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 23";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estRoraima = estadosCidades.getEstado();
		}
		return estRoraima;
	}

	// Estados - Santa Catarina
	public String scEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 24";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estSantaCatarina = estadosCidades.getEstado();
		}
		return estSantaCatarina;
	}

	// Estados - São Paulo
	public String spEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 25";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estSaoPaulo = estadosCidades.getEstado();
		}
		return estSaoPaulo;
	}

	// Estados - Sergipe
	public String seEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 26";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estSergipe = estadosCidades.getEstado();
		}
		return estSergipe;
	}

	// Estados - Tocantins
	public String toEstado() throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "select descricao from estado where codestado = 27";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			EstadosCidadesVO estadosCidades = new EstadosCidadesVO();

			estadosCidades.setEstado(rs.getString("descricao"));
			estTocantins = estadosCidades.getEstado();
		}
		return estTocantins;
	}

	public String listarCidadesEstados() throws Exception {

		EstadosCidadesDAO ec = new EstadosCidadesDAO();

		// cidades
		String cidAcre = ec.acCidades();
//		  System.out.println(cidAcre);

		String cidAlagoas = ec.alCidades();
//		  System.out.println(cidAlagoas);

		String cidAmapa = ec.apCidades();
//		  System.out.println(cidAmapa);

		String cidAmazonas = ec.amCidades();
//		  System.out.println(cidAmazonas);

		String cidBahia = ec.baCidades();
//		  System.out.println(cidBahia);

		String cidCeara = ec.ceCidades();
//		  System.out.println(cidCeara);

		String cidDistritoFederal = ec.dfCidades();
//		  System.out.println(cidDistritoFederal);

		String cidEspiritoSanto = ec.esCidades();
//		  System.out.println(cidEspiritoSanto);

		String cidGoias = ec.goCidades();
//		  System.out.println(cidGoias);	

		String cidMaranhao = ec.maCidades();
//		  System.out.println(cidMaranhao);	

		String cidMatoGrosso = ec.mtCidades();
//		  System.out.println(cidMatoGrosso);	

		String cidMatoGrossoSul = ec.msCidades();
//		  System.out.println(cidMatoGrossoSul);	

		String cidMinasGerais = ec.mgCidades();
//		  System.out.println(cidMinasGerais);	

		String cidPara = ec.paCidades();
//		  System.out.println(cidPara);	

		String cidParaiba = ec.pbCidades();
//		  System.out.println(cidParaiba);

		String cidParana = ec.prCidades();
//		  System.out.println(cidParana);  

		String cidPernambuco = ec.peCidades();
//		  System.out.println(cidPernambuco); 

		String cidPiaui = ec.piCidades();
//		  System.out.println(cidPiaui); 

		String cidRiodeJaneiro = ec.rjCidades();
//		  System.out.println(cidRiodeJaneiro); 

		String cidRioGrandeNorte = ec.rnCidades();
//		  System.out.println(cidRioGrandedoNorte); 

		String cidRioGrandeSul = ec.rsCidades();
//		  System.out.println(cidRioGrandedoSul);

		String cidRondonia = ec.roCidades();
//		  System.out.println(cidRondonia); 

		String cidRoraima = ec.rrCidades();
//		  System.out.println(cidRoraima); 

		String cidSantaCatarina = ec.scCidades();
//		  System.out.println(cidSantaCatarina); 

		String cidSaoPaulo = ec.spCidades();
//		  System.out.println(cidSaoPaulo); 

		String cidSergipe = ec.seCidades();
//		  System.out.println(cidSergipe); 

		String cidTocantins = ec.toCidades();
//		  System.out.println(cidTocantins); 

		// estados
		String estAcre = ec.acEstado();
//		  System.out.println(estAcre);

		String estAlagoas = ec.alEstado();
//		  System.out.println(estAlagoas);

		String estAmapa = ec.apEstado();
//		  System.out.println(estAmapa);

		String estAmazonas = ec.amEstado();
//		  System.out.println(estAmazonas);

		String estBahia = ec.baEstado();
//		  System.out.println(estBahia);

		String estCeara = ec.ceEstado();
//		  System.out.println(estCeara);

		String estDistritoFederal = ec.dfEstado();
//		  System.out.println(estDistritoFederal);

		String estEspiritoSanto = ec.esEstado();
//		  System.out.println(estEspiritoSanto);

		String estGoias = ec.goEstado();
//		  System.out.println(estGoias);

		String estMaranhao = ec.maEstado();
//		  System.out.println(estMaranhao);

		String estMatoGrosso = ec.mtEstado();
//		  System.out.println(estMatoGrosso);

		String estMatoGrossoSul = ec.msEstado();
//		  System.out.println(estMatoGrossoSul);

		String estMinasGerais = ec.mgEstado();
//		  System.out.println(estMinasGerais);

		String estPara = ec.paEstado();
//		  System.out.println(estPara);

		String estParaiba = ec.pbEstado();
//		  System.out.println(estParaiba);

		String estParana = ec.prEstado();
//		  System.out.println(estParana);

		String estPernambuco = ec.peEstado();
//		  System.out.println(estPernambuco);

		String estPiaui = ec.piEstado();
//		  System.out.println(estPiaui);

		String estRiodeJaneiro = ec.rjEstado();
//		  System.out.println(estRiodeJaneiro);

		String estRioGrandeNorte = ec.rnEstado();
//		  System.out.println(estRioGrandeNorte);

		String estRioGrandeSul = ec.rsEstado();
//		  System.out.println(estRioGrandeSul);

		String estRondonia = ec.rdEstado();
//		  System.out.println(estRondonia);

		String estRoraima = ec.rrEstado();
//		  System.out.println(estRoraima);

		String estSantaCatarina = ec.scEstado();
//		  System.out.println(estSantaCatarina);

		String estSaoPaulo = ec.spEstado();
//		  System.out.println(estSaoPaulo);

		String estSergipe = ec.seEstado();
//		  System.out.println(estSergipe);

		String estTocantins = ec.toEstado();
//		  System.out.println(estTocantins);

		String json = "[{\"estado\":\"" + estAcre + "\"" + ",\"cidades\":" + cidAcre + "},{\"estado\":\"" + estAlagoas
				+ "\"" + ",\"cidades\":" + cidAlagoas + "}," + "{\"estado\":\"" + estAmapa + "\"" + ",\"cidades\":"
				+ cidAmapa + "},{\"estado\":\"" + estAmazonas + "\"" + ",\"cidades\":" + cidAmazonas + "},"
				+ "{\"estado\":\"" + estBahia + "\"" + ",\"cidades\":" + cidBahia + "},{\"estado\":\"" + estCeara + "\""
				+ ",\"cidades\":" + cidCeara + "}," + "{\"estado\":\"" + estDistritoFederal + "\"" + ",\"cidades\":"
				+ cidDistritoFederal + "},{\"estado\":\"" + estEspiritoSanto + "\"" + ",\"cidades\":" + cidEspiritoSanto
				+ "}," + "{\"estado\":\"" + estGoias + "\"" + ",\"cidades\":" + cidGoias + "},{\"estado\":\""
				+ estMaranhao + "\"" + ",\"cidades\":" + cidMaranhao + "}," + "{\"estado\":\"" + estMatoGrosso + "\""
				+ ",\"cidades\":" + cidMatoGrosso + "},{\"estado\":\"" + estMatoGrossoSul + "\"" + ",\"cidades\":"
				+ cidMatoGrossoSul + "}," + "{\"estado\":\"" + estMinasGerais + "\"" + ",\"cidades\":" + cidMinasGerais
				+ "},{\"estado\":\"" + estPara + "\"" + ",\"cidades\":" + cidPara + "}," + "{\"estado\":\"" + estParaiba
				+ "\"" + ",\"cidades\":" + cidParaiba + "},{\"estado\":\"" + estParana + "\"" + ",\"cidades\":"
				+ cidParana + "}," + "{\"estado\":\"" + estPernambuco + "\"" + ",\"cidades\":" + cidPernambuco
				+ "},{\"estado\":\"" + estPiaui + "\"" + ",\"cidades\":" + cidPiaui + "}," + "{\"estado\":\""
				+ estRiodeJaneiro + "\"" + ",\"cidades\":" + cidRiodeJaneiro + "},{\"estado\":\"" + estRioGrandeNorte
				+ "\"" + ",\"cidades\":" + cidRioGrandeNorte + "}," + "{\"estado\":\"" + estRioGrandeSul + "\""
				+ ",\"cidades\":" + cidRioGrandeSul + "},{\"estado\":\"" + estRondonia + "\"" + ",\"cidades\":"
				+ cidRondonia + "}," + "{\"estado\":\"" + estRoraima + "\"" + ",\"cidades\":" + cidRoraima
				+ "},{\"estado\":\"" + estSantaCatarina + "\"" + ",\"cidades\":" + cidSantaCatarina + "},"
				+ "{\"estado\":\"" + estSaoPaulo + "\"" + ",\"cidades\":" + cidSaoPaulo + "},{\"estado\":\""
				+ estSergipe + "\"" + ",\"cidades\":" + cidSergipe + "}," + "{\"estado\":\"" + estTocantins + "\""
				+ ",\"cidades\":" + cidTocantins + "}]";

		return json;
	}

}
