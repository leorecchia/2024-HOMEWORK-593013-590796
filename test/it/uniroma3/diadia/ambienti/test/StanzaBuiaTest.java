package it.uniroma3.diadia.ambienti.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private StanzaBuia stanzaBuia;
	private Attrezzo attrezzoIlluminante;

	@Before
	public void setUp() {
		stanzaBuia=new StanzaBuia("stanzaBuia", "attrezzoIlluminante");
	}
	
	@Test
	public void attrezzoIlluminanteAssente() {
		assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
	}
	
	@Test 
	public void attrezzoIlluminantePresente() {
		attrezzoIlluminante=new Attrezzo("attrezzoIlluminante", 1);
		stanzaBuia.addAttrezzo(attrezzoIlluminante);
		assertNotEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
	}
	
	@Test
	public void uguaglianza() {
		StanzaBuia buia1=new StanzaBuia("buia", "luce");
		StanzaBuia buia2=new StanzaBuia("buia", "luce");
		assertEquals(buia1, buia2);
	}
	
	@Test
	public void disuguaglianza() {
		StanzaBuia buia1=new StanzaBuia("buia", "luce");
		StanzaBuia buia2=new StanzaBuia("buia", "luci");
		assertNotEquals(buia1, buia2);
	}
	

}
