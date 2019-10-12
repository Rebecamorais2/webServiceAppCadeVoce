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

import br.com.cadevoce.dao.EstadoDAO;
import br.com.cadevoce.vo.EstadoVO;

@Path("/estado")
public class EstadoService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private EstadoDAO estadoDAO;

	@PostConstruct
	private void init() {
		estadoDAO = new EstadoDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public EstadoVO[] listarEstado() {
		EstadoVO[] lista = null;
		try {
			lista = estadoDAO.listarEstado();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirEstado(EstadoVO estado) {
		String msg = "";

		System.out.println(estado.getDescricao());

		try {
			int idGerado = estadoDAO.inserirEstado(estado);

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
	public EstadoVO buscarEstadoPorId(@PathParam("id") int codigo) {
		EstadoVO estado = null;
		try {
			estado = estadoDAO.buscarEstadoPorId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estado;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarEstado(EstadoVO estado, @PathParam("id") int codigo) {
		String msg = "";

		System.out.println(estado.getDescricao());

		try {
			estadoDAO.editarEstado(estado, codigo);

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
	public String removerEstado(@PathParam("id") int codigo) {
		String msg = "";

		try {
			estadoDAO.removerEstado(codigo);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
