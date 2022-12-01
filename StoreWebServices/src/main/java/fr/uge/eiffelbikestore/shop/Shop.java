package fr.uge.eiffelbikestore.shop;

import java.rmi.Naming;
import java.util.Set;
import java.util.stream.Collectors;

import fr.uge.eiffelbikestore.store.IStore;

import fr.uge.eiffelbikestore.bike.IBike;

public class Shop {
	
	private final Set<IBike> bikes;
	
	private final Set<IBike> cart;
	
	Public Shop() {
		this.bikes = ((IStore) Naming.lookup("rmi://localhost/BikeShopService")).getCanBeBuy();
	}
	
	public Integer getPriceById(Long id) {
		return bikes.stream().filter(x -> x.getId() == id).findFirst().orElseThrow().getPrice();
	}
	
	public boolean isAvailableById(Long id) {
		return bikes.stream().anyMatch(x -> x.getId() == id);
	}
	
	public void addToCart(Long id) {
		cart.add(bikes.stream().filter(x -> x.getId() == id).findFirst().orElseThrow());
	}
	
	public void cartValidation() {
		bikes.removeAll(cart.stream().filter(x -> bikes.contains(x)).collect(Collectors.toSet()));
	}
	
	public String get() {
		return "Shop";
	}
}
