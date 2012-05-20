package Game.testadapter.Activators;

import Game.Die;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.AssetTranslator;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsulActivator implements
        framework.interfaces.activators.ConsulActivator {
    PlayerView myView;
    GameController controller;
    PlayerAction action;

    int changeAmt;
    int originalRoll;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static ConsulActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        ConsulActivator consulActivator = new ConsulActivator();
        consulActivator.myView = myView;
        consulActivator.controller = controller;
        consulActivator.action = action;
        return consulActivator;
    }

    /**
     * Choose the amount a dice disc value changes by.
     * <p/>
     * <p>
     * Valid changes are -1 or 1 in the current game.
     * </p>
     *
     * @param amount the amount to change by.
     */
    @Override
    public void chooseConsulChangeAmount(int amount) {
        this.changeAmt = amount;
    }

    @Override
    public void chooseWhichDiceChanges(int originalRoll) {
        this.originalRoll = originalRoll;
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
        controller.useFollowingActivatorPlayerDelegate(new ConsulActivatorDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    public class ConsulActivatorDelegatedPlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }

        @Override
        public boolean conditionalInteraction(String question, String trueChar, String falseChar) {
            return changeAmt == 1;
        }

        @Override
        public Die diceChooser(String message, String emptyMessage) {
            return AssetTranslator.findEquivelentDie(myView.getDice(), originalRoll);
        }
    }
}
