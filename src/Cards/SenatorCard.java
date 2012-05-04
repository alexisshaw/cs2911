package Cards;

import Game.CardView;
import Game.Die;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/4/12
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class SenatorCard implements Card {
    public boolean isBuilding() {
        return false;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 5;
    }

    public int getDefence() {
        return 1;
    }

    public CardAction getCardAction(CardView input) {
        Card[] location = new Card[Die.getMaxDiceValue()];
        Vector<Card> currentHand = new Vector<Card>(input.getMyPlayerView().getHand());
        while(!currentHand.isEmpty() && input.getPlayer().conditionalInteraction("Do You Wish to lay a Card (Y/N)", "Y", "N")){
            Card[] chosenCard = input.getPlayer().cardChooser("Please Choose a card to place on the field",
                    "You cannot place a card",
                    1,
                    currentHand);
            if(!chosenCard[0].isBuilding()){
                currentHand.removeAll(Arrays.asList(chosenCard));
                Card[] tempLocation = input.getPlayer().cardPlacer(Arrays.asList(chosenCard),
                        "","Please choose where you wish to place this card\n");
                for(int i=0; i<location.length ; i++){
                    if(tempLocation[i] != null){
                        location[i] = tempLocation[i];
                    }
                }
            }
        }
        CardAction myAction = new CardAction();
        myAction.setLayCards(location);
        return myAction;
    }

    public String getCardOracle() {
        return "Enables the player to lay as many character cards as they wish free of charge. The player is allowed to cover any cards";
    }
}
