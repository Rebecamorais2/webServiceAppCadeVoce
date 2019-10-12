package br.com.cadevoce.vo;

import br.com.cadevoce.vo.CidadeVO;
import br.com.cadevoce.vo.EstadoVO;

public class EstadosCidadesVO {

	CidadeVO c = new CidadeVO();
	EstadoVO e = new EstadoVO();

	String estado = e.descricao;
	String cidade = c.descricao;

	public EstadosCidadesVO(String estado, String cidade) {
		super();
		this.estado = estado;
		this.cidade = cidade;
	}

	public EstadosCidadesVO() {
	};

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
