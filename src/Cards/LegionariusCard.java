package Cards;

import Game.CardView;




public class LegionariusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Legionarius";
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
        return 5;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView input){
        return new CardAction();
    }

    //returns description of card
    public String getCardOracle(){
        return "attacks the opponent's card which is directly opposite, whether it is a character or a building card.";
    }
}
