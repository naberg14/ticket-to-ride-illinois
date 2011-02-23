package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CreateStandardEurope {

	public static void main(String[] args) {
		Game game = new Game();
		for (int i = 0; i < 12; i++) {
			game.drawableCards.add(Card.PURPLE);
			game.drawableCards.add(Card.WHITE);
			game.drawableCards.add(Card.BLUE);
			game.drawableCards.add(Card.YELLOW);
			game.drawableCards.add(Card.ORANGE);
			game.drawableCards.add(Card.BLACK);
			game.drawableCards.add(Card.RED);
			game.drawableCards.add(Card.GREEN);
			game.drawableCards.add(Card.ENGINE);
		}
		game.drawableCards.add(Card.ENGINE);
		game.drawableCards.add(Card.ENGINE);
		
		// Add Cities
		City Edinburgh = new City("Edinburgh");
		game.map.cities.add(Edinburgh);
		City London = new City("London");
		game.map.cities.add(London);
		City Amsterdam = new City("Amsterdam");
		game.map.cities.add(Amsterdam);
		City Dieppe = new City("Dieppe");
		game.map.cities.add(Dieppe);
		City Brest = new City("Brest");
		game.map.cities.add(Brest);
		City Bruxelles = new City ("Bruxelles");
		game.map.cities.add(Bruxelles);
		City Paris = new City("Paris");
		game.map.cities.add(Paris);
		City Pamplona = new City("Pamplona");
		game.map.cities.add(Pamplona);
		City Barcelona = new City("Barcelona");
		game.map.cities.add(Barcelona);
		City Madrid = new City("Madrid");
		game.map.cities.add(Madrid);
		City Lisboa = new City("Lisboa");
		game.map.cities.add(Lisboa);
		City Cadiz = new City("Cadiz");
		game.map.cities.add(Cadiz);
		City Marseille = new City("Marseille");
		game.map.cities.add(Marseille);
		City Zurich = new City("Zurich");
		game.map.cities.add(Zurich);
		City Essen = new City("Essen");
		game.map.cities.add(Essen);
		City Frankfurt = new City("Frankfurt");
		game.map.cities.add(Frankfurt);
		City Munchen = new City("Munchen");
		game.map.cities.add(Munchen);
		City Berlin = new City("Berlin");
		game.map.cities.add(Berlin);
		City Venezia = new City("Venezia");
		game.map.cities.add(Venezia);
		City Roma = new City("Roma");
		game.map.cities.add(Roma);
		City Wien = new City("Wien");
		game.map.cities.add(Wien);
		City Zagrab = new City("Zagrab");
		game.map.cities.add(Zagrab);
		City Brindisi = new City("Brindisi");
		game.map.cities.add(Brindisi);
		City Palermo = new City("Palermo");
		game.map.cities.add(Palermo);
		City Danzig = new City("Danzig");
		game.map.cities.add(Danzig);
		City Budapest = new City("Budapest");
		game.map.cities.add(Budapest);
		City Sarajevo = new City("Sarajevo");
		game.map.cities.add(Sarajevo);
		City Athina = new City("Athina");
		game.map.cities.add(Athina);
		City Kobenhavn = new City("Kobenhavn");
		game.map.cities.add(Kobenhavn);
		City Stockholm = new City("Stockholm");
		game.map.cities.add(Stockholm);
		City Riga = new City("Riga");
		game.map.cities.add(Riga);
		City Warszawa = new City("Warszawa");
		game.map.cities.add(Warszawa);
		City Wilno = new City("Wilno");
		game.map.cities.add(Wilno); 
		City Smolensk = new City("Smolensk");
		game.map.cities.add(Smolensk);
		City Moskva = new City("Moskva");
		game.map.cities.add(Moskva);
		City Kyiv = new City("Kyiv");
		game.map.cities.add(Kyiv);
		City Petrograd = new City("Petrograd");
		game.map.cities.add(Petrograd);
		City Kharkov = new City("Kharkov");
		game.map.cities.add(Kharkov);
		City Sofia = new City("Sofia");
		game.map.cities.add(Sofia);
		City Bucuresti = new City("Bucuresti");
		game.map.cities.add(Bucuresti);
		City Constantinople = new City("Constantinople");
		game.map.cities.add(Constantinople);
		City Angora = new City("Angora");
		game.map.cities.add(Angora);
		City Sevastopol = new City("Sevastopol");
		game.map.cities.add(Sevastopol);
		City Sochi = new City("Sochi");
		game.map.cities.add(Sochi);
		City Erzurum = new City("Erzurum");
		game.map.cities.add(Erzurum);
		City Rostov = new City("Rostov");
		game.map.cities.add(Rostov);
		
		try {
			FileOutputStream fos = new FileOutputStream("EuropeMap.ttr");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(game);
			out.close();
			FileInputStream fis = new FileInputStream("EuropeMap.ttr");;
			ObjectInputStream in = new ObjectInputStream(fis);
			Game loadedGame = (Game)in.readObject();
			System.out.println("Loaded game has the following cities:");
			int counter = 1;
			for (City c : loadedGame.map.cities) {
				System.out.println(counter + ": " + c.name);
				counter++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
