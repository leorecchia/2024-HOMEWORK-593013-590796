package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * comando che permette di visualizzare le informazioni relative alla partita, 
 * alla stanza corrente, alla borsa de giocatore e ai suoi cfu rimasti
 */
public class ComandoGuarda implements Comando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("\nTi trovi in: "+partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("I tuoi CFU rimasti sono: "+partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());	
	}
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIO(IO io) {
		this.io=io;
	}
}
