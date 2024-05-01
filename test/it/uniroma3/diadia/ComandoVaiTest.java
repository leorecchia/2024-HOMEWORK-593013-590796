package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class ComandoVaiTest {
	private Partita partita;
	private Comando comando;
	FabbricaDiComandiFisarmonica factory;
	private IO io = new IOConsole();
	@Before
	public void setUp() {
		partita = new Partita();
		factory = new FabbricaDiComandiFisarmonica(io);
	}
	
	@Test
	public void testEsegui_direzioneNulla() {
		Stanza stanzaCorrenteIniziale = partita.getStanzaCorrente();
		comando = factory.costruisciComando("vai");
		comando.esegui(partita);
		assertEquals (stanzaCorrenteIniziale, partita.getStanzaCorrente());
	}
	
	
	@Test
	public void testEsegui_direzioneNonNulla() {
		Stanza stanzaCorrenteIniziale = partita.getStanzaCorrente();
		comando = factory.costruisciComando("vai nord");
		comando.esegui(partita);
		assertNotEquals (stanzaCorrenteIniziale, partita.getStanzaCorrente());
	}
	
	
	@Test
	public void testEsegui_direzioneCorrettaECfuDiminuiti() {
		int CfuIniziali = partita.getGiocatore().getCfu();
		comando = factory.costruisciComando("vai nord");
		comando.esegui(partita);
		assertEquals (CfuIniziali-1, partita.getGiocatore().getCfu());
	}
	
}
