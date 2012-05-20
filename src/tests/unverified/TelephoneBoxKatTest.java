package tests.unverified;

import framework.Rules;
import framework.Test;
import framework.cards.Card;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;
import framework.interfaces.activators.LegionariusActivator;
import framework.interfaces.activators.TelephoneBoxActivator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * @Author : Chris FONG
 * Date: 19/05/12
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TelephoneBoxKatTest extends Test {

    private final int PLAYER_1 = 0;
    private final int PLAYER_2 = 1;

    @Override
    public String getShortDescription() {
        return "Testing Kat interacting with Telephone Box";
    }

    @Override
    public void run(GameState gameState, MoveMaker move) throws AssertionError, UnsupportedOperationException, IllegalArgumentException {

        Collection<Card> hand = new ArrayList<Card>();
        hand.add(Card.KAT);
        gameState.setDiscard(new ArrayList<Card>());
        gameState.setPlayerHand(PLAYER_2,hand);
        gameState.setPlayerSestertii(PLAYER_1,100);
        gameState.setPlayerSestertii(PLAYER_2,100);
        gameState.setPlayerVictoryPoints(PLAYER_1, 15);
        gameState.setPlayerVictoryPoints(PLAYER_2,15);

        Card[][] playerFields = new Card[Rules.NUM_PLAYERS][Rules.NUM_DICE_DISCS];

        playerFields[PLAYER_1] = new Card[] {
                Card.SCAENICUS,
                Card.SCAENICUS,
                Card.LEGIONARIUS,
                Card.SCAENICUS,
                Card.SCAENICUS,
                Card.SCAENICUS,
                Card.SCAENICUS
        };

        playerFields[PLAYER_2] = new Card[] {
                Card.SCAENICUS,
                Card.SCAENICUS,
                Card.SCAENICUS,
                Card.TELEPHONEBOX,
                Card.SCAENICUS,
                Card.SCAENICUS,
                Card.SCAENICUS
        };

        gameState.setPlayerCardsOnDiscs(PLAYER_1,playerFields[PLAYER_1]);
        gameState.setPlayerCardsOnDiscs(PLAYER_2,playerFields[PLAYER_2]);

        gameState.setWhoseTurn(PLAYER_1);                               //TURN : 1
        gameState.setActionDice(new int[] {1,1,5});

        move.endTurn();
        gameState.setActionDice(new int[] {1,1,5});

        move.placeCard(Card.KAT, Rules.DICE_DISC_3);
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.KAT);
        assert(gameState.getDiscard().contains(Card.SCAENICUS));

        move.endTurn();                                                //TURN : 2

        gameState.setActionDice(new int[]{3,3,3});

        LegionariusActivator legionarius = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        legionarius.giveAttackDieRoll(2);
        legionarius.complete();
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.KAT);    //8

        legionarius = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        legionarius.giveAttackDieRoll(2);
        legionarius.complete();
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.KAT);    //7

        legionarius = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        legionarius.giveAttackDieRoll(2);
        legionarius.complete();
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.KAT);    //6

        move.endTurn();                                              //TURN : 3

        gameState.setActionDice(new int[] {4,3,3});

        TelephoneBoxActivator jerk = (TelephoneBoxActivator) move.chooseCardToActivate(Rules.DICE_DISC_4);
        jerk.chooseDiceDisc(3);
        jerk.setSecondDiceUsed(3);
        jerk.shouldMoveForwardInTime(false);
        jerk.complete();                                                     //5 --> 4 --> 3

        move.endTurn();                                              //TURN : 4
        gameState.setActionDice(new int[]{3,3,3});

        legionarius = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        legionarius.giveAttackDieRoll(2);
        legionarius.complete();                                              //2
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.KAT);

        legionarius = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        legionarius.giveAttackDieRoll(2);
        legionarius.complete();
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.KAT);    //1

        legionarius = (LegionariusActivator) move.chooseCardToActivate(Rules.DICE_DISC_3);
        legionarius.giveAttackDieRoll(2);
        legionarius.complete();                                              //0
        assert(gameState.getPlayerCardsOnDiscs(PLAYER_2)[2] == Card.NOT_A_CARD);

    }
}
