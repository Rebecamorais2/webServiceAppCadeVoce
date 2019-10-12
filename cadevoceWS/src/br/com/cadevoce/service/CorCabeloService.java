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

import br.com.cadevoce.dao.CorCabeloDAO;
import br.com.cadevoce.vo.CorCabeloVO;

@Path("/corCabelo")
public class CorCabeloService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private CorCabeloDAO corCabeloDAO;

	@PostConstruct
	private void init() {
		corCabeloDAO = new CorCabeloDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public CorCabeloVO[] listarCorCabelo() {
		CorCabeloVO[] lista = null;
		try {
			lista = corCabeloDAO.listarCorCabelo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirCorCabelo(CorCabeloVO corCabelo) {
		String msg = "";

		System.out.println(corCabelo.getDescricao());

		try {
			int idGerado = corCabeloDAO.inserirCorCabelo(corCabelo);

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
	public CorCabeloVO buscarCorCabeloPorId(@PathParam("id") int codigo) {
		CorCabeloVO corCabelo = null;
		try {
			corCabelo = corCabeloDAO.buscarCorCabeloPorId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return corCabelo;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarCorCabelo(CorCabeloVO corCabelo, @PathParam("id") int codigo) {
		String msg = "";

		System.out.println(corCabelo.getDescricao());

		try {
			corCabeloDAO.editarCorCabelo(corCabelo, codigo);

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
	public String removerCorCabelo(@PathParam("id") int codigo) {
		String msg = "";

		try {
			corCabeloDAO.removerCorCabelo(codigo);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
