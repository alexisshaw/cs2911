package Game.testadapter.Activators;

import Game.Disk;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.AssetTranslator;
import Game.testadapter.GameController;
import Game.testadapter.delegatedPlayer;
import framework.cards.Card;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/21/12
 * Time: 1:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class MachinaActivator implements
        framework.interfaces.activators.MachinaActivator,
        ActivatorWithCreate<MachinaActivator>
{
    Map<Disk,Card> toReLay = new HashMap<Disk, Card>();
    PlayerAction action;
    GameController controller;
    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public MachinaActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        MachinaActivator machinaActivator = new MachinaActivator();
        machinaActivator.action = action;
        machinaActivator.controller = controller;
        return machinaActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new MachinaActivatorDelegatedPlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }
    private class MachinaActivatorDelegatedPlayer extends delegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }

        @Override
        public Map<Disk, card.Card> cardMultiPlacer(Collection<card.Card> toChooseFrom, boolean mustPlaceAll) {
            Map<Disk, card.Card> returnValue = new HashMap<Disk, card.Card>();
            for(Map.Entry<Disk,Card> e: toReLay.entrySet()){
                returnValue.put(e.getKey(), AssetTranslator.findEquivelentCard(toChooseFrom, e.getValue()));
            }
            return returnValue;
        }
    }

    /**
     * Place a floating card on to a dice disc.
     * <p/>
     * <p>
     * When cards that allow rearrangement are activated, all the cards
     * affected enter a floating state. Cards are then given new
     * locations with this method. The pending activation cannot be
     * completed while there are floating cards.
     * </p>
     *
     * @param card     the card to place
     * @param diceDisc the location for the card to be placed
     */
    @Override
    public void placeCard(Card card, int diceDisc) {
        toReLay.put(new Disk(diceDisc), card);
    }
}
