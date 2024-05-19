package it.uniroma3.diadia.ambienti;

//import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * 
 * @see Attrezzo
 * 
 * @version base
 */

public class Stanza {
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String,Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	//private int numeroStanzeAdiacenti;
	private Set<String> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		//this.numeroStanzeAdiacenti = 0;
		//this.numeroAttrezzi = 0;
		this.direzioni = new HashSet<String>();
		this.stanzeAdiacenti = new HashMap<String,Stanza>();
		this.attrezzi = new HashMap<String,Attrezzo>();
	}

	/**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	
    	if(this.stanzeAdiacenti.size()<NUMERO_MASSIMO_DIREZIONI) {
    		this.stanzeAdiacenti.put(direzione, stanza);
    		direzioni.add(direzione);
    	}
    	
   }

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
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
	public Map<String,Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			String nome=attrezzo.getNome();
			this.attrezzi.put(nome,attrezzo);
			return true;
		} 
		else return false;
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
		risultato.append("\nUscite: ");
		risultato.append(this.direzioni);
		risultato.append("\nAttrezzi nella stanza: ");
		/*Collection<Attrezzo> collezioneAttrezzi=attrezzi.values();
		for (Attrezzo attrezzo : collezioneAttrezzi) 
			risultato.append(attrezzo.toString() + " ");*/
		risultato.append(this.attrezzi.toString());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		
		String nomeDaRimuovere=attrezzo.getNome();
		if(this.attrezzi.containsKey(nomeDaRimuovere)) {
			attrezzi.remove(nomeDaRimuovere);
			return true;
		}
		return false;
	}

	public Set<String> getDirezioni() {
		return this.direzioni;
	}

	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.nome.hashCode() + this.direzioni.hashCode() + this.stanzeAdiacenti.hashCode()
		+ this.attrezzi.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if( o==null || o.getClass()!=this.getClass()) return false;
		Stanza that=(Stanza)o;
		return this.nome.equals(that.nome) && this.direzioni.equals(that.direzioni) && this.attrezzi.equals(that.attrezzi)
				&& this.stanzeAdiacenti.equals(that.stanzeAdiacenti);
	}
	
}





