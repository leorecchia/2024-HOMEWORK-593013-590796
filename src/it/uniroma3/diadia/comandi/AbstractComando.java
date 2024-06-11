package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	private String parametro;
	private IO io;
	
	public IO getIo() {
		return io;
	}

	public void setIo (IO io) {
		this.io=io;
	}
	
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	public String getParametro () {
		return this.parametro;
	}
	
	public abstract void esegui(Partita partita);
	public abstract String getNome();
}
