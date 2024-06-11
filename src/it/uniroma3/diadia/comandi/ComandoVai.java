package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	private Direzione direzione;
	
	public void setParametro (Direzione parametro) {
		this.direzione=parametro;
	}
	
	@Override
	public String getNome () {
		return "Vai";
	}

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (super.getParametro() == null) {
			getIo().mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione"); // Hai inserito solo 'vai'
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null) {
			getIo().mostraMessaggio("Direzione inesistente"); // Dove voglio andare non ci sono uscite
			return;
		}
		partita.setStanzaCorrente(prossimaStanza); // Aggiorno la stanza corrente essendomi spostato
		int cfu = partita.getGiocatore().getCfu();

		partita.getGiocatore().setCfu(--cfu); // Invece qua prima decremento quindi aggiorno quindi modifico
		
		// creo il comandoGuarda per visualizzare le informazioni della stanza e della borsa
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(super.getIo());
		AbstractComando comandoGuarda = factory.costruisciComando("guarda");
		comandoGuarda.esegui(partita);
	}
}
