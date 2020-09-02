package ec.jorgetorres.bowling.domain;

import ec.jorgetorres.bowling.entities.Player;
import ec.jorgetorres.bowling.exceptions.ExceededPinFallException;
import ec.jorgetorres.bowling.exceptions.NotAllowedPinFallException;

import java.util.LinkedList;
import java.util.Queue;

public class PoolofPlayers {
  private Queue<Player> poolOfPlayers;
  public PoolofPlayers() {
    this.poolOfPlayers = new LinkedList<>();
  }

  public void addResult(String name, int numberOfPinFall) throws ExceededPinFallException, NotAllowedPinFallException {
    Player playerFound = poolOfPlayers.stream()
        .filter(player -> player.getName().equals(name))
        .findAny()
        .orElse(null);
    if (playerFound == null) {
      Player newPlayer = new Player(name, numberOfPinFall);
      newPlayer.calculateScore();
      poolOfPlayers.add(newPlayer);
    } else {
      playerFound.addResult(numberOfPinFall);
      playerFound.calculateScore();
    }
  }

  public void addFault(String name, String fault) throws ExceededPinFallException, NotAllowedPinFallException{
    Player playerFound = poolOfPlayers.stream()
        .filter(player -> player.getName().equals(name))
        .findAny()
        .orElse(null);
    if (playerFound == null) {
      Player newPlayer = new Player(name, fault);
      newPlayer.calculateScore();
      poolOfPlayers.add(newPlayer);
    } else {
      playerFound.addResult(fault);
      playerFound.calculateScore();
    }
  }

  public void printGame(int gameIndex) {
    System.out.println(String.format("====== Game Number %d", gameIndex));
    System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
    while(!poolOfPlayers.isEmpty())
      printPlayer(poolOfPlayers.poll());
    System.out.println();
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
