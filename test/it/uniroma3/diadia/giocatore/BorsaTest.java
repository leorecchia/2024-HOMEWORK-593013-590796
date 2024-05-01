package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class BorsaTest {
	
	private Borsa bag;
	
	@Before
	public void setUp() {
		bag = new Borsa();
	}
	
	/*********** TEST METODO getAttrezzo ***************/
	
	//verifichiamo che sia presente un attrezzo specifico nella borsa
	@Test
	public void testGetAttrezzo_attrezzoPresente () {
		Attrezzo attrezzoPresente = new Attrezzo("attrezzoPresente", 1);
		bag.addAttrezzo(attrezzoPresente);
		assertNotNull (bag.getAttrezzo("attrezzoPresente"));
	}
	
	//verichiamo che in una borsa non vuota non risulti presente un attrezzo assente nella borsa
	@Test
	public void testGetAttrezzo_attrezzoNonPresente () {
		Attrezzo attrezzoPresente = new Attrezzo("attrezzoPresente", 1);
		bag.addAttrezzo(attrezzoPresente);
		assertNull (bag.getAttrezzo("attrezzoAssente"));
	}
	
	//verifichiamo che non esista uno specifico attrezzo quando la borsa è vuota
	@Test
	public void testGetAttrezzo_attrezzoAssente_inBorsaVuota () {
		Borsa bagVuota = new Borsa();
		assertNull (bagVuota.getAttrezzo("attrezzoAssente"));
	}

	
	/************* TEST METODO isEmpty *****************/
	
	//verifichiamo che una borsa vuota risulti vuota
	@Test
	public void testIsEmpty_borsaVuota () {
		Borsa bagVuota = new Borsa();
		assertTrue (bagVuota.isEmpty());
	}
	
	//verifichiamo che una borsa non vuota risulti non vuota
	@Test
	public void testIsEmpty_borsaNonVuota () {
		Borsa bagNonVuota = new Borsa();
		Attrezzo attrezzoPresente = new Attrezzo ("attrezzoPresente", 0);
		bagNonVuota.addAttrezzo(attrezzoPresente);
		assertFalse (bagNonVuota.isEmpty());
	}
	
	//verifichiamo che una borsa non vuota e una borsa non vuota non danno lo stesso risultato
	@Test
	public void testIsEmpty_borsaNonVuota_nonRisultaVuota () {
		Borsa bagVuota = new Borsa();
		Borsa bagNonVuota = new Borsa();
		Attrezzo attrezzoPresente = new Attrezzo ("attrezzoPresente", 0);
		bagNonVuota.addAttrezzo(attrezzoPresente);
		assertNotEquals (bagVuota.isEmpty(), bagNonVuota.isEmpty());
	}
	
	
	/*********** TEST METODO getPeso ***************/
	
	//verificare che una borsa vuota ha peso 0
	@Test
	public void testGetPeso_borsaVuota () {
		Borsa bagVuota = new Borsa();
		assertEquals (0, bagVuota.getPeso());
	}
	
	//verificare che una borsa con un attrezzo la borsa abbia il peso dell'attrezzo
	@Test
	public void testGetPeso_borsaNonVuota () {
		Borsa bagNonVuota = new Borsa();
		Attrezzo attrezzoPresente = new Attrezzo ("attrezzoPresente", 10);
		bagNonVuota.addAttrezzo(attrezzoPresente);
		assertEquals (attrezzoPresente.getPeso(), bagNonVuota.getPeso());
	}
	
	//verificare che in una borsa non vuota il valore restituito non sia errato
	@Test
	public void testGetPeso_borsaNonVuota_nonErrato () {
		Borsa bagNonVuota = new Borsa();
		Attrezzo attrezzoPresente = new Attrezzo ("attrezzoPresente", 10);
		bagNonVuota.addAttrezzo(attrezzoPresente);
		Attrezzo attrezzoNonPresente = new Attrezzo ("attrezzoNonPresente", 5);
		assertNotEquals (attrezzoNonPresente.getPeso(), bagNonVuota.getPeso());
	}
}
