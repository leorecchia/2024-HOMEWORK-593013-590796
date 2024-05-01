package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	@Test
	public void testAddAttrezzo_sogliaNonRaggiunta() {
		StanzaMagica stanzaMagicaSogliaNonRaggiunta = new StanzaMagica("stanzaMagicaSogliaNonRaggiunta", 1);
		Attrezzo attrezzoNonMagico = new Attrezzo("attrezzoNonMagico", 2);
		stanzaMagicaSogliaNonRaggiunta.addAttrezzo(attrezzoNonMagico);
		assertEquals (attrezzoNonMagico, stanzaMagicaSogliaNonRaggiunta.getAttrezzo("attrezzoNonMagico"));
	}
	
	@Test
	public void testAddAttrezzo_sogliaNonRaggiunta_AttrezzoNonRaddoppiaPeso() {
		StanzaMagica stanzaMagicaSogliaRaggiunta = new StanzaMagica("stanzaMagicaSogliaRaggiunta", 1);
		Attrezzo attrezzoMagico = new Attrezzo("attrezzoMagico", 2);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(attrezzoMagico);
		assertEquals (attrezzoMagico.getPeso(), stanzaMagicaSogliaRaggiunta.getAttrezzo("attrezzoMagico").getPeso());
	}
	
	@Test
	public void testAddAttrezzo_sogliaRaggiunta_AttrezzoNomeNonInvertito() {
		StanzaMagica stanzaMagicaSogliaRaggiunta = new StanzaMagica("stanzaMagicaSogliaRaggiunta", 1);
		Attrezzo attrezzoMagico = new Attrezzo("Magic", 2);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(attrezzoMagico);
		assertFalse (stanzaMagicaSogliaRaggiunta.hasAttrezzo("cigaM"));
	}
	
	
	@Test
	public void testAddAttrezzo_sogliaRaggiuntaMaAggiungeComunqueAttrezzo() {
		StanzaMagica stanzaMagicaSogliaRaggiunta = new StanzaMagica("stanzaMagicaSogliaRaggiunta", 1);
		Attrezzo riempiSoglia = new Attrezzo("riempiSoglia", 1);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(riempiSoglia);
		Attrezzo attrezzoMagico = new Attrezzo("attrezzoMagico", 2);
		assertTrue (stanzaMagicaSogliaRaggiunta.addAttrezzo(attrezzoMagico));
	}
	
	@Test
	public void testAddAttrezzo_sogliaRaggiunta_AttrezzoRaddoppiaPeso() {
		StanzaMagica stanzaMagicaSogliaRaggiunta = new StanzaMagica("stanzaMagicaSogliaRaggiunta", 1);
		Attrezzo riempiSoglia = new Attrezzo("riempiSoglia", 1);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(riempiSoglia);
		Attrezzo attrezzoMagico = new Attrezzo("Magic", 2);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(attrezzoMagico);
		assertEquals (attrezzoMagico.getPeso()*2, stanzaMagicaSogliaRaggiunta.getAttrezzo("cigaM").getPeso());
	}
	
	@Test
	public void testAddAttrezzo_sogliaRaggiunta_AttrezzoNomeInvertito() {
		StanzaMagica stanzaMagicaSogliaRaggiunta = new StanzaMagica("stanzaMagicaSogliaRaggiunta", 1);
		Attrezzo riempiSoglia = new Attrezzo("riempiSoglia", 1);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(riempiSoglia);
		Attrezzo attrezzoMagico = new Attrezzo("Magic", 2);
		stanzaMagicaSogliaRaggiunta.addAttrezzo(attrezzoMagico);
		assertTrue (stanzaMagicaSogliaRaggiunta.hasAttrezzo("cigaM"));
	}
	
	
}
