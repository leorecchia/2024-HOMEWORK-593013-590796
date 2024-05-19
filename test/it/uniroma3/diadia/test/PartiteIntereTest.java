package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
//import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.StanzaBuia;
//import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartiteIntereTest {
	
	private LabirintoBuilder labirinto;
	private Partita partita;
	private Giocatore giocatore;
	private IOConsole io;
	private FabbricaDiComandiFisarmonica factory;
	private Comando comando;
	private Borsa borsa;
	
	@Before
	public void setUp(){
		this.labirinto=new LabirintoBuilder()
				.addStanzaIniziale("ingresso")
				.addAttrezzo("spada", 1)
				.addStanzaVincente("uscita")
				.addStanza("salone")
				.addAttrezzo("cucchiaio", 1)
				.addAdiacenza("ingresso", "salone", "nord")
				.addAdiacenza("salone", "ingresso", "sud")
				.addStanza("bagno")
				.addAttrezzo("torcia", 1)
				.addStanzaBuia("cameraDaLetto", "torcia")
				.addAttrezzo("pass", 2)
				.addAdiacenza("salone", "bagno", "ovest")
				.addAdiacenza("bagno", "salone", "est")
				.addAdiacenza("salone", "cameraDaLetto", "nord")
				.addAdiacenza("cameraDaLetto", "salone", "sud")
				.addStanzaBloccata("bunker", "est", "pass")
				.addAdiacenza("bunker", "ingresso", "ovest")
				.addAdiacenza("ingresso", "bunker", "est")
				.addStanzaMagica("studio", 0)
				.addAttrezzo("penna", 1)
				.addAdiacenza("studio", "cameraDaLetto", "ovest")
				.addAdiacenza("cameraDaLetto", "studio", "est")
				.addAdiacenza("bunker", "uscita", "est")
				.addAdiacenza("uscita", "bunker", "ovest");
		this.partita=new Partita(labirinto);
		this.giocatore=this.partita.getGiocatore();
		this.io=new IOConsole();
		this.factory=new FabbricaDiComandiFisarmonica(this.io);
		this.borsa=this.partita.getGiocatore().getBorsa();
	}
	
	@Test
	public void controlloParametriIniziali() {
		assertTrue(this.partita.giocatoreIsVivo());
		assertFalse(this.partita.isFinita());
		assertEquals(20, this.giocatore.getCfu());
		assertEquals(7, this.labirinto.getStanze().size());
		assertEquals(this.partita.getStanzaCorrente(), this.labirinto.getStanzaIniziale());
		assertEquals(this.labirinto.getStanze().get("ingresso"), this.labirinto.getStanzaIniziale());
		assertEquals(this.labirinto.getStanze().get("uscita"), this.labirinto.getStanzaVincente());
		assertTrue(this.borsa.isEmpty());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void test_simulazione() {
		this.comando=this.factory.costruisciComando("prendi spada");
		this.comando.esegui(this.partita);
		assertFalse(this.borsa.isEmpty());
		assertTrue(this.borsa.hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		this.comando=this.factory.costruisciComando("guarda");
		this.comando.esegui(this.partita);
		
		this.comando=this.factory.costruisciComando("vai nord");
		this.comando.esegui(this.partita);
		assertEquals(19, this.giocatore.getCfu());
		assertEquals(this.labirinto.getStanze().get("salone"), this.partita.getStanzaCorrente());
		
		this.comando=this.factory.costruisciComando("prendi cucchiaio");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		assertEquals(2, this.borsa.getContenutoOrdinatoPerPeso().size());
		assertTrue(this.borsa.hasAttrezzo("cucchiaio"));
		assertEquals(2, this.borsa.getPeso());
		
		this.comando=this.factory.costruisciComando("vai ovest");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("bagno"), this.partita.getStanzaCorrente());
		assertEquals(18, this.giocatore.getCfu());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		
		this.comando=this.factory.costruisciComando("prendi torcia");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		assertEquals(3, this.borsa.getContenutoOrdinatoPerNome().size());
		assertTrue(this.borsa.hasAttrezzo("torcia"));
		assertEquals(3, this.borsa.getPeso());
		
		this.comando=this.factory.costruisciComando("posa cucchiaio");
		this.comando.esegui(this.partita);
		assertFalse(this.borsa.hasAttrezzo("cucchiaio"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		assertEquals(2, this.borsa.getContenutoOrdinatoPerPeso().size());
		assertEquals(2, this.borsa.getPeso());
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("salone"), this.partita.getStanzaCorrente());
		assertEquals(17, this.giocatore.getCfu());
		
		this.comando=this.factory.costruisciComando("vai nord");
		this.comando.esegui(this.partita);
		StanzaBuia buia=(StanzaBuia)this.partita.getStanzaCorrente();
		assertEquals(this.labirinto.getStanze().get("cameraDaLetto"), this.partita.getStanzaCorrente());
		assertEquals(16, this.giocatore.getCfu());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(buia.getAttrezzoIlluminante()));
		assertEquals("qui c'è un buio pesto", this.partita.getStanzaCorrente().getDescrizione());
		assertTrue(this.borsa.hasAttrezzo(buia.getAttrezzoIlluminante()));
		assertNotEquals("spada", buia.getAttrezzoIlluminante());
		assertEquals("torcia", buia.getAttrezzoIlluminante());
		this.comando=this.factory.costruisciComando("posa torcia");
		this.comando.esegui(this.partita);
		assertEquals(1, this.borsa.getContenutoRaggruppatoPerPeso().size());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("torcia"));
		assertNotEquals("qui c'è un buio pesto", this.partita.getStanzaCorrente().getDescrizione());
		assertEquals(this.partita.getStanzaCorrente().toString(), this.partita.getStanzaCorrente().getDescrizione());
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("studio"), this.partita.getStanzaCorrente());
		assertEquals(15, this.giocatore.getCfu());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("penna"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("annep"));
		assertNotEquals(1, this.partita.getStanzaCorrente().getAttrezzo("annep").getPeso());
		assertEquals(1*2, this.partita.getStanzaCorrente().getAttrezzo("annep").getPeso());
		this.comando=this.factory.costruisciComando("posa spada");
		this.comando.esegui(this.partita);
		assertTrue(this.borsa.isEmpty());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("adaps"));
		assertEquals(1*2, this.partita.getStanzaCorrente().getAttrezzo("adaps").getPeso());
		
		this.comando=this.factory.costruisciComando("vai ovest");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("cameraDaLetto"), this.partita.getStanzaCorrente());
		assertEquals(14, this.giocatore.getCfu());
		this.comando=this.factory.costruisciComando("prendi pass");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("pass"));
		assertFalse(this.borsa.isEmpty());
		assertEquals(1, this.borsa.getSortedSetOrdinatoPerPeso().size());
		assertTrue(this.borsa.hasAttrezzo("pass"));
		assertEquals(2, this.borsa.getPeso());
		
		this.comando=this.factory.costruisciComando("vai sud");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("salone"), this.partita.getStanzaCorrente());
		assertEquals(13, this.giocatore.getCfu());
		
		this.comando=this.factory.costruisciComando("vai sud");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("ingresso"), this.partita.getStanzaCorrente());
		assertEquals(12, this.giocatore.getCfu());
		
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("bunker"), this.partita.getStanzaCorrente());
		assertEquals(11, this.giocatore.getCfu());
		assertEquals(this.partita.getStanzaCorrente(), this.partita.getStanzaCorrente().getStanzaAdiacente("est"));
		assertEquals(this.labirinto.getStanze().get("ingresso"), this.partita.getStanzaCorrente().getStanzaAdiacente("ovest"));
		this.comando.esegui(this.partita);
		assertEquals(this.labirinto.getStanze().get("bunker"), this.partita.getStanzaCorrente());
		assertEquals(10, this.giocatore.getCfu());
		this.comando=this.factory.costruisciComando("posa pass");
		this.comando.esegui(this.partita);
		assertTrue(this.borsa.isEmpty());
		assertFalse(this.borsa.hasAttrezzo("pass"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("pass"));
		assertEquals(this.labirinto.getStanze().get("uscita"), this.partita.getStanzaCorrente().getStanzaAdiacente("est"));
		this.comando=this.factory.costruisciComando("vai est");
		this.comando.esegui(this.partita);
		assertEquals(9, this.giocatore.getCfu());
		assertTrue(this.partita.giocatoreIsVivo());
		assertEquals(this.labirinto.getStanze().get("uscita"), this.partita.getStanzaCorrente());
		assertTrue(this.partita.vinta());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void comandiNonValidi() {
		
		this.comando=this.factory.costruisciComando(" ");
		this.comando.esegui(this.partita);
		assertEquals("Non valido",this.comando.getNome());
		
		this.comando=this.factory.costruisciComando("vai");
		this.comando.esegui(this.partita);
		assertEquals(20, this.giocatore.getCfu());
		assertEquals(this.labirinto.getStanzaIniziale(), this.partita.getStanzaCorrente());
		assertNull(this.comando.getParametro());
		
		assertTrue(this.partita.getStanzaCorrente().getMapStanzeAdiacenti().containsKey("nord"));
		this.comando=this.factory.costruisciComando("vado nord");
		this.comando.esegui(this.partita);
		assertEquals(20, this.giocatore.getCfu());
		assertEquals(this.labirinto.getStanzaIniziale(), this.partita.getStanzaCorrente());
		
		assertFalse(this.partita.getStanzaCorrente().getMapStanzeAdiacenti().containsKey("sud"));
		this.comando=this.factory.costruisciComando("vai sud");
		this.comando.esegui(this.partita);
		assertEquals("sud", comando.getParametro());
		assertEquals(20, this.giocatore.getCfu());
		assertEquals(this.labirinto.getStanzaIniziale(), this.partita.getStanzaCorrente());
		
		this.comando=this.factory.costruisciComando("prendi");
		this.comando.esegui(this.partita);
		assertTrue(this.borsa.isEmpty());
		assertNull(this.comando.getParametro());
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		this.comando=this.factory.costruisciComando("prendo spada");
		this.comando.esegui(this.partita);
		assertTrue(this.borsa.isEmpty());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		this.comando=this.factory.costruisciComando("prendi cucchiaio");
		this.comando.esegui(this.partita);
		assertEquals("cucchiaio", comando.getParametro());
		assertTrue(this.borsa.isEmpty());		
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
		
		this.comando=this.factory.costruisciComando("posa");
		this.comando.esegui(this.partita);
		assertNull(this.comando.getParametro());
		assertEquals(1, this.partita.getStanzaCorrente().getAttrezzi().size());
		
		this.comando=this.factory.costruisciComando("prendi spada");
		this.comando.esegui(this.partita);
		assertTrue(this.borsa.hasAttrezzo("spada"));
		this.comando=this.factory.costruisciComando("poso spada");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue(this.borsa.hasAttrezzo("spada"));
		assertFalse(this.borsa.hasAttrezzo("cucchiaio"));
		this.comando=this.factory.costruisciComando("posa cucchiaio");
		this.comando.esegui(this.partita);
		assertEquals("cucchiaio", comando.getParametro());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("cucchiaio"));
	}
}
