package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;



public class LabirintoTest {
	
	private Labirinto labirinto;
	
	@Before
	public void setUp () {
		labirinto = new Labirinto();
	}
	
	/************** TEST METODO getStanzaVincente **************/
	
	//verifichiamo che la stanza vincente non si chiama Atrio
	@Test
	public void testStanzaVincente_nomeStanzaVincente_nonSiaSbagliato() {
		assertNotEquals ("Atrio", labirinto.getStanzaVincente());
	}
	
	//verifichiamo che la stanza vincente si chiama Bibilioetca
	@Test
	public void testStanzaVincente_nomeStanzaVincente_siaCorretto() {
		assertNotEquals ("Biblioteca", labirinto.getStanzaVincente());
	}
	
	//verifichiamo che la stanza vincente non sia la stanza iniziale
	@Test
	public void testStanzaVincente_non_è_StanzaIniziale() {
		assertNotEquals (labirinto.getStanzaIniziale(), labirinto.getStanzaVincente());
	}
	
	
	/************** TEST METODO getStanzaIniziale **************/
	
	//verifichiamo che la stanza iniziale non si chiama Biblioteca
	@Test
	public void testStanzaIniziale_nomeStanzaIniziale_nonSiaSbagliato() {
		assertNotEquals ("Biblioteca", labirinto.getStanzaIniziale());
	}
	
	//verifichiamo che la stanza iniziale si chiama Atrio
	@Test
	public void testStanzaIniziale_nomeStanzaIniziale_siaCorretto() {
		assertNotEquals ("Atrio", labirinto.getStanzaIniziale());
	}
	
	//verifichiamo che la stanza iniziale abbia l'attrezzo osso
	@Test
	public void testStanzaIniziale_possiedeAttrezzoOsso() {
		assertTrue (labirinto.getStanzaIniziale().hasAttrezzo("osso"));
	}
	
}
