package Cards;

import Game.CardView;

public class ScaenicusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Scaenicus";
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
        return 8;
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
        return "He performs no action of his own but can copy the action of any of the player's face-up character cards, and the next time round that of another.";
    }
}