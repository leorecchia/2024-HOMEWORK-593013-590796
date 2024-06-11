package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version rivisitazione di Federico Sepe, Leonardo Recchia
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.\n";


	private Partita partita;
	private IO io;

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		AbstractComando comandoPerGuardare;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(io);
		comandoPerGuardare = factory.costruisciComando("guarda");
		comandoPerGuardare.esegui(partita);
		
		do
			istruzione = this.io.leggiRiga();

		while (!processaIstruzione(istruzione) && partita.getGiocatore().getCfu() > 0);

		if (this.partita.getGiocatore().getCfu() == 0) {
			this.io.mostraMessaggio("CFU terminati, hai perso.");
			this.partita.setFinita();
		}
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */

	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(io);
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) {
		try {
			Scanner scanner = new Scanner(System.in);
			IO io = new IOConsole(scanner);
			Labirinto labirinto = new LabirintoBuilder()
					.addStanzaIniziale("LabCampusOne")
					.addAttrezzo("spada", 1)
					.addStanzaVincente("Biblioteca")
					.addAdiacenza("LabCampusOne","Biblioteca",Direzione.OVEST)
					.getLabirinto();
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();		
			scanner.close();
		}catch(Exception e) {
			System.out.println("Errore!");
		}
	}
}