package card;

import Game.CardView;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class TelephoneBoxCard implements Card {
    @Override
    public String toString() {
        return "Telephone Box";
    }

    @Override
    public boolean isBuilding() {
        return true;
    }

    @Override
    public int getNumberOfDiceRequired() {
        return 2;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getPrice() {
        return 5;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getDefence() {
        return 2;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CardAction getCardPlacementAction(CardView input) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CardAction getCardActivationAction(CardView input) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCardOracle() {
        return "When activated by an action die the telephone Box card sends one of"+
                " the owner's cards already on the board forwards or backwards in time."+
                " The sent card is called the time-traveling card.\n" +
                "\n" +
                "Using the Telephone Box card requires two of the three action dice,"+
                " one to activate the Telephone Box card on its dice disc in the usual way,"+
                " and one to determine n how many turns forwards or backwards in time the"+
                " time-traveling card is to be sent."+
                " The player selects which is to be the time-traveling card to be send through time,"+
                " and in which direction it is to be sent: forwards or backwards.";
    }
}
