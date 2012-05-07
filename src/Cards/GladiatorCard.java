package Cards;

import Game.CardView;

import java.util.Collection;
import java.util.HashSet;

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
        Collection<Card> opposingCards = in.getOpposingCards(this);
        Collection<Card> cardsToChooseFrom = new HashSet<Card>();
        for(Card c:opposingCards) if(c.isBuilding()) cardsToChooseFrom.add(c);
        Collection<Card> chosenCard = in.getPlayer().cardChooser(
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
