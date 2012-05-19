package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.*;

public class HaruspexCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Haruspex";
    }

    //returns if the card is a building or not
    public boolean isBuilding(){
        return false;
    }

    @Override
    public CardAction getCardPlacementAction(CardView input) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        return 3;
    }

    //Returns this cards Card Action
    public CardAction getCardActivationAction(CardView in){
        CardAction returnValue = new CardAction();
        Stack<Card> deck = in.getDeck();
        List<Card> deckClone = new ArrayList<Card>(deck);
        Collections.shuffle(deckClone);
        Collection<Card> chosen = in.getPlayer().cardChooser("Please Choose one of the following to add to your hand","",1,deckClone);
        returnValue.setAddToHand(chosen);
        returnValue.setToRemoveFromDeck(chosen);
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "The player can choose any card from the pile of face-down cards and add it to their hand.  Afterwards the pile is shuffled.";
    }
}
