package ec.jorgetorres.bowling.entities;

import ec.jorgetorres.bowling.exceptions.ExceededPinFallException;

public class NormalFrame {
  private Roll[] rolls = new Roll[2];
  private int score;
  private boolean calculated = false;

  public NormalFrame(Roll roll) {
    this.rolls[0] = roll;
    this.setScore(0);
  }

  public void setLatestRoll(Roll roll) throws ExceededPinFallException {
    if ((rolls[0].getScore() + roll.getScore()) > 10) {
      throw new ExceededPinFallException();
    } else {
      this.rolls[1] = roll;
    }
  }

  public boolean firstRollTaken() {
    return this.rolls[0] != null;
  }

  public boolean secondRollTaken() {
    return this.rolls[1] != null;
  }

  public boolean isSpare() {
    if (!isStrike()) {
      if (this.rolls[1] != null) {
        int scoreOne = this.rolls[0].getScore();
        int scoreTwo = this.rolls[1].getScore();
        return (scoreOne + scoreTwo) == 10;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public boolean isStrike() {
    return this.rolls[0] != null
        && this.rolls[0].getScore() == 10;
  }

  public Roll getFirstRoll() {
    return rolls[0];
  }

  public Roll getSecondRoll() {
    return rolls[1];
  }

  public int getScore() {
    return this.score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public boolean isCalculated() {
    return calculated;
  }

  public void setCalculated(boolean calculated) {
    this.calculated = calculated;
  }
}
