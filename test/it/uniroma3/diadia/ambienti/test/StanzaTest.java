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
		System.out.println(stanzaNonVuota.toString());
		assertNotNull(stanzaNonVuota.getAttrezzo("attrezzoInStanzaNonVuota"));
	}
	
	/******* TEST PER LA FUNZIONE getDirezioni ********/
	
	// verifichiamo che la stanzaConDirezione abbia almeno una direzione nel suo set di direzioni
	@Test
	public void direzioniNonNulle() {
		Stanza stanzaConDirezione=new Stanza("conDirezione");
		Stanza stanzaANord=new Stanza("nord");
		stanzaConDirezione.impostaStanzaAdiacente("nord", stanzaANord);
		assertFalse(stanzaConDirezione.getDirezioni().isEmpty());
	}
	
	// verifichiamo che una stanza nuova ha l'array di direzioni vuoto e quindi non ha stanze adiacenti
	@Test
	public void direzioniNulle() {
		Stanza nessunaAdiacente=new Stanza("nessunaAdiacente");
		assertEquals(nessunaAdiacente.getDirezioni().size(), 0);
	}
	
	// test che verifica che avendo inserito una direzione a stanza1 risulti una lunghezza del set di direzioni corretta
	@Test
	public void numeroGiusto() {
		Stanza stanzaConDirezione=new Stanza("conDirezione");
		Stanza stanzaANord=new Stanza("nord");
		stanzaConDirezione.impostaStanzaAdiacente("nord", stanzaANord);
		assertEquals(1, stanzaConDirezione.getDirezioni().size());
	}
	
	/******* TEST PER LA FUNZIONE equals ********/
	
	@Test
	public void uguaglianza() {
		Stanza s1=new Stanza("s1");
		Stanza s2=new Stanza("s1");
		assertEquals(s1, s2);
	}
	
	@Test
	public void disuguaglianza1() {
		Stanza s1=new Stanza("s1");
		Stanza s2=new Stanza("s2");
		assertNotEquals(s1, s2);
	}
	
	@Test
	public void disuguaglianza2() {
		Stanza s1=new Stanza("s1");
		Stanza s2=new Stanza("s1");
		s2.impostaStanzaAdiacente("nord", s2);
		assertNotEquals(s1, s2);
	}
	
	@Test
	public void disuguaglianza3() {
		Stanza s1=new Stanza("s1");
		Stanza s2=new Stanza("s1");
		Attrezzo a1=new Attrezzo("a1", 2);
		s1.addAttrezzo(a1);
		assertNotEquals(s1, s2);
	}
}






