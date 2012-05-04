package Cards;

import Game.CardView;
import Game.Die;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 5/1/12
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class NeroCard implements Card{
    public boolean isBuilding() {
        return false;
    }

    public int getNumberOfDiceRequired() {
        return 1;
    }

    public int getPrice() {
        return 8;
    }

    public int getDefence() {
        return 9;
    }

    public CardAction getCardAction(CardView in) {
        Card[] field = in.getMyPlayerView().getField(in.getMyPlayerView().getPlayerId());
        Vector<Card> vectorField = new Vector<Card>(Arrays.asList(field));
        int cardIndex = vectorField.indexOf(this);
        Vector<Card> cardsToChooseFrom = new Vector<Card>();
        for (int i=0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if(i != in.getMyPlayerView().getPlayerId()){
                if(cardIndex != 0 && in.getMyPlayerView().getField(i)[cardIndex - 1] != null && in.getMyPlayerView().getField(i)[cardIndex - 1].isBuilding()){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex - 1]);
                }
                if(in.getMyPlayerView().getField(i)[cardIndex] != null && in.getMyPlayerView().getField(i)[cardIndex].isBuilding()){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex]);
                }
                if(cardIndex == Die.getMaxDiceValue()-1 && in.getMyPlayerView().getField(i)[cardIndex + 1] != null && in.getMyPlayerView().getField(i)[cardIndex + 1].isBuilding()){
                    cardsToChooseFrom.add(in.getMyPlayerView().getField(i)[cardIndex+1]);
                }
            }
        }
        Card[] chosenCard = in.getPlayer().cardChooser("Please choose one of the following opponents cards to destroy",
                "You cannot destroy a card",
                1,
                cardsToChooseFrom);
        CardAction returnValue = new CardAction();
        returnValue.setDestroyCards(new Card[]{this, chosenCard[0]});
        return returnValue;
    }

    public String getCardOracle() {
        return "Destroys any face-up opposing building card. The card destroyed and Nero are both discarded";
    }
}
