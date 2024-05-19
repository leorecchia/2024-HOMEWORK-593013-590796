package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando {

	private String direzione;
	private IO io;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();

		Stanza prossimaStanza = null;

		if (this.direzione == null) {

			io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione"); // Hai inserito solo 'vai'

			return;

		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);

		if (prossimaStanza == null) {

			io.mostraMessaggio("Direzione inesistente"); // Dove voglio andare non ci sono uscite

			return;

		}

		partita.setStanzaCorrente(prossimaStanza); // Aggiorno la stanza corrente essendomi spostato
		
		int cfu = partita.getGiocatore().getCfu();

		partita.getGiocatore().setCfu(--cfu); // Invece qua prima decremento quindi aggiorno quindi modifico
		
		/* eseguo il comando per 'guardare' ogni volta che ci spostiamo da una stanza all'altra */
		FabbricaDiComandiFisarmonica factory=new FabbricaDiComandiFisarmonica(this.io);
		Comando comandoPerGuardare=factory.costruisciComando("guarda");
		comandoPerGuardare.esegui(partita);
	}

	/**
	 * sovrascriviamo il metodo definito nell'interfaccia
	 */
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return direzione;
	}
	
	@Override
	public void setIO(IO io) {
		this.io=io;
	}
}
