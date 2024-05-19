package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

/* per ora confiniamo il codice a fisarmonica in quest'altra classe
 * -->non risolviamo veramente il problema */
public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	
	private IO io; 
	public FabbricaDiComandiFisarmonica(IO io) {
		this.io=io;
	}

	/**
	 * metodo che restituisce un comando partendo dall'istruzione che viene passata
	 * come parametro
	 */
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		if (nomeComando == null)
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else
			comando = new ComandoNonValido();
		comando.setParametro(parametro);
		comando.setIO(io);
		return comando;
	}
}
