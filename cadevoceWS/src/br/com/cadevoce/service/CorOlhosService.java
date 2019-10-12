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

import br.com.cadevoce.dao.CorOlhosDAO;
import br.com.cadevoce.vo.CorOlhosVO;

@Path("/corOlhos")
public class CorOlhosService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private CorOlhosDAO corOlhosDAO;

	@PostConstruct
	private void init() {
		corOlhosDAO = new CorOlhosDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public CorOlhosVO[] listarCorOlhos() {
		CorOlhosVO[] lista = null;
		try {
			lista = corOlhosDAO.listarCorOlhos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirCorOlhos(CorOlhosVO corOlhos) {
		String msg = "";

		System.out.println(corOlhos.getDescricao());

		try {
			int idGerado = corOlhosDAO.inserirCorOlhos(corOlhos);

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
	public CorOlhosVO buscarCorOlhosPorId(@PathParam("id") int codigo) {
		CorOlhosVO corOlhos = null;
		try {
			corOlhos = corOlhosDAO.buscarCorOlhosPorId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return corOlhos;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarCorOlhos(CorOlhosVO corOlhos, @PathParam("id") int codigo) {
		String msg = "";

		System.out.println(corOlhos.getDescricao());

		try {
			corOlhosDAO.editarCorOlhos(corOlhos, codigo);

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
	public String removerCorOlhos(@PathParam("id") int codigo) {
		String msg = "";

		try {
			corOlhosDAO.removerCorOlhos(codigo);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
