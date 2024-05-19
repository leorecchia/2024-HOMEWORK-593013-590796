package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * 
 * @see Stanza
 * @see Labirinto
 * @see Giocatore
 * 
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	
	public Partita(Labirinto labirinto){
		this.labirinto=labirinto;
		this.stanzaCorrente=this.labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore=new Giocatore();
	}
    

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public boolean giocatoreIsVivo() {
		return (this.giocatore.getCfu()>0);
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto=labirinto;
	}
}
