package br.com.cadevoce.vo;

import br.com.cadevoce.vo.CidadeVO;
import br.com.cadevoce.vo.EstadoVO;
import br.com.cadevoce.vo.UsuarioVO;
import br.com.cadevoce.vo.CorCabeloVO;
import br.com.cadevoce.vo.CorOlhosVO;
import br.com.cadevoce.vo.RacaDesapVO;
import br.com.cadevoce.vo.FacesVO;
import br.com.cadevoce.vo.SexoVO;

public class DesaparecidoVO {

	private int codDesap;
	private String nome;
	private int idade;
	private double altura;
	private String dataDesaparecimento;
	private String dataCadastro;
	private String parentesco;

	private String telefoneContato;
	private String descricao;
	private int idFaces;

	CidadeVO cidade = new CidadeVO();
	CorCabeloVO corcabelo = new CorCabeloVO();
	CorOlhosVO corolhos = new CorOlhosVO();
	RacaDesapVO raca = new RacaDesapVO();
	UsuarioVO usuario = new UsuarioVO();
	EstadoVO estado = new EstadoVO();
	FacesVO faces = new FacesVO();
	SexoVO sexo = new SexoVO();

	private int idCidade = cidade.codigo;
	private int idCorCabelo = corcabelo.codigo;
	private int idCorOlhos = corolhos.codigo;
	private int idRaca = raca.codigo;
	private int idUsuario = usuario.codigo;
	private int idSexo = sexo.codigo;
	private String cidDescricao = cidade.descricao;
	private String estDescricao = estado.descricao;
	private String racaDescricao = raca.descricao;
	private String olhosDescricao = corolhos.descricao;
	private String cabeloDescricao = corcabelo.descricao;
	private String face_01 = faces.face_01;
	private String face_02 = faces.face_02;
	private String face_03 = faces.face_03;
	private String sexoDescricao = sexo.descricao;

	public DesaparecidoVO() {
	}

	public DesaparecidoVO(int codDesap, String nome, int idade, double altura, String dataDesaparecimento,
			String dataCadastro, String parentesco, String telefoneContato, String descricao, int idFaces, int idCidade,
			int idCorCabelo, int idCorOlhos, int idSexo, int idRaca, int idUsuario, String cidDescricao,
			String estDescricao, String racaDescricao, String olhosDescricao, String cabeloDescricao, String face_01,
			String face_02, String face_03, String sexoDescricao) {
		super();
		this.codDesap = codDesap;
		this.nome = nome;
		this.idade = idade;
		this.altura = altura;
		this.dataDesaparecimento = dataDesaparecimento;
		this.dataCadastro = dataCadastro;
		this.parentesco = parentesco;
		this.telefoneContato = telefoneContato;
		this.descricao = descricao;
		this.idFaces = idFaces;
		this.idCidade = idCidade;
		this.idCorCabelo = idCorCabelo;
		this.idCorOlhos = idCorOlhos;
		this.idRaca = idRaca;
		this.idUsuario = idUsuario;
		this.idSexo = idSexo;
		this.estDescricao = estDescricao;
		this.cidDescricao = cidDescricao;
		this.racaDescricao = racaDescricao;
		this.olhosDescricao = olhosDescricao;
		this.cabeloDescricao = cabeloDescricao;
		this.face_01 = face_01;
		this.face_02 = face_02;
		this.face_03 = face_03;
		this.sexoDescricao = sexoDescricao;

	}

	public int getCodDesap() {
		return codDesap;
	}

	public void setCodDesap(int codDesap) {
		this.codDesap = codDesap;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getDataDesaparecimento() {
		return dataDesaparecimento;
	}

	public void setDataDesaparecimento(String dataDesaparecimento) {
		this.dataDesaparecimento = dataDesaparecimento;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdFaces() {
		return idFaces;
	}

	public void setIdFaces(int idFaces) {
		this.idFaces = idFaces;
	}

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public int getIdCorOlhos() {
		return idCorOlhos;
	}

	public void setIdCorOlhos(int idCorOlhos) {
		this.idCorOlhos = idCorOlhos;
	}

	public int getIdCorCabelo() {
		return idCorCabelo;
	}

	public void setIdCorCabelo(int idCorCabelo) {
		this.idCorCabelo = idCorCabelo;
	}

	public int getIdRaca() {
		return idRaca;
	}

	public void setIdRaca(int idRaca) {
		this.idRaca = idRaca;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}

	public String getEstDescricao() {
		return estDescricao;
	}

	public void setEstDescricao(String estDescricao) {
		this.estDescricao = estDescricao;
	}

	public String getCidDescricao() {
		return cidDescricao;
	}

	public void setCidDescricao(String cidDescricao) {
		this.cidDescricao = cidDescricao;
	}

	public String getRacaDescricao() {
		return racaDescricao;
	}

	public void setRacaDescricao(String racaDescricao) {
		this.racaDescricao = racaDescricao;
	}

	public String getOlhosDescricao() {
		return olhosDescricao;
	}

	public void setOlhosDescricao(String olhosDescricao) {
		this.olhosDescricao = olhosDescricao;
	}

	public String getCabeloDescricao() {
		return cabeloDescricao;
	}

	public void setCabeloDescricao(String cabeloDescricao) {
		this.cabeloDescricao = cabeloDescricao;
	}

	public String getFace_01() {
		return face_01;
	}

	public void setFace_01(String face_01) {
		this.face_01 = face_01;
	}

	public String getFace_02() {
		return face_02;
	}

	public void setFace_02(String face_02) {
		this.face_02 = face_02;
	}

	public String getFace_03() {
		return face_03;
	}

	public void setFace_03(String face_03) {
		this.face_03 = face_03;
	}

	public String getSexoDescricao() {
		return sexoDescricao;
	}

	public void setSexoDescricao(String sexoDescricao) {
		this.sexoDescricao = sexoDescricao;
	}

}
