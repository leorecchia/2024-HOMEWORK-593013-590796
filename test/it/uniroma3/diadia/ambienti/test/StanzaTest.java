package it.uniroma3.diadia.ambienti.test;

import static org.junit.Assert.*;

//import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	/******** TEST PER LA FUNZIONE getStanzaAdiacente *******/
	
	// verifico che, come da setUp, la stanza adiacente alla stanza1 a nord sia la stanza2
	@Test
	public void stanzaAdiacenteGiusta() {
		Stanza stanzaHaAdiacente=new Stanza("stanzaHaAdiacente");
		Stanza stanzaAdiacente=new Stanza("stanzaAdiacente");
		stanzaHaAdiacente.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertEquals(stanzaAdiacente, stanzaHaAdiacente.getStanzaAdiacente("nord"));
	}
	
	// verifico che non ci sia nessuna stanza adiacente a stanza1 a est, non avendolo impostato
	@Test
	public void stanzaAdiacenteNulla() {
		Stanza nessunaAdiacente=new Stanza("nessunaAdiacente");
		assertNull(nessunaAdiacente.getStanzaAdiacente("est"));
	}
	
	// verifico che getStanzaAdiacente passandogli null come direzione mi restituisca null
	@Test
	public void stanzaAdiacenteDirezioneNulla() {
		Stanza stanzaHaAdiacente=new Stanza("stanzaHaAdiacente");
		Stanza stanzaAdiacente=new Stanza("stanzaAdiacente");
		stanzaHaAdiacente.impostaStanzaAdiacente("nord", stanzaAdiacente);
		assertNull(stanzaHaAdiacente.getStanzaAdiacente(null));
	}
	
	/****** TEST PER LA FUNZIONE getAttrezzo *******/
	
	// verifichiamo che stanza1 contenga l'attrezzo passato come parametro
	@Test
	public void stanzaHaAttrezzo() {
		Stanza stanzaConAttrezzo=new Stanza("stanzaConAttrezzo");
		Attrezzo attrezzoPresente=new Attrezzo("attrezzoPresente", 3);
		stanzaConAttrezzo.addAttrezzo(attrezzoPresente);
		assertEquals(attrezzoPresente, stanzaConAttrezzo.getAttrezzo("attrezzoPresente"));
	}
	
	// verifchiamo che stanza2 non contenga l'attrezzo passato come parametro
	@Test
	public void stanzaNonHaAttrezzo() {
		Stanza stanzaSenzaAttrezzo=new Stanza("stanzaSenzaAttrezzo");
		assertNull(stanzaSenzaAttrezzo.getAttrezzo("attrezzoNonPresente"));
	}
	
	// verifichiamo che la stanza non sia vuota (contiene almeno un attrezzo)
	@Test
	public void stanzaNonVuota() {
		Stanza stanzaNonVuota=new Stanza("stanzaNonVuota");
		Attrezzo attrezzoInStanzaNonVuota=new Attrezzo("attrezzoInStanzaNonVuota", 3);
		stanzaNonVuota.addAttrezzo(attrezzoInStanzaNonVuota);
		assertNotNull(stanzaNonVuota.getAttrezzo("attrezzoInStanzaNonVuota"));
	}
	
	/******* TEST PER LA FUNZIONE getDirezioni ********/
	
	// verifichiamo che la stanza1 abbia almeno una direzione nel suo array di direzioni
	@Test
	public void direzioniNonNulle() {
		Stanza stanzaConDirezione=new Stanza("conDirezione");
		Stanza stanzaANord=new Stanza("nord");
		stanzaConDirezione.impostaStanzaAdiacente("nord", stanzaANord);
		assertNotNull(stanzaConDirezione.getDirezioni()[0]);
	}
	
	// verifichiamo che una stanza nuova ha l'array di direzioni vuoto e quindi non ha stanze adiacenti
	@Test
	public void direzioniNulle() {
		Stanza nessunaAdiacente=new Stanza("nessunaAdiacente");
		assertEquals(nessunaAdiacente.getDirezioni().length, 0);
	}
	
	// test che verifica che avendo inserito una direzione a stanza1 risulti una lunghezza dell'array di direzioni corretta
	@Test
	public void numeroGiusto() {
		Stanza stanzaConDirezione=new Stanza("conDirezione");
		Stanza stanzaANord=new Stanza("nord");
		stanzaConDirezione.impostaStanzaAdiacente("nord", stanzaANord);
		assertEquals(1, stanzaConDirezione.getDirezioni().length);
	}
}






