package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	@Override
	public String getNome () {
		return "Guarda";
	}
	
	@Override
	public void esegui (Partita partita) {
		getIo().mostraMessaggio("Ti trovi nella stanza:  " + partita.getStanzaCorrente().getDescrizione());
		getIo().mostraMessaggio ("CFU rimanenti: " + partita.getGiocatore().getCfu());
		getIo().mostraMessaggio(partita.getGiocatore().getBorsa()./*getContenutoRaggruppatoPerPeso().*/toString());
	}
	
}
