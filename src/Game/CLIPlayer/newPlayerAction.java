package Game.CLIPlayer;

import card.Card;
import Game.Disk;
import Game.PlayerAction;

import java.util.*;

public class newPlayerAction {
    private final Player player;

    public newPlayerAction(Player player) {
        this.player = player;
    }

    PlayerAction getNextActionInteractionInnards() {
        PlayerAction returnValue;
        player.printGameState();
        System.out.println("Choose one of the following actions:");
        System.out.println("   1: Pass");
        System.out.println("   2: Collect money");
        System.out.println("   3: Draw some cards and choose one");
        System.out.println("   4: Place Card on Board");
        System.out.println("   5: Activate a card already on the field");

        int actionCode = 0;

        while (!(actionCode > 0 && actionCode <= 5)) {
            actionCode = new Scanner(System.in).nextInt();
        }

        if (actionCode == 1) {
            returnValue = new PlayerAction(PlayerAction.CardType.Pass);
        } else if (actionCode == 2) {
            returnValue = collectMoneyActionInteraction();
        } else if (actionCode == 3) {
            returnValue = drawCardsActionInteraction();
        } else if (actionCode == 4) {
            returnValue = placeCardActionInteraction();
        } else if (actionCode == 5) {
            returnValue = activateCardActionInteraction();
        } else {
            returnValue = null;
        }
        return returnValue;
    }

    PlayerAction placeCardActionInteraction() {
        player.printGameState();
        System.out.println("You have chosen to place a card");
        int locationId = 0;
        Collection<Card> chosenCard = player.cardChooser("Please choose a card to place", "Sorry, you have no card to place on the field", 1, player.getMyView().getHand());
        if (chosenCard != null && !chosenCard.isEmpty()) {
            Map<Disk,Card> CardLocation =  player.cardPlacer(chosenCard,
                    "Please choose where you wish to place your card \n", "");
            Map.Entry<Disk,Card> entry = CardLocation.entrySet().iterator().next();
            System.out.println(locationId);
            System.out.print('\n');
            return new PlayerAction(PlayerAction.CardType.Place, entry.getValue(), entry.getKey());
        } else {
            return new PlayerAction();
        }
    }

    PlayerAction drawCardsActionInteraction() {
        player.printGameState();
        System.out.println("You have chosen to draw a card");
        return new PlayerAction(PlayerAction.CardType.Draw, player.diceChooser(
                "Please select the number of the dice you wish to use:"));
    }

    PlayerAction collectMoneyActionInteraction() {
        player.printGameState();
        System.out.println("You have chosen to Collect come money");
        return new PlayerAction(PlayerAction.CardType.Money, player.diceChooser(
                "Please select the number of the dice you wish to use:"));
    }

    PlayerAction activateCardActionInteraction() {
        player.printGameState();
        System.out.println("You have chosen to Activate a card");
        return new PlayerAction(PlayerAction.CardType.Activate, player.diceChooser(
                "Please select the number of the dice disk beside the card you wish to activate:"));
    }
}