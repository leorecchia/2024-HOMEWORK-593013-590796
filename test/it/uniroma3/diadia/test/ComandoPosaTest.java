package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class ComandoPosaTest {

	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	private IO io;

	@Before
	public void setUp() {
		partita = new Partita(new LabirintoBuilder());
		io=new IOConsole();
		factory = new FabbricaDiComandiFisarmonica(io);
	}

	/* verifico che se provo a posare un attrezzo che non esiste nella borsa, esso non risulti
	 * aggiunto alla stanza */
	@Test
	public void oggettoInesistenteInBorsa() {
		partita.setStanzaCorrente(new Stanza("corrente"));
		Comando comandoOggettoInesistente = factory.costruisciComando("posa attrezzoInesistente");
		comandoOggettoInesistente.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("oggettoInesistente"));
	}
	
	/* verifico che, dopo aver aggiunto un attrezzo in una borsa e averlo posato nella stanza
	 * corrente, risulti che la stanza abbia l'attrezzo */
	@Test
	public void oggettoEsistente_aggiuntoInStanza() {
		partita.setStanzaCorrente(new Stanza("corrente"));
		Attrezzo attrezzoEsistente=new Attrezzo("attrezzoEsistente", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzoEsistente);
		Comando comandoOggettoEsistente = factory.costruisciComando("posa attrezzoEsistente");
		comandoOggettoEsistente.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attrezzoEsistente"));
	}
	
	/* verifico che, dopo aver aggiunto un attrezzo in una borsa e averlo posato nella stanza
	 * corrente, la borsa non abbia più l'attrezzo che abbiamo posato */
	@Test
	public void oggettoEsistente_borsaVuota() {
		partita.setStanzaCorrente(new Stanza("corrente"));
		Attrezzo attrezzoEsistente=new Attrezzo("attrezzoEsistente", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzoEsistente);
		Comando comandoOggettoEsistente = factory.costruisciComando("posa attrezzoEsistente");
		comandoOggettoEsistente.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("attrezzoEsistente"));
	}

}
