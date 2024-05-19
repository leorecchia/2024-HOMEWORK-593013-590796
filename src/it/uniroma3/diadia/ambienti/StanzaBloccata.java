package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String nomeDirezioneBloccata;
	private String nomeAttrezzoChiave;

	public StanzaBloccata(String nome, String nomeDirezioneBloccata, String nomeAttrezzoChiave) {
		super(nome);
		this.nomeDirezioneBloccata = nomeDirezioneBloccata;
		this.nomeAttrezzoChiave = nomeAttrezzoChiave;
	}

	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (!dir.equals(nomeDirezioneBloccata) || this.hasAttrezzo(nomeAttrezzoChiave)) {
			return super.getStanzaAdiacente(dir);
		} else
			return this;
	}
	
	@Override
	public String getDescrizione() {
		return this.toString()+"\nLa stanza in cui ti trovi è bloccata nella direzione " + nomeDirezioneBloccata +
				 ". \nPuoi accedere alla stanza nella direzione bloccata esclusivamente tramite l'attrezzo di nome " + nomeAttrezzoChiave+".";
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.nomeAttrezzoChiave.hashCode() + this.nomeDirezioneBloccata.hashCode() + super.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || o.getClass()!=this.getClass()) return false;
		StanzaBloccata that=(StanzaBloccata)o;
		return super.equals((Stanza)that) && this.nomeAttrezzoChiave.equals(that.nomeAttrezzoChiave) 
				&& this.nomeDirezioneBloccata.equals(that.nomeDirezioneBloccata);
	}
	
}
