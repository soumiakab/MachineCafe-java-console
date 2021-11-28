
public class Money {
	int montant;
	int quantite;
	public Money(int montant,int quantite){
		this.montant=montant;
		this.quantite=quantite;
	}
	public  void afficherMoney() {
		System.out.println("-"+this.montant +" Dh \t quantite disonible "+this.quantite +" ");
	}
	
}
