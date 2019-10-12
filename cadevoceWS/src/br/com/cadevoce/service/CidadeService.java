package br.com.cadevoce.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.cadevoce.dao.CidadeDAO;
import br.com.cadevoce.vo.CidadeVO;

@Path("/cidade")
public class CidadeService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private CidadeDAO cidadeDAO;

	@PostConstruct
	private void init() {
		cidadeDAO = new CidadeDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public CidadeVO[] listarCidade() {
		CidadeVO[] lista = null;
		try {
			lista = cidadeDAO.listarCidade();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirCidade(CidadeVO cidade) {
		String msg = "";

		System.out.println(cidade.getDescricao());

		try {
			int idGerado = cidadeDAO.inserirCidade(cidade);

			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao adicionar cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public CidadeVO buscarCidadePorId(@PathParam("id") int codigo) {
		CidadeVO cidade = null;
		try {
			cidade = cidadeDAO.buscarCidadePorId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cidade;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarCidade(CidadeVO cidade, @PathParam("id") int codigo) {
		String msg = "";

		System.out.println(cidade.getDescricao());

		try {
			cidadeDAO.editarCidade(cidade, codigo);

			msg = "Cadastro editado com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerCidade(@PathParam("id") int codigo) {
		String msg = "";

		try {
			cidadeDAO.removerCidade(codigo);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
