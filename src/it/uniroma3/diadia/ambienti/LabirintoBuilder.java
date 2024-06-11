package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{
	
	private Map<String, Stanza> stanze;
	private Stanza ultimaStanzaAggiunta;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public LabirintoBuilder () {
		stanze = new HashMap<>();
		this.ultimaStanzaAggiunta=null;
		this.stanzaIniziale=null;
		this.stanzaVincente=null;
		super.setStanzaIniziale(null);
		super.setStanzaVincente(null);
	}

	public LabirintoBuilder addStanzaIniziale (String nomeStanza) {
		if (nomeStanza!=null) {
			stanzaIniziale = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, stanzaIniziale);
			this.setStanzaIniziale(stanzaIniziale);
			this.ultimaStanzaAggiunta=stanzaIniziale;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente (String nomeStanza) {
		if (nomeStanza!=null) {
			stanzaVincente = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, stanzaVincente);
			super.setStanzaVincente(stanzaVincente);
			this.ultimaStanzaAggiunta=stanzaVincente;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia (String nomeStanza, String nomeAttrezzoIlluminante) {
		if (nomeStanza!=null && nomeAttrezzoIlluminante!=null) {
			StanzaBuia stanzaBuia = new StanzaBuia(nomeStanza, nomeAttrezzoIlluminante);
			this.stanze.put(nomeStanza, stanzaBuia);
			this.ultimaStanzaAggiunta=stanzaBuia;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata (String nomeStanza, Direzione DirezioneBloccata ,String nomeAttrezzoChiave) {
		if (nomeStanza!=null && nomeAttrezzoChiave!=null && DirezioneBloccata!=null) {
			StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanza,DirezioneBloccata, nomeAttrezzoChiave);
			this.stanze.put(nomeStanza, stanzaBloccata);
			this.ultimaStanzaAggiunta=stanzaBloccata;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica (String nomeStanza, int soglia) {
		if (nomeStanza!=null) {
			StanzaMagica stanzaMagica = new StanzaMagica(nomeStanza, soglia);
			this.stanze.put(nomeStanza, stanzaMagica);
			this.ultimaStanzaAggiunta=stanzaMagica;
		}
		return this;
	}
	
	public LabirintoBuilder addStanza (String nomeStanza) {
		if (nomeStanza!=null) {
			Stanza stanza = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, stanza);
			this.ultimaStanzaAggiunta=stanza;
		}
		return this;
	}
	
	public LabirintoBuilder addAdiacenza (String nomeCorrente, String nomeAdiacente, Direzione direzione) {
		if (stanze.get(nomeCorrente)!=null && stanze.get(nomeAdiacente)!=null)
		this.stanze.get(nomeCorrente).impostaStanzaAdiacente(direzione, this.stanze.get(nomeAdiacente));
		return this;
	}
	
	public LabirintoBuilder addAttrezzo (String nomeAttrezzo, int peso) {
		if (this.ultimaStanzaAggiunta!=null && nomeAttrezzo!=null) {
			Attrezzo attrezzoDaPrendere = new Attrezzo(nomeAttrezzo, peso);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzoDaPrendere);
		}
		return this;
	}
	
	public LabirintoBuilder getLabirinto () {
		return this;
	}
	
	public Map<String, Stanza> getStanze () {
		return this.stanze;
	}
	
}
