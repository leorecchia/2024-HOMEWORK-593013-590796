package it.uniroma3.diadia;

//import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version rivisitazione Leonardo Recchia e Federico Sepe
 */

public class DiaDia {
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole ioConsole;
	
	public DiaDia(IOConsole ioConsole) {
		this.partita = new Partita();
		this.ioConsole=ioConsole;
	}

	public void gioca() {
		String istruzione; 

		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		ioConsole.mostraMessaggio("Ti trovi in:\n"+partita.getStanzaCorrente().getDescrizione());
		ioConsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
		do 
			
			istruzione = ioConsole.leggiRiga();
		
		while (!processaIstruzione(istruzione) && this.partita.getGiocatore().getCfu()>0);
		if(this.partita.getGiocatore().getCfu()==0) {
			ioConsole.mostraMessaggio("CFU finiti, hai perso!");
			partita.setFinita();
		}		
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		if(istruzione.isEmpty()) return false;		// guardie
		
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			ioConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i<elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i]+" ");			//stampa la lista di comandi che si possono scegliere
		ioConsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);	
		if (prossimaStanza == null) {
			ioConsole.mostraMessaggio("Direzione inesistente");
		}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		ioConsole.mostraMessaggio("\nTi trovi in: "+partita.getStanzaCorrente().getDescrizione());
		ioConsole.mostraMessaggio("I tuoi CFU rimasti sono: "+partita.getGiocatore().getCfu());
		ioConsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
	}
	
	/**
	 * Cerca di aggiungere un attrezzo alla borsa rimuovendolo dalla stanza.
	 * Se la stanza ha un attrezzo con il nome specificato e la borsa può contenerlo,
	 * l'operazione va a buon fine. Altrimenti viene stampato un messaggio di errore.
	 */
	private void prendi(String nomeAttrezzoDaPrendere) {
		if (!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzoDaPrendere))
			ioConsole.mostraMessaggio("L'attrezzo che stai cercando non è presente nella stanza.");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				ioConsole.mostraMessaggio("L'attrezzo è stato correttamente rimosso dalla stanza e aggiunto alla borsa.");
				ioConsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
			}	
			else
				ioConsole.mostraMessaggio("Impossibile aggiungere l'attrezzo alla borsa, parametri massimi della borsa raggiunti.");
		}
	}
	
	/**
	 * Cerca di aggiungere un attrezzo alla stanza rimuovendolo dalla borsa.
	 * Se la borsa ha un attrezzo con il nome specificato e la borsa può contenerlo,
	 * l'operazione va a buon fine. Altrimenti viene stampato un messaggio di errore.
	 */
	private void posa(String nomeAttrezzoDaPosare) {
		Borsa borsa=this.partita.getGiocatore().getBorsa();
		if (!borsa.hasAttrezzo(nomeAttrezzoDaPosare))
			ioConsole.mostraMessaggio("L'attrezzo che stai cercando non è presente nella borsa.");
		else {
			Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzoDaPosare);
			if(this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				borsa.removeAttrezzo(nomeAttrezzoDaPosare);
				ioConsole.mostraMessaggio("L'attrezzo è stato correttamente rimosso dalla borsa e aggiunto alla stanza.");
				ioConsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
			}
			else
				ioConsole.mostraMessaggio("Impossibile aggiungere attrezzo alla stanza, numero massimo di attrezzi raggiunto.");
		}
	}
	
	

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole ioConsole=new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}

}
