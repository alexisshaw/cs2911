package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Disk;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class MercatusCard implements Card {
    public String toString() {
        return "Mercatus";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 6;
    }

    public int getDefence() {
        return 3;
    }
    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    public CardAction getCardActivationAction(CardView in) {
        int[] victoryChange = new int[in.getMyPlayerView().getNoPlayers()];
        for (int i=0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if(i != in.getMyPlayerView().getPlayerId()){
                Map<Disk,Card> playerField = in.getMyPlayerView().getField(i);
                int newVictoryPoints = 0;
                for (Card c : playerField.values()){
                    if(c.getClass() == ForumCard.class){
                        newVictoryPoints ++;
                    }
                }
                victoryChange[in.getMyPlayerView().getPlayerId()] += newVictoryPoints;
                victoryChange[i] = -newVictoryPoints;
            }
        }
        CardAction returnValue = new CardAction();
        returnValue.setVictoryPointsChangeArray(victoryChange);
        return returnValue;
    }

    public String getCardOracle() {
        return "The player gets 1 victory point for every face-up Forum " +
                "that the opponent has.";
    }
}
