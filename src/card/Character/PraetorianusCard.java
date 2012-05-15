package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Disk;

import java.util.Collection;

public class PraetorianusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Praetorianus";
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
        CardAction returnValue = new CardAction();
        Collection<Disk> toBlock = in.getPlayer().diskChooser("Please Choose a disk to Block", "", 1, Disk.diskSet());
        returnValue.setToBlock(toBlock);
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "Any of the opponent's dice disk can be blocked for one go.";
    }
}