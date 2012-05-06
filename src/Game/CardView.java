package Game;

//import Cards.Card;


import Cards.Card;

import java.util.*;

public class CardView {
    private GameState state;
    private int playerId;
    
    public CardView(GameState state, int playerNo){
        this.state=state;
        this.playerId=playerNo;
    }

    public Collection<Card> getOpposingCards(Card me){
        Card[] field = getMyPlayerView().getField(getMyPlayerView().getPlayerId());
        Vector<Card> vectorField = new Vector<Card>(Arrays.asList(field));
        int cardIndex = vectorField.indexOf(me);
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        for (int i=0; i < getMyPlayerView().getNoPlayers(); i++){
            if(i != getMyPlayerView().getPlayerId()){
                if(cardIndex != 0 && getMyPlayerView().getField(i)[cardIndex - 1] != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i)[cardIndex - 1]);
                }
                if(getMyPlayerView().getField(i)[cardIndex] != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i)[cardIndex]);
                }
                if(cardIndex == Die.getMaxDiceValue()-1 && getMyPlayerView().getField(i)[cardIndex + 1] != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i)[cardIndex+1]);
                }
            }
        }
        return cardsToChooseFrom;
    }
    /*public Card[][] getOpposingFields(){
        Card[][] returnValue = new Card[state.getNumPlayers() - 1][];
        int j=0;
        for(int i=0; i<state.getNumPlayers();i++){
            if(i!=playerId){
                returnValue[j] = state.getPlayerState(i).getField().clone();
                j++;
            }
        }
        return returnValue;
    }*/
    public PlayerView getMyPlayerView(){
        return new PlayerView(state,playerId);
    }
    public Player getPlayer(){
        return state.getPlayer(playerId);
    }
}
