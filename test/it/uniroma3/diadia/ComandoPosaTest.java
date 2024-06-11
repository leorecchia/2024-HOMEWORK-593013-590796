package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {
	
	private Partita partita;
	private FabbricaDiComandiRiflessiva factory;
	Scanner scannerDiLinee = new Scanner(System.in);
	private IO io = new IOConsole(scannerDiLinee);
	
	@Before
	public void setUp () {
		Labirinto labirinto = new Labirinto();
		partita = new Partita(labirinto);
		factory = new FabbricaDiComandiRiflessiva(io);
	}
		
	@Test
	public void testEsegui_attrezzoInesistenteInBorsa() {
		AbstractComando comando = factory.costruisciComando("posa NomeOggettoInesistente");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("NomeOggettoInesistente"));
	}
	
	@Test
	public void testEsegui_attrezzoPosatoNellaStanza() {
		Attrezzo attrezzoPosatoNellaStanza = new Attrezzo("attrezzoPosatoNellaStanza", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPosatoNellaStanza);
		AbstractComando comandoOggettoEsistente = factory.costruisciComando("posa attrezzoPosatoNellaStanza");
		comandoOggettoEsistente.esegui(partita);
		assertTrue (partita.getStanzaCorrente().hasAttrezzo("attrezzoPosatoNellaStanza"));
	}

	@Test
	public void testEsegui_attrezzoToltoDallaBorsa() {
		Attrezzo attrezzoToltoDallaBorsa = new Attrezzo("attrezzoToltoDallaBorsa", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzoToltoDallaBorsa);
		AbstractComando comandoOggettoEsistente = factory.costruisciComando("posa attrezzoToltoDallaBorsa");
		comandoOggettoEsistente.esegui(partita);
		assertFalse (partita.getGiocatore().getBorsa().hasAttrezzo("attrezzoToltoDallaBorsa"));
	}
	
}
