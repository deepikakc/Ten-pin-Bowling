package ec.jorgetorres.bowling.domain;

import ec.jorgetorres.bowling.entities.Player;

import java.util.LinkedList;
import java.util.Queue;

public class PoolofPlayers {
  private Queue<Player> poolOfPlayers;
  public PoolofPlayers() {
    this.poolOfPlayers = new LinkedList<>();
  }

  public void addResult(String name, int numberOfPinFall) {
    Player playerFound = poolOfPlayers.stream()
        .filter(player -> player.getName().equals(name))
        .findAny()
        .orElse(null);
    if (playerFound == null) {
      Player newPlayer = new Player(name, numberOfPinFall);
      newPlayer.calculateScore();
      poolOfPlayers.add(new Player(name, numberOfPinFall));
    } else {
      playerFound.addResult(numberOfPinFall);
      playerFound.calculateScore();
    }
  }

  public void addFault(String name, String fault) {
    Player playerFound = poolOfPlayers.stream()
        .filter(player -> player.getName().equals(name))
        .findAny()
        .orElse(null);
    if (playerFound == null) {
      poolOfPlayers.add(new Player(name, fault));
    } else {
      playerFound.addResult(fault);
    }
  }

  public void printGame() {
    System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
    while(!poolOfPlayers.isEmpty())
      printPlayer(poolOfPlayers.poll());
  }

  private void printPlayer(Player player) {
    System.out.println(player.getName());
    System.out.print("Pinfalls");
    player.printPinFalls();
    System.out.println();
    System.out.print("Score");
    player.printScore();
    System.out.println();
  }
}
