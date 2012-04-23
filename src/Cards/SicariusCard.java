package Cards;

import Game.CardView;
import Game.Game;
import Game.Die;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;


public class SicariusCard  implements Card {
    //returns the name of the card
    public String toString(){
        return "Sicarius";
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
        return 9;
    }

    //returns defence value of the card
    public int getDefence(){
        return 2;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        Card[] field = in.getMyPlayerView().getField(in.getMyPlayerView().getPlayerId());
        Vector<Card> vectorField = new Vector<Card>(Arrays.asList(field));
        int cardIndex = vectorField.indexOf(this);
        Vector<Card> cardsToChooseFrom = new Vector<Card>();
        for (int i=0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if(i != in.getMyPlayerView().getPlayerId()){
                if(cardIndex != 0 && in.getMyPlayerView().getField(i)[cardIndex - 1] != null){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex - 1]);
                    
                }
                if(in.getMyPlayerView().getField(i)[cardIndex] != null){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex]);
                }
                if(cardIndex == Die.getMaxDiceValue()-1 && in.getMyPlayerView().getField(i)[cardIndex + 1] != null){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex+1]);
                }
            }
        }
        Card[] chosenCard = in.getPlayer().cardChooser("Please choose one of the following opponents cards to destroy",
                "You cannot destroy a card",
                1,
                cardsToChooseFrom);
        CardAction returnValue = new CardAction();
        returnValue.setDestroyCards(new Card[]{this, chosenCard[0]});
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "Eliminates and opposing face up character card. The opposing card and Sicarius are both discarded";
    }
}
