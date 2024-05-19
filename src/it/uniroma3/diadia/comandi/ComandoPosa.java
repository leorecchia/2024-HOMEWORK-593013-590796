package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Cerca di aggiungere un attrezzo alla stanza rimuovendolo dalla borsa.
 * Se la borsa ha un attrezzo con il nome specificato e la borsa può contenerlo,
 * l'operazione va a buon fine. Altrimenti viene stampato un messaggio di errore.
 * 
 * @param nome dell'attrezzo che bisogna posare
 */
public class ComandoPosa implements Comando{
	
	private IO io;
	private String nomeAttrezzoDaPosare;
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa=partita.getGiocatore().getBorsa();
		if (!borsa.hasAttrezzo(nomeAttrezzoDaPosare))
			io.mostraMessaggio("L'attrezzo che stai cercando non è presente nella borsa.");
		else {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzoDaPosare);
			if(partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				borsa.removeAttrezzo(nomeAttrezzoDaPosare);
				io.mostraMessaggio("L'attrezzo è stato correttamente rimosso dalla borsa e aggiunto alla stanza.");
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
			else
				io.mostraMessaggio("Impossibile aggiungere attrezzo alla stanza, numero massimo di attrezzi raggiunto.");
		}

	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPosare=parametro;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzoDaPosare;
	}
	
	@Override
	public void setIO(IO io) {
		this.io=io;
	}

}
