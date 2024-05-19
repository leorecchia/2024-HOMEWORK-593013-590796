package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica factory;
	private IO io;
	
	@Before
	public void setUp()  {
		io=new IOConsole();
		factory=new FabbricaDiComandiFisarmonica(io);
	}
	
	/* verifica che un comando valido non risulti non valido*/
	@Test
	public void testComandoValido() {
		Comando comandoValido=factory.costruisciComando("vai nord");
		assertNotEquals("Non valido", comandoValido.getNome());
	}
	
	/* verifica che un comando non valido risulti non valido */
	@Test
	public void comandoNonValido() {
		Comando nonValido=factory.costruisciComando("nonValido");
		assertEquals("Non valido", nonValido.getNome());
	}
	
	/* verifica che il nome del comando valido sia giusto */
	@Test
	public void comandoConNomeGiusto() {
		Comando comandoGiusto=factory.costruisciComando("vai nord");
		assertEquals("vai", comandoGiusto.getNome());
	}
	
	/* verifica che il parametro del comando valido sia giusto */
	@Test
	public void comandoConParametroGiusto() {
		Comando comandoConParametro=factory.costruisciComando("prendi osso");
		assertEquals("osso", comandoConParametro.getParametro());
	}
	
	/* verifica che il parametro di un comando senza parametro risulti nullo */
	@Test
	public void comandoSenzaParametro() {
		Comando comandoSenzaParametro=factory.costruisciComando("guarda");
		assertNull(comandoSenzaParametro.getParametro());
	}
	
}
