package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private StanzaBloccata stanzaBloccata;
	
	
	@Test
	public void testGetStanzaAdiacente_DirezioneNonBloccata() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", "direzioneBloccata", "attrezzoChiave");
		Stanza stanzaAdiacenteNonBloccata = new Stanza("stanzaAdiacenteNonBloccata");
		stanzaBloccata.impostaStanzaAdiacente("direzioneNonBloccata", stanzaAdiacenteNonBloccata);
		assertEquals (stanzaAdiacenteNonBloccata, stanzaBloccata.getStanzaAdiacente("direzioneNonBloccata"));
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataSenzaAttrezzo() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", "direzioneBloccata", "attrezzoChiave");
		Stanza stanzaAdiacenteNonBloccata = new Stanza("stanzaAdiacenteNonBloccata");
		stanzaBloccata.impostaStanzaAdiacente("direzioneBloccata", stanzaAdiacenteNonBloccata);
		assertEquals (stanzaBloccata, stanzaBloccata.getStanzaAdiacente("direzioneBloccata"));
	}
	
	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataConAttrezzoChiave() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", "direzioneBloccata", "attrezzoChiave");
		Stanza stanzaAdiacenteNonBloccataInDirezioneBloccata = new Stanza("stanzaAdiacenteNonBloccataInDirezioneBloccata");
		Attrezzo attrezzoChiave = new Attrezzo("attrezzoChiave", 1);
		stanzaBloccata.addAttrezzo(attrezzoChiave);
		stanzaBloccata.impostaStanzaAdiacente("direzioneBloccata", stanzaAdiacenteNonBloccataInDirezioneBloccata);
		assertEquals (stanzaAdiacenteNonBloccataInDirezioneBloccata, stanzaBloccata.getStanzaAdiacente("direzioneBloccata"));
	}

}
