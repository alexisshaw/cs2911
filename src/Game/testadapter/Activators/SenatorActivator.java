package Game.testadapter.Activators;

import Game.Disk;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.AssetTranslator;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;
import framework.cards.Card;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 20/05/12
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class SenatorActivator implements
        framework.interfaces.activators.SenatorActivator {
    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;


    Stack<Card> toLay = new Stack<Card>();
    Stack<Disk> diskToUse = new Stack<Disk>();

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static SenatorActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        SenatorActivator newSenatorActivator = new SenatorActivator();
        newSenatorActivator.myView = myView;
        newSenatorActivator.controller = controller;
        newSenatorActivator.activationAction = action;
        return newSenatorActivator;
    }

    @Override
    public void layCard(Card myCard, int whichDiceDisc) {
        toLay.push(myCard);
        diskToUse.push(new Disk(whichDiceDisc));
    }

    /**
     * Mark the pending activation as complete.
     * <p/>
     * <p>
     * This method must be called when an activation is complete.
     * This method cannot be called until all required activation
     * methods have been called. No other methods in the move maker can
     * be called after a CardActivator has been received until after its
     * complete method is called. This is really important.
     * </p>
     */
    @Override
    public void complete() {
        controller.useFollowingActivatorPlayerDelegate(new SenatorAcceptanceDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class SenatorAcceptanceDelegatedPlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;
        }

        @Override
        public Map<Disk, card.Card> cardMultiPlacer(Collection<card.Card> toChooseFrom, boolean mustPlaceAll) {
            Map<Disk, card.Card> returnValue = new HashMap<Disk, card.Card>();
            while (!toLay.isEmpty()) {
                returnValue.put(diskToUse.pop(), AssetTranslator.findEquivelentCard(toChooseFrom, toLay.pop()));
            }
            return returnValue;
        }
    }
}



