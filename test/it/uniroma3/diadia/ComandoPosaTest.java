package it.uniroma3.diadia;

import static org.junit.Assert.*;


import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {
	
	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	private IO io = new IOConsole();
	
	@Before
	public void setUp () {
		partita = new Partita();
		factory = new FabbricaDiComandiFisarmonica(io);
	}
		
	@Test
	public void testEsegui_attrezzoInesistenteInBorsa() {
		Comando comando = factory.costruisciComando("posa NomeOggettoInesistente");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("NomeOggettoInesistente"));
	}
	
	@Test
	public void testEsegui_attrezzoPosatoNellaStanza() {
		Attrezzo attrezzoPosatoNellaStanza = new Attrezzo("attrezzoPosatoNellaStanza", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPosatoNellaStanza);
		Comando comandoOggettoEsistente = factory.costruisciComando("posa attrezzoPosatoNellaStanza");
		comandoOggettoEsistente.esegui(partita);
		assertTrue (partita.getStanzaCorrente().hasAttrezzo("attrezzoPosatoNellaStanza"));
	}

	@Test
	public void testEsegui_attrezzoToltoDallaBorsa() {
		Attrezzo attrezzoToltoDallaBorsa = new Attrezzo("attrezzoToltoDallaBorsa", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzoToltoDallaBorsa);
		Comando comandoOggettoEsistente = factory.costruisciComando("posa attrezzoToltoDallaBorsa");
		comandoOggettoEsistente.esegui(partita);
		assertFalse (partita.getGiocatore().getBorsa().hasAttrezzo("attrezzoToltoDallaBorsa"));
	}
	
}
