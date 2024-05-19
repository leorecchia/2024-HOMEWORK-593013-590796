package it.uniroma3.diadia.ambienti.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class LabirintoBuilderTest {
	
	private LabirintoBuilder labirinto;
	
	
	/******* TEST PER addStanzaIniziale ********/
	@Test
	public void testInizialeNullo() {
		this.labirinto=new LabirintoBuilder().getLabirinto();
		assertNull(labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testStanzaIniziale() {
		this.labirinto=new LabirintoBuilder().addStanzaIniziale("iniziale");
		assertEquals("iniziale", labirinto.getStanzaIniziale().getNome());
	}
	
	
	/******* TEST PER addStanzaVincente ********/
	@Test
	public void testVincenteNullo() {
		this.labirinto=new LabirintoBuilder().getLabirinto();
		assertNull(labirinto.getStanzaVincente());
	}
	
	@Test
	public void testStanzaVincente() {
		this.labirinto=new LabirintoBuilder().addStanzaVincente("vincente");
		assertEquals("vincente", labirinto.getStanzaVincente().getNome());
	}
	
	
	/********** TEST PER addAttrezzo **********/
	@Test
	public void inizialeContieneAttrezzo() {
		this.labirinto=new LabirintoBuilder().addStanzaIniziale("iniziale").addAttrezzo("attrezzo1", 1);
		assertTrue(labirinto.getStanzaIniziale().hasAttrezzo("attrezzo1"));
	}
	
	@Test
	public void inizialeNonContieneAttrezzo() {
		this.labirinto=new LabirintoBuilder().addStanzaIniziale("iniziale")
				.addStanzaVincente("vincente")
				.addAttrezzo("attrezzo1", 1);
		assertFalse(labirinto.getStanzaIniziale().hasAttrezzo("attrezzo1"));
		assertTrue(labirinto.getStanzaVincente().hasAttrezzo("attrezzo1"));
	}
	
	
	/********* TEST PER addAdiacenza **********/
	@Test
	public void adiacenzaCorretta() {
		this.labirinto=new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addStanzaVincente("vincente")
				.addAdiacenza("iniziale", "vincente", "nord");
		assertEquals("vincente", labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void adiacenzaNulla() {
		this.labirinto=new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addAdiacenza("iniziale", "stanza2", "nord");
		assertNull(labirinto.getStanzaIniziale().getStanzaAdiacente("nord"));
	}
	
	/********** TEST PER addStanza ************/
	@Test
	public void stanzaNomeNullo() {
		this.labirinto=new LabirintoBuilder().addStanza(null);
		assertTrue(labirinto.getStanze().isEmpty());
	}

	@Test
	public void aggiuntaStanza() {
		this.labirinto=new LabirintoBuilder().addStanza("aggiunta");
		assertEquals(1, this.labirinto.getStanze().size());
		assertTrue(this.labirinto.getStanze().containsKey("aggiunta"));
	}
	
	@Test
	public void aggiunteStanze() {
		this.labirinto=new LabirintoBuilder().addStanza("aggiunta1").addStanza("aggiunta2");
		assertEquals(2, this.labirinto.getStanze().size());
		assertTrue(this.labirinto.getStanze().containsKey("aggiunta1"));
		assertTrue(this.labirinto.getStanze().containsKey("aggiunta2"));
	}

}












