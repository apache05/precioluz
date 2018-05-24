package com.ruben.precioluz2.Clases;


public class ErrorClass {

	private String detalle;
	private String version;

	public ErrorClass(){
		detalle="";
		version="";
	}
	public ErrorClass(String un_detalle, String una_version) {
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
