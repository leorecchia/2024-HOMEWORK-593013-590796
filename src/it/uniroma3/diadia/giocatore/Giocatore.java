package it.uniroma3.diadia.giocatore;

/**
 * Questa classe implementa un giocatore che possiede dei cfu
 * e una borsa.
 * 
 * @author Leonardo Recchia e Federico Sepe
 * 
 * @see Borsa
 * 
 * @version base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa=new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
}
