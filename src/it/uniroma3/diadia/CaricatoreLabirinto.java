package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";

	/*
	 * prefisso della riga contenente le specifiche degli attrezzi da collocare nel
	 * formato <nomeAttrezzo> <peso> <nomeStanza>
	 */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/*
	 * prefisso della riga contenente le specifiche dei collegamenti tra stanza nel
	 * formato <nomeStanzaDa> <direzione> <nomeStanzaA>
	 */
	private static final String USCITE_MARKER = "Uscite:";
	
	private static final String STANZA_BUIA_MARKER = "Buia:";
	
	private static final String STANZA_BLOCCATA_MARKER = "Bloccata:";
	
	private static final String STANZA_MAGICA_MARKER = "Magica:";
	
	private static final String MAGO_MARKER = "Maghi:";
	
	private static final String STREGA_MARKER = "Streghe:";
	
	private static final String CANE_MARKER = "Cani:";

	/*
	 * Esempio di un possibile file di specifica di un labirinto (vedi
	 * POO-26-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11 Inizio: N10 Vincente: N11 Attrezzi: martello 10
	 * biblioteca, pinza 2 N10 Uscite: biblioteca nord N10, biblioteca sud N11
	 * 
	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.reader = new LineNumberReader(nomeFile);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiStanzaBuia();
			this.leggiStanzaBloccata();
			this.leggiStanzaMagica();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}
	
	private void leggiStanzaBuia() throws FormatoFileNonValidoException {
		String nomiStanzeBuie = this.leggiRigaCheCominciaPer(STANZA_BUIA_MARKER);
		for (String specificaStanzaBuia : separaStringheAlleVirgole(nomiStanzeBuie)) {
			String nomeStanzaBuia = null;
			String nomeAttrezzoIlluminante = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza buia."));
				nomeStanzaBuia = scannerLinea.next();
				check(isStanzaValida(nomeStanzaBuia), msgTerminazionePrecoce(nomeStanzaBuia + " non definita."));
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo illuminante " + nomeStanzaBuia + "."));
				nomeAttrezzoIlluminante = scannerLinea.next();
			}
			Stanza buia = nome2stanza.get(nomeStanzaBuia);
			buia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoIlluminante);
			this.nome2stanza.put(nomeStanzaBuia, buia);
		}
	}
	
	private void leggiStanzaBloccata() throws FormatoFileNonValidoException {
		String nomiStanzeBloccate = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MARKER);
		for (String specificaStanzaBloccata : separaStringheAlleVirgole(nomiStanzeBloccate)) {
			String nomeStanzaBloccata = null;
			String nomeDirezioneBloccata = null;
			String nomeAttrezzoChiave = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza bloccata."));
				nomeStanzaBloccata = scannerLinea.next();
				check(isStanzaValida(nomeStanzaBloccata), msgTerminazionePrecoce(nomeStanzaBloccata + " non definita."));
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la direzione in cui " + nomeStanzaBloccata + "è bloccata."));
				nomeDirezioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo chiave per accedere a " + nomeStanzaBloccata + "."));
				nomeAttrezzoChiave = scannerLinea.next();
			}
			Stanza bloccata = nome2stanza.get(nomeStanzaBloccata);
			bloccata = new StanzaBloccata(nomeStanzaBloccata, this.nome2Direzione(nomeDirezioneBloccata), nomeAttrezzoChiave);
			this.nome2stanza.put(nomeStanzaBloccata, bloccata);
		}
	}
	
	private void leggiStanzaMagica() throws FormatoFileNonValidoException {
		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MARKER);
		for (String specificaStanzaBloccata : separaStringheAlleVirgole(nomiStanzeMagiche)) {
			String nomeStanzaMagica = null;
			String sogliaMagica = null;
			int soglia=3;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una stanza magica."));
				nomeStanzaMagica = scannerLinea.next();
				check(isStanzaValida(nomeStanzaMagica), msgTerminazionePrecoce(nomeStanzaMagica + " non definita."));
				if (scannerLinea.hasNext()) {
					sogliaMagica = scannerLinea.next();
					soglia = Integer.parseInt(sogliaMagica);
				}
			}
			catch (NumberFormatException e) {
				check(false, "Soglia magica di " + nomeStanzaMagica + " non valida. Stanza magica creata con una soglia di default");
			}
			Stanza magica = nome2stanza.get(nomeStanzaMagica);
			magica = new StanzaMagica(nomeStanzaMagica, soglia);
			this.nome2stanza.put(nomeStanzaMagica, magica);
		}
	}
	
	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String nomiMaghi = this.leggiRigaCheCominciaPer(MAGO_MARKER);
		for (String specificaMaghi : separaStringheAlleVirgole(nomiMaghi)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazionePersonaggio = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			int peso = 1;
			try (Scanner scannerLinea = new Scanner(specificaMaghi)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la stanza dove si trova il mago."));
				nomeStanza = scannerLinea.next();
				check(isStanzaValida(nomeStanza), msgTerminazionePrecoce(nomeStanza + " non definita."));
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome del mago."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la presentazione di " + nomePersonaggio + "."));
				presentazionePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("l'attrezzo del mago."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso di " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				peso = Integer.parseInt(pesoAttrezzo);
			}
			catch (NumberFormatException e) {
				check(false, "Peso di " + nomeAttrezzo + " non valido. Mago creato con un attrezzo con un peso di default");
			}
			AbstractPersonaggio mago = new Mago(nomePersonaggio, presentazionePersonaggio, new Attrezzo (nomeAttrezzo, peso));
			this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
		}
	}
	
	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String nomiStreghe = this.leggiRigaCheCominciaPer(STREGA_MARKER);
		for (String specificaStrega : separaStringheAlleVirgole(nomiStreghe)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazionePersonaggio = null;
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la stanza dove si trova la strega."));
				nomeStanza = scannerLinea.next();
				check(isStanzaValida(nomeStanza), msgTerminazionePrecoce(nomeStanza + " non definita."));
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome della strega."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la presentazione di " + nomePersonaggio + "."));
				presentazionePersonaggio = scannerLinea.next();
			}
			AbstractPersonaggio strega = new Strega(nomePersonaggio, presentazionePersonaggio);
			this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
		}
	}
	
	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String nomiCani = this.leggiRigaCheCominciaPer(CANE_MARKER);
		for (String specificaCani : separaStringheAlleVirgole(nomiCani)) {
			String nomeStanza = null;
			String nomePersonaggio = null;
			String presentazionePersonaggio = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaCani)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la stanza dove si trova il cane."));
				nomeStanza = scannerLinea.next();
				check(isStanzaValida(nomeStanza), msgTerminazionePrecoce(nomeStanza + " non definita."));
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome del cane."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la presentazione di " + nomePersonaggio + "."));
				presentazionePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("l'attrezzo preferito del cane."));
				nomeAttrezzo = scannerLinea.next();
			}
			AbstractPersonaggio cane = new Cane(nomePersonaggio, presentazionePersonaggio, nomeAttrezzo);
			this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
		}
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for (String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {
				String stanzaPartenza = null;
				String dir = null;
				String stanzaDestinazione = null;

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
					stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),
							msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
					dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la destinazione di una uscita della stanza "
							+ stanzaPartenza + " nella direzione " + dir));
					stanzaDestinazione = scannerDiLinea.next();
					Direzione direzione = this.nome2Direzione(dir);
					impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
				}
			}
		}

	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + dir);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public Map<String, Stanza> getStanze() {
		return this.nome2stanza;
	}
	
	public Direzione nome2Direzione (String d) {
		Direzione direzione=null;
		if (d.equals("Nord")) direzione = Direzione.NORD;
		if (d.equals("Ovest")) direzione = Direzione.OVEST;
		if (d.equals("Sud")) direzione = Direzione.SUD;
		if (d.equals("Est")) direzione = Direzione.EST;
		return direzione;
	}
}
