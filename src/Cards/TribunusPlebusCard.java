package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 9:11 PM
 * Tribunus Plebus Cards.Card implementation
 */
public class TribunusPlebusCard implements Card {

    //returns the name of the card
    public String toString(){
        return "Tribunus Plebis";
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
        return 5;
    }

    //returns defence value of the card
    public int getDefence(){
        return 5;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        CardAction toReturn = new CardAction();
        toReturn.setVictoryPointsToAdd(1);
        return toReturn;
    }

    //returns description of card
    public String getCardOracle(){
        return "The player gets 1 victory point from their opponent.";
    }
}
