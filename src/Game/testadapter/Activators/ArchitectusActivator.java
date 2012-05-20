package Game.testadapter.Activators;

import Game.Disk;
import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.AssetTranslator;
import Game.testadapter.GameController;
import Game.testadapter.delegatedPlayer;
import framework.cards.Card;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArchitectusActivator implements
        framework.interfaces.activators.ArchitectusActivator,
        ActivatorWithCreate<ArchitectusActivator> {
    PlayerView myView;
    GameController controller;
    PlayerAction action;

    Stack<Card> toLay = new Stack<Card>();
    Stack<Disk> diskToUse = new Stack<Disk>();

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    @Override
    public ArchitectusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        ArchitectusActivator architectusActivator = new ArchitectusActivator();
        architectusActivator.myView = myView;
        architectusActivator.controller = controller;
        architectusActivator.action = action;
        return architectusActivator;
    }

    @Override
    public void layCard(Card myCard, int whichDiceDisc) {
        toLay.push(myCard);
        diskToUse.push(new Disk(whichDiceDisc));
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
    private class ArchitectusActivatorPlayerDelegate extends delegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }

        @Override
        public Map<Disk, card.Card> cardMultiPlacer(Collection<card.Card> toChooseFrom, boolean mustPlaceAll) {
            Map<Disk, card.Card> returnValue = new HashMap<Disk, card.Card>();
            while(!toLay.isEmpty()){
                returnValue.put(diskToUse.pop(), AssetTranslator.findEquivelentCard(toChooseFrom,toLay.pop()));
            }
            return returnValue;
        }
    }
}
