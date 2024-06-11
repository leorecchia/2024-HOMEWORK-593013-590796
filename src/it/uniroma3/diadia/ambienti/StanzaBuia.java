package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoIlluminante;
	
	public StanzaBuia (String nome, String nomeAttrezzoIlluminante) {
		super(nome);
		this.nomeAttrezzoIlluminante=nomeAttrezzoIlluminante;
	}
	
	public String getNomeAttrezzoIlluminante () {
		return this.nomeAttrezzoIlluminante;
	}
	
	@Override
	public String getDescrizione() {
		if (!this.hasAttrezzo(nomeAttrezzoIlluminante))
			return "qui c'è buio pesto";
		else
			return this.toString();
	}
	
	@Override
	public int hashCode () {
		return super.hashCode()+this.getClass().hashCode()+this.nomeAttrezzoIlluminante.hashCode();
	}
	
	@Override
	public boolean equals (Object o) {
		if (o==null || o.getClass()!=this.getClass()) return false;
		StanzaBuia that = (StanzaBuia) o;
		return super.equals((Stanza)that) && this.getNomeAttrezzoIlluminante().equals(that.getNomeAttrezzoIlluminante());
	}
}
