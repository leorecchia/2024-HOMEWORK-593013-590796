package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	private IO io;

	@Override
	public void setIo (IO io) {
		this.io=io;
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getParametro () {
		return null;
	}
	
	@Override
	public String getNome () {
		return "Non Valido";
	}

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando inserito non valido" );
	}
}
