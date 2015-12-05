public class Couple {
	private int p;
	private int d;
	
	public Couple(int pre, int deu){
		this.p = pre;
		this.d = deu;
	}
	
	public long getCoupleP(){
		return this.p;
	}
	
	public int getCoupleD(){
		return this.d;
	}
	
	public void setCoupleP(int i){
		this.p=i;
	}
	
	public void setCoupleD(int i){
		this.d=i;
	}
	
	public boolean inferieur( Couple b){
		
		return this.getCoupleP()<=b.getCoupleP() ;
	}
	
	public boolean nul(){
		return this.getCoupleP() == 0 && this.getCoupleD() ==0 ;
	}
	
	public boolean egal(Couple a, Couple b){
		
		return a.getCoupleP()==b.getCoupleP() ;
	}

	public boolean superieur( Couple b){
	
	return this.getCoupleP() >= b.getCoupleP() ;
}
	
	public String toString() {
		String aRetourner;
		aRetourner = "(" + this.getCoupleP() + "," + this.getCoupleD() + ")" ;
		
		return aRetourner;
	}
	
	public String show(boolean [] t){
		String aRetourner = "[";
		int l = t.length;
		for (int i=0 ; i<l-1 ; i++)
		{
			aRetourner = aRetourner + t[i] + ";" ;
		}
		return aRetourner  + t[l-1] + "]";
		
	}

	}
	

