package Game;

//import card.Card;


import Game.GameState.GameState;
import card.Card;
import Game.CLIPlayer.Player;

import java.util.*;

public class CardView {
    private GameState state;
    private int playerId;
    
    public CardView(GameState state, int playerNo){
        this.state=state;
        this.playerId=playerNo;
    }
    public Disk getCardKey(Card me) throws NoSuchElementException{
        Map<Disk, Card> field = getMyPlayerView().getField(getMyPlayerView().getPlayerId());
        for(Map.Entry<Disk,Card> e: field.entrySet()) if(e.getValue().equals(me)) return e.getKey();
        throw new NoSuchElementException();
    }

    public Collection<Card> getOpposingCards(Card me){
        Disk cardIndex = getCardKey(me);
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        for (int i=0; i < getMyPlayerView().getNoPlayers(); i++){
            if(i != getMyPlayerView().getPlayerId()){
                for(Disk d: cardIndex.getBeside()){
                    if(getMyPlayerView().getField(i).get(d) != null){
                        cardsToChooseFrom.add(getMyPlayerView().getField(i).get(d));
                    }
                }
                if(getMyPlayerView().getField(i).get(cardIndex) != null){
                    cardsToChooseFrom.add(getMyPlayerView().getField(i).get(cardIndex));
                }
            }
        }
        return cardsToChooseFrom;
    }
    public Collection<Card> getCardsNextTo(Card me){
        Disk cardIndex = getCardKey(me);
        int i = playerId;
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        for(Disk d: cardIndex.getBeside()){
            if(getMyPlayerView().getField(i).get(d) != null){
                cardsToChooseFrom.add(getMyPlayerView().getField(i).get(d));
            }
        }
        return cardsToChooseFrom;
    }
    public Collection<Card> getDiscard(){
        return state.getDiscardPile();
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
