package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private LabirintoBuilder labirinto;
	private Partita game;
	private Stanza room;
	
	@Before
	public void setUp () {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD);
		game = new Partita(labirinto);	
	}

	
	/*********** TEST METODO isFinita ***************/
	@Test 
	public void testIsFinita_partitaAppenaIniziataNonFinisceSubito () {
		assertEquals (false, game.isFinita());
	}
	
	@Test
	public void testIsFinita_partitaFinisceCon0Cfu() {
		game.getGiocatore().setCfu(0);
		assertEquals (true, game.isFinita());
	}
	
	@Test
	public void testIsFinita_settarePartitaFinita_eLaPartitaFinisce() {
		game.setFinita();
		assertEquals (true, game.isFinita());
	}
	
	@Test
	public void testIsFinita_partitaFinisceInBiblioteca() {
		game.setStanzaCorrente(labirinto.getStanzaVincente());
		game.setStanzaCorrente(labirinto.getStanzaVincente());
		assertSame (game.getStanzaCorrente(), labirinto.getStanzaVincente());
	}
	
	
	/*********** TEST METODO getStanzaCorrente ***************/
	
	//test per controllare che una partita al momento della creazione partita si trovi nella stanza "Atrio"
	@Test
	public void testGetStanzaCorrente_StanzaIniziale_è_atrio () {
		assertEquals ("Atrio", game.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testGetStanzaCorrente_StanzaCorrente_è_sbagliata () {
		room = new Stanza("stanzaCorrenteNoncorretta");
		assertNotEquals (room, game.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaCorrente_StanzaCorrenteNonèNull () {
		assertNotNull (game.getStanzaCorrente());
	}
	
	
	/*********** TEST METODO vinta ***************/
	
	//verifichaimo che la partita appena iniziata non sia vinta
	@Test
	public void testVinta_partitaAppenaIniziata_NonVintaSubito () {
		assertFalse (game.vinta());
	}
	
	//verifichaimo che se ci troviamo nella stanza vincente la partita viene vinta
	@Test
	public void testVinta_partitaVieneVinta_quandoSiamoInStanzaVincente () {
		game.setStanzaCorrente(game.getLabirinto().getStanzaVincente());
		assertTrue (game.vinta());
	}
	
	//verifichaimo che se non ci troviamo nella stanza vincente la partita non viene vinta
	@Test
	public void testVinta_partitaNonVieneVinta_quandoSiamoInStanzaNONVincente () {
		room = new Stanza("nonVincente");
		game.setStanzaCorrente(room);
		assertFalse (game.vinta());
	}
}
