package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class ComandoPrendiTest {
	
	private FabbricaDiComandiRiflessiva factory;
	private Partita partita;
	Scanner scannerDiLinee = new Scanner(System.in);
	private IO io = new IOConsole(scannerDiLinee);
	
	@Before
	public void setUp () {
		Labirinto labirinto = new Labirinto();
		partita = new Partita(labirinto);
		factory = new FabbricaDiComandiRiflessiva(io);
	}
		
	@Test
	public void testEsegui_attrezzoInesistente() {
		AbstractComando comandoOggettoInesistente = factory.costruisciComando("prendi NomeOggettoInesistente");
		comandoOggettoInesistente.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testEsegui_attrezzoEsistentePresoDallaStanza() {
		AbstractComando comandoOggettoEsistente = factory.costruisciComando("prendi osso");
		comandoOggettoEsistente.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	@Test
	public void testEsegui_attrezzoEsistenteMessoNellaBorsa() {
		AbstractComando comandoOggettoEsistente = factory.costruisciComando("prendi osso");
		comandoOggettoEsistente.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

}
