package Cards;

import Game.CardView;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 8:19 PM
 * Cards.Card interface to control all of the card objects
 */
public interface Card {
    //returns the name of the card
    public String toString();

    //returns if the card is a building or not
    public boolean isBuilding();

    //returns the amount of dice required to activate card
    public int getNumberOfDiceRequired();

    //returns dollar value of placing card
    public int getPrice();

    //returns defence value of the card
    public int getDefence();

    //returns if the card can be activated in this turn
    public CardAction getCardAction(CardView input);

    //returns description of card
    public String getCardOracle();
}
