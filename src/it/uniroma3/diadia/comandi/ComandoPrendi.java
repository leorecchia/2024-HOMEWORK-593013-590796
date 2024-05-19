package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Cerca di aggiungere un attrezzo alla borsa rimuovendolo dalla stanza.
 * Se la stanza ha un attrezzo con il nome specificato e la borsa può contenerlo,
 * l'operazione va a buon fine. Altrimenti viene stampato un messaggio di errore.
 * 
 * @param nome dell'attrezzo che bisogna prendere
 */
public class ComandoPrendi implements Comando{
	
	private IO io;
	private String nomeAttrezzoDaPrendere;
	
	@Override
	public void esegui(Partita partita) {
		if (!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzoDaPrendere))
			io.mostraMessaggio("L'attrezzo che stai cercando non è presente nella stanza.");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggio("L'attrezzo è stato correttamente rimosso dalla stanza e aggiunto alla borsa.");
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}	
			else
				io.mostraMessaggio("Impossibile aggiungere l'attrezzo alla borsa, parametri massimi della borsa raggiunti.");
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPrendere=parametro;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzoDaPrendere;
	}
	
	@Override
	public void setIO(IO io) {
		this.io=io;
	}
	
}
