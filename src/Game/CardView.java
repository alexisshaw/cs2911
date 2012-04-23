package Game;

//import Cards.Card;


public class CardView {
    private GameState state;
    private int playerId;
    
    public CardView(GameState state, int playerNo){
        this.state=state;
        this.playerId=playerNo;
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
