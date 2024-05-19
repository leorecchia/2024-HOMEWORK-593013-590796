package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class ComandoVaiTest {
	
	private Partita partita;
	private Comando comando;
	private FabbricaDiComandiFisarmonica factory;
	private IO io;
	
	@Before
	public void setUp() {
		io=new IOConsole();
		partita=new Partita(new LabirintoBuilder());
		factory=new FabbricaDiComandiFisarmonica(io);
	}
			
	@Test
	public void direzioneNulla() {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		comando=factory.costruisciComando("vai");
		comando.esegui(partita);
		assertEquals(stanzaCorrente, partita.getStanzaCorrente());
	}
	
	@Test
	public void direzioneEsistente() {
		Labirinto nuovo=new LabirintoBuilder().addStanzaIniziale("iniziale").addStanza("adiacente")
				.addAdiacenza("iniziale", "adiacente", "sud");
		partita=new Partita(nuovo);
		partita.setStanzaCorrente(nuovo.getStanzaIniziale());
		Stanza stanzaSud=partita.getStanzaCorrente().getStanzaAdiacente("sud");
		comando=factory.costruisciComando("vai sud");
		comando.esegui(partita);
		assertEquals(stanzaSud, partita.getStanzaCorrente());
	}
	
	@Test
	public void decrementaCFU() {
		Labirinto nuovo=new LabirintoBuilder().addStanzaIniziale("iniziale").addStanza("adiacente")
				.addAdiacenza("iniziale", "adiacente", "sud");
		partita=new Partita(nuovo);
		partita.setStanzaCorrente(nuovo.getStanzaIniziale());
		int cfuIniziali=partita.getGiocatore().getCfu();
		comando=factory.costruisciComando("vai sud");
		comando.esegui(partita);
		assertEquals(--cfuIniziali, partita.getGiocatore().getCfu());
	}

	
}
