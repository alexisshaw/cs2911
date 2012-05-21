package Game;

//import card.Card;


import Game.GameState.GameState;
import card.Card;
import Game.Player;
import card.DefenseModificationActor;

import java.util.*;

public class CardView {
    private GameState state;
    private int playerId;

    public Collection<Card> getAllOpposingCards(Card me){
        Disk cardIndex = getCardKey(me);
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        for (int i=0; i < getMyPlayerView().getNoPlayers(); i++){
            if(i != getMyPlayerView().getPlayerId()){
                cardsToChooseFrom.addAll(state.getPlayerState(i).getField().values());
            }
        }

        return cardsToChooseFrom;
    }

    public Card getMe(Card me){
        Disk d = getCardKey(me);
        return state.getPlayerState(state.getCurrentPlayerID()).getField().get(d);
    }

    public CardView(GameState state, int playerNo){
        this.state=state;
        this.playerId=playerNo;
    }

    public CardView(CardView in){
        this.state = in.state;
        this.playerId = in.playerId;
    }

    public int getCurrentPlayerID(){
        return state.getCurrentPlayerID();
    }
    public int getMyPlayerID(Card me){
        for(int i=0; i<(getMyPlayerView().getNoPlayers()) ; i++){
            if (getMyPlayerView().getField(i).containsValue(me))
                return i;
        }
        return -1;
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
    public Collection<Card> getDirectlyOpposingCards(Card me){
        Disk cardIndex = getCardKey(me);
        Set<Card> cardsToChooseFrom = new HashSet<Card>();
        for (int i=0; i < getMyPlayerView().getNoPlayers(); i++){
            if(i != getMyPlayerView().getPlayerId()){
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
    public Die getBattleDie(){
        return state.isBattleDieIsSet()?state.getBattleDie(): new Die(new Random());
    }

    public int getTurnNumber(){
        return state.getTurnNumber();
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
    public int getDefenseDelta(Card c){
        int defenseDelta = 0;
        for(DefenseModificationActor a: state.getDefenseModificationActors()){
            if(a.doesModify(c)){
                defenseDelta += a.getDefenseDelta(c);
            }
        }
        return defenseDelta;
    }
}
