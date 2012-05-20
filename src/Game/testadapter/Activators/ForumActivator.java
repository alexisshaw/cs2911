package Game.testadapter.Activators;


import Game.Die;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ForumActivator implements
        framework.interfaces.activators.ForumActivator {
    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;

    boolean doesUseTemplum = false;
    int templumDieValue;
    int forumDieValue;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */

    public static ForumActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        ForumActivator newForumActivator = new ForumActivator();
        newForumActivator.myView = myView;
        newForumActivator.controller = controller;
        newForumActivator.activationAction = action;
        return newForumActivator;
    }

    /**
     * Choose whether to activate a Templum with your forum activation
     *
     * @param activate true if the user wishes to activate a Templum.
     */
    @Override
    public void chooseActivateTemplum(boolean activate) {
        doesUseTemplum = activate;
    }

    /**
     * Choose the dice to use with the Templum.
     * <p/>
     * This should only be called if chooseActivateTemplum has previously
     * been called with true as a parameter.
     *
     * @param diceValue the dice to use
     */
    @Override
    public void chooseActivateTemplum(int diceValue) {
        templumDieValue = diceValue;
    }

    /**
     * The user chooses an action dice.
     * <p/>
     * <p>
     * An action dice of the given value is used. A dice of that value
     * must exist in the user's current unused action dice. The same
     * dice cannot be used multiple times on the same turn. The same
     * value can be used if the user has multiple dice of that value.
     * </p>
     * <p/>
     * <p>
     * Action dice are always referred to by their value, rather than
     * their index, because action dice do not have either an implicit
     * or explicit ordering.
     * </p>
     *
     * @param actionDiceValue the value of the dice to use
     */
    @Override
    public void chooseActionDice(int actionDiceValue) {
        forumDieValue = actionDiceValue;
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
        controller.useFollowingActivatorPlayerDelegate(new ForumAcceptanceDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class ForumAcceptanceDelegatedPlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;
        }

        @Override
        public Die diceChooser(String message, String emptyMessage) {
            return Game.testadapter.AssetTranslator.findEquivelentDie(myView.getDice(), forumDieValue);
        }

        @Override
        public Die diceChooser(String message, String emptyMessage, List<Die> d) {
            return Game.testadapter.AssetTranslator.findEquivelentDie(myView.getDice(), templumDieValue);
        }

        @Override
        public boolean conditionalInteraction(String question, String trueChar, String falseChar) {
            return doesUseTemplum;
        }
    }
}
