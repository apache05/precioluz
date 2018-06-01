package com.rubisoft.precioluz.Clases;

public class SugerenciaClass {

	private String sugerencia;
	private String version;

	public SugerenciaClass(){
		sugerencia="";
		version="";
	}
	public SugerenciaClass(String una_sugerencia, String una_version) {
		sugerencia= una_sugerencia;
		version= una_version;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}