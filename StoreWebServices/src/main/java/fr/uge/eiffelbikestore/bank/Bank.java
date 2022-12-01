package fr.uge.eiffelbikestore.bank;

public class Bank {
	
	private Integer balance;
	
	Public Bank(Integer balance) {
		this.balance = balance;
	}
	
	public boolean canPurchase(Integer price) {
		if(balance < price) {
			System.out.println("You don't have enough money to purchase this !");
			return false;
		}
		return true;
	}
	
	public void purchase(Integer price) {
		balance -= price;
	}
	
	public String get() {
		return "Bank";
	}
}
