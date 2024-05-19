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

public class ComandoPrendiTest {

	private Partita partita;
	private FabbricaDiComandiFisarmonica factory;
	private IO io;
	
	@Before
	public void setUp(){
		partita=new Partita(new LabirintoBuilder());
		io=new IOConsole();
		factory=new FabbricaDiComandiFisarmonica(io);
	}
	
	@Test
	public void oggettoInesistente_borsaVuota() {
		partita.setStanzaCorrente(new Stanza("corrente"));
		Comando comandoOggettoInesistente=factory.costruisciComando("prendi oggettoInesistente");
		comandoOggettoInesistente.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void oggettoEsistente_borsaNonVuota() {
		partita.setStanzaCorrente(new Stanza("corrente"));
		Attrezzo osso=new Attrezzo("osso", 1);
		partita.getStanzaCorrente().addAttrezzo(osso);
		Comando comandoOggettoEsistente=factory.costruisciComando("prendi osso");
		comandoOggettoEsistente.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	
	@Test
	public void oggettoEsistente_stanzaVuota() {
		partita.setStanzaCorrente(new Stanza("corrente"));
		Comando comandoOggettoEsistente=factory.costruisciComando("prendi osso");
		comandoOggettoEsistente.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}
	
	


}
