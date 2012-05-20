package Game.testadapter.Activators;

import Game.Player;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.GameController;
import Game.testadapter.delegatedPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 21/05/12
 * Time: 1:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class MercatorActivator implements
        framework.interfaces.activators.MercatorActivator,
        ActivatorWithCreate<MercatorActivator> {

    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;

    int victoryPoints;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public MercatorActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        MercatorActivator newMercatorActivator = new MercatorActivator();
        newMercatorActivator.myView = myView;
        newMercatorActivator.controller = controller;
        newMercatorActivator.activationAction = action;
        return newMercatorActivator;
    }

    /**
     * Choose the number of victory points to buy with the Mercator.
     *
     * @param VPToBuy the number of points to buy
     */
    @Override
    public void chooseMercatorBuyNum(int VPToBuy) {
        victoryPoints = VPToBuy;
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
        controller.useFollowingActivatorPlayerDelegate(new MercatorAcceptanceDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class MercatorAcceptanceDelegatedPlayer extends delegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int integerInteraction(String question, int maxVal, int minVal) {
            return victoryPoints;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
