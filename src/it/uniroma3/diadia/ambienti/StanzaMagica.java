package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	final static private int SOGLIA_MAGICA_DEFAULT = Configurazione.getSogliaMagica();
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}
	
	public boolean isMagica() {
		return this.contatoreAttrezziPosati>this.sogliaMagica;
	}
	
	@Override
	public int hashCode () {
		return super.hashCode()+this.getClass().hashCode()+this.sogliaMagica;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o==null || o.getClass()!=this.getClass()) return false;
		StanzaMagica that = (StanzaMagica) o;
		return super.equals((Stanza)that) && this.sogliaMagica==that.sogliaMagica;
	}
}
