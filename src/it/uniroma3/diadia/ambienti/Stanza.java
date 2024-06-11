package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = Configurazione.getDirezioniMax();
	static final private int NUMERO_MASSIMO_ATTREZZI = Configurazione.getAttrezziMax();

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	// private int numeroAttrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	// private int numeroStanzeAdiacenti; //inutile usare size
	private Set<Direzione> direzioni;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		// this.numeroStanzeAdiacenti = 0;
		// this.numeroAttrezzi = 0;
		this.direzioni = new HashSet<Direzione>();
		this.stanzeAdiacenti = new HashMap<Direzione, Stanza>();
		this.attrezzi = new HashMap<String, Attrezzo>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if (this.stanzeAdiacenti.size() < NUMERO_MASSIMO_DIREZIONI) {
			this.stanzeAdiacenti.put(direzione, stanza);
			direzioni.add(direzione);
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		} else
			return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: " + this.direzioni.toString());
		/*
		 * for (String iterDirezioni:direzioni) if (iterDirezioni!=null)
		 * risultato.append(" " + iterDirezioni);
		 */
		risultato.append("\nAttrezzi nella stanza: ");

		Collection<Attrezzo> collezioneAttrezzi = this.attrezzi.values();
		for (Attrezzo attrezzo : collezioneAttrezzi) {
			if (attrezzo != null) // inutile il foreach non controlla i null
				risultato.append(attrezzo.toString() + " ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.containsKey(attrezzo.getNome())) {
			this.attrezzi.remove(attrezzo.getNome());
			return false;
		}
		// this.attrezzi.remove(attrezzo.getNome()); -->abbiamo gia cancellato non mi
		// serve
		// numeroAttrezzi--;
		return true;
	}

	public Set<Direzione> getDirezioni() {
		return this.direzioni;
	}


	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getClass().hashCode() + this.direzioni.hashCode()
		+ this.stanzeAdiacenti.hashCode() + this.attrezzi.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass())
			return false;
		Stanza that = (Stanza) o;
		return this.getNome().equals(that.getNome()) && this.getAttrezzi().equals(that.getAttrezzi())
				&& this.getDirezioni().equals(that.getDirezioni());
	}
}