package it.uniroma3.diadia.ambienti.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	
	private StanzaMagica stanzaMagica;
	
	@Test
	public void sogliaMagicaNonRaggiunta_nonCambiaNome() {
		stanzaMagica=new StanzaMagica("stanzaMagica", 1);
		Attrezzo attrezzoNonMagico=new Attrezzo("attrezzoNonMagico", 1);
		stanzaMagica.addAttrezzo(attrezzoNonMagico);
		assertTrue(stanzaMagica.hasAttrezzo("attrezzoNonMagico"));
	}
	
	@Test
	public void sogliaMagicaNonRaggiunta_nonRaddoppiaPeso() {
		stanzaMagica=new StanzaMagica("stanzaMagica", 1);
		Attrezzo attrezzoNonMagico=new Attrezzo("attrezzoNonMagico", 1);
		stanzaMagica.addAttrezzo(attrezzoNonMagico);
		assertEquals(stanzaMagica.getAttrezzo("attrezzoNonMagico").getPeso(), 1);
	}
	
	@Test
	public void aggiungeAttrezzoAncheSeSogliaRaggiunta() {
		Attrezzo daAggiungere=new Attrezzo("daAggiungere", 5);
		stanzaMagica=new StanzaMagica("stanzaMagica", 0);
		assertTrue(stanzaMagica.addAttrezzo(daAggiungere));
	}
	
	@Test
	public void sogliaMagicaRaggiunta_cambiaNome() {
		stanzaMagica=new StanzaMagica("stanzaMagica", 0);
		Attrezzo attrezzoMagico=new Attrezzo("attrezzoMagico", 2);
		stanzaMagica.addAttrezzo(attrezzoMagico);
		assertTrue(stanzaMagica.hasAttrezzo("ocigaMozzertta"));
	}
	
	@Test
	public void sogliaMagicaRaggiunta_raddoppiaPeso() {
		stanzaMagica=new StanzaMagica("stanzaMagica", 0);
		Attrezzo attrezzoMagico=new Attrezzo("attrezzoMagico", 2);
		stanzaMagica.addAttrezzo(attrezzoMagico);
		assertEquals(stanzaMagica.getAttrezzo("ocigaMozzertta").getPeso(), 4);
	}
	
	@Test
	public void uguaglianza() {
		StanzaMagica magica1=new StanzaMagica("magica", 1);
		StanzaMagica magica2=new StanzaMagica("magica", 1);
		assertEquals(magica1, magica2);
	}
	
	@Test
	public void disuguaglianza() {
		StanzaMagica m1=new StanzaMagica("magica", 2);
		StanzaMagica m2=new StanzaMagica("magica", 1);
		assertNotEquals(m1, m2);
	}
	
	
}












