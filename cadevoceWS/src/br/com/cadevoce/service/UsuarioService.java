package br.com.cadevoce.service;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
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

import br.com.cadevoce.AI.CadeVoceAI;
import br.com.cadevoce.AI.EmailUtil;
import br.com.cadevoce.dao.UsuarioDAO;
import br.com.cadevoce.vo.DesaparecidoVO;
import br.com.cadevoce.vo.UsuarioVO;

@Path("/usuarios")
public class UsuarioService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private UsuarioDAO usuarioDAO;

	@PostConstruct
	private void init() {
		usuarioDAO = new UsuarioDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public UsuarioVO[] listarUsuarios() {
		UsuarioVO[] lista = null;
		try {
			lista = usuarioDAO.listarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public UsuarioVO inserirUsuario(UsuarioVO usuario) {
		String msg = "";
		// tratar email existente
		System.out.println(usuario.getNome());

		try {
			int idGerado = usuarioDAO.inserirUsuario(usuario);

			msg = String.valueOf(idGerado);

			usuario.setCodigo(idGerado);

		} catch (Exception e) {
			msg = "Erro ao adicionar usuário!";
			e.printStackTrace();
		}

		return usuario;
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public UsuarioVO buscarPorId(@PathParam("id") int codUser) {
		UsuarioVO usuario = null;
		try {
			usuario = usuarioDAO.buscarUsuarioPorId(codUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarUsuario(UsuarioVO usuario, @PathParam("id") int codUser) {
		String msg = "";

		System.out.println(usuario.getNome());

		try {
			usuarioDAO.editarUsuario(usuario, codUser);

			msg = "Usuário editado com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar usuário!";
			e.printStackTrace();
		}

		return msg;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerUsuario(@PathParam("id") int codUser) {
		String msg = "";

		try {
			usuarioDAO.removerDependenciaUsuario(codUser);
			usuarioDAO.removerUsuario(codUser);

			msg = "Usuário removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover usuário!";
			e.printStackTrace();
		}

		return msg;
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public UsuarioVO validarUsuario(UsuarioVO usuario, @PathParam("email") String email,
			@PathParam("senha") String senha) {

		try {

			usuario = usuarioDAO.validarUsuario(usuario.getEmail(), usuario.getSenha());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@GET
	@Path("/verifica_email")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public String buscarSenha(@QueryParam("email") String email) {
		String senha = null;
		String validador = "false";
		String contaEmail = "cadevoceprojeto@outlook.com";//ao trocar não esquecer de mudar o smtp.xxxx.com

		senha = usuarioDAO.buscarSenha(email);

		if (senha == null || senha == "false") {
			System.out.println("E-mail não encontrado, verifique novamente o endereço digitado");
			senha = "false";
			
		} else {

			try {

				validador = "true";

				final String fromEmail = contaEmail; // requires valid gmail id
				final String password = "desaparecidos123"; // correct password for gmail id
				final String toEmail = email; // can be any email id
				String tituloEmail = "Solicitação de envio de senha";
				String mensagem = "Olá!\nSua senha é " + senha + ".";

//				System.out.println("SSLEmail Start");
//				Properties props = new Properties();
//				props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
//				props.put("mail.smtp.socketFactory.port", "465"); // SSL Port 
//				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
//				props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
//				props.put("mail.smtp.port", "465"); // SMTP Port
				
				System.out.println("TLSEmail Start");
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.outlook.com"); //SMTP Host
				props.put("mail.smtp.port", "587"); //TLS Port
				props.put("mail.smtp.auth", "true"); //enable authentication
				props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

				Authenticator auth = new Authenticator() {
					// override the getPasswordAuthentication method
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, password);
					}
				};

				Session session = Session.getDefaultInstance(props, auth);
				System.out.println("Sessão criada");
				EmailUtil.sendEmail(session, toEmail, tituloEmail, mensagem);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return validador;
	}

}
