package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoIlluminante;
	
	public StanzaBuia (String nome, String nomeAttrezzoIlluminante) {
		super(nome);
		this.nomeAttrezzoIlluminante=nomeAttrezzoIlluminante;
	}
	
	@Override
	public String getDescrizione() {
		if (!this.hasAttrezzo(nomeAttrezzoIlluminante))
			return "qui c'è buio pesto";
		else
			return this.toString();
	}
}
