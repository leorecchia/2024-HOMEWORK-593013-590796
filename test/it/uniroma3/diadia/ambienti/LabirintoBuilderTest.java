package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoBuilderTest {
	
	private Labirinto labirinto;

	
	/***************** TEST addStanzaIniziale *****************/
	@Test
	public void testAddStanzaIniziale_stanzaInizialeCorretta() {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("stanzaIniziale");
		assertEquals ("stanzaIniziale", labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAddStanzaIniziale_stanzaInizialeNull() {
		labirinto = new LabirintoBuilder();
		assertNull (labirinto.getStanzaIniziale());
	}

	
	
	/***************** TEST addStanzaVincente *****************/
	@Test
	public void testAddStanzaVincente_stanzaVincenteCorretta() {
		labirinto = new LabirintoBuilder()
				.addStanzaVincente("stanzaVincente");
		assertEquals ("stanzaVincente", labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testAddStanzaVincente_stanzaVincenteNull() {
		labirinto = new LabirintoBuilder();
		assertNull (labirinto.getStanzaVincente());
	}

	
	
	/************** TEST addStanza *****************/
	@Test
	public void testAddStanza() {
		labirinto = new LabirintoBuilder();
	}

	
	
	/***************** TEST addAdiacenza *****************/
	@Test
	public void testAddAdiacenza_corretta() {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("stanzaCorrente")
				.addStanza("stanzaAdiacente")
				.addAdiacenza("stanzaCorrente", "stanzaAdiacente", Direzione.NORD);
		assertEquals ("stanzaAdiacente", labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
	}
	
	@Test
	public void testAddAdiacenza_nullaSeUnaDelleStanzeNonEsiste() {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("stanzaCorrente")
				.addAdiacenza("stanzaCorrente", "stanzaAdiacente", Direzione.NORD);
		assertNull (labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD));
	}

	
	
	/***************** TEST addAttrezzo *****************/
	@Test
	public void testAddAttrezzo_attrezzoAggiuntoAUltimaStanza() {
		labirinto = new LabirintoBuilder()
				.addStanza("nomeStanzaSenzaAttrezzo")
				.addStanzaIniziale("nomeStanzaConAttrezzo")
				.addAttrezzo("nomeAttrezzo", 1);
		assertTrue (labirinto.getStanzaIniziale().hasAttrezzo("nomeAttrezzo"));
	}
	
	@Test
	public void testAddAttrezzo_attrezzoNonAggiuntoAlPrimo() {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("nomeStanzaSenzaAttrezzo")
				.addStanza("nomeStanzaConAttrezzo")
				.addAttrezzo("nomeAttrezzo", 1);
		assertFalse (labirinto.getStanzaIniziale().hasAttrezzo("nomeAttrezzo"));
	}

}
