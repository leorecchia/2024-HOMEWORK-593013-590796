package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiRiflessiva factory;
	Scanner scannerDiLinee = new Scanner(System.in);
	private IO io = new IOConsole(scannerDiLinee);
	@Before
	public void setUp () {
		factory = new FabbricaDiComandiRiflessiva(io);
	}
	
	// verifica che un comando valido non risulti non valido
	@Test
	public void testCostruisciComando_comandoValidoNonDaComandoNonValido() {
		AbstractComando comandoValido = factory.costruisciComando("vai nord");
		assertNotEquals("Non Valido", comandoValido.getNome());
	}
	
	// verifica che un comando non valido risulti non valido
	@Test
	public void testCostruisciComando_comandoNonValidoDaComandoNonValido() {
		AbstractComando comandoNonValido = factory.costruisciComando("Comando NonValido");
		assertEquals("Non Valido", comandoNonValido.getNome());
	}
	
	// verifica che il nome di un comando valido sia giusto
	@Test
	public void testCostruisciComando_comandoConNomeGiusto() {
		AbstractComando comandoConNomeGiusto = factory.costruisciComando("vai nord");
		assertEquals("Vai", comandoConNomeGiusto.getNome());
	}
	
	// verifica che il parametro di un comando valido sia giusto
	@Test
	public void testCostruisciComando_comandoConParametroGiusto() {
		AbstractComando comandoConParametroGiusto = factory.costruisciComando("prendi osso");
		assertEquals("osso", comandoConParametroGiusto.getParametro());
	}
	
	// verifica che il nome di un comando valido senza parametro non abbia effettivamente il parametro
	@Test
	public void testCostruisciComando_comandoSenzaParametro() {
		AbstractComando comandoSenzaParametro = factory.costruisciComando("guarda");
		assertNull(comandoSenzaParametro.getParametro());
	}

}
