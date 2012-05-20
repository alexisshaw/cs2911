package Game.testadapter.Activators;


import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;
import card.Card;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class AesculapinumActivator implements
        framework.interfaces.activators.AesculapinumActivator {
    int pileIndex = 0;

    PlayerView myView;
    GameController controller;
    PlayerAction action;

    /**
     * Common Card Activator Creator, for use in the factory
     *
     * @param myView     thePlayerView to use
     * @param controller the GameController the activator to use
     * @param action     the action for the game to use
     * @return A new activator of the generic type
     */
    public static AesculapinumActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        AesculapinumActivator aesculapinumActivator = new AesculapinumActivator();
        aesculapinumActivator.myView = myView;
        aesculapinumActivator.controller = controller;
        aesculapinumActivator.action = action;
        return aesculapinumActivator;
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
        controller.useFollowingActivatorPlayerDelegate(new AesculapinumActivatorDelegatePlayer());
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    private class AesculapinumActivatorDelegatePlayer extends DelegatedPlayer {
        @Override
        public PlayerAction getNextActionInteraction() {
            return action;
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            return ((List) cardsToChoseFromIn).subList(cardsToChoseFromIn.size() - 1 - pileIndex, cardsToChoseFromIn.size() - 1 - pileIndex);
        }
    }

    /**
     * The user chooses a card from a pile.
     * <p/>
     * <p>
     * The tester will get your discard pile, and scan through it for the card required.
     * So, the card they have choosen will be at getDiscard().get(indexOfCard);
     * </p>
     *
     * @param indexOfCard the index of the card to use
     */
    @Override
    public void chooseCardFromPile(int indexOfCard) {
        pileIndex = indexOfCard;
    }
}
