package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

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
	
	
	
	
	
	/*********** TEST METODO getContenutoOrdinatoPerPeso ***************/

	//verificare che una borsa vuota sia effettivamente vuota
	@Test
	public void testGetContenutoOrdinatoPerPeso_borsaVuota () {
		Borsa bagVuota = new Borsa();
		assertTrue(bagVuota.getContenutoOrdinatoPerPeso().isEmpty());
	}
	
	//verificare che in una borsa con 1 elemento abbia un solo elemento e che sia restituito come primo
	@Test
	public void testGetContenutoOrdinatoPerPeso_borsaCon1elemento () {
		Borsa bag1elemento = new Borsa();
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		bag1elemento.addAttrezzo(attrezzo);
		assertEquals(1, bag1elemento.getContenutoOrdinatoPerPeso().size());
		assertEquals("attrezzo", bag1elemento.getContenutoOrdinatoPerPeso().get(0).getNome());
	}
	
	
	//verificare che in una borsa con 2 elementi questi vengano restituiti in ordine di peso
	@Test
	public void testGetContenutoOrdinatoPerPeso_borsaCon2elementiPesoDiverso () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzo1 = new Attrezzo("attrezzoCheVaPerSecondo", 3);
		Attrezzo attrezzo2 = new Attrezzo("attrezzoCheVaPerPrimo", 1);
		bag2elementi.addAttrezzo(attrezzo1);
		bag2elementi.addAttrezzo(attrezzo2);
		assertEquals("attrezzoCheVaPerPrimo", bag2elementi.getContenutoOrdinatoPerPeso().get(0).getNome());
		assertEquals("attrezzoCheVaPerSecondo", bag2elementi.getContenutoOrdinatoPerPeso().get(1).getNome());
	}
	
	//verificare che una borsa con 2 elementi con stesso peso vengano restituiti in ordine in base al nome
	@Test
	public void testGetContenutoOrdinatoPerPeso_borsaCon2elementiStessoPeso () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzo1 = new Attrezzo("attrezzoA_primo", 3);
		Attrezzo attrezzo2 = new Attrezzo("attrezzoB_secondo", 3);
		bag2elementi.addAttrezzo(attrezzo1);
		bag2elementi.addAttrezzo(attrezzo2);
		assertEquals("attrezzoA_primo", bag2elementi.getContenutoOrdinatoPerPeso().get(0).getNome());
		assertEquals("attrezzoB_secondo", bag2elementi.getContenutoOrdinatoPerPeso().get(1).getNome());
	}
	
	
	//verificare che una borsa con 2 elementi con peso uguale e uno con con peso diverso (maggiore) 
	//questi vengano restituiti quelli con peso uguale in ordine di nome e il terzo di peso
	@Test
	public void testGetContenutoOrdinatoPerPeso_borsacon3Elementi_2pesoUguale () {
		Borsa bag3elementi = new Borsa();
		Attrezzo attrezzoUGUALE1 = new Attrezzo("attrezzoA1", 1);
		Attrezzo attrezzoUGUALE2 = new Attrezzo("attrezzoB2", 1);
		Attrezzo attrezzoDIVERSO = new Attrezzo("attrezzoC3", 2);
		bag3elementi.addAttrezzo(attrezzoUGUALE1);
		bag3elementi.addAttrezzo(attrezzoUGUALE2);
		bag3elementi.addAttrezzo(attrezzoDIVERSO);
		assertEquals(3, bag3elementi.getContenutoOrdinatoPerPeso().size());
		assertEquals("attrezzoA1", bag3elementi.getContenutoOrdinatoPerPeso().get(0).getNome());
		assertEquals("attrezzoB2", bag3elementi.getContenutoOrdinatoPerPeso().get(1).getNome());
		assertEquals("attrezzoC3", bag3elementi.getContenutoOrdinatoPerPeso().get(2).getNome());
	}
	
	
	
	/********** TEST METODO  getContenutoOrdinatoPerNome ***************/
	
	//verificare che una borsa vuota sia effettivamente vuota
	@Test
	public void testGetContenutoOrdinatoPerNome_borsaVuota () {
		Borsa bagVuota = new Borsa();
		assertTrue(bagVuota.getContenutoOrdinatoPerNome().isEmpty());
	}
	
	//verificare che in una borsa con 1 elemento abbia un solo elemento e che sia restituito come primo
	@Test
	public void testGetContenutoOrdinatoPerNome_borsaCon1elemento () {
		Borsa bag1elemento = new Borsa();
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		bag1elemento.addAttrezzo(attrezzo);
		assertEquals(1, bag1elemento.getContenutoOrdinatoPerNome().size());
		Iterator<Attrezzo> iteratoreAttrezzi = bag1elemento.getContenutoOrdinatoPerNome().iterator();
		assertEquals("attrezzo", iteratoreAttrezzi.next().getNome());
	}
	
	//verificare che in una borsa con 2 elementi questi vengano restituiti in ordine di nome
	@Test
	public void testGetContenutoOrdinatoPerNome_borsaCon2elementi () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzo1 = new Attrezzo("attrezzoB_secondo", 3);
		Attrezzo attrezzo2 = new Attrezzo("attrezzoA_primo", 1);
		bag2elementi.addAttrezzo(attrezzo1);
		bag2elementi.addAttrezzo(attrezzo2);
		Iterator<Attrezzo> iteratoreAttrezzi = bag2elementi.getContenutoOrdinatoPerNome().iterator();
		assertEquals("attrezzoA_primo", iteratoreAttrezzi.next().getNome());
		assertEquals("attrezzoB_secondo", iteratoreAttrezzi.next().getNome());
	}
	
	
	
	/*********** TEST METODO getContenutoRaggruppatoPerPeso ***************/
	
	//verificare che una borsa vuota sia effettivamente vuota
	@Test
	public void testGetContenutoRaggruppatoPerPeso_borsaVuota () {
		Borsa bagVuota = new Borsa();
		assertTrue(bagVuota.getContenutoRaggruppatoPerPeso().isEmpty());
	}
	
	//verificare che in una borsa con 2 elementi con peso diverso questi vengano restituiti in due set diversi
	@Test
	public void testGetContenutoRaggruppatoPerPeso_borsaCon2elementi_pesoDiverso () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzoPesoMin = new Attrezzo("attrezzoPesoMin", 1);
		Attrezzo attrezzoPesoMax = new Attrezzo("attrezzoPesoMax", 2);
		bag2elementi.addAttrezzo(attrezzoPesoMin);
		bag2elementi.addAttrezzo(attrezzoPesoMax);
		assertEquals(2, bag2elementi.getContenutoRaggruppatoPerPeso().size());	
		Set<Attrezzo> setPrimoElemento = bag2elementi.getContenutoRaggruppatoPerPeso().get(1);
		Set<Attrezzo> setSecondoElemento = bag2elementi.getContenutoRaggruppatoPerPeso().get(2);
		assertEquals(1, setPrimoElemento.size());
		assertEquals(1, setSecondoElemento.size());
		assertTrue("attrezzoPesoMin", setPrimoElemento.contains(attrezzoPesoMin));
		assertTrue("attrezzoPesoMax", setSecondoElemento.contains(attrezzoPesoMax));
	}
	
	
	//verificare che in una borsa con 2 elementi con peso diverso questi vengano restituiti nello stesso set
	@Test
	public void testGetContenutoRaggruppatoPerPeso_borsaCon2elementi_pesoUguale () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzoA1 = new Attrezzo("attrezzoA1", 1);
		Attrezzo attrezzoB2 = new Attrezzo("attrezzoB2", 1);
		bag2elementi.addAttrezzo(attrezzoB2);
		bag2elementi.addAttrezzo(attrezzoA1);
		assertEquals(1, bag2elementi.getContenutoRaggruppatoPerPeso().size());
		Set<Attrezzo> setPrimoElemento = bag2elementi.getContenutoRaggruppatoPerPeso().get(1);
		assertEquals(2, setPrimoElemento.size());
		assertTrue (setPrimoElemento.contains(attrezzoA1));
		assertTrue(setPrimoElemento.contains(attrezzoB2));
	}
	
	
	//verificare che una borsa con 2 elementi con peso uguale e uno con con peso diverso (maggiore) 
	//questi vengano restituiti quelli con peso uguale in un set e il terzo in un secondo set
	@Test
	public void testGetContenutoRaggruppatoPerPeso_borsacon3Elementi_2pesoUguale () {
		Borsa bag3elementi = new Borsa();
		Attrezzo attrezzoUGUALE1 = new Attrezzo("attrezzoA1", 1);
		Attrezzo attrezzoUGUALE2 = new Attrezzo("attrezzoB2", 1);
		Attrezzo attrezzoDIVERSO = new Attrezzo("attrezzoC3", 2);
		bag3elementi.addAttrezzo(attrezzoUGUALE1);
		bag3elementi.addAttrezzo(attrezzoUGUALE2);
		bag3elementi.addAttrezzo(attrezzoDIVERSO);
		assertEquals(2, bag3elementi.getContenutoRaggruppatoPerPeso().size());
		Set<Attrezzo> setPrimoElemento = bag3elementi.getContenutoRaggruppatoPerPeso().get(1);
		Set<Attrezzo> setSecondoElemento = bag3elementi.getContenutoRaggruppatoPerPeso().get(2);
		assertEquals(2, setPrimoElemento.size());
		assertEquals(1, setSecondoElemento.size());
		assertTrue (setPrimoElemento.contains(attrezzoUGUALE1));
		assertTrue (setPrimoElemento.contains(attrezzoUGUALE2));
		assertTrue (setSecondoElemento.contains(attrezzoDIVERSO));
	}
	
	
	
	/*********** TEST METODO getSortedSetOrdinatoPerPeso ***************/
	
	//verificare che una borsa vuota sia effettivamente vuota
	@Test
	public void testGetSortedSetOrdinatoPerPeso_borsaVuota () {
		Borsa bagVuota = new Borsa();
		assertTrue(bagVuota.getContenutoRaggruppatoPerPeso().isEmpty());
	}
	
	//verificare che una borsa con 2 elementi con peso diverso questi vengano restituiti in ordine di peso
	@Test
	public void testGetSortedSetOrdinatoPerPeso_borsacon2Elementi_pesoDiverso () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzoPesoMin = new Attrezzo("attrezzoPesoMin", 1);
		Attrezzo attrezzoPesoMax = new Attrezzo("attrezzoPesoMax", 2);
		bag2elementi.addAttrezzo(attrezzoPesoMin);
		bag2elementi.addAttrezzo(attrezzoPesoMax);
		Iterator <Attrezzo> iterAttrezzi = bag2elementi.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals ("attrezzoPesoMin", iterAttrezzi.next().getNome());
		assertEquals ("attrezzoPesoMax", iterAttrezzi.next().getNome());
	}
	
	//verificare che una borsa con 2 elementi con peso uguale questi vengano restituiti in ordine di nome
	@Test
	public void testGetSortedSetOrdinatoPerPeso_borsacon2Elementi_pesoUguale () {
		Borsa bag2elementi = new Borsa();
		Attrezzo attrezzoA1 = new Attrezzo("attrezzoA1", 1);
		Attrezzo attrezzoB2 = new Attrezzo("attrezzoB2", 1);
		bag2elementi.addAttrezzo(attrezzoA1);
		bag2elementi.addAttrezzo(attrezzoB2);
		Iterator <Attrezzo> iterAttrezzi = bag2elementi.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals ("attrezzoA1", iterAttrezzi.next().getNome());
		assertEquals ("attrezzoB2", iterAttrezzi.next().getNome());
	}
	
	//verificare che una borsa con 2 elementi con peso uguale e uno con con peso diverso (maggiore) 
	//questi vengano restituiti quelli con peso uguale in ordine per nome e il terzo in ordine di peso
	@Test
	public void testGetSortedSetOrdinatoPerPeso_borsacon3Elementi_2pesoUguale () {
		Borsa bag3elementi = new Borsa();
		Attrezzo attrezzoUGUALE1 = new Attrezzo("attrezzoA1", 1);
		Attrezzo attrezzoUGUALE2 = new Attrezzo("attrezzoB2", 1);
		Attrezzo attrezzoDIVERSO = new Attrezzo("attrezzoC3", 2);
		bag3elementi.addAttrezzo(attrezzoUGUALE1);
		bag3elementi.addAttrezzo(attrezzoUGUALE2);
		bag3elementi.addAttrezzo(attrezzoDIVERSO);
		Iterator <Attrezzo> iterAttrezzi = bag3elementi.getSortedSetOrdinatoPerPeso().iterator();
		assertEquals ("attrezzoA1", iterAttrezzi.next().getNome());
		assertEquals ("attrezzoB2", iterAttrezzi.next().getNome());
		assertEquals ("attrezzoC3", iterAttrezzi.next().getNome());
	}
	

}
