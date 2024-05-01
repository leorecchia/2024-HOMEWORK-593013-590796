package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String nomeDirezioneBloccata;
	private String nomeAttrezzoChiave;
	
	public StanzaBloccata (String nome, String nomeDirezioneBloccata, String nomeAttrezzoChiave) {
		super(nome);
		this.nomeDirezioneBloccata=nomeDirezioneBloccata;
		this.nomeAttrezzoChiave=nomeAttrezzoChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (!dir.equals(nomeDirezioneBloccata) || this.hasAttrezzo(nomeAttrezzoChiave)) {
			return super.getStanzaAdiacente(dir);
		}
		else
			return this;
	}
	
	@Override
	public String getDescrizione() {
		return this.toString() + 
				"\nLa stanza in cui ti trovi è bloccata nell direzione " + nomeDirezioneBloccata + 
				" ,per accederci la stanza deve l'attrezzo '" + nomeAttrezzoChiave + "'.";
	}
}
