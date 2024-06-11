package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class ComandoVaiTest {
	private Partita partita;
	private AbstractComando comando;
	FabbricaDiComandiRiflessiva factory;
	Scanner scannerDiLinee = new Scanner(System.in);
	private IO io = new IOConsole(scannerDiLinee);
	
	@Before
	public void setUp() {
		Labirinto labirinto = new Labirinto();
		partita = new Partita(labirinto);
		factory = new FabbricaDiComandiRiflessiva(io);
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
