package it.uniroma3.diadia.ambienti.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaInDirezioneBloccata;

	@Before
	public void setUp(){
		stanzaBloccata=new StanzaBloccata("stanzaBloccata", "direzioneBloccata", "attrezzoChiave");
		stanzaInDirezioneBloccata=new Stanza("stanzaInDirezioneBloccata");
		stanzaBloccata.impostaStanzaAdiacente("direzioneBloccata", stanzaInDirezioneBloccata);
	}

	@Test
	public void direzioneNonBloccata() {
		Stanza stanzaInDirezioneNonBloccata=new Stanza("stanzaInDirezioneNonBloccata");
		stanzaBloccata.impostaStanzaAdiacente("direzioneNonBloccata", stanzaInDirezioneNonBloccata);
		assertSame(stanzaInDirezioneNonBloccata, stanzaBloccata.getStanzaAdiacente("direzioneNonBloccata"));
	}
	
	@Test
	public void direzioneBloccata_senzaAttrezzoChiave() {
		assertSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("direzioneBloccata"));
	}
	
	@Test
	public void direzioneBloccata_conAttrezzoChiave() {
		Attrezzo attrezzoChiave=new Attrezzo("attrezzoChiave", 4);
		stanzaBloccata.addAttrezzo(attrezzoChiave);
		assertSame(stanzaInDirezioneBloccata , stanzaBloccata.getStanzaAdiacente("direzioneBloccata"));
	}
	
	@Test
	public void uguaglianza() {
		StanzaBloccata bloccata1=new StanzaBloccata("bloccata", "nord", "chiave");
		StanzaBloccata bloccata2=new StanzaBloccata("bloccata", "nord", "chiave");
		assertEquals(bloccata1, bloccata2);
	}
	
	@Test
	public void disuguaglianza1() {
		StanzaBloccata bloccata1=new StanzaBloccata("bloccata", "nord", "chiave");
		StanzaBloccata bloccata2=new StanzaBloccata("bloccata", "sud", "chiave");
		assertNotEquals(bloccata1, bloccata2);
	}
	
	@Test
	public void disuguaglianza2() {
		StanzaBloccata bloccata1=new StanzaBloccata("bloccata", "nord", "chiave");
		StanzaBloccata bloccata2=new StanzaBloccata("bloccata", "nord", "chiavi");
		assertNotEquals(bloccata1, bloccata2);
	}
	
	
	

}
