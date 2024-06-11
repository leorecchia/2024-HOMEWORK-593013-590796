package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private StanzaBloccata stanzaBloccata;
	
	
	@Test
	public void testGetStanzaAdiacente_DirezioneNonBloccata() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", Direzione.NORD, "attrezzoChiave");
		Stanza stanzaAdiacenteNonBloccata = new Stanza("stanzaAdiacenteNonBloccata");
		stanzaBloccata.impostaStanzaAdiacente(Direzione.SUD, stanzaAdiacenteNonBloccata);
		assertEquals (stanzaAdiacenteNonBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.SUD));
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataSenzaAttrezzo() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", Direzione.NORD, "attrezzoChiave");
		Stanza stanzaAdiacenteNonBloccata = new Stanza("stanzaAdiacenteNonBloccata");
		stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacenteNonBloccata);
		assertEquals (stanzaBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataConAttrezzoChiave() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", Direzione.NORD, "attrezzoChiave");
		Stanza stanzaAdiacenteNonBloccataInDirezioneBloccata = new Stanza("stanzaAdiacenteNonBloccataInDirezioneBloccata");
		Attrezzo attrezzoChiave = new Attrezzo("attrezzoChiave", 1);
		stanzaBloccata.addAttrezzo(attrezzoChiave);
		stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacenteNonBloccataInDirezioneBloccata);
		assertEquals (stanzaAdiacenteNonBloccataInDirezioneBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
	}

}
