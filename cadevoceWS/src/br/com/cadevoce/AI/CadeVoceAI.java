package br.com.cadevoce.AI;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.cadevoce.dao.*;

public class CadeVoceAI {

	static String KEY = "13e05e9989d1420db8281cfb13980b2f";
	static String PERSONGROUPID = "desaparecidos";

	static DesaparecidoDAO desaparecidoDAO = new DesaparecidoDAO();

	// [PersonGroup Person] Passa o personGroupId ("desaparecidos") e o nome da
	// pessoa a ser criada
	// retorna o personId da pessoa.
	public static void criarPessoa(String nomedesap, String url) {
		HttpClient httpclient = HttpClients.createDefault();
		String body = "{name:\"" + nomedesap + "\"}";
		String personId = null;
		int codDesap = Integer.parseInt(nomedesap);

		try {
			URIBuilder builder = new URIBuilder(
					"https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/persongroups/" + PERSONGROUPID
							+ "/persons");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", KEY);

			// Request body
			StringEntity reqEntity = new StringEntity(body);
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			// "cortando" o json para gravar somente o valor da chave
			personId = EntityUtils.toString(entity).substring(13, 49);

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
				System.out.println("Pessoa criada no Azure com sucesso! PersonId = " + personId);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		adicionarRosto(personId, url);
		FacesDAO.salvarPersonId(personId, codDesap);
	}

	// Adiciona uma foto (persistedFaceIds) à pessoa criada,
	// Precisa passar o personGroupId ("desaparecidos") e o personId gerado ao criar
	// uma pessoa
	// Como resposta retorna o persistedFaceId
	public static void adicionarRosto(String personId, String url) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			URIBuilder builder = new URIBuilder(
					"https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/persongroups/" + PERSONGROUPID
							+ "/persons/" + personId + "/persistedFaces");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", KEY);

			// Request body
			StringEntity reqEntity = new StringEntity("{\"url\":\"" + url + "\"}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
				System.out.println("Foto gravada no Azure!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		treinarGrupo(PERSONGROUPID);
	}

	public static void treinarGrupo(String personGroupId) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			URIBuilder builder = new URIBuilder(
					"https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/persongroups/" + personGroupId
							+ "/train");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Ocp-Apim-Subscription-Key", KEY);

			// Request body
			StringEntity reqEntity = new StringEntity("{body}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
				System.out.println("Grupo " + personGroupId + " treinado!");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Inicio da etapa de identificação
	public static String detectarRosto(String http) {

		HttpClient httpclient = HttpClients.createDefault();
		String faceId = null;
		String verificador = "false";

		try {
			URIBuilder builder = new URIBuilder("https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/detect");

			builder.setParameter("returnFaceId", "true");
			builder.setParameter("returnFaceLandmarks", "false");
			// builder.setParameter("returnFaceAttributes", "{string}");//caso precise dos
			// atributos do rosto

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", KEY);

			// Request body
			StringEntity reqEntity = new StringEntity("{\"url\":\"" + http + "\"}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			String entityComplete = EntityUtils.toString(entity);
			faceId = entityComplete.substring(12, 48);

			if (entity != null) {
				verificador = "true";
				System.out.println(entityComplete);
				System.out.println("Rosto detectado com sucesso! faceId = " + faceId);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("FaceId gerado: " + faceId);

		identificarRosto(faceId);
		return verificador;
	}

	public static String identificarRosto(String faceId) {
		HttpClient httpclient = HttpClients.createDefault();
		// parametros
		String groupId = "desaparecidos";
		String resultadosQtd = "1"; // Quantidade de pessoas a serem listadas
		String resultado;
		String personId = null;
		String confidence = null;

		try {
			System.out.println("Entrou no método identificarRosto");
			URIBuilder builder = new URIBuilder("https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/identify");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", KEY);

			// Request body
			StringEntity reqEntity = new StringEntity("{\"personGroupId\":\"" + groupId + "\"," + "\"faceIds\": [\""
					+ faceId + "\"]," + "\"maxNumOfCandidatesReturned\":" + resultadosQtd + "}");

			System.out.println("Requisição " + reqEntity.toString());
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				resultado = EntityUtils.toString(entity);

				System.out.println("Resultado da pesquisa: " + resultado);

				// cortando as strings para pegar os resultados
				personId = resultado.substring(77, 113);
				confidence = resultado.substring(128, 135);

				System.out.println("Extraindo valor de personId: " + personId);
				System.out.println("Extraindo valor de confidence: " + confidence);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return personId;
	}
	
	
	public static void apagarPersonAzure(String personId) {
		
		HttpClient httpclient = HttpClients.createDefault();
		
		 try
	        {
	            URIBuilder builder = new URIBuilder("https://brazilsouth.api.cognitive.microsoft.com/face/v1.0/persongroups/"+PERSONGROUPID+"/persons/"+ personId);


	            URI uri = builder.build();
	            HttpDelete request = new HttpDelete(uri);
	            request.setHeader("Ocp-Apim-Subscription-Key", KEY);


	            // Request body
//	            StringEntity reqEntity = new StringEntity("{body}");
//	            request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();

	            if (entity != null) 
	            {
	                System.out.println(EntityUtils.toString(entity));
	                System.out.println("Person apagado do Azure!");
	            }
	            treinarGrupo(PERSONGROUPID);
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	}

}
