package project20220411;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Offers;
import entity.Products;
import entity.Purchases;

public class FruitShop {

	public static void main(String[] args) {
		/* PRODUCTS:
		 	Pears 	0.75€
			Apples 	0.9€
			Oranges 1€
		 */
		/* OFFERS:
		 	3x2 on apples
			1 Orange free for every 2 Pears
			1€ off 4€ on Pears
		 */
		/* Entry 1: list of Products and Pricing
		  	PRODUCT	PRICE
		 	Pear 	0.75
			Apple	0.9
			Orange	1
		 */
		/* Entry 2: Purchase
			PRODUCT	QUANTITY
			Pear	3
			Orange	25
			Apple 	12
		*/
		/* OUTPUT must include:
		 	total price
		 	list of products
		 	applied offers
		 */
		Products[] selector = productAndPricesSelector();
		Purchases[] purchase = purchaseIn();
		outputReceipt(selector, purchase);
		
		
	}
	
	private static void outputReceipt(Products[] products, Purchases[] purchase) {
		System.out.println("List of products:");
		System.out.println("Product - Ammount - Price");
		double total = 0.00;
		for(Purchases pur : purchase) {
			for(Products pro : products) {
				if (pur.getProductId().equals(pro.getProductId())) {
					System.out.println(pro.getProductName() + " - " + pur.getProductQuantity() + " - " + pro.getProductPrice() + "€");
					total += pur.getProductQuantity() * pro.getProductPrice();
				} 
			}
		}
		applyDiscounts(products, purchase, total);
		System.out.println("Total: " + total + "€");
	}

	private static void applyDiscounts(Products[] products, Purchases[] purchase, double total) {
		Offers[] offers = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			offers = mapper.readValue(Paths.get("./src/main/java/Offers/Offers 1").toFile(), Offers[].class);
			
			for (Offers offer : offers) {
				for(Purchases pur : purchase) {
					if (offer.getTrigger().equals(pur))
					{
						System.out.println("Se aplica descuento: " + offer.getName());
					}
				}
			}
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	private static Purchases[] purchaseIn() {
		Purchases[] selection = null;
		System.out.println("Now please input a purchase: ");
		String filename = null;
		File dir = new File("./src/main/java/Purchases");
	    String[] children = dir.list();
		for (int i = 0; i< children.length; i++) {
            filename = children[i];
            System.out.println("[" + (i+1) + "] - " + filename);
         }
		System.out.print("Select one: ");
		try {
			Scanner scanner = new Scanner(System.in);
			int c =  scanner.nextInt();
			System.out.println("You chose option " + children[c-1]);
			ObjectMapper mapper = new ObjectMapper();
			selection = mapper.readValue(Paths.get("./src/main/java/Purchases/" + children[c-1]).toFile(), Purchases[].class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return selection;
	}

	public static Products[] productAndPricesSelector () {
		Products[] selection = null;
		String filename = null;
		File dir = new File("./src/main/java/Products and Pricing");
	    String[] children = dir.list();
		System.out.println("Welcome to the simple Fruit shop!");
		System.out.println("Please select the file with the list of Products and Pricing");
		for (int i = 0; i< children.length; i++) {
            filename = children[i];
            System.out.println("[" + (i+1) + "] - " + filename);
         }
		System.out.print("Select one: ");
		try {
			Scanner scanner = new Scanner(System.in);
			int c =  scanner.nextInt();
			// char c = (char) System.in.read();
			
			System.out.println("You chose option: " + children[c-1]);
			ObjectMapper mapper = new ObjectMapper();
			selection = mapper.readValue(Paths.get("./src/main/java/Products and Pricing/" + children[c-1]).toFile(), Products[].class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return selection;
	}

}
