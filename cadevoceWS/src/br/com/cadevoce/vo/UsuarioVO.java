package br.com.cadevoce.vo;

public class UsuarioVO {

	protected int codigo;
	private String nome;
	private String email;
	private String senha;

	public UsuarioVO() {
	}

	public UsuarioVO(int codigo, String nome, String email, String senha) {

		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.senha = senha;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
