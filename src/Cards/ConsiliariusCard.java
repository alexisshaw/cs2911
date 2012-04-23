package Cards;

import Game.CardView;

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

        return new CardAction();
    }

    //returns description of card
    public String getCardOracle(){
        return "The player picks up their character cards and can then lay them again on any dice disc. Buildings can be covered";
    }
}
