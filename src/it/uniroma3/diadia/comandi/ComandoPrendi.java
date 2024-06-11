package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	@Override
	public String getNome () {
		return "Prendi";
	}
	
	@Override
	public void esegui (Partita partita)  {
		if (!partita.getStanzaCorrente().hasAttrezzo(super.getParametro()))
			getIo().mostraMessaggio("L'attrezzo che stai cercando non è presente nella stanza");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(super.getParametro());
			if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				getIo().mostraMessaggio("L'attrezzo è stato aggiunto alla borsa e rimosso dalla stanza");
				getIo().mostraMessaggio(partita.getGiocatore().getBorsa()/*.getContenutoOrdinatoPerNome()*/.toString());
			} else
				getIo().mostraMessaggio(
						"Non è stato possibile aggiungere l'attrezzo alla borsa. Raggiunto il valore massimo dei parametri della borsa");
		}

	}
}
