package card;

import Game.CardView;
import Game.DiscardView;
import Game.Disk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/22/12
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class GrimReaperDiscardActor implements DiscardActor {
    public static Map<Integer,Integer> currentlyActive = new HashMap<Integer,Integer>();
    private static boolean savedCard = false;
    Card reference = null;
    private int playerID;
    CardView myView;
    static CardView ourView = null;

    public GrimReaperDiscardActor(Card reference, CardView in){
        this.reference = reference;
        this.playerID = in.getCurrentPlayerID();
        if(!currentlyActive.containsKey(playerID)){
            currentlyActive.put(playerID,0);
        }
        currentlyActive.put(playerID,currentlyActive.get(playerID) + 1);
        myView = in;
        if(ourView == null){
            ourView = in;
        }
    }

    public static boolean willSaveMe(Card card){
        boolean returnValue = false;
        for(Integer i:currentlyActive.keySet()){
            if(ourView.getMyPlayerView().getField(i).containsValue(card)){
                returnValue = true;
            }
        }
        return returnValue;
    }


    @Override
    public CardAction getAction(DiscardView in) {
        CardAction returnValue = new CardAction();
        int playerID = myView.getMyPlayerID(reference);
        if(in.getOriginalField() == playerID){
            if((in.getCardBeingDiscarded() == reference) && (currentlyActive.get(playerID) <= 1)){
                returnValue.setDiscardActorToRemove(this);
                currentlyActive.put(playerID,currentlyActive.get(playerID) - 1);
            } else if (in.getCardBeingDiscarded().getClass() == KatCard.class &&
                    ((KatCard) in.getCardBeingDiscarded()).getCurrentLivesRemaining() > 0){
            } else if (savedCard == false &&
                    in.getManorOfDiscard().equals(DiscardView.DiscardManor.NORMAL) &&
                    !in.getCardBeingDiscarded().isBuilding()){
                savedCard = true;
                List<Card> returnToHand = new LinkedList<Card>();
                returnToHand.add(in.getCardBeingDiscarded());
                returnValue.setToAddToHandFromDiscard(returnToHand);
            }
        }
        return returnValue;
    }
    public void cleanupAfterRound(){
        savedCard = false;
    }

    @Override
    public Card getCard() {
        return reference;
    }
}
