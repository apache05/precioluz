package com.rubisoft.precioluz2.Clases;

public class Sugerencia {

	public String sugerencia;
	public String version;

	public Sugerencia() {
		// Default constructor required for calls to DataSnapshot.getValue(User.class)
	}

	public Sugerencia( String una_sugerencia, String una_version) {
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