package matching;

import java.util.ArrayList;


public class Paire {
	private int deb;
	private int fin;
	
	public Paire (int deb, int fin) {
		this.deb = deb;
		this.fin = fin; 
	}
	
	public int getDeb() {
		return this.deb;
	}
	public int getFin() {
		return this.fin;
	}
	
	public static ArrayList<Paire> generateAll (int n) {
		
		ArrayList<Paire> liste = new ArrayList<Paire>();
		
		for (int i=0; i<n; i++) {
				liste.add(new Paire (i, i+2));
				i++;
		}
		return liste;
	}
	public String toString(){
		String s="";
		s="{"+this.deb+","+this.fin+"}";
		return s;
	}
	public static void main(String[] args) {
		Paire p=new Paire(1,3);
		System.out.println(p.toString());
	}

}
