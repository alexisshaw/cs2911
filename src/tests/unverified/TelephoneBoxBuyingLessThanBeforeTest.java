package tests.unverified;

import java.util.ArrayList;

import framework.Rules;
import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.LegionariusActivator;
import framework.interfaces.activators.MercatorActivator;
import framework.interfaces.activators.TelephoneBoxActivator;

/**
 * @author Luke Harrison
 * @author Kenneth Wong
 * 
 * Tests whether they can cope with you buying more victory points than normal, with a TIME MACHINE :D
 */

public class TelephoneBoxBuyingLessThanBeforeTest extends Test {
   private final int PLAYER_1 = 0;
   private final int PLAYER_2 = 1;
   @Override
   public String getShortDescription() {
      return "Using Mercator and Time Machine to end up with different amounts of money";
   }

   @Override
   public void run(GameState gameState, MoveMaker move) throws AssertionError,
         UnsupportedOperationException, IllegalArgumentException {
      Card[][] playerFields = new Card[Rules.NUM_PLAYERS][Rules.NUM_DICE_DISCS];
      playerFields[PLAYER_1] = new Card[] {
            Card.TELEPHONEBOX,
            Card.TURRIS,
            Card.SICARIUS,
            Card.SICARIUS,
            Card.SICARIUS,
            Card.SICARIUS,
            Card.SICARIUS
      };

      playerFields[PLAYER_2] = new Card[] {
            Card.MERCATOR,
            Card.LEGIONARIUS,
            Card.LEGIONARIUS,
            Card.LEGIONARIUS,
            Card.LEGIONARIUS,
            Card.LEGIONARIUS,
            Card.LEGIONARIUS
      };
      
      ArrayList<Card> player1Hand = new ArrayList<Card>();
      player1Hand.add(Card.KAT); // Player 1 has a kat! That will win him the game!

      
      gameState.setWhoseTurn(PLAYER_2);
      gameState.setPlayerCardsOnDiscs(PLAYER_1,playerFields[PLAYER_1]);
      gameState.setPlayerCardsOnDiscs(PLAYER_2,playerFields[PLAYER_2]);
      gameState.setPlayerHand(PLAYER_1, player1Hand);
      gameState.setPlayerHand(PLAYER_2, new ArrayList<Card>());
      gameState.setPlayerVictoryPoints(PLAYER_1, 10);
      gameState.setPlayerVictoryPoints(PLAYER_2, 10);
      gameState.setPlayerSestertii(PLAYER_1, 10);
      gameState.setPlayerSestertii(PLAYER_2, 50);

      // Turn 1: (Where player 2 tries to kill player 1's cards. Little does he know that luck is not on his side.)
      gameState.setActionDice(new int[] {1,3,4});
      LegionariusActivator attacker1 = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
      attacker1.giveAttackDieRoll(2); // OHHHH so close.
      attacker1.complete();
      assert (gameState.getPlayerCardsOnDiscs(PLAYER_1)[Rules.DICE_DISC_3 - 1] == Card.SICARIUS);
      LegionariusActivator attacker2 = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_4);
      attacker2.giveAttackDieRoll(2); // Damn it, close again.
      attacker2.complete();
      assert (gameState.getPlayerCardsOnDiscs(PLAYER_1)[Rules.DICE_DISC_4 - 1] == Card.SICARIUS);
      move.endTurn(); // Gives up
      
      // Turn 2: (Dumbfounded by this assult, player 1 passes).
      gameState.setActionDice(new int[] {1,3,4});
      assert (gameState.getPlayerVictoryPoints(PLAYER_1) == 10);
      move.endTurn();
      
      // Turn 3: (Player 2 rubs it into player 1, by getting rid of all his tokens (except for 1!))
      gameState.setActionDice(new int[] {1,3,4});
      assert (gameState.getPlayerVictoryPoints(PLAYER_2) == 10);
      MercatorActivator buyer = (MercatorActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
      buyer.chooseMercatorBuyNum(9);
      buyer.complete();
      assert (gameState.getPlayerSestertii(PLAYER_2) == 50 - 2*9);
      assert (gameState.getPlayerSestertii(PLAYER_1) == 10 + 2*9);
      assert (gameState.getPlayerVictoryPoints(PLAYER_2) == 10 + 9);
      assert (gameState.getPlayerVictoryPoints(PLAYER_1) == 10 - 9);
      move.endTurn();
      
      // Turn 4: (Where player 1 has time and a kat on his side)
      gameState.setActionDice(new int[] {1, 3, 4});
      move.placeCard(Card.KAT, Rules.DICE_DISC_2); // Quick Kat! To the rescue!
      assert (gameState.getPlayerCardsOnDiscs(PLAYER_1)[Rules.DICE_DISC_2 - 1] == Card.KAT);
      assert (gameState.getPlayerHand(PLAYER_1).size() == 0);
      assert (gameState.getPlayerSestertii(PLAYER_1) == 10 + 2*9 - 5);
      // And at this point, player 1 realises that if he had his Kat 3 turns ago, he would have survived!
      TelephoneBoxActivator tardis = (TelephoneBoxActivator) move.chooseCardToActivate(Rules.DICE_DISC_1);
      tardis.chooseDiceDisc(Rules.DICE_DISC_2);
      tardis.setSecondDiceUsed(3);
      tardis.shouldMoveForwardInTime(false);
      tardis.complete();
      
      // But what is this?
      assert (gameState.isGameCompleted());
      
      // It seems player 1 lost!!!!! NOOOOOOOO
      assert (gameState.getPlayerSestertii(PLAYER_2) == 50 - 2*8);
      assert (gameState.getPlayerSestertii(PLAYER_1) == 10 + 2*8);
      
      // The Kat betrayed him! It ran off with his TARDIS, leaving him abandoned...
      assert (gameState.getPlayerVictoryPoints(PLAYER_2) == 10 + 8);
      assert (gameState.getPlayerVictoryPoints(PLAYER_1) == 0);
   }

}
