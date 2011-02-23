package model;

import java.util.ArrayList;

public interface GameFactory {

	public Game create(ArrayList<Player> players);
}
