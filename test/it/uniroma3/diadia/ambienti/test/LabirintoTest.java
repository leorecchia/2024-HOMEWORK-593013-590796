package it.uniroma3.diadia.ambienti.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {
	
	private Labirinto labirinto;
	
	@Before
	public void setUp() {
		this.labirinto=new Labirinto();
	}
	
	/******* TEST METODO getStanzaVincente *******/
	
	// verifichiamo che la stanza vincente non si chiama Atrio
	@Test
	public void nomeSbagliatoStanzaVincente() {
		assertNotEquals("Atrio", labirinto.getStanzaVincente().getNome());
	}
	
	// verifichiamo che la stanza vincente si chiama Biblioteca
	@Test
	public void nomeCorrettoStanzaVincente() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	
	// verifichiamo che la stanza vincente non sia quella iniziale
	@Test
	public void inizialeNonVincente() {
		assertNotEquals(labirinto.getStanzaIniziale(), labirinto.getStanzaVincente());
	}
	
/******* TEST METODO getStanzaIniziale *******/
	
	// verifichiamo che la stanza iniziale non si chiama Biblioteca
	@Test
	public void nomeSbagliatoStanzaIniziale() {
		assertNotEquals("Biblioteca", labirinto.getStanzaIniziale().getNome());
	}
	
	// verifichiamo che la stanza iniziale si chiama Atrio
	@Test
	public void nomeCorrettoStanzaIniziale() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	
	// verifichiamo che la stanza iniziale abbia attrezzo 'osso'
	@Test
	public void stanzaInizialeHaOsso() {
		assertTrue(labirinto.getStanzaIniziale().hasAttrezzo("osso"));
	}
	
	
}
