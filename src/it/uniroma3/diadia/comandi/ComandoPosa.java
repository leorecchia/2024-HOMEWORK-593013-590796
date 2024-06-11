package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando{
	
	@Override
	public String getNome () {
		return "Posa";
	}
	
	@Override
	public void esegui (Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();

		if (!borsa.hasAttrezzo(super.getParametro()))
			getIo().mostraMessaggio("L'attrezzo che stai cercando non è presente nella borsa");
		else {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(super.getParametro());
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				borsa.removeAttrezzo(super.getParametro());
				getIo().mostraMessaggio("L'attrezzo è stato aggiunto alla stanza e rimosso dalla borsa");
				getIo().mostraMessaggio(partita.getGiocatore().getBorsa()/*.getContenutoOrdinatoPerPeso()*/.toString());
			} else
				getIo().mostraMessaggio(
						"Non è stato possibile aggiungere l'attrezzo alla stanza. Numero massimo di attrezzi della stanza raggiunto");
		}
	}
}
