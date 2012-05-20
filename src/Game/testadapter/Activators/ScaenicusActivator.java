package Game.testadapter.Activators;

import Game.*;
import Game.testadapter.ActivatorFactory;
import Game.testadapter.DelegatedPlayer;
import Game.testadapter.GameController;
import card.Card;
import framework.interfaces.activators.CardActivator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kent
 * Date: 21/05/12
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScaenicusActivator implements
        framework.interfaces.activators.ScaenicusActivator {

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
    public static ScaenicusActivator create(PlayerView myView, GameController controller, PlayerAction action) {
        ScaenicusActivator newScaenicusActivator = new ScaenicusActivator();
        newScaenicusActivator.myView = myView;
        newScaenicusActivator.controller = controller;
        newScaenicusActivator.activationAction = action;
        return newScaenicusActivator;
    }

    Player subDelegate;
    card.Card chosenCard;
    boolean hasSetBattleDieRoll;
    int battleDieRoll;

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
        chosenCard = myView.getField(myView.getPlayerId()).get(new Disk(diceDisc));
        GameController wrapperController = new GameController() {
            @Override
            public void performAction() {
            }

            @Override
            public void useFollowingActivatorPlayerDelegate(Player p) {
                subDelegate = p;
            }

            @Override
            public void ceaseUsingActivatorPlayerDelegate() {
            }

            @Override
            public void setBattleDieRoll(int roll) {
                hasSetBattleDieRoll = true;
                battleDieRoll = roll;
            }

            @Override
            public Deck getDeck() {
                return controller.getDeck();
            }
        };
        return ActivatorFactory.createActivator(chosenCard, myView, wrapperController, activationAction);
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
        controller.useFollowingActivatorPlayerDelegate(new wrapperDelegatePlayer());
        if (hasSetBattleDieRoll) controller.setBattleDieRoll(battleDieRoll);
        controller.performAction();
        controller.ceaseUsingActivatorPlayerDelegate();
    }

    public class wrapperDelegatePlayer extends DelegatedPlayer {
        boolean hasSentScandicusCardChooserPayload = false;

        @Override
        public boolean conditionalInteraction(String question, String trueChar, String falseChar) {
            return subDelegate.conditionalInteraction(question, trueChar, falseChar);
        }

        @Override
        public int integerInteraction(String question, int maxVal, int minVal) {
            return subDelegate.integerInteraction(question, maxVal, minVal);
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            if (hasSentScandicusCardChooserPayload) {
                return subDelegate.cardChooser(message, emptyMessage, numCards, cardsToChoseFromIn);
            } else {
                hasSentScandicusCardChooserPayload = true;
                return Arrays.asList(chosenCard);
            }
        }

        @Override
        public Collection<Disk> diskChooser(String message, String emptyMessage, int numCards, Collection<Disk> cardsToChoseFromIn) {
            return diskChooser(message, emptyMessage, numCards, cardsToChoseFromIn);
        }

        @Override
        public Map<Disk, Card> cardPlacer(Collection<Card> cards, String titleMessage, String perCardMessage) {
            return cardPlacer(cards, titleMessage, perCardMessage);
        }

        @Override
        public Die diceChooser(String message, String emptyMessage) {
            return diceChooser(message, emptyMessage);
        }

        @Override
        public Die diceChooser(String message, String emptyMessage, List<Die> d) {
            return diceChooser(message, emptyMessage, d);
        }

        @Override
        public Map<Disk, Card> cardMultiPlacer(Collection<Card> toChooseFrom, boolean mustPlaceAll) {
            return cardMultiPlacer(toChooseFrom, mustPlaceAll);
        }
    }
}
