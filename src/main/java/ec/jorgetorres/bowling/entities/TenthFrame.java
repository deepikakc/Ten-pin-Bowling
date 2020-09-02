package ec.jorgetorres.bowling.entities;

public class TenthFrame {
  private Roll[] rolls = new Roll[3];
  private int score;
  private boolean isFirstCalculated = false;
  private boolean isSecondCalculated = false;
  private boolean isThirdCalculated = false;

  public TenthFrame(Roll roll) {
    this.rolls[0] = (roll);
    this.setScore(0);
  }

  public void setSecondRoll(Roll roll) {
    this.rolls[1] = roll;
  }

  public void setThirdRoll(Roll roll) {
    this.rolls[2] = roll;
  }

  public int getPartialScore() {
    if (this.rolls[1] != null) {
      return (this.rolls[0].getScore() + this.rolls[1].getScore());
    } else {
      return this.rolls[0].getScore();
    }
  }

  public int getSecondPartialScore() {
    if (this.rolls[2] != null) {
      return (this.rolls[1].getScore() + this.rolls[2].getScore());
    } else {
      return 0;
    }
  }

  public Roll getFirstRoll() {
    return this.rolls[0];
  }

  public Roll getSecondRoll() {
    return this.rolls[1];
  }

  public Roll getThirdRoll() {
    return this.rolls[2];
  }

  public boolean firstRollTaken() {
    return this.rolls[0] != null;
  }

  public boolean secondRollTaken() {
    return this.rolls[1] != null;
  }

  public boolean thirdRollTaken() {  return this.rolls[2] != null; }

  public boolean isFirstCalculated() {
    return isFirstCalculated;
  }

  public void setFirstCalculated(boolean firstCalculated) {
    isFirstCalculated = firstCalculated;
  }

  public boolean isSecondCalculated() {
    return isSecondCalculated;
  }

  public void setSecondCalculated(boolean secondCalculated) {
    isSecondCalculated = secondCalculated;
  }

  public boolean isThirdCalculated() {
    return isThirdCalculated;
  }

  public void setThirdCalculated(boolean thirdCalculated) {
    isThirdCalculated = thirdCalculated;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
