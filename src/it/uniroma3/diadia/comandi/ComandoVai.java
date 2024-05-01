package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	private IO io;
	
	@Override
	public void setIo (IO io) {
		this.io=io;
	}
	
	@Override
	public void setParametro(String direzione) {
		this.direzione = direzione;
	}

	@Override
	public String getParametro () {
		return this.direzione;
	}
	
	@Override
	public String getNome () {
		return "Vai";
	}

	/**
	 * Stampa informazioni di aiuto.
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

		partita.getGiocatore().setCfu(--cfu); // Invece qua prima decremento quindi aggiorno quindi modifico
		
		// creo il comandoGuarda per visualizzare le informazioni della stanza e della borsa
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(io);
		Comando comandoGuarda = factory.costruisciComando("guarda");
		comandoGuarda.esegui(partita);
	}
}
