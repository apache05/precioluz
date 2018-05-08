package com.rubisoft.precioluz2.Clases;


public class Error {

	private String detalle;
	private String version;

	public Error(){
		detalle="";
		version="";
	}
	public Error( String un_detalle, String una_version) {
		detalle= un_detalle;
		version= una_version;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
