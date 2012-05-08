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
    public int getCardIndex(Card me){
        Map<Integer, Card> field = getMyPlayerView().getField(getMyPlayerView().getPlayerId());
        for(Map.Entry<Integer,Card> e: field.entrySet()) if(e.getValue().equals(me)) return e.getKey();
        return 0;
    }

    public Collection<Card> getOpposingCards(Card me){
        int cardIndex = getCardIndex(me);
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        for (int i=0; i < getMyPlayerView().getNoPlayers(); i++){
            if(i != getMyPlayerView().getPlayerId()){
                if(getMyPlayerView().getField(i).get(cardIndex - 1) != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex - 1));
                }
                if(getMyPlayerView().getField(i).get(cardIndex) != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex));
                }
                if(getMyPlayerView().getField(i).get(cardIndex + 1) != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex + 1));
                }
            }
        }
        return cardsToChooseFrom;
    }
    public Collection<Card> getCardsNextTo(Card me){
        int cardIndex = getCardIndex(me);
        int i = playerId;
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        if(getMyPlayerView().getField(i).get(cardIndex - 1) != null){
            cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex - 1));
        }
        if(getMyPlayerView().getField(i).get(cardIndex) != null){
            cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex));
        }
        if(getMyPlayerView().getField(i).get(cardIndex + 1) != null){
            cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex + 1));
        }
        return cardsToChooseFrom;
    }

    public Stack<Card> getDeck(){
        return state.getDeck().getDeck();
    }
    public PlayerView getMyPlayerView(){
        return new PlayerView(state,playerId);
    }
    public Player getPlayer(){
        return state.getPlayer(playerId);
    }
}
