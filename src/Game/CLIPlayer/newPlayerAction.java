package Game.CLIPlayer;

import Game.Die;
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
                "Please select the number of the dice you wish to use:", "You cannot choose a die"));
    }

    PlayerAction collectMoneyActionInteraction() {
        player.printGameState();
        System.out.println("You have chosen to Collect come money");
        return new PlayerAction(PlayerAction.CardType.Money, player.diceChooser(
                "Please select the number of the dice you wish to use:", "You cannot choose a die"));
    }

    PlayerAction activateCardActionInteraction() {
        player.printGameState();
        System.out.println("You have chosen to Activate a card");
        Disk disk = player.diskChooser(
                "Please select the number of the dice disk beside the card you wish to activate:",
                "You cannot activate a card",
                1,
                getActionableDisks()
        ).iterator().next();
        Die activationDie;
        if (disk.equals(Disk.BRIBE_DISK)){
            activationDie = player.diceChooser("Please Choose a die to use for Bribing", "",getBribeDice());
        } else {
            activationDie = new Die(disk.intValue());
        }
        return new PlayerAction(PlayerAction.CardType.Activate, disk,activationDie);
    }
    List<Disk> getActionableDisks(){
        List<Die> dice = player.getMyView().getDice();
        List<Disk> disks = new LinkedList<Disk>();
        for(Die d:dice){
            disks.add(new Disk(d.getDieValue()));
        }
        List<Die> bribeDice = getBribeDice();
        if(!bribeDice.isEmpty()) disks.add(Disk.BRIBE_DISK);
        return disks;
    }
    List<Die> getBribeDice(){
        int money = player.getMyView().getMoney(player.getMyView().getPlayerId());
        List<Die> dice = player.getMyView().getDice();
        List<Die> bribeDice = new LinkedList<Die>();
        for(Die d:dice){
            if(d.getDieValue() <= money){
                bribeDice.add(d);
            }
        }
        return bribeDice;
    }
}
