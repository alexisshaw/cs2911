package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Disk;

import java.util.Map;

public class LegatCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Legat";
    }

    //returns if the card is a building or not
    public boolean isBuilding(){
        return false;
    }

    //returns the amount of dice required to activate card
    public int getNumberOfDiceRequired(){
        return 1;
    }

    //returns dollar value of placing card
    public int getPrice(){
        return 5;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    //returns defence value of the card
    public int getDefence(){
        return 2;
    }

    //Returns this cards Card Action
    public CardAction getCardActivationAction(CardView in){
        int newVictoryPoints = 0;
        for (int i=0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if(i != in.getMyPlayerView().getPlayerId()){
                Map<Disk,Card> playerField = in.getMyPlayerView().getField(i);
                for (Disk d : Disk.diskList()){
                    if (!playerField.containsKey(d)){
                        newVictoryPoints++;
                    }
                }
            }
        }
        CardAction returnValue = new CardAction();
        returnValue.setVictoryPointsToAdd(newVictoryPoints);
        return new CardAction();
    }

    //returns description of card
    public String getCardOracle(){
        return "The player gets 1 victory point from the stockpile for every disc not occupied by the opponent";
    }
}
