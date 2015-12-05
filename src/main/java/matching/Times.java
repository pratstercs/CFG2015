package matching;

import java.util.ArrayList;


public class Times {
	private ArrayList<Paire>[] days;

	public Times(){
		this.days=new ArrayList[7];
		for(int i=0;i<7;i++){
			this.days[i]=Paire.generateAll(24);
		}

	}
	public static void main(String[] args) {
		Times t=new Times();
		String s = ""; 
		for (int i = 0; i < 7; i++) { 
			s = s + " " + t.days[i];
			s = s + "\n"; 
		} 
		 System.out.println(s); 
	}
}