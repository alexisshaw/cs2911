package Game;

import Cards.Card;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 7:55 PM
 * Interface for player actions
 */
public class PlayerAction {
    public enum CardType {
        Pass {
            @Override
            public String toString() {
                return "Pass";
            }
        },
        Money {
            @Override
            public String toString() {
                return "Take Money";
            }
        },
        Activate {
            @Override
            public String toString() {
                return "Activate Cards.Card";
            }
        },
        Draw {
            @Override
            public String toString() {
                return "Draw a Cards.Card";
            }
        },
        Place{
            @Override
            public String toString() {
                return "Place a card";
            }
        },
        Loop{
            @Override
            public String toString() {
                return "Null Player Action";
            }
        }
    }

    private final CardType actionType;

    private Die[] dice = null;
    private Card cardToLay= null;
    private int location = 0;

    //Constructor, currently sets action to pass
    public PlayerAction() {
        actionType = CardType.Loop;
    }
    public PlayerAction(CardType type){
        actionType = type;
    }
    public PlayerAction(CardType type, Die dieToUse){
        actionType = type;
        dice = new Die[1];
        dice[0] = dieToUse;
    }
    public PlayerAction(CardType type, Card myCard, int location){
        actionType = type;
        cardToLay = myCard;
        this.location  = location;
    }

    public int getLocation() {
        return location;
    }

    public Die[] getDice() {
        return dice;
    }

    public CardType getActionType() {
        return actionType;
    }
    public Card getCard(){
        return cardToLay;
    }
    /*//test
    public static void test(){
        System.out.println("Testing Game.PlayerAction");
        PlayerAction testAction = new PlayerAction();
        assert(testAction.getActionType()== CardType.Pass);
    }*/
}
