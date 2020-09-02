package ec.jorgetorres.bowling.utils;

import ec.jorgetorres.bowling.entities.Player;

import java.util.Queue;

public class Players {
  public static Player findPlayer(Queue<Player> poolOfPlayers, String name) {
    return poolOfPlayers.stream()
        .filter(player -> player.getName().equals(name))
        .findAny()
        .orElse(null);
  }
}
