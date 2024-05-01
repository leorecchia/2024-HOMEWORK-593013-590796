package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	private IO io;
	private String nomeAttrezzoDaPrendere;
	
	@Override
	public void setIo (IO io) {
		this.io=io;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPrendere=parametro;
	}
	
	@Override
	public String getParametro () {
		return nomeAttrezzoDaPrendere;
	}
	
	@Override
	public String getNome () {
		return "Prendi";
	}
	
	@Override
	public void esegui (Partita partita)  {
		if (!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzoDaPrendere))
			io.mostraMessaggio("L'attrezzo che stai cercando non è presente nella stanza");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
			if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggio("L'attrezzo è stato aggiunto alla borsa e rimosso dalla stanza");
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			} else
				io.mostraMessaggio(
						"Non è stato possibile aggiungere l'attrezzo alla borsa. Raggiunto il valore massimo dei parametri della borsa");
		}

	}
}
