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

import br.com.cadevoce.dao.RacaDesapDAO;
import br.com.cadevoce.vo.RacaDesapVO;

@Path("/racaDesap")
public class RacaDesapService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private RacaDesapDAO racaDesapDAO;

	@PostConstruct
	private void init() {
		racaDesapDAO = new RacaDesapDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public RacaDesapVO[] listarRacaDesap() {
		RacaDesapVO[] lista = null;
		try {
			lista = racaDesapDAO.listarRacaDesap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirRacaDesap(RacaDesapVO racaDesap) {
		String msg = "";

		System.out.println(racaDesap.getDescricao());

		try {
			int idGerado = racaDesapDAO.inserirRacaDesap(racaDesap);

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
	public RacaDesapVO buscarRacaDesapPorId(@PathParam("id") int codigo) {
		RacaDesapVO racaDesap = null;
		try {
			racaDesap = racaDesapDAO.buscarRacaDesapPorId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return racaDesap;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarRacaDesap(RacaDesapVO racaDesap, @PathParam("id") int codigo) {
		String msg = "";

		System.out.println(racaDesap.getDescricao());

		try {
			racaDesapDAO.editarRacaDesap(racaDesap, codigo);

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
	public String removerRacaDesap(@PathParam("id") int codigo) {
		String msg = "";

		try {
			racaDesapDAO.removerRacaDesap(codigo);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
