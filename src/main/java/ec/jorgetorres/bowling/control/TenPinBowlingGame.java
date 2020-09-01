package ec.jorgetorres.bowling.control;

import ec.jorgetorres.bowling.App;
import ec.jorgetorres.bowling.domain.PoolofPlayers;

import java.io.File;
import java.util.Scanner;

public class TenPinBowlingGame {
  private PoolofPlayers poolofPlayers = new PoolofPlayers();

  public TenPinBowlingGame(String path) {
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);
      while(sc.hasNextLine()) {
        if (!parseLine(sc.nextLine()))
          return;
      }
      poolofPlayers.printGame();
    } catch (Exception e) {
      System.out.println(e + new App().getGreeting());
    }
  }

  private boolean parseLine(String line) {
    String[] splitedLines = line.split(" ");
    if (splitedLines.length == 2) {
      String name = splitedLines[0];
      if (splitedLines[1].equals("F")) {
        poolofPlayers.addFault(name, splitedLines[1]);
      } else {
        try {
          int numberOfPinFall = Integer.parseInt(splitedLines[1]);
          poolofPlayers.addResult(name, numberOfPinFall);
        } catch (NumberFormatException e) {
          System.out.println("Incorrect Line Formating");
          return false;
        }
      }
      return true;
    } else {
      System.out.println("Incorrect Line Formating");
      return false;
    }
  }

}
