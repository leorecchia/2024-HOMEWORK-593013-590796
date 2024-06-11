package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class PartitaInteraTest {
	
	private LabirintoBuilder labirinto;
	private Partita partita;
	Scanner scannerDiLinee = new Scanner(System.in);
	private IO io;
	private FabbricaDiComandiRiflessiva factory;
	private AbstractComando comando;

	@Before
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("ingresso")
				.addAttrezzo("spada", 1)
				.addStanzaVincente("uscita")
				.addStanza("salone")
				.addAttrezzo("cucchiaio", 1)
				.addAdiacenza("ingresso", "salone", Direzione.NORD)
				.addAdiacenza("salone", "ingresso", Direzione.SUD)
				.addStanza("bagno")
				.addAttrezzo("torcia", 1)
				.addStanzaBuia("cameraDaLetto", "torcia")
				.addAttrezzo("pass", 2)
				.addAdiacenza("salone", "bagno", Direzione.OVEST)
				.addAdiacenza("bagno", "salone", Direzione.EST)
				.addAdiacenza("salone", "cameraDaLetto", Direzione.NORD)
				.addAdiacenza("cameraDaLetto", "salone", Direzione.SUD)
				.addStanzaBloccata("bunker", Direzione.EST, "pass")
				.addAdiacenza("ingresso", "bunker", Direzione.EST)
				.addAdiacenza("bunker", "ingresso", Direzione.OVEST)
				.addStanzaMagica("studio", 0)
				.addAttrezzo("penna", 1)
				.addAdiacenza("cameraDaLetto", "studio", Direzione.EST)
				.addAdiacenza("studio", "cameraDaLetto", Direzione.OVEST)
				.addAdiacenza("bunker", "uscita", Direzione.EST)
				.addAdiacenza("uscita", "bunker", Direzione.OVEST);
		this.partita = new Partita(labirinto);
		io = new IOConsole(scannerDiLinee);
		factory = new FabbricaDiComandiRiflessiva(this.io);
	}

	@Test
	public void test_controlloParametriIniziali() {
		assertFalse (partita.isFinita());
		assertTrue (partita.giocatoreIsVivo());
		assertEquals (20, this.partita.getGiocatore().getCfu());
		assertEquals (7, labirinto.getStanze().size());
		assertEquals (partita.getStanzaCorrente(), labirinto.getStanzaIniziale());
		assertEquals (labirinto.getStanze().get("ingresso"), labirinto.getStanzaIniziale());
		assertEquals (labirinto.getStanze().get("uscita"), labirinto.getStanzaVincente());
		assertTrue (this.partita.getGiocatore().getBorsa().isEmpty());
		assertTrue (partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void test_controlloPartita() {
		comando = factory.costruisciComando("prendi spada");
		comando.esegui(partita);
		assertFalse (partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertFalse (this.partita.getGiocatore().getBorsa().isEmpty());
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		
		this.comando=this.factory.costruisciComando("vai nord");
		this.comando.esegui(partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());
		assertEquals (this.partita.getStanzaCorrente(), this.labirinto.getStanze().get("salone"));
		
		this.comando=this.factory.costruisciComando("prendi cucchiaio");
		this.comando.esegui(partita);
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		assertEquals (2, this.partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso().size());
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo("cucchiaio"));
		assertEquals (2, this.partita.getGiocatore().getBorsa().getPeso());
		
		this.comando=this.factory.costruisciComando("vai ovest");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("bagno"), this.partita.getStanzaCorrente());
		assertEquals(18, this.partita.getGiocatore().getCfu());
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		

		this.comando=this.factory.costruisciComando("prendi torcia");
		this.comando.esegui(partita);
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		assertEquals (3, this.partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso().size());
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertEquals (3, this.partita.getGiocatore().getBorsa().getPeso());
		
		this.comando=this.factory.costruisciComando("posa cucchiaio");
		this.comando.esegui(partita);
		assertFalse (this.partita.getGiocatore().getBorsa().hasAttrezzo("cucchiaio"));
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		assertEquals (2, this.partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso().size());
		assertEquals (2, this.partita.getGiocatore().getBorsa().getPeso());
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("salone"), this.partita.getStanzaCorrente());
		assertEquals (17, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("vai nord");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("cameraDaLetto"), this.partita.getStanzaCorrente());
		assertEquals (16, this.partita.getGiocatore().getCfu());
		StanzaBuia buia = (StanzaBuia) this.partita.getStanzaCorrente();
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo(buia.getNomeAttrezzoIlluminante()));
		assertEquals ("qui c'è buio pesto", this.partita.getStanzaCorrente().getDescrizione());
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo(buia.getNomeAttrezzoIlluminante()));
		assertNotEquals (buia.getNomeAttrezzoIlluminante(), "spada");
		assertEquals (buia.getNomeAttrezzoIlluminante(), "torcia");
		
		this.comando=this.factory.costruisciComando("posa torcia");
		this.comando.esegui(partita);
		assertFalse (this.partita.getGiocatore().getBorsa().hasAttrezzo("torcia"));
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		assertEquals (1, this.partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso().size());
		assertEquals (this.partita.getStanzaCorrente().toString(), this.partita.getStanzaCorrente().getDescrizione());
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("studio"), this.partita.getStanzaCorrente());
		assertEquals (15, this.partita.getGiocatore().getCfu());
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("penna"));
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("annep"));
		assertNotEquals (1, this.partita.getStanzaCorrente().getAttrezzo("annep").getPeso());
		assertEquals (1*2, this.partita.getStanzaCorrente().getAttrezzo("annep").getPeso());
		
		this.comando=this.factory.costruisciComando("posa spada");
		this.comando.esegui(partita);
		assertFalse (this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("adaps"));
		assertTrue (this.partita.getGiocatore().getBorsa().isEmpty());
		assertNotEquals (1, this.partita.getStanzaCorrente().getAttrezzo("adaps").getPeso());
		assertEquals (1*2, this.partita.getStanzaCorrente().getAttrezzo("adaps").getPeso());
		
		this.comando=this.factory.costruisciComando("vai ovest");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("cameraDaLetto"), this.partita.getStanzaCorrente());
		assertEquals (14, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("prendi pass");
		this.comando.esegui(partita);
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("pass"));
		assertFalse (this.partita.getGiocatore().getBorsa().isEmpty());
		assertEquals (1, this.partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso().size());
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo("pass"));
		assertEquals (2, this.partita.getGiocatore().getBorsa().getPeso());
		
		this.comando=this.factory.costruisciComando("vai sud");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("salone"), this.partita.getStanzaCorrente());
		assertEquals (13, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("vai sud");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("ingresso"), this.partita.getStanzaCorrente());
		assertEquals (12, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("bunker"), this.partita.getStanzaCorrente());
		assertEquals (11, this.partita.getGiocatore().getCfu());
		assertEquals (this.partita.getStanzaCorrente(), this.partita.getStanzaCorrente().getStanzaAdiacente(Direzione.EST));
		assertEquals (this.labirinto.getStanzaIniziale(), this.partita.getStanzaCorrente().getStanzaAdiacente(Direzione.OVEST));
		
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("bunker"), this.partita.getStanzaCorrente());
		assertEquals (10, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("posa pass");
		this.comando.esegui(partita);
		assertFalse (this.partita.getGiocatore().getBorsa().hasAttrezzo("pass"));
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("pass"));
		assertTrue (this.partita.getGiocatore().getBorsa().isEmpty());
		assertEquals (this.labirinto.getStanze().get("uscita"), this.partita.getStanzaCorrente().getStanzaAdiacente(Direzione.EST));
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(partita);
		assertEquals (this.labirinto.getStanze().get("uscita"), this.partita.getStanzaCorrente());
		assertEquals (9, this.partita.getGiocatore().getCfu());
		assertTrue (this.partita.giocatoreIsVivo());
		assertTrue (this.partita.vinta());
		assertTrue (this.partita.isFinita());
	}
	
	@Test
	public void test_controlloComandiNonValidi() {
		this.comando=this.factory.costruisciComando("");
		this.comando.esegui(partita);
		assertEquals ("Non Valido", comando.getNome());
		
		assertTrue (this.partita.getStanzaCorrente().getMapStanzeAdiacenti().containsKey(Direzione.NORD));
		this.comando=this.factory.costruisciComando("vado nord");
		this.comando.esegui(partita);
		assertEquals ("Non Valido", comando.getNome());
		assertEquals (this.labirinto.getStanze().get("ingresso"), this.partita.getStanzaCorrente());
		assertEquals (20, this.partita.getGiocatore().getCfu());
		
		assertFalse (this.partita.getStanzaCorrente().getMapStanzeAdiacenti().containsKey(Direzione.SUD));
		this.comando=this.factory.costruisciComando("vai sud");
		this.comando.esegui(partita);
		assertEquals ("Vai", comando.getNome());
		assertEquals ("sud", comando.getParametro().toString());
		assertEquals (this.labirinto.getStanze().get("ingresso"), this.partita.getStanzaCorrente());
		assertEquals (20, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("vai");
		this.comando.esegui(partita);
		assertEquals ("Vai", comando.getNome());
		assertNull (comando.getParametro());
		assertEquals (this.labirinto.getStanze().get("ingresso"), this.partita.getStanzaCorrente());
		assertEquals (20, this.partita.getGiocatore().getCfu());
		
		this.comando=this.factory.costruisciComando("prendi");
		this.comando.esegui(partita);
		assertNull (comando.getParametro());
		assertTrue (this.partita.getGiocatore().getBorsa().isEmpty());
		assertEquals (0, this.partita.getGiocatore().getBorsa().getPeso());
		
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		this.comando=this.factory.costruisciComando("prendo spada");
		this.comando.esegui(partita);
		assertEquals ("Non Valido", comando.getNome());
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue (this.partita.getGiocatore().getBorsa().isEmpty());	
		
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		this.comando=this.factory.costruisciComando("prendi cucchiaio");
		this.comando.esegui(partita);
		assertEquals ("Prendi", comando.getNome());
		assertEquals ("cucchiaio", comando.getParametro().toString());
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		assertTrue (this.partita.getGiocatore().getBorsa().isEmpty());
		
		this.comando=this.factory.costruisciComando("posa");
		this.comando.esegui(partita);
		assertNull (comando.getParametro());
		assertEquals (1, this.partita.getStanzaCorrente().getAttrezzi().size());
		
		assertTrue (this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		this.comando=this.factory.costruisciComando("prendi spada");
		this.comando.esegui(partita);
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		this.comando=this.factory.costruisciComando("poso spada");
		this.comando.esegui(partita);
		assertEquals ("Non Valido", comando.getNome());
		assertTrue (this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertFalse (this.partita.getGiocatore().getBorsa().isEmpty());
		
		assertFalse (this.partita.getGiocatore().getBorsa().hasAttrezzo("cucchiaio"));
		this.comando=this.factory.costruisciComando("posa cucchiaio");
		this.comando.esegui(partita);
		assertFalse (this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
	}
}