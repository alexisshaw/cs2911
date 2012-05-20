package Game.testadapter.Activators;

import Game.Player;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.GameController;
import Game.testadapter.delegatedPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 20/05/12
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TribunusPlebisActivator implements
        framework.interfaces.activators.TribunusPlebisActivator,
        ActivatorWithCreate<TribunusPlebisActivator>{

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
    @Override
    public TribunusPlebisActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        TribunusPlebisActivator newTribunusPlebisActivator = new TribunusPlebisActivator();
        newTribunusPlebisActivator.myView = myView;
        newTribunusPlebisActivator.controller = controller;
        newTribunusPlebisActivator.activationAction = action;
        return newTribunusPlebisActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new TribunusPlebisAcceptanceDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();//To change body of implemented methods use File | Settings | File Templates.
    }

    private class TribunusPlebisAcceptanceDelegatedPlayer extends delegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
