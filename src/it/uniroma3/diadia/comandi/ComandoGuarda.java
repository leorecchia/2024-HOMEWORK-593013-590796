package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private IO io;
	
	@Override
	public void setIo (IO io) {
		this.io=io;
	}
	
	@Override
	public void setParametro (String parametro) {}
	
	@Override
	public String getParametro () {
		return null;
	}
	
	@Override
	public String getNome () {
		return "Guarda";
	}
	
	@Override
	public void esegui (Partita partita) {
		io.mostraMessaggio("Ti trovi nella stanza:  " + partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio ("CFU rimanenti: " + partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
}
