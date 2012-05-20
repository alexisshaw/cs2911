package Game.testadapter.Activators;

import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 21/05/12
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class MercatusActivator implements
        framework.interfaces.activators.MercatusActivator {

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
    public static MercatusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        MercatusActivator newMercatusActivator = new MercatusActivator();
        newMercatusActivator.myView = myView;
        newMercatusActivator.controller = controller;
        newMercatusActivator.activationAction = action;
        return newMercatusActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new MercatusAcceptanceDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class MercatusAcceptanceDelegatedPlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;
        }
    }
}
