package Cards;

import Game.CardView;

public class HaruspexCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Haruspex";
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
        return 3;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        CardAction returnValue = new CardAction();

        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "The player can choose any card from the pile of face-down cards and add it to their hand.  Afterwards the pile is shuffled.";
    }
}