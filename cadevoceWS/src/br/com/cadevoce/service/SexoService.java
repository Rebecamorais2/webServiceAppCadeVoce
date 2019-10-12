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

import br.com.cadevoce.dao.SexoDAO;
import br.com.cadevoce.vo.SexoVO;

@Path("/sexo")
public class SexoService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private SexoDAO sexoDAO;

	@PostConstruct
	private void init() {
		sexoDAO = new SexoDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public SexoVO[] listarSexo() {
		SexoVO[] lista = null;
		try {
			lista = sexoDAO.listarSexo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirSexo(SexoVO sexo) {
		String msg = "";

		System.out.println(sexo.getDescricao());

		try {
			int idGerado = sexoDAO.inserirSexo(sexo);

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
	public SexoVO buscarSexo(@PathParam("id") int codigo) {
		SexoVO sexo = null;
		try {
			sexo = sexoDAO.buscarSexo(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sexo;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarSexo(SexoVO sexo, @PathParam("id") int codigo) {
		String msg = "";

		System.out.println(sexo.getDescricao());

		try {
			sexoDAO.editarSexo(sexo, codigo);

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
	public String removerSexo(@PathParam("id") int codigo) {
		String msg = "";

		try {
			sexoDAO.removerSexo(codigo);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
