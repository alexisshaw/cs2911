package Cards;

import Game.Die;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/26/12
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardAction {
    Card[] LayCards = null;
    Card[] destroyCards = null;
    Card[] placeCards = null;
    Card[] addToHand = null;
    int victoryPointsToAdd = 0;
    int[] victoryPointsChangeArray = null;
    int moneyToPay;
    Map<Die, Integer> diceModifications = null;

    public Map<Die, Integer> getDiceModifications() {
        return diceModifications;
    }

    public void setDiceModifications(Map<Die, Integer> diceModifications) {
        this.diceModifications = diceModifications;
    }

    public int[] getVictoryPointsChangeArray() {
        return victoryPointsChangeArray;
    }

    public void setVictoryPointsChangeArray(int[] victoryPointsChangeArray) {
        this.victoryPointsChangeArray = victoryPointsChangeArray;
    }

    public int getMoneyToPay() {
        return moneyToPay;
    }

    public void setMoneyToPay(int moneyToPay) {
        this.moneyToPay = moneyToPay;
    }

    public Card[] getAddToHand() {
        return addToHand;
    }

    public void setAddToHand(Card[] addToHand) {
        this.addToHand = addToHand;
    }

    public Card[] getPlaceCards() {
        return placeCards;
    }

    public void setPlaceCards(Card[] placeCards) {
        this.placeCards = placeCards;
    }

    public Card[] getLayCards() {
        return LayCards;
    }

    public void setLayCards(Card[] layCards) {
        LayCards = layCards;
    }

    public Card[] getDestroyCards() {
        return destroyCards;
    }

    public int getVictoryPointsToAdd() {
        return victoryPointsToAdd;
    }

    public void setVictoryPointsToAdd(int victoryPointsToAdd) {
        this.victoryPointsToAdd = victoryPointsToAdd;
    }

    public void setDestroyCards(Card[] destroyCards) {
        this.destroyCards = destroyCards;
    }
}
