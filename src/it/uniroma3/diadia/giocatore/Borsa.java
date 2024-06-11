package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = Configurazione.getPesoMax();
	private Map<String, Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
		//this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			return false;
		}
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		Collection<Attrezzo> collezioneAttrezzi = this.attrezzi.values();
		for (Attrezzo attrezzo: collezioneAttrezzi)
			peso += attrezzo.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append(getContenutoRaggruppatoPerPeso().toString());
		/*	Collection<Attrezzo> collezioneAttrezzi = this.attrezzi.values();
			for (Attrezzo attrezzo : collezioneAttrezzi)
				s.append(attrezzo.toString() + " "); */
		}

		else
			s.append("Borsa vuota");
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		Collection<Attrezzo> listaAttrezzi = this.attrezzi.values();
		final List<Attrezzo> ordinata = new ArrayList<>(listaAttrezzi);
		final ComparatoreAttrezzoPerPeso cmp = new ComparatoreAttrezzoPerPeso();
		Collections.sort(ordinata, cmp);	//ordina "ordinata" in base al comparatore esterno cmp
		return ordinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome () {
		Collection<Attrezzo> listaAttrezzi=this.attrezzi.values();
		final SortedSet<Attrezzo> ordinato=new TreeSet<>(listaAttrezzi);
		return ordinato;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		final Map<Integer, Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		Collection<Attrezzo> listaAttrezzi = this.attrezzi.values();
		for(Attrezzo attrezzo:listaAttrezzi) {
			if (peso2attrezzi.containsKey(attrezzo.getPeso())) {
				final Set<Attrezzo> stessoPeso=peso2attrezzi.get(attrezzo.getPeso());
				stessoPeso.add(attrezzo);
			}
			else {
				final Set<Attrezzo> nuovoSet =new HashSet<>();
				nuovoSet.add(attrezzo);
				peso2attrezzi.put(attrezzo.getPeso(), nuovoSet);
			}
		}
		return peso2attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		final Collection<Attrezzo> listaAttrezzi = this.attrezzi.values();
		final ComparatoreAttrezzoPerPeso cmp = new ComparatoreAttrezzoPerPeso();		//creo il comparatore che userà come criterio per ordinare gli elementi
		SortedSet<Attrezzo> ordinato = new TreeSet<>(cmp);		//creo il SortedSet con il criterio per ordinare gli elementi
		for (Attrezzo attrezzo : listaAttrezzi) {			//faccio un for per inserire uno alla volta tutti gli elementi e ordinarli con l'inserimento
			ordinato.add(attrezzo);
		}
		return ordinato;
	}
	
}
