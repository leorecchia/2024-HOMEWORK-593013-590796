package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class ComandoPrendiTest {
	
	private FabbricaDiComandiFisarmonica factory;
	private Partita partita;
	private IO io = new IOConsole();
	@Before
	public void setUp () {
		partita = new Partita();
		factory = new FabbricaDiComandiFisarmonica(io);
	}
		
	@Test
	public void testEsegui_attrezzoInesistente() {
		Comando comandoOggettoInesistente = factory.costruisciComando("prendi NomeOggettoInesistente");
		comandoOggettoInesistente.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testEsegui_attrezzoEsistentePresoDallaStanza() {
		Comando comandoOggettoEsistente = factory.costruisciComando("prendi osso");
		comandoOggettoEsistente.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testEsegui_attrezzoEsistenteMessoNellaBorsa() {
		Comando comandoOggettoEsistente = factory.costruisciComando("prendi osso");
		comandoOggettoEsistente.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

}
