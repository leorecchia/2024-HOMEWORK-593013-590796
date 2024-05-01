package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	@Test
	public void testGetDescrizione_attrezzoIlluminanteAssente() {
		StanzaBuia stanzaBuiaSenzaAttrezzoIlluminante = new StanzaBuia("stanzaBuiaSenzaAttrezzoIlluminante", "attrezzoAssente");
		assertEquals ("qui c'è buio pesto", stanzaBuiaSenzaAttrezzoIlluminante.getDescrizione());
	}
	
	@Test
	public void testGetDescrizione_attrezzoIlluminantePresente() {
		Attrezzo attrezzoIlluminante = new Attrezzo("attrezzoIlluminante", 1);
		StanzaBuia stanzaBuiaConAttrezzoIlluminante = new StanzaBuia("stanzaBuiaConAttrezzoIlluminante", "attrezzoIlluminante");
		stanzaBuiaConAttrezzoIlluminante.addAttrezzo(attrezzoIlluminante);
		assertNotEquals ("qui c'è buio pesto", stanzaBuiaConAttrezzoIlluminante.getDescrizione());
	}

}
