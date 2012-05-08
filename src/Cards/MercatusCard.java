package Cards;

import Game.CardView;

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

    public CardAction getCardAction(CardView in) {
        int newVictoryPoints = 0;
        for (int i=0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if(i != in.getMyPlayerView().getPlayerId()){
                Map<Integer,Card> playerField = in.getMyPlayerView().getField(i);
                for (Card c : playerField.values()){
                    if(c.getClass() == ForumCard.class){
                        newVictoryPoints ++;
                    }
                }
            }
        }
        CardAction returnValue = new CardAction();
        returnValue.setVictoryPointsToAdd(newVictoryPoints);
        return new CardAction();
    }

    public String getCardOracle() {
        return "The player gets 1 victory point for every face-up Forum " +
                "that the opponent has.";
    }
}
