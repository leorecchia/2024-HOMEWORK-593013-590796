package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i<elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");			//stampa la lista di comandi che si possono scegliere
		io.mostraMessaggio("");
	}
	
	@Override 
	public void setParametro(String parametro) {}
	
	@Override
	public String getNome() {
		return "aiuto";
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
