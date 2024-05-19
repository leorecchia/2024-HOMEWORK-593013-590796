package it.uniroma3.diadia.giocatore.test;

import static org.junit.Assert.*;

//import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa borsa;

	@Before
	public void setUp(){
		borsa=new Borsa(50);
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
		//System.out.println(borsa.toString());
		assertNotEquals(borsa.getPeso(), 8);
	}
	
	/******* TEST PER LA FUNZIONE getContenutoOrdinatoPerPeso ***********/
	
	// verifico che se invoco la funzione su una borsa vuota mi restituisce a sua volta una lista vuota
	@Test
	public void listaBorsaVuota() {
		assertTrue(this.borsa.getContenutoOrdinatoPerPeso().isEmpty());
	}
	
	// verifico che se invoco la funzione su una borsa con 1 elemento mi restituisce a sua volta una lista con 1 elemento
	@Test
	public void listaBorsaCon1Elemento() {
		Attrezzo a1=new Attrezzo("a1", 2);
		this.borsa.addAttrezzo(a1);
		List<Attrezzo> listaAttrezzi=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(listaAttrezzi.get(0).getPeso(), 2);
		assertEquals(1, listaAttrezzi.size());
	}
	
	// verifico che se invoco la funzione su una borsa con due elementi con peso diverso me li ordina in modo corretto
	@Test 
	public void listaBorsaCon2Elementi_pesoDiverso() {
		Attrezzo a1=new Attrezzo("a1", 3);
		Attrezzo a2=new Attrezzo("a2", 2);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		List<Attrezzo> listaAttrezzi=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(listaAttrezzi.get(0).getPeso(), 2);
		assertEquals(listaAttrezzi.get(1).getPeso(), 3);
	}
	
	// verifico che se invoco la funzione su una borsa con due elementi con stesso peso me li ordina in modo corretto rispetto al nome
	@Test 
	public void listaBorsaCon2Elementi_stessoPeso() {
		Attrezzo a1=new Attrezzo("a1", 2);
		Attrezzo a2=new Attrezzo("a2", 2);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		List<Attrezzo> listaAttrezzi=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(listaAttrezzi.get(0).getNome(), "a1");
		assertEquals(listaAttrezzi.get(1).getNome(), "a2");
	}
	
	@Test 
	public void listaBorsaCon2ElementiConStessoPesoEunodiverso() {
		Attrezzo a1=new Attrezzo("a1", 2);
		Attrezzo a2=new Attrezzo("a2", 2);
		Attrezzo a3=new Attrezzo("a3", 1);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a3);
		this.borsa.addAttrezzo(a1);
		List<Attrezzo> listaAttrezzi=this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(listaAttrezzi.get(0).getNome(), "a3");
		assertEquals(listaAttrezzi.get(1).getNome(), "a1");
		assertEquals(listaAttrezzi.get(2).getNome(), "a2");
	}
	
