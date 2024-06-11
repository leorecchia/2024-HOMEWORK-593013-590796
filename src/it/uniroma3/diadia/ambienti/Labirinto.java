package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	

	public Labirinto (/*String nomeFile */) /*throws FileNotFoundException, FormatoFileNonValidoException*/ {
//		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
//		c.carica();
//		this.stanzaIniziale=c.getStanzaIniziale();
//		this.stanzaVincente=c.getStanzaVincente();
		creaStanze();
	}
	
	 
	/**
     * Crea tutte le stanze e le porte di collegamento
     */ 
	private void creaStanze() {

			/* crea gli attrezzi */
	    	Attrezzo lanterna = new Attrezzo("lanterna",3);
			Attrezzo osso = new Attrezzo("osso",1);
	    	
			/* crea stanze del labirinto */
			Stanza atrio = new Stanza("Atrio");
			Stanza aulaN11 = new Stanza("Aula N11");
			Stanza aulaN10 = new Stanza("Aula N10");
			Stanza laboratorio = new Stanza("Laboratorio Campus");
			Stanza biblioteca = new Stanza("Biblioteca");
			
			/* collega le stanze */
			atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
			atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
			atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
			atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
			aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
			aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
			aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
			aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
			aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
			laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
			laboratorio.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
			biblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);

	        /* pone gli attrezzi nelle stanze */
			aulaN10.addAttrezzo(lanterna);
			atrio.addAttrezzo(osso);

			// il gioco comincia nell'atrio
	        stanzaIniziale = atrio;  
			stanzaVincente = biblioteca;
	    }
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente=stanzaVincente;
	}
	
}
