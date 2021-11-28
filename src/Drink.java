
public class Drink {
	String nom;
	int prix;
	public Drink(int prix,String nom){
		this.nom=nom;
		this.prix=prix;
	}
	
	public  void afficherBoisson(int num) {
		System.out.println((num+1)+ "-"+ this.nom +" : "+this.prix +" Dh");
	}
	
}
