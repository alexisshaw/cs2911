package Game.testadapter.Activators;

import Game.Disk;
import Game.Player;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.GameController;
import Game.testadapter.delegatedPlayer;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 21/05/12
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class PraetorianusActivator implements
        framework.interfaces.activators.PraetorianusActivator,
        ActivatorWithCreate<PraetorianusActivator> {

    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;

    Collection<Disk> diceDiskHolder;
    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public PraetorianusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        PraetorianusActivator newPraetorianusActivator = new PraetorianusActivator();
        newPraetorianusActivator.myView = myView;
        newPraetorianusActivator.controller = controller;
        newPraetorianusActivator.activationAction = action;
        return newPraetorianusActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new SenatorAcceptanceDelegatedPlayer());
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
        diceDiskHolder = Arrays.asList(new Disk[diceDisc]);

    }

    private class SenatorAcceptanceDelegatedPlayer extends delegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return activationAction;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Collection<Disk> diskChooser(String message, String emptyMessage, int numCards, Collection<Disk> cardsToChoseFromIn) {
            return diceDiskHolder;
        }
    }
}
