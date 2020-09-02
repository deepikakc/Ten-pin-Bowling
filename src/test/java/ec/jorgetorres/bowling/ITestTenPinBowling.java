package ec.jorgetorres.bowling;

import ec.jorgetorres.bowling.control.TenPinBowlingGame;
import ec.jorgetorres.bowling.entities.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ITestTenPinBowling {
  @Test
  public void testSampleInput() {
    TenPinBowlingGame bowlingGame = new TenPinBowlingGame("Ten-pin-Sample", 1);
    Player Jeff = bowlingGame.getPoolofPlayers().getPoolOfPlayers().poll();
    Player John = bowlingGame.getPoolofPlayers().getPoolOfPlayers().poll();
    assertEquals(167, Jeff.getScore());
    assertEquals(151, John.getScore());
  }


  @Test
  public void testPerfectScore() {
    TenPinBowlingGame bowlingGame = new TenPinBowlingGame("Ten-pin-PerfectScore", 1);
    Player Carl = bowlingGame.getPoolofPlayers().getPoolOfPlayers().poll();
    assertEquals(300, Carl.getScore());
  }

  @Test
  public void testZeroScore() {
    TenPinBowlingGame bowlingGame = new TenPinBowlingGame("Ten-pin-ZeroScore", 1);
    Player Jorge = bowlingGame.getPoolofPlayers().getPoolOfPlayers().poll();
    assertEquals(0, Jorge.getScore());
  }

}
