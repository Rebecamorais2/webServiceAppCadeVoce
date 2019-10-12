package br.com.cadevoce.service;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import br.com.cadevoce.AI.CadeVoceAI;
import br.com.cadevoce.dao.FacesDAO;
import br.com.cadevoce.vo.FacesVO;

@Path("/faces")
public class FacesService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private FacesDAO facesDAO;

	@PostConstruct
	private void init() {
		facesDAO = new FacesDAO();
	}

	@POST
	@Path("/upload_fotos")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public String uploadFotosNuvem(@FormParam("fileName") String desaparecidoId, @FormParam("base64") String base64) {
		String msg = "";
		String result = "";
		String resultFileNamePath = "";
		String validador = "false";// para tratar a mensagem ao subir foto no android

		// não esquecer que o desaparecidoId tem que ser transformado em string para vir
		// pra cá
		
		if (desaparecidoId != null && base64 != null) {

			try {
				Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "cadevoce", "api_key",
						"393111438232317", "api_secret", "SaRNGEWKvUsDGPGwbpml6tH2Jso"));

				String toUpload = base64;

			
					Map uploadResult = cloudinary.uploader().upload(toUpload,
							ObjectUtils.asMap("folder", "desaparecidos" + "/" + desaparecidoId + "/"));
					

					result = (String) uploadResult.get("url");
					resultFileNamePath = (String) uploadResult.get("public_id"); //nome do arquivo no cloudinary
					System.out.println("Valor de public_id: "+resultFileNamePath);
					
					System.out.println("Upload realizado com sucesso! URL : " + result);
					validador = "true";
					
					int codDesap = Integer.parseInt(desaparecidoId);
					String http = result.substring(0, result.length());
					FacesDAO.inserirFaces1(http, codDesap, resultFileNamePath);
					CadeVoceAI.criarPessoa(desaparecidoId, http);
					CadeVoceAI.treinarGrupo("desaparecidos");


			} catch (Exception e) {
				msg = "Erro ao adicionar cadastro!";
				e.printStackTrace();
			}

		} else {
			System.out.println("desaparecidoId ou base64 nulo");
		}

		return validador;

	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public FacesVO[] listarFaces() {
		FacesVO[] lista = null;
		try {
			lista = FacesDAO.listarFaces();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public FacesVO buscarFacesPorId(@PathParam("id") String idFaces) {
		FacesVO faces = null;
		try {
			faces = FacesDAO.buscarFacesPorId(idFaces);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return faces;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerFaces(@PathParam("id") String idFaces) {
		String msg = "";

		try {
			FacesDAO.removerFaces(idFaces);

			msg = "Cadastro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover cadastro!";
			e.printStackTrace();
		}

		return msg;
	}

}
