package it.uniroma3.diadia.giocatore.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa borsa;

	@Before
	public void setUp(){
		borsa=new Borsa();
	}
	
	/********* TEST PER LA FUNZIONE getAttrezzo ********/
	
	// verifichiamo che sia presente un attrezzo specifico nella borsa
	@Test
	public void aggiungiamoInBorsaVuota() {
		Attrezzo attrezzoAggiunto=new Attrezzo("attrezzoAggiunto", 1);
		borsa.addAttrezzo(attrezzoAggiunto);
		assertNotNull(borsa.getAttrezzo("attrezzoAggiunto"));
	}
	
	// verifichiamo che in una borsa non vuota non risulti un attrezzo che non ci sta
	@Test
	public void attrezzoNonEsiste_inBorsaNonVuota() {
		Attrezzo attrezzoAggiunto=new Attrezzo("attrezzoAggiunto", 1);
		borsa.addAttrezzo(attrezzoAggiunto);
		assertNull(this.borsa.getAttrezzo("inesistente"));
	}
	
	// verifichiamo che non esista un attrezzo in una borsa vuota
	@Test
	public void attrezzoNonEsiste_inBorsaVuota() {
		Borsa borsaVuota=new Borsa();
		assertNull(borsaVuota.getAttrezzo("vuoto"));
	}
	
	/****** TEST PER LA FUNZIONE isEmpty *******/
	
	// verifichiamo che la borsa sia vuota
	@Test
	public void verificaBorsaVuota() {
		assertTrue(borsa.isEmpty());
	}
	
	// verifichiamo che la borsa non sia vuota
	@Test
	public void verificaBorsaNonVuota() {
		Attrezzo attrezzoPresente=new Attrezzo("attrezzoPresente", 5);
		borsa.addAttrezzo(attrezzoPresente);
		assertFalse(borsa.isEmpty());
	}
	
	// verifichiamo disuguaglianza tra una borsa vuota e una non vuota
	@Test
	public void verificaDisuguaglianzaTraBorse() {
		Attrezzo attrezzoInBorsaNonVuota=new Attrezzo("attrezzoInBorsaNonVuota", 3);
		borsa.addAttrezzo(attrezzoInBorsaNonVuota);
		Borsa borsaVuota=new Borsa();
		assertNotEquals(borsa.isEmpty(), borsaVuota.isEmpty());
	}
	
	/****** TEST PER LA FUNZIONE getPeso *********/
	
	// verifichiamo che una borsa vuota ha peso 0
	@Test
	public void pesoBorsaVuota() {
		assertEquals(0, borsa.getPeso());
	}
	
	// verifichiamo che una borsa con 1 attrezzo ha il peso di quell'attrezzo
	@Test
	public void pesoBorsaNonVuota() {
		Attrezzo attrezzoPesato=new Attrezzo("attrezzoPesato", 5);
		borsa.addAttrezzo(attrezzoPesato);
		assertEquals(borsa.getPeso(), 5);
	}
	
	// verifichiamo che una borsa non vuota non abbia il peso sbagliato
	@Test
	public void pesoBorsaNonVuota_nonErrato() {
		Attrezzo attrezzoPesato=new Attrezzo("attrezzoPesato", 5);
		borsa.addAttrezzo(attrezzoPesato);
		assertNotEquals(borsa.getPeso(), 8);
	}
	
	
	
	
	
	
	
	
	
}
