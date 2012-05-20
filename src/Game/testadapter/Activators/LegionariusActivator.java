package Game.testadapter.Activators;

import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/21/12
 * Time: 1:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class LegionariusActivator implements
        framework.interfaces.activators.LegionariusActivator {
    GameController controller;
    PlayerAction action;

    int attackDieRoll;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static LegionariusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        LegionariusActivator legionariusActivator = new LegionariusActivator();
        legionariusActivator.controller = controller;
        legionariusActivator.action = action;
        return legionariusActivator;
    }

    /**
     * Give the result of an attack die roll.
     * <p/>
     * <p>
     * Only valid if the pending activation requires an attack dice
     * roll.
     * </p>
     *
     * @param roll the outcome of the attack dice roll
     */
    @Override
    public void giveAttackDieRoll(int roll) {
        attackDieRoll = roll;
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
        controller.useFollowingActivatorPlayerDelegate(new LegionatiusActivatorDelegatedCard());
        controller.setBattleDieRoll(attackDieRoll);
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();

    }

    private class LegionatiusActivatorDelegatedCard extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }
    }
}
