package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Questa classe serve per le stampe e le lettura all'interno
 * dell'intero gioco.
 * 
 * @author docente
 * 
 * @version base
 * 
 */

public class IOConsole implements IO{
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
}
