package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_SPOSTAMENTO_NEGATIVO = "Hai sbagliato a non salutarmi, maleducato!" + 
			"Nella stanza con meno attrezzi verrai spostato";
	
	private static final String MESSAGGIO_SPOSTAMENTO_POSITIVO = "Grazie per avermi salutato, maleducato!" + 
			"Nella stanza con più attrezzi verrai spostato";
	private static final String MESSAGGIO_RISATA = "AH-AH-AH-AH";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		
		if (partita.getStanzaCorrente().getPersonaggio().haSalutato()) {
			
			Stanza stanzaMax = partita.getStanzaCorrente().getMapStanzeAdiacenti().values().iterator().next();
			for (Stanza s : partita.getStanzaCorrente().getMapStanzeAdiacenti().values()) {
				
				if (s.getAttrezzi().size()>=stanzaMax.getAttrezzi().size()) 
					stanzaMax = s;
				
			}
			partita.setStanzaCorrente(stanzaMax);
			return MESSAGGIO_SPOSTAMENTO_POSITIVO;
		}
		else {
			
			Stanza stanzaMin = partita.getStanzaCorrente().getMapStanzeAdiacenti().values().iterator().next();
			for (Stanza s : partita.getStanzaCorrente().getMapStanzeAdiacenti().values()) {
				
				if (s.getAttrezzi().size()<=stanzaMin.getAttrezzi().size()) 
					stanzaMin = s;
				
			}
			partita.setStanzaCorrente(stanzaMin);
			return MESSAGGIO_SPOSTAMENTO_NEGATIVO;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RISATA;
	}

}
