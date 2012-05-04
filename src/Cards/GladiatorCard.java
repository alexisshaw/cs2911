package Cards;

import Game.CardView;
import Game.Die;

import java.util.Arrays;
import java.util.Vector;

public class GladiatorCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Gladiator";
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
        return 6;
    }

    //returns defence value of the card
    public int getDefence(){
        return 5;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        Card[] field = in.getMyPlayerView().getField(in.getMyPlayerView().getPlayerId());
        Vector<Card> vectorField = new Vector<Card>(Arrays.asList(field));
        int cardIndex = vectorField.indexOf(this);
        Vector<Card> cardsToChooseFrom = new Vector<Card>();
        for (int i=0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if(i != in.getMyPlayerView().getPlayerId()){
                if(cardIndex != 0 &&
                        in.getMyPlayerView().getField(i)[cardIndex - 1] != null &&
                        !in.getMyPlayerView().getField(i)[cardIndex - 1].isBuilding()){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex - 1]);
                }
                if(in.getMyPlayerView().getField(i)[cardIndex] != null &&
                        !in.getMyPlayerView().getField(i)[cardIndex - 1].isBuilding()){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex]);
                }
                if(cardIndex == Die.getMaxDiceValue()-1 &&
                        in.getMyPlayerView().getField(i)[cardIndex + 1] != null &&
                        !in.getMyPlayerView().getField(i)[cardIndex - 1].isBuilding()){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex+1]);
                }
            }
        }
        Card[] chosenCard = in.getPlayer().cardChooser(
                "Please choose one of the following opponents cards to return to their hand",
                "You cannot return a characters card to their hand",
                1,
                cardsToChooseFrom);
        CardAction returnValue = new CardAction();
        returnValue.setAddToHand(chosenCard);
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "An opponent's face up character card (Chosen by the player whose turn it is) must be returned to the opponent's hand";
    }
}
