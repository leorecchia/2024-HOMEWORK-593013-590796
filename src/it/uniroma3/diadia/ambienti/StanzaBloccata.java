package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private Direzione DirezioneBloccata;
	private String nomeAttrezzoChiave;
	
	public StanzaBloccata (String nome, Direzione nomeDirezioneBloccata, String nomeAttrezzoChiave) {
		super(nome);
		this.DirezioneBloccata=nomeDirezioneBloccata;
		this.nomeAttrezzoChiave=nomeAttrezzoChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
		if (!dir.equals(DirezioneBloccata) || this.hasAttrezzo(nomeAttrezzoChiave)) {
			return super.getStanzaAdiacente(dir);
		}
		else
			return this;
	}
	
	@Override
	public String getDescrizione() {
		return this.toString() + 
				"\nLa stanza in cui ti trovi è bloccata nell direzione " + DirezioneBloccata + 
				" ,per accederci la stanza deve l'attrezzo '" + nomeAttrezzoChiave + "'.";
	}
	
	@Override
	public int hashCode () {
		return super.hashCode()+this.getClass().hashCode()+this.DirezioneBloccata.hashCode()+this.nomeAttrezzoChiave.hashCode();
	}
	
	@Override
	public boolean equals (Object o) {
		if (o==null || o.getClass()!=this.getClass()) return false;
		StanzaBloccata that = (StanzaBloccata) o;
		return super.equals((Stanza)that) && this.nomeAttrezzoChiave.equals(that.nomeAttrezzoChiave)
				&& this.DirezioneBloccata.equals(that.DirezioneBloccata);
	}
}
