package Game.testadapter.Activators;

import Game.Disk;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;
import card.Card;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 20/05/12
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class SicariusActivator implements
        framework.interfaces.activators.SicariusActivator {

    int diceDiskIndex;

    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static SicariusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        SicariusActivator newSicariusActivator = new SicariusActivator();
        newSicariusActivator.myView = myView;
        newSicariusActivator.controller = controller;
        newSicariusActivator.activationAction = action;
        return newSicariusActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new SicariusAcceptanceDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    /**
     * The user chooses a dice disc.
     * <p/>
     * <p>
     * Only valid if the pending activation requires a dice disc from
     * the user at the point this method is called.
     * </p>
     *
     * @param diceDisc dice disc to use, by dice value
     */
    @Override
    public void chooseDiceDisc(int diceDisc) {
        diceDiskIndex = diceDisc;
    }

    private class SicariusAcceptanceDelegatedPlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            card.Card toReturn = myView.getField((myView.getPlayerId() + 1) % myView.getNoPlayers()).get(new Disk(diceDiskIndex));
            return Arrays.asList(toReturn);
        }
    }
}
