package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;

import java.util.Collection;
import java.util.HashSet;

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
        Collection<Card> cardsOnField = in.getMyPlayerView().getField(in.getMyPlayerView().getPlayerId()).values();
        Collection<Card> characterCardsOnField = new HashSet<Card>();
        for(Card c:cardsOnField) if(!c.isBuilding()) characterCardsOnField.add(c);
        CardAction returnValue = new CardAction();
        returnValue.setReLayCards(in.getPlayer().cardMultiPlacer(characterCardsOnField,true));
        return null;
    }

    //returns description of card
    public String getCardOracle(){
        return "The player picks up their character cards and can then lay them again on any dice disc. Buildings can be covered";
    }
}
