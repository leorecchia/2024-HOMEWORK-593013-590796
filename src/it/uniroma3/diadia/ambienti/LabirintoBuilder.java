package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> stanze;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		this.stanze = new HashMap<String, Stanza>();
		this.stanzaIniziale = null;
		this.stanzaVincente = null;
		this.ultimaStanzaAggiunta = null;
		super.setStanzaIniziale(stanzaIniziale);
		super.setStanzaVincente(stanzaVincente);
	}

	public LabirintoBuilder addStanzaIniziale(String iniziale) {
		if (iniziale != null) {
			this.stanzaIniziale = new Stanza(iniziale);
			this.stanze.put(iniziale, stanzaIniziale);
			this.setStanzaIniziale(stanzaIniziale);
			this.ultimaStanzaAggiunta = stanzaIniziale;
		}
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String vincente) {
		if (vincente != null) {
			stanzaVincente = new Stanza(vincente);
			this.setStanzaVincente(stanzaVincente);
			stanze.put(vincente, stanzaVincente);
			ultimaStanzaAggiunta = stanzaVincente;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String magica, int soglia) {
		if(magica!=null) {
			StanzaMagica stanzaMagica=new StanzaMagica(magica, soglia);
			stanze.put(magica, stanzaMagica);
			ultimaStanzaAggiunta=stanzaMagica;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeBloccata, String direzioneBloccata, String attrezzoChiave) {
		if(nomeBloccata!=null && direzioneBloccata!=null && attrezzoChiave!=null) {
			StanzaBloccata stanzaBloccata=new StanzaBloccata(nomeBloccata, direzioneBloccata, attrezzoChiave);
			stanze.put(nomeBloccata,stanzaBloccata);
			ultimaStanzaAggiunta=stanzaBloccata;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String buia, String attrezzoIlluminante) {
		if(buia!=null && attrezzoIlluminante!=null) {
			StanzaBuia stanzaBuia=new StanzaBuia(buia, attrezzoIlluminante);
			stanze.put(buia, stanzaBuia);
			ultimaStanzaAggiunta=stanzaBuia;
		}
		return this;
	}

	public LabirintoBuilder addStanza(String nome) {
		if (nome != null) {
			Stanza nuova = new Stanza(nome);
			this.stanze.put(nome, nuova);
			ultimaStanzaAggiunta = nuova;
		}
		return this;
	}

	public LabirintoBuilder addAdiacenza(String nome1, String nome2, String direzione) {
		if (stanze.get(nome1)!=null && stanze.get(nome2)!=null) {
			Stanza stanza1 = stanze.get(nome1);
			Stanza stanza2 = stanze.get(nome2);
			stanza1.impostaStanzaAdiacente(direzione, stanza2);
		}
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		if (nome != null) {
			Attrezzo attrezzoDaAggiungere = new Attrezzo(nome, peso);
			if (ultimaStanzaAggiunta != null)
				ultimaStanzaAggiunta.addAttrezzo(attrezzoDaAggiungere);
		}
		return this;
	}

	public Map<String, Stanza> getStanze() {
		return this.stanze;
	}

	public LabirintoBuilder getLabirinto() {
		return this;
	}
}
