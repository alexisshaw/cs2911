package Game.testadapter.Activators;

import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.GameController;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivatorWithCreate<T extends framework.interfaces.activators.CardActivator> {
    /**
     * Common Card Activator Creator, for use in the factory
     * @param myView       thePlayerView to use
     * @param controller   the GameController the activator to use
     * @param action       the action for the game to use
     * @return A new activator of the generic type
     */
    public T create(PlayerView myView, GameController controller, PlayerAction action);
}
