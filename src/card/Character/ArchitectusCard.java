package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Disk;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArchitectusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Architectus";
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
        return 3;
    }

    //returns defence value of the card
    public int getDefence(){
        return 4;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView input){
        //Get the Building cards the player has in their hand
        Set<Card> currentHand = new HashSet<Card>(input.getMyPlayerView().getHand());
        Set<Card> buildingCardsInHand = new HashSet<Card>();

        for(Card c: currentHand) if(c.isBuilding()) buildingCardsInHand.add(c);
        //The player chooses to place cards on the field and where
        Map<Disk, Card> Location = input.getPlayer().cardMultiPlacer(buildingCardsInHand, false);

        //Create and populate the CardAction
        CardAction myAction = new CardAction();
        myAction.setDestroyCards(Location.values());
        myAction.setLayCards(Location);
        return myAction;
    }

    //returns description of card
    public String getCardOracle(){
        return "enables the player to lay as many building cards as they wish free of charge. The player is allowed to cover any cards";
    }
}
