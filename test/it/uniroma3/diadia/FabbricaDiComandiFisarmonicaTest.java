package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica factory;
	private IO io = new IOConsole();
	@Before
	public void setUp () {
		factory = new FabbricaDiComandiFisarmonica(io);
	}
	
	// verifica che un comando valido non risulti non valido
	@Test
	public void testCostruisciComando_comandoValidoNonDaComandoNonValido() {
		Comando comandoValido = factory.costruisciComando("vai nord");
		assertNotEquals("Non Valido", comandoValido.getNome());
	}
	
	// verifica che un comando non valido risulti non valido
	@Test
	public void testCostruisciComando_comandoNonValidoDaComandoNonValido() {
		Comando comandoNonValido = factory.costruisciComando("Comando NonValido");
		assertEquals("Non Valido", comandoNonValido.getNome());
	}
	
	// verifica che il nome di un comando valido sia giusto
	@Test
	public void testCostruisciComando_comandoConNomeGiusto() {
		Comando comandoConNomeGiusto = factory.costruisciComando("vai nord");
		assertEquals("Vai", comandoConNomeGiusto.getNome());
	}
	
	// verifica che il parametro di un comando valido sia giusto
	@Test
	public void testCostruisciComando_comandoConParametroGiusto() {
		Comando comandoConParametroGiusto = factory.costruisciComando("prendi osso");
		assertEquals("osso", comandoConParametroGiusto.getParametro());
	}
	
	// verifica che il nome di un comando valido senza parametro non abbia effettivamente il parametro
	@Test
	public void testCostruisciComando_comandoSenzaParametro() {
		Comando comandoSenzaParametro = factory.costruisciComando("guarda");
		assertNull(comandoSenzaParametro.getParametro());
	}

}
