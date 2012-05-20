package Game.testadapter.Activators;

import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.GameController;
import framework.interfaces.activators.CardActivator;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 21/05/12
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScaenicusActivator implements
        framework.interfaces.activators.ScaenicusActivator,
        ActivatorWithCreate<ScaenicusActivator> {

    PlayerView myView;
    GameController controller;
    PlayerAction activationAction;

    int diceDiskToMimic;
    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public ScaenicusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        ScaenicusActivator newScaenicusActivator = new ScaenicusActivator();
        newScaenicusActivator.myView = myView;
        newScaenicusActivator.controller = controller;
        newScaenicusActivator.activationAction = action;
        return newScaenicusActivator;
    }

    /**
     * Select a card to mimic with the Scaenicus.
     * <p/>
     * <p>
     * This method selects a card for an activated Scaenicus to mimic.
     * A new CardActivator corresponding to the chosen card is returned,
     * so the test may use it to activate the selected card.
     * </p>
     *
     * @param diceDisc
     * @return
     */
    @Override
    public CardActivator getScaenicusMimicTarget(int diceDisc) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
