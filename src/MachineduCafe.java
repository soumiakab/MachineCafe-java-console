import java.util.Scanner;
public class MachineduCafe {
	
	static Scanner scan =new Scanner(System.in);
	static int choose;
	static String Temp;
	static boolean doneOperation=true;
	static Money[] cashRegister=new Money[] {new Money(10,3),new Money(5,13),new Money(2,23),new Money(1,30)};
	
	public static Money[] recoverRemainingAmount(int remainingAmount) {
		
		Money[] reste=new Money[] {new Money(10,0),new Money(5,0),new Money(2,0),new Money(1,0)};
		
		for(int i=0; i < cashRegister.length; i++){			
			if((remainingAmount / cashRegister[i].montant)>=1) {				
				reste[i].quantite=remainingAmount / cashRegister[i].montant;
				
				if(reste[i].quantite > cashRegister[i].quantite) {
					remainingAmount -=reste[i].montant * cashRegister[i].quantite;
					reste[i].quantite=cashRegister[i].quantite;
					cashRegister[i].quantite=0;
				}
				else {
					remainingAmount -=reste[i].montant * reste[i].quantite;
					cashRegister[i].quantite -= reste[i].quantite;					
				}				
			}
		}	
		
		
		if(remainingAmount >0) {
			doneOperation=false;
		}
		else {
			doneOperation=true;
		}
		return reste;
		
	}

	static void addAmountToCashRegister(int amount) {
		
		for(int i=0; i < cashRegister.length; i++){
			if(cashRegister[i].montant == amount) {
				cashRegister[i].quantite++;
			}
		}
		
	}
	static boolean checkNumber(String nb,int maxval){
		try {
			int temp= Integer.parseInt(nb);	
			if(temp<=0 ||temp>maxval) {
				return false;
			}
			return true;
		}catch(NumberFormatException e){
			return false;
		}		
	}
	
	
	public static void listDrinks(Drink[] drinks) {
		
		System.out.println("Bonjour , Voici la liste des boissons disponibles");
		
		for(int i=0; i< drinks.length; i++){			
			drinks[i].afficherBoisson(i);
		}
		
	}
	

	public static void clientChoose(int length) {
		
		do {
			System.out.println("Veuillez entrer le numero qui convient a votre choix");	
			Temp=scan.nextLine();
			
		}while(!checkNumber(Temp,length));
		
		choose=Integer.parseInt(Temp);
	}

	
	
	public static void main(String[] args) {
				
		Drink[] boissons=new Drink[] {new Drink(2,"lait"),new Drink(1,"cafe"),new Drink(2,"chocolat")};
		
		int montant;
		
		
		listDrinks(boissons);
		
		clientChoose(boissons.length);
		
		do {			
			 System.out.println("Veuillez entrer "+ boissons[choose-1].prix + " Dh " +"pour avoir votre commande");
			 Temp=scan.nextLine();
			 
		}while(!checkNumber(Temp,1000));
		
		
		montant=Integer.parseInt(Temp);
		
		
		if(montant >= boissons[choose-1].prix) {
			
			if(montant > boissons[choose-1].prix) {
				
				 Money[] montantReste= recoverRemainingAmount(montant-boissons[choose-1].prix);
				 
				 if(doneOperation) {
					 
					 System.out.println("Take Your order & welcome to the Java class");
					 System.out.println("Recuperer Le montant qui vous reste :"+ (montant - boissons[choose-1].prix));
						System.out.println("sous forme de  :");
						
						for(int i=0; i< montantReste.length; i++){			
							System.out.print(montantReste[i].quantite +" x "+montantReste[i].montant+" Dh; ");
						}
						addAmountToCashRegister(montant);
						
						System.out.println("\n l argent restante dans la caisse est");
						for(int i=0; i< cashRegister.length; i++){			
							cashRegister[i].afficherMoney();
						}
						
				 }else {
					 System.out.println("On peut pas valider votre commande ,nous avons pas d argent suffisante pour vous donnez le reste!");
				 }				
				
			}
			else {
				addAmountToCashRegister(montant);
				System.out.println("Take Your order & welcome to the Java class");
			}
		}
		
		else {
			System.out.println("votre commande n'est valider Merci d entrer un montant valide");
		}
		
   }

}
