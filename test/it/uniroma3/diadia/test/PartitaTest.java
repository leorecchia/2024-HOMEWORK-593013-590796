package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita partita;
	private LabirintoBuilder lab;
	
	@Before
	public void setUp() {
		lab=new LabirintoBuilder()
				.addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord");
		partita=new Partita(lab);
	}
	
		
	/********* TEST PER LA FUNZIONE isFinita ********/
	
	// verifichiamo che la partita appena creata non è finita
	@Test
	public void nonFinita() {
		assertEquals(false, partita.isFinita());
	}
	
	// verifichiamo che settando i cfu a 0 la partita finisca effettivamente
	@Test
	public void cfuFiniti() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}
	
	// verifichiamo che usando la funzione setFinita la partita finisca
	@Test
	public void partitaFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	
	/******** TEST PER LA FUNZIONE getStanzaCorrente *********/
	
	// verifichiamo che la stanza corrente non sia null
	@Test
	public void nonStanzaNulla() {
		assertNotNull(partita.getStanzaCorrente());
	}
	
	// verifichiamo che la stanza corrente sia corretta
	@Test
	public void stanzaGiusta(){
		assertEquals(lab.getStanzaIniziale(), partita.getStanzaCorrente());
	}
	
	// verifichiamo che usando l'asserzione con una stanza sbagliata non ci dica che corrisponde a quella corrente
	@Test
	public void stanzaSbagliata() {
		assertNotEquals(lab.getStanzaVincente(), partita.getStanzaCorrente());
	}
		
	/******* TEST PER LA FUNZIONE vinta ******/
	
	// verifichiamo che la partita appena iniziata non sia vinta
	@Test
	public void appenaIniziata() {
		assertFalse(partita.vinta());
	}
	
	// verifichiamo che se la stanza corrente è la stanza vincente la partita è vinta
	@Test
	public void siamoInStanzaVincente() {
		partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	// verifichiamo che se non ci troviamo nella stanza vincente la partita non risulti vinta
	@Test
	public void nonSiamoInStanzaVincente() {
		Stanza nonVincente=new Stanza("nonVincente");
		partita.setStanzaCorrente(nonVincente);
		assertFalse(partita.vinta());
	}
}
	
	
	
	
	
