package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" , "guarda"};
	
	@Override
	public String getNome () {
		return "Aiuto";
	}
	
	@Override
	public void esegui(Partita partita) {
		for (int i = 0; i < elencoComandi.length; i++)
			getIo().mostraMessaggio(elencoComandi[i] + " "); // stampa tutti i comandi
		getIo().mostraMessaggio("");
	}
}
