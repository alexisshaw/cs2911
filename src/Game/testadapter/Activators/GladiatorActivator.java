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
 * User: Alexis Shaw
 * Date: 5/21/12
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class GladiatorActivator implements
        framework.interfaces.activators.GladiatorActivator {
    PlayerView myView;
    GameController controller;
    PlayerAction action;

    Disk toAttack;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static GladiatorActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        GladiatorActivator gladiatorActivator = new GladiatorActivator();
        gladiatorActivator.myView = myView;
        gladiatorActivator.controller = controller;
        gladiatorActivator.action = action;
        return gladiatorActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new GladiatorActivatorDelegatePlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class GladiatorActivatorDelegatePlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            return Arrays.asList(myView.getField((myView.getPlayerId() + 1) % myView.getNoPlayers()).get(toAttack));    //To change body of overridden methods use File | Settings | File Templates.
        }
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
        toAttack = new Disk(diceDisc);
    }
}
