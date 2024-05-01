package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {

	private Partita game ;
	
	@Before
	public void setUp () {
		game = new Partita();		
	}
	
	
	/*********** TEST METODO getBorsa ***************/
	//verifichiamo che la borsa sia vuota
	@Test 
	public void testGetBorsa_Vuota () {
		Giocatore playerConBorsaVuota = game.getGiocatore();
		assertTrue (playerConBorsaVuota.getBorsa().isEmpty());
	}
	
	//verifichiamo che la borsa non sia vuota
	@Test 
	public void testGetBorsa_NonVuota () {
		Giocatore playerConBorsaNonVuota = game.getGiocatore();
		Attrezzo attrezzoInBorsa = new Attrezzo("attrezzoInBorsa", 1);
		playerConBorsaNonVuota.getBorsa().addAttrezzo(attrezzoInBorsa);
		assertFalse (playerConBorsaNonVuota.getBorsa().isEmpty());
	}
	
	//verifichiamo che la borsa del giocatore contenga uno specifico attrezzo
	@Test 
	public void testGetBorsa_conAttrezzoPresente () {
		Giocatore playerConBorsaNonVuota = game.getGiocatore();
		Attrezzo attrezzoPresente = new Attrezzo("attrezzoPresente", 1);
		playerConBorsaNonVuota.getBorsa().addAttrezzo(attrezzoPresente);
		assertTrue (playerConBorsaNonVuota.getBorsa().hasAttrezzo("attrezzoPresente"));
	}
	
	
	/*********** TEST METODO getCfu ***************/
	
	//test per controllare se il numero di cfu settati alla creazione della partita sia pari a 20
	@Test 
	public void testGetCfu_inizialiSono20 () {
		assertEquals (20, game.getGiocatore().getCfu());
	}
	
	//test per controllare se i Cfu siano diversi da zero in una partita appena iniziata
	@Test
	public void testGetCfu_diversiDa0Allinizio () {
		assertNotEquals (0, game.getGiocatore().getCfu());
	}
	
	//test per controllare se cambiando il numero di Cfu ottengo il numero inserito
	@Test 
	public void testGetCfu_cfuInizialiNonSbagliati () {
		assertNotEquals (10, game.getGiocatore().getCfu());
	}
	
}
