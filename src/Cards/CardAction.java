package Cards;

import Game.Die;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/26/12
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CardAction {
    Map<Integer, Card> LayCards = null;
    Map<Integer, Card>  placeCards = null;
    Map<Integer, Card> reLayCards = null;
    Collection<Card> destroyCards = null;
    Collection<Card> addToHand = null;
    int victoryPointsToAdd = 0;
    int[] victoryPointsChangeArray = null;
    int moneyToPay = 0;
    Map<Die, Integer> diceModifications = null;
    Collection<Die>   diceUsed = null;
    Collection<Card> toRemoveFromDeck = null;

    public Collection<Die> getDiceUsed() {
        return diceUsed;
    }

    public void setDiceUsed(Collection<Die> diceUsed) {
        this.diceUsed = diceUsed;
    }

    public Map<Integer, Card> getReLayCards() {
        return reLayCards;
    }

    public void setReLayCards(Map<Integer, Card> reLayCards) {
        this.reLayCards = reLayCards;
    }

    public Collection<Card> getToRemoveFromDeck() {
        return toRemoveFromDeck;
    }

    public void setToRemoveFromDeck(Collection<Card> toRemoveFromDeck) {
        this.toRemoveFromDeck = toRemoveFromDeck;
    }

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

    public Collection<Card> getAddToHand() {
        return addToHand;
    }

    public void setAddToHand(Collection<Card> addToHand) {
        this.addToHand = addToHand;
    }

    public Map<Integer,Card> getPlaceCards() {
        return placeCards;
    }

    public void setPlaceCards(Map<Integer,Card> placeCards) {
        this.placeCards = placeCards;
    }

    public Map<Integer,Card> getLayCards() {
        return LayCards;
    }

    public void setLayCards(Map<Integer,Card> layCards) {
        LayCards = layCards;
    }

    public Collection<Card> getDestroyCards() {
        return destroyCards;
    }

    public void setDestroyCards(Collection<Card> destroyCards) {
        this.destroyCards = destroyCards;
    }

    public int getVictoryPointsToAdd() {
        return victoryPointsToAdd;
    }

    public void setVictoryPointsToAdd(int victoryPointsToAdd) {
        this.victoryPointsToAdd = victoryPointsToAdd;
    }
}
