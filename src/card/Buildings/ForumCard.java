package card.Buildings;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Die;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/8/12
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForumCard implements Card {
    public String toString() {
        return "Forum";
    }

    public boolean isBuilding() {
        return true;
    }

    public int getNumberOfDiceRequired() {
        return 2;
    }

    public int getPrice() {
        return 5;
    }

    public int getDefence() {
        return 5;
    }

    public CardAction getCardPlacementAction(CardView input) {
        return null;
    }

    public CardAction getCardActivationAction(CardView input) {
        Die secondDie = input.getPlayer().diceChooser("Please Choose a second die to activate "+ this.toString() +":\n", "You cannot choose a die");
        Collection<Die> dieSet = new HashSet<Die>();
        dieSet.add(secondDie);
        CardAction returnValue = new CardAction();
        returnValue.setVictoryPointsToAdd(secondDie.getDieValue());
        for(Card c: input.getCardsNextTo(this)){
            if (c.getClass() == BasilicaCard.class){
                returnValue.setVictoryPointsToAdd(returnValue.getVictoryPointsToAdd()+2);
            }
            if (c.getClass() == TempulmCard.class){
                if(input.getPlayer().conditionalInteraction("Would you like to activate " + c.toString()+ "?" ,"y","n")){
                    List<Die> secondChoiceDie = new LinkedList<Die>(input.getMyPlayerView().getDice());
                    secondChoiceDie.remove(secondDie);
                    Die thirdDie = input.getPlayer().diceChooser("Please Choose a Third die to activate "+ this.toString() +":\n", "You cannot choose a die", secondChoiceDie);
                    dieSet.add(thirdDie);
                    returnValue.setVictoryPointsToAdd(returnValue.getVictoryPointsToAdd() +thirdDie.getDieValue());
                    break;
                }
            }
        }
        returnValue.setDiceUsed(dieSet);

        return returnValue;
    }

    public String getCardOracle() {
        return "requires 2 action dice: one to activate " +
                "the Forum and the other to determine " +
                "how many victory points the player receives.";
    }
}
