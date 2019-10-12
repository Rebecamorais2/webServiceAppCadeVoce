package br.com.cadevoce.vo;

public class FacesVO {
	protected int idFaces;
	protected String personId;
	protected String face_01;
	protected String face_02;
	protected String face_03;

	public FacesVO() {
	}

	public FacesVO(int idFaces, String personId, String face_01, String face_02, String face_03) {
		super();
		this.idFaces = idFaces;
		this.personId = personId;
		this.face_01 = face_01;
		this.face_02 = face_02;
		this.face_03 = face_03;
	}

	public int getIdFaces() {
		return idFaces;
	}

	public void setIdFaces(int idFaces) {
		this.idFaces = idFaces;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getFace_01() {
		return face_01;
	}

	public void setFace_01(String face_01) {
		this.face_01 = face_01;
	}

	public String getFace_02() {
		return face_02;
	}

	public void setFace_02(String face_02) {
		this.face_02 = face_02;
	}

	public String getFace_03() {
		return face_03;
	}

	public void setFace_03(String face_03) {
		this.face_03 = face_03;
	}
}
