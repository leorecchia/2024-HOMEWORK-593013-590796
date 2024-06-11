package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_MORSO = "Il mio morso un CFU ti toglierà";
	private static final String MESSAGGIO_REGALO_ACCETTATO = "Grazie, è proprio il mio cibo preferito ora ti lascio un dono";
	private static final String MESSAGGIO_REGALO_RIFIUTATO = "Questo regalo non mi piace affatto, ti meriti un bel morso";
	private final String attrezzoPreferito;
	private final Attrezzo dono;
	
	public Cane(String nome, String presentazione, String attrezzoPreferito) {
		super(nome, presentazione);
		this.attrezzoPreferito=attrezzoPreferito;
		this.dono = new Attrezzo ("Dono del Cane", 1);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (!attrezzo.getNome().equals(attrezzoPreferito)) {
			this.agisci(partita);
			return MESSAGGIO_REGALO_RIFIUTATO;
		}
		partita.getStanzaCorrente().addAttrezzo(dono);
		return MESSAGGIO_REGALO_ACCETTATO;
	}

}
