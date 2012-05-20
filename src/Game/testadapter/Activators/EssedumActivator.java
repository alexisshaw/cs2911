package Game.testadapter.Activators;

import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.GameController;
import Game.testadapter.delegatedPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/21/12
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class EssedumActivator implements
        framework.interfaces.activators.EssedumActivator,
        ActivatorWithCreate<EssedumActivator>{
    GameController controller;
    PlayerAction action;
    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public EssedumActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        EssedumActivator essedumActivator = new EssedumActivator();
        essedumActivator.controller = controller;
        essedumActivator.action = action;
        return essedumActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new EssedumActivatorDelegatePlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }
    private class EssedumActivatorDelegatePlayer extends delegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }
    }
}
