package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto caricatore;
	private StringReader reader;
	private static final String monolocale = "Stanze:unica\n" 
										   + "Inizio:unica\n"
										   + "Vincente:unica\n"
										   + "Buia:\n"
										   + "Bloccata:\n"
									   	   + "Magica:\n"
										   + "Attrezzi:osso 1 unica\n"
										   + "Uscite:\n"
										   + "Maghi:\n"
										   + "Streghe:\n"
										   + "Cani:";
	
	private static final String bilocale = "Stanze:biblioteca,N10\n"
											+ "Inizio:N10\n"
											+ "Vincente:biblioteca\n"
											+ "Buia:\n"
										    + "Bloccata:biblioteca nord pinza\n"
									   	    + "Magica:\n"
											+ "Attrezzi:martello 10 biblioteca,pinza 2 N10\n"
											+ "Uscite:biblioteca nord N10\n"
											+ "Maghi:\n"
										    + "Streghe:\n"
									   	    + "Cani:";
	
	private static final String trilocale = "Stanze:biblioteca,N10,N11\n"
											+ "Inizio:N10\n"
											+ "Vincente:N11\n"
											+ "Buia:\n"
										    + "Bloccata:\n"
									   	    + "Magica:\n"
											+ "Attrezzi:martello 10 biblioteca,pinza 2 N10\n"
											+ "Uscite:biblioteca nord N10,biblioteca sud N11\n"
											+ "Maghi:\n"
										    + "Streghe:\n"
									   	    + "Cani:";
//	@Before
//	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
//		reader = new StringReader(monolocale);
//		caricatore = new CaricatoreLabirinto(reader);
//		this.caricatore.carica();
//	}

	@Test
	public void testCarica_monolocale() throws FileNotFoundException, FormatoFileNonValidoException{
		reader = new StringReader(monolocale);
		caricatore = new CaricatoreLabirinto(reader);
		this.caricatore.carica();
		assertEquals(1, this.caricatore.getStanze().size());
		assertEquals("unica", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("unica", this.caricatore.getStanzaVincente().getNome());
		assertTrue(this.caricatore.getStanzaIniziale().hasAttrezzo("osso"));
		assertEquals(1, this.caricatore.getStanzaIniziale().getAttrezzo("osso").getPeso());
		assertNull (this.caricatore.getStanzaIniziale().getMapStanzeAdiacenti().get("unica"));
	}
	
	@Test
	public void testCarica_bilocale() throws FileNotFoundException, FormatoFileNonValidoException{
		reader = new StringReader(bilocale);
		caricatore = new CaricatoreLabirinto(reader);
		this.caricatore.carica();
		assertEquals(2, this.caricatore.getStanze().size());
		assertTrue(this.caricatore.getStanze().containsKey("N10"));
		assertTrue(this.caricatore.getStanze().containsKey("biblioteca"));
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("biblioteca", this.caricatore.getStanzaVincente().getNome());
		assertTrue(this.caricatore.getStanzaIniziale().hasAttrezzo("pinza"));
		assertEquals(2, this.caricatore.getStanzaIniziale().getAttrezzo("pinza").getPeso());
//		assertTrue(this.caricatore.getStanzaVincente().hasAttrezzo("martello"));
//		assertEquals(10, this.caricatore.getStanzaVincente().getAttrezzo("martello").getPeso());
	}
	
	@Test
	public void testCarica_trrilocale() throws FileNotFoundException, FormatoFileNonValidoException{
		reader = new StringReader(trilocale);
		caricatore = new CaricatoreLabirinto(reader);
		this.caricatore.carica();
		assertEquals(3, this.caricatore.getStanze().size());
		assertTrue(this.caricatore.getStanze().containsKey("N10"));
		assertTrue(this.caricatore.getStanze().containsKey("biblioteca"));
		assertTrue(this.caricatore.getStanze().containsKey("N11"));
		assertEquals("N10", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("N11", this.caricatore.getStanzaVincente().getNome());
		assertTrue(this.caricatore.getStanzaIniziale().hasAttrezzo("pinza"));
		assertEquals(2, this.caricatore.getStanzaIniziale().getAttrezzo("pinza").getPeso());
		assertTrue(this.caricatore.getStanze().get("biblioteca").hasAttrezzo("martello"));
		assertEquals(10, this.caricatore.getStanze().get("biblioteca").getAttrezzo("martello").getPeso());
	}

}
