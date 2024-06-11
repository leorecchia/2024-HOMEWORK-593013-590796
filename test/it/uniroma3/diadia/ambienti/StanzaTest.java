package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {


	/************** TEST METODO getAttrezzo **************/
	
	@Test
	public void testGetAttrezzo_stanzaNONVuotaDiAttrezzi() {
		Stanza stanza = new Stanza("stanza");
		Attrezzo attrezzo = new Attrezzo ("attrezzo", 0);
		stanza.addAttrezzo(attrezzo);
		assertNotNull (stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	public void testGetAttrezzo_attrezzoPresente() {
		Stanza stanzaConAttrezzo = new Stanza("stanzaConAttrezzo");
		Attrezzo attrezzoPresente = new Attrezzo ("nomeDiAttrezzoPresente", 0);
		stanzaConAttrezzo.addAttrezzo(attrezzoPresente);
		assertNotNull (stanzaConAttrezzo.getAttrezzo("nomeDiAttrezzoPresente"));
	}
	
	@Test
	public void testGetAttrezzo_attrezzoAssente() {
		Stanza stanzaConAttrezzoAssente = new Stanza("stanzaConAttrezzoAssente");
		assertNull (stanzaConAttrezzoAssente.getAttrezzo("nomeDiAttrezzoAssente"));
	}
	
	
	/*********** TEST METODO getStanzaAdiacente ***********/
	@Test
	public void testGetStanzaAdiacenteCorretta() {
		Stanza stanzaPrincipale = new Stanza("stanzaPrincipale");
		Stanza stanzaAdiacente = new Stanza("stanzaAdiacente");
		stanzaPrincipale.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		assertEquals (stanzaAdiacente, stanzaPrincipale.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetStanzaAdiacenteNulla() {
		Stanza stanzaPrincipale = new Stanza("stanzaPrincipale");
		assertNull (stanzaPrincipale.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetStanzaAdiacente_conDirezioneNulla() {
		Stanza stanzaPrincipale = new Stanza("stanzaPrincipale");
		Stanza stanzaAdiacente = new Stanza("stanzaAdiacente");
		stanzaPrincipale.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		assertNull (stanzaPrincipale.getStanzaAdiacente(null));
	}
	
	
	/************** TEST METODO getDirezioni **************/
	
	//test che controlla che non ha direzioni vuota, ovvero se ha una stanza adiacente
	@Test
	public void testGetDirezioni_DirezioniNonVuote () {
		Stanza stanzaPrincipale = new Stanza("stanzaPrincipale");
		Stanza stanzaAdiacente = new Stanza ("stanzaAdiacente");
		stanzaPrincipale.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		assertFalse (stanzaPrincipale.getDirezioni().isEmpty());
	}
	
	//test che controlla che una stanza non ha stanze adiacenti
	@Test
	public void testGetDirezioni_DirezioniVuote () {
		Stanza stanzaPrincipale = new Stanza("stanzaPrincipale_senzaAdiacenti");
		assertTrue (stanzaPrincipale.getDirezioni().isEmpty());
	}
	
	//test che controlla se la prima direzione di una stanza sia la prima stranza adiacente inserita
	@Test
	public void testGetDirezioni_primaDirezioneInserita_è_quellaCorretta () {
		Stanza stanzaPrincipale = new Stanza("stanzaPrincipale");
		Stanza stanzaAdiacente1 = new Stanza ("stanzaAdiacente1");
		Stanza stanzaAdiacente2 = new Stanza ("stanzaAdiacente2");
		stanzaPrincipale.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente1);
		stanzaPrincipale.impostaStanzaAdiacente(Direzione.SUD, stanzaAdiacente2);
		Iterator<Direzione> iteratore= stanzaPrincipale.getDirezioni().iterator();
		assertEquals (Direzione.SUD, iteratore.next());
		assertEquals (Direzione.NORD, iteratore.next());
	}
}
