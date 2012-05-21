package card;

import Game.Die;
import Game.Disk;

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
    Map<Disk, Card> LayCards = null;
    Map<Disk, Card>  placeCards = null;
    Map<Disk, Card> reLayCards = null;
    Collection<Card> destroyCards = null;
    Collection<Card> addToHand = null;
    int victoryPointsToAdd = 0;
    int[] victoryPointsChangeArray = null;
    int moneyToPay = 0;
    Map<Die, Integer> diceModifications = null;
    Collection<Die>   diceUsed = null;
    Collection<Card> toRemoveFromDeck = null;
    Collection<Card> toAddToHandFromDiscard = null;
    PlayerTurnChangeActor playerTurnChangeActorToAdd = null;
    PlayerTurnChangeActor playerTurnChangeActorToRemove = null;
    DiscardActor discardActorToAdd = null;
    DiscardActor discardActorToRemove = null;
    Collection<Disk> toBlock = null;
    Collection<Disk> toUnblock = null;
    DefenseModificationActor defenseModificationActorToAdd = null;
    DefenseModificationActor defenseModificationActorToRemove = null;
    int[] moneyChangeArray = null;
    Collection<Card> addToCurrentPlayersHand;

    public Collection<Card> getAddToCurrentPlayersHand() {
        return addToCurrentPlayersHand;
    }

    public void setAddToCurrentPlayersHand(Collection<Card> addToCurrentPlayersHand) {
        this.addToCurrentPlayersHand = addToCurrentPlayersHand;
    }

    public int[] getMoneyChangeArray() {
        return moneyChangeArray;
    }

    public void setMoneyChangeArray(int[] moneyChangeArray) {
        this.moneyChangeArray = moneyChangeArray;
    }

    public DefenseModificationActor getDefenseModificationActorToAdd() {
        return defenseModificationActorToAdd;
    }

    public void setDefenseModificationActorToAdd(DefenseModificationActor defenseModificationActorToAdd) {
        this.defenseModificationActorToAdd = defenseModificationActorToAdd;
    }

    public DefenseModificationActor getDefenseModificationActorToRemove() {
        return defenseModificationActorToRemove;
    }

    public void setDefenseModificationActorToRemove(DefenseModificationActor defenseModificationActorToRemove) {
        this.defenseModificationActorToRemove = defenseModificationActorToRemove;
    }

    public Collection<Disk> getToUnblock() {
        return toUnblock;
    }

    public void setToUnblock(Collection<Disk> toUnblock) {
        this.toUnblock = toUnblock;
    }

    public PlayerTurnChangeActor getPlayerTurnChangeActorToAdd() {
        return playerTurnChangeActorToAdd;
    }

    public void setPlayerTurnChangeActorToAdd(PlayerTurnChangeActor playerTurnChangeActorToAdd) {
        this.playerTurnChangeActorToAdd = playerTurnChangeActorToAdd;
    }

    public PlayerTurnChangeActor getPlayerTurnChangeActorToRemove() {
        return playerTurnChangeActorToRemove;
    }

    public void setPlayerTurnChangeActorToRemove(PlayerTurnChangeActor playerTurnChangeActorToRemove) {
        this.playerTurnChangeActorToRemove = playerTurnChangeActorToRemove;
    }

    public DiscardActor getDiscardActorToAdd() {
        return discardActorToAdd;
    }

    public void setDiscardActorToAdd(DiscardActor discardActorToAdd) {
        this.discardActorToAdd = discardActorToAdd;
    }

    public DiscardActor getDiscardActorToRemove() {
        return discardActorToRemove;
    }

    public void setDiscardActorToRemove(DiscardActor discardActorToRemove) {
        this.discardActorToRemove = discardActorToRemove;
    }

    public Collection<Card> getToAddToHandFromDiscard() {
        return toAddToHandFromDiscard;
    }

    public void setToAddToHandFromDiscard(Collection<Card> toAddToHandFromDiscard) {
        this.toAddToHandFromDiscard = toAddToHandFromDiscard;
    }

    public Collection<Disk> getToBlock() {
        return toBlock;
    }

    public void setToBlock(Collection<Disk> toBlock) {
        this.toBlock = toBlock;
    }

    public Collection<Die> getDiceUsed() {
        return diceUsed;
    }

    public void setDiceUsed(Collection<Die> diceUsed) {
        this.diceUsed = diceUsed;
    }

    public Map<Disk, Card> getReLayCards() {
        return reLayCards;
    }

    public void setReLayCards(Map<Disk, Card> reLayCards) {
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

    public Map<Disk,Card> getPlaceCards() {
        return placeCards;
    }

    public void setPlaceCards(Map<Disk,Card> placeCards) {
        this.placeCards = placeCards;
    }

    public Map<Disk,Card> getLayCards() {
        return LayCards;
    }

    public void setLayCards(Map<Disk,Card> layCards) {
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
