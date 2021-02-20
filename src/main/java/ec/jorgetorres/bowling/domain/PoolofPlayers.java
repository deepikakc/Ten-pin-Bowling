package ec.jorgetorres.bowling.domain;

import ec.jorgetorres.bowling.entities.Player;
import ec.jorgetorres.bowling.exceptions.ExceededPinFallException;
import ec.jorgetorres.bowling.exceptions.NotAllowedPinFallException;
import ec.jorgetorres.bowling.utils.Players;

import java.util.LinkedList;
import java.util.Queue;

public class PoolofPlayers {
  final private Queue<Player> poolOfPlayers;
  public PoolofPlayers() {
    this.poolOfPlayers = new LinkedList<>();
  }

  public void addPinFall(String name, int numberOfPinFall) throws ExceededPinFallException, NotAllowedPinFallException {
    Player playerFound = Players.findPlayer(getPoolOfPlayers(), name);
    if (playerFound == null) {
      Player newPlayer = new Player(name, numberOfPinFall);
      newPlayer.calculateScore();
      getPoolOfPlayers().add(newPlayer);
    } else {
      playerFound.addResult(numberOfPinFall);
      playerFound.calculateScore();
    }
  }

  public void addFault(String name, String fault) throws ExceededPinFallException, NotAllowedPinFallException{
    Player playerFound = Players.findPlayer(getPoolOfPlayers(), name);
    if (playerFound == null) {
      Player newPlayer = new Player(name, fault);
      newPlayer.calculateScore();
      getPoolOfPlayers().add(newPlayer);
    } else {
      playerFound.addResult(fault);
      playerFound.calculateScore();
    }
  }

  public void printGame(int gameIndex) {
    System.out.printf("====== Game Number %d%n", gameIndex);
    System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
    while(!getPoolOfPlayers().isEmpty())
      printPlayer(getPoolOfPlayers().poll());
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

  public Queue<Player> getPoolOfPlayers() {
    return poolOfPlayers;
  }
}
