package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if (partita.getStanzaCorrente().getPersonaggio() == null) {
			this.getIo().mostraMessaggio("Non è possibile regalare un'attrezzo poichè non c'è nessuno nella stanza");
		}
		else {
			if (!partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())) {
				this.getIo().mostraMessaggio(
						"Non puoi regalare " + this.getParametro() + "perchè non lo possiedi nella borsa");
			} else {
				Attrezzo regalo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
				partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				partita.getStanzaCorrente().getPersonaggio().riceviRegalo(regalo, partita);
			}
		}
	}

	@Override
	public String getNome() {
		return "Regala";
	}

}
