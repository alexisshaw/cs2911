package Cards;

import Game.CardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ConsiliariusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Consiliarius";
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
        return 4;
    }

    //returns defence value of the card
    public int getDefence(){
        return 4;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        Card[] field = in.getMyPlayerView().getField(in.getMyPlayerView().getPlayerId());
        Vector<Card> playerCardsOnField = new Vector<Card>();
        for (Card c: field){
            if(c!= null && !c.isBuilding()){
                playerCardsOnField.add(c);
            }
        }
        CardAction returnValue = new CardAction();
        returnValue.setDestroyCards(playerCardsOnField.toArray(field));
        Card[] location = new Card[field.length];
        for (Card c: field){
            if(c != null){
                ArrayList<Card> cardToPlace = new ArrayList<Card>(1);
                cardToPlace.add(c);
                Card[] tempLocation = in.getPlayer().cardPlacer(cardToPlace,
                        "","Please choose where you wish to place "+c.toString()+"\n");
                for(int i=0; i<location.length ; i++){
                    if(tempLocation[i] != null){
                        location[i] = tempLocation[i];
                    }
                }
            }
        }
        returnValue.setPlaceCards(location);
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "The player picks up their character cards and can then lay them again on any dice disc. Buildings can be covered";
    }
}
