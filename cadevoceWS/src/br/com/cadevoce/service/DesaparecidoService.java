package br.com.cadevoce.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import br.com.cadevoce.AI.CadeVoceAI;
import br.com.cadevoce.dao.DesaparecidoDAO;
import br.com.cadevoce.dao.FacesDAO;
import br.com.cadevoce.vo.DesaparecidoVO;
import br.com.cadevoce.vo.FacesVO;

@Path("/desaparecidos")
public class DesaparecidoService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private DesaparecidoDAO desaparecidoDAO;

	@PostConstruct
	private void init() {
		desaparecidoDAO = new DesaparecidoDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public DesaparecidoVO[] listarDesaparecidos() {
		DesaparecidoVO[] lista = null;
		try {
			lista = desaparecidoDAO.listarDesaparecidos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public DesaparecidoVO inserirDesaparecido(DesaparecidoVO desaparecido) {
		FacesVO faces = new FacesVO();
		String msg = "";

		System.out.println(desaparecido.getNome());

		try {
			int idGerado = desaparecidoDAO.inserirDesaparecido(desaparecido);

			msg = String.valueOf(idGerado);
			desaparecido.setCodDesap(idGerado);
			faces.setIdFaces(idGerado);
		} catch (Exception e) {
			msg = "Erro ao adicionar cadastro!";
			e.printStackTrace();
		}

		return desaparecido;
	}

	@GET
	@Path("/search")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public DesaparecidoVO[] buscarDesaparecidoPorNome(@QueryParam("nome") String nome,
			@QueryParam("estado") String estado, @QueryParam("cidade") String cidade) {
		DesaparecidoVO[] lista = null;
		try {
			lista = desaparecidoDAO.buscarDesaparecidoPorNome(nome, estado, cidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarDesaparecido(DesaparecidoVO desaparecido, @PathParam("id") int coddesap) {
		String msg = "";

		System.out.println(desaparecido.getNome());

		try {
			desaparecidoDAO.editarDesaparecido(desaparecido, coddesap);

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
	public String removerDesaparecido(@PathParam("id") int codDesap) {
		String msg = "";

		try {
			desaparecidoDAO.removerDesaparecido(codDesap);
			
			String idDesap = String.valueOf(codDesap);
			
			FacesDAO.buscarFacesPorId(idDesap); //busca as informações e apaga os dados do cloudinary e cognitive services
			
			FacesDAO.removerFaces(idDesap);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

	@GET
	@Path("/recent")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public DesaparecidoVO[] exibirCadastrosRecentes() {
		DesaparecidoVO[] lista = null;
		try {
			lista = desaparecidoDAO.exibirCadastrosRecentes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@GET
	@Path("/meuscadastros/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public DesaparecidoVO[] meusCadastros(@PathParam("id") int codUser) {
		DesaparecidoVO[] lista = null;
		try {
			lista = desaparecidoDAO.meusCadastros(codUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	// teste recebendo o faceId
	@GET
	@Path("/identificar")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public DesaparecidoVO[] identificarRosto(@QueryParam("faceId") String faceId) {
		DesaparecidoVO[] lista = null;
		String msg = "";
		String result = "";
		String personId = null;

		if (faceId != null) {

			try {
				System.out.println("identificar rosto: " + faceId);
				personId = CadeVoceAI.identificarRosto(faceId);
				lista = desaparecidoDAO.resultadoBuscarPorFoto(personId);

			} catch (Exception e) {
				msg = "Erro na identificação!";
				e.printStackTrace();
			}

		}
		return lista;

	}

}
