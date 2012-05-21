package Game.testadapter;

import Game.*;
import Game.GameState.*;
import Game.GameState.GameState;
import card.Card;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameController {
    Game game;
    DelegatingPlayer[] players;
    Player standardDelegate;
    toChooseFunctor toChoose;

    public GameController(Game game, Player standardDelegate, DelegatingPlayer[] players) {
        this.game = game;
        this.standardDelegate = standardDelegate;
        this.players = players;
    }
    public GameController(GameController controller) {
        this.game = controller.game;
        this.standardDelegate = controller.standardDelegate;
        this.players = controller.players;
    }

    public void performAction() {
        game.getPlayersNextAction();
    }

    public void useFollowingActivatorPlayerDelegate(Player in) {
        for(DelegatingPlayer p: players){
           p.setDelegate(in);
        }
    }

    public void ceaseUsingActivatorPlayerDelegate() {
        for(DelegatingPlayer p: players){
            p.setDelegate(standardDelegate);
        }
    }

    public void setBattleDieRoll(int roll) {
        game.getGameState().setBattleDieIsSet(true);
        game.getGameState().setBattleDie(new Die(roll));
    }

    public Deck getDeck() {
        return game.getGameState().getDeck();
    }

    public void setToSend(PlayerAction in) {
        for(DelegatingPlayer p: players){
            p.setDelegate(new GameControllerDelegatedPlayer(in));
        }
    }
    
    private class GameControllerDelegatedPlayer extends DelegatedPlayer{
        PlayerAction toSend;

        GameControllerDelegatedPlayer (PlayerAction p){
            toSend = p;
        }

        @Override
        public PlayerAction getNextActionInteraction() {
            return toSend;
        }

        @Override
        public Collection<Card> cardChooser(String message, String emptyMessage, int numCards, Collection<Card> cardsToChoseFromIn) {
            return toChoose.cardToChoose(cardsToChoseFromIn);
        }
    }


    public PlayerView getMyPlayerView() {
        return players[game.getGameState().getCurrentPlayerID()].getMyView();
    }


    public interface toChooseFunctor {
        Collection<Card> cardToChoose(Collection<card.Card> chooseFrom);
    }

    public void setToChoose(toChooseFunctor functor) {
        this.toChoose = functor;
    }

}