/******* TEST PER LA FUNZIONE getContenutoOrdinatoPerNome ***********/
	
	// verifico che se invoco la funzione su una borsa vuota mi restituisce a sua volta una lista vuota
	@Test
	public void setBorsaVuota() {
		assertTrue(this.borsa.getContenutoOrdinatoPerNome().isEmpty());
	}
	
	// verifico che se invoco la funzione su una borsa con 1 elemento mi restituisce a sua volta una lista con 1 elemento
	@Test
	public void setBorsaCon1Elemento() {
		Attrezzo a1=new Attrezzo("a1", 2);
		this.borsa.addAttrezzo(a1);
		Set<Attrezzo> setAttrezzi=this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratoreAttrezzo=setAttrezzi.iterator();
		assertEquals(1, setAttrezzi.size());
		assertEquals("a1", iteratoreAttrezzo.next().getNome());
	}
	
	// verifico che se invoco la funzione su una borsa con due elementi con peso diverso me li ordina in modo corretto
	@Test 
	public void setBorsaCon2Elementi_nomeDiverso() {
		Attrezzo a1=new Attrezzo("a1", 3);
		Attrezzo a2=new Attrezzo("a2", 2);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		Set<Attrezzo> setAttrezzi=this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> iteratore=setAttrezzi.iterator();
		assertEquals("a1", iteratore.next().getNome());
		assertEquals("a2", iteratore.next().getNome());
	}	
	
	/******* TEST PER LA FUNZIONE getContenutoRaggruppatoPerPeso ***********/
	@Test
	public void mapBorsaVuota() {
		assertTrue(this.borsa.getContenutoRaggruppatoPerPeso().isEmpty());
	}
	
	@Test
	public void mapBorsaConDueElementiPesoDiverso() {
		
		Attrezzo a1=new Attrezzo("a1", 4);
		Attrezzo a2=new Attrezzo("a2", 2);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		Map<Integer, Set<Attrezzo>> mapAttrezzi=this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(2, mapAttrezzi.size());
		Set<Attrezzo> setAttrezzoPrimoPeso=mapAttrezzi.get(4);
		assertEquals(1, setAttrezzoPrimoPeso.size());
		Set<Attrezzo> setAttrezzoSecondoPeso=mapAttrezzi.get(2);
		assertEquals(1, setAttrezzoSecondoPeso.size());
		assertTrue(setAttrezzoPrimoPeso.contains(a1));
		assertTrue(setAttrezzoSecondoPeso.contains(a2));
		
	}
	
	
	/* DA CHIEDERE INSERIMENTO ORDINATO E/O NON */
	@Test
	public void mapBorsaConDueElementiPesoUguale() {
		
		Attrezzo a2=new Attrezzo("a2", 4);
		Attrezzo a1=new Attrezzo("a1", 4);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		Map<Integer, Set<Attrezzo>> mapAttrezzi=this.borsa.getContenutoRaggruppatoPerPeso();
		//System.out.println(mapAttrezzi);
		assertEquals(1, mapAttrezzi.size());
		Set<Attrezzo> setAttrezzoStessoPeso=mapAttrezzi.get(4);
		assertEquals(2, setAttrezzoStessoPeso.size());
		assertTrue(setAttrezzoStessoPeso.contains(a1));
		assertTrue(setAttrezzoStessoPeso.contains(a2));
	}
	
	@Test
	public void mapBorsaConDueElementiPesoUgualeEunoDiverso() {
		
		Attrezzo a1=new Attrezzo("a1", 4);
		Attrezzo a2=new Attrezzo("a2", 2);
		Attrezzo a3=new Attrezzo("a3", 4);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a3);
		Map<Integer, Set<Attrezzo>> mapAttrezzi=this.borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(2, mapAttrezzi.size());
		Set<Attrezzo> setAttrezzoPrimoPeso=mapAttrezzi.get(4);
		assertEquals(2, setAttrezzoPrimoPeso.size());
		Set<Attrezzo> setAttrezzoSecondoPeso=mapAttrezzi.get(2);
		assertEquals(1, setAttrezzoSecondoPeso.size());
		assertTrue(setAttrezzoPrimoPeso.contains(a3));
		assertTrue(setAttrezzoSecondoPeso.contains(a2));
		
	}
	
	
	/******* TEST PER LA FUNZIONE getSortedSetOrdinatoPerPeso ***********/
	@Test
	public void sortedSetVuoto() {
		assertEquals(0, this.borsa.getSortedSetOrdinatoPerPeso().size());
	}
	
	@Test
	public void sortedSetDueElementiPesoDiverso() {
		Attrezzo a1=new Attrezzo("a1", 3);
		Attrezzo a2=new Attrezzo("a2", 5);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		Set<Attrezzo> sorted=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> itAttrezzi=sorted.iterator();
		assertEquals(3, itAttrezzi.next().getPeso());
		assertEquals(5, itAttrezzi.next().getPeso());
	}
	
	@Test
	public void sortedSetDueElementiPesoUguale() {
		Attrezzo a1=new Attrezzo("a1", 3);
		Attrezzo a2=new Attrezzo("a2", 3);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		Set<Attrezzo> sorted=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> itAttrezzi=sorted.iterator();
		assertEquals("a1", itAttrezzi.next().getNome());
		assertEquals("a2", itAttrezzi.next().getNome());
	}
	
	@Test
	public void sortedSetDueElementiPesoUgualeEunoDiverso() {
		Attrezzo a1=new Attrezzo("a1", 3);
		Attrezzo a2=new Attrezzo("a2", 3);
		Attrezzo a3=new Attrezzo("a3", 4);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a3);
		Set<Attrezzo> sorted=this.borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> itAttrezzi=sorted.iterator();
		//assertEquals("a3", itAttrezzi.next().getNome());
		assertEquals("a1", itAttrezzi.next().getNome());
		assertEquals("a2", itAttrezzi.next().getNome());
		assertEquals("a3", itAttrezzi.next().getNome());
	}
	
	
	
}
