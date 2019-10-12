package br.com.cadevoce.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.cadevoce.dao.EstadosCidadesDAO;

@Path("/estado-cidades")
public class EstadosCidadesService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private EstadosCidadesDAO estadosCidadesDAO;

	@PostConstruct
	private void init() {
		estadosCidadesDAO = new EstadosCidadesDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public String listarCidadesEstados() {
		String lista = null;
		try {
			lista = estadosCidadesDAO.listarCidadesEstados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
