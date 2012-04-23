package Cards;

import Game.CardView;

public class MercatorCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Mercator";
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
        return 7;
    }

    //returns defence value of the card
    public int getDefence(){
        return 2;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        return new CardAction();
    }

    //returns description of card
    public String getCardOracle(){
        return "For 2 sestertii each, the player can buy 1 victory point as long as there are money and victory points left. The opponent gets the money";
    }
}
