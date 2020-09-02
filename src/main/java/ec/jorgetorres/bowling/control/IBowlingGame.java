package ec.jorgetorres.bowling.control;

import ec.jorgetorres.bowling.domain.PoolofPlayers;

public interface IBowlingGame {

  void printGame();

  PoolofPlayers getPoolofPlayers();
}
