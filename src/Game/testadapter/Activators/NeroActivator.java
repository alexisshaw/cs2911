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
 * Date: 21/05/12
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class NeroActivator implements
        framework.interfaces.activators.NeroActivator {

    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;

    int diceDiskIndex;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static NeroActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        NeroActivator newNeroActivator = new NeroActivator();
        newNeroActivator.myView = myView;
        newNeroActivator.controller = controller;
        newNeroActivator.activationAction = action;
        return newNeroActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new NeroAcceptanceDelegatedPlayer());
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

    private class NeroAcceptanceDelegatedPlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            card.Card toReturn = myView.getField((myView.getPlayerId() + 1) % myView.getNoPlayers()).get(new Disk(diceDiskIndex));
            return Arrays.asList(toReturn);
        }
    }
}
