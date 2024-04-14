package it.uniroma3.diadia.giocatore.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	
	private Partita partita;
	
	@Before
	public void setUp() {
		partita=new Partita();
	}

	/******* TEST METODO getCfu ******/
	
	// Inizialmente la partita viene creata con 20 cfu, quindi verifico che i cfu della partita siano tali
	@Test
	public void ventiCfu() {
		assertEquals(20, partita.getGiocatore().getCfu());
	}
	
	// con la funzione setCfu cambio il numero di cfu della partita in 15 e verifico che il cambiamento sia avvenuto
	@Test
	public void nuoviCfu() {
		partita.getGiocatore().setCfu(15);
		assertEquals(15, partita.getGiocatore().getCfu());
	}
	
	// verifichiamo che non ci dia un numero sbgaliato di cfu
	@Test
	public void nonUguali() {
		partita.getGiocatore().setCfu(5);
		assertNotEquals(0, partita.getGiocatore().getCfu());
	}
	
	/******* TEST METODO getBorsa *********/
	
	// verifichiamo che la borsa del giocatore sia vuota
	@Test
	public void giocatoreHaBorsaVuota() {
		Giocatore giocatoreConBorsaVuota=partita.getGiocatore();
		assertTrue(giocatoreConBorsaVuota.getBorsa().isEmpty());
	}
	
	// verifichiamo che la borsa del giocatore non sia vuota
	@Test
	public void giocatoreHaBorsaNonVuota() {
		Giocatore giocatoreConBorsaNonVuota=partita.getGiocatore();
		Attrezzo attrezzoAggiunto=new Attrezzo("attrezzoAggiunto", 5);
		giocatoreConBorsaNonVuota.getBorsa().addAttrezzo(attrezzoAggiunto);
		assertFalse(giocatoreConBorsaNonVuota.getBorsa().isEmpty());
	}
	
	// verifichiamo che la borsa del giocatore contenga uno specifico attrezzo
	@Test
	public void borsaConAttrezzoGiusto() {
		Giocatore giocatoreConBorsaConAttrezzo=partita.getGiocatore();
		Attrezzo attrezzoPresente=new Attrezzo("attrezzoPresente", 5);
		giocatoreConBorsaConAttrezzo.getBorsa().addAttrezzo(attrezzoPresente);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzoPresente"));
	}
}






