package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if (partita.getStanzaCorrente().getPersonaggio()==null)
			getIo().mostraMessaggio("Nessun personaggio presente nella stanza da salutare");
		else
			getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
	}

	@Override
	public String getNome() {
		return "Saluta";
	}

}
