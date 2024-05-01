package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando{
	private IO io;
	String nomeAttrezzoDaPosare;
	
	@Override
	public void setIo (IO io) {
		this.io=io;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPosare=parametro;
	}
	
	@Override
	public String getParametro () {
		return this.nomeAttrezzoDaPosare;
	}
	
	@Override
	public String getNome () {
		return "Posa";
	}
	
	@Override 
	public void esegui (Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();

		if (!borsa.hasAttrezzo(nomeAttrezzoDaPosare))
			io.mostraMessaggio("L'attrezzo che stai cercando non è presente nella borsa");
		else {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzoDaPosare);
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				borsa.removeAttrezzo(nomeAttrezzoDaPosare);
				io.mostraMessaggio("L'attrezzo è stato aggiunto alla stanza e rimosso dalla borsa");
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			} else
				io.mostraMessaggio(
						"Non è stato possibile aggiungere l'attrezzo alla stanza. Numero massimo di attrezzi della stanza raggiunto");
		}
	}
}
