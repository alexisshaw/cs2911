package Game.testadapter.Activators;

import Game.Die;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.AssetTranslator;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;
import card.Card;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenturioActivator implements
        framework.interfaces.activators.CenturioActivator,
        ActivatorWithCreate<CenturioActivator> {
    PlayerView myView;
    GameController controller;
    PlayerAction action;

    boolean addActionDieP = false;
    int actionDieValue = 0;
    int battleDieRoll = 0;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public CenturioActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        CenturioActivator centurioActivator = new CenturioActivator();
        centurioActivator.myView = myView;
        centurioActivator.controller = controller;
        centurioActivator.action = action;
        return centurioActivator;
    }

    /**
     * Choose whether to add an action dice to your current attack.
     *
     * @param attackAgain whether to attack again
     */
    @Override
    public void chooseCenturioAddActionDie(boolean attackAgain) {
        addActionDieP = attackAgain;
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
        this.actionDieValue = actionDiceValue;
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
        battleDieRoll = roll;
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
        controller.useFollowingActivatorPlayerDelegate(new CenturioActivatorDelegatePlayer());
        controller.setBattleDieRoll(battleDieRoll);
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class CenturioActivatorDelegatePlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            return (new LinkedList<Card>(cardsToChoseFromIn)).subList(0, 0);
        }

        @Override
        public boolean conditionalInteraction(String question, String trueChar, String falseChar) {
            return addActionDieP;
        }

        @Override
        public Die diceChooser(String message, String emptyMessage) {
            return AssetTranslator.findEquivelentDie(myView.getDice(), actionDieValue);
        }
    }
}
