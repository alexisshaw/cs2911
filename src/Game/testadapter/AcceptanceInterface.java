package Game.testadapter;

import Game.CLIPlayer.Player;
import Game.Game;
import Game.Deck;
import Game.DelegatingPlayer;
import Game.testadapter.*;
import com.google.common.base.Strings;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcceptanceInterface implements framework.interfaces.AcceptanceInterface {
    Game currentGame;
    GameController controller;

    public static void main(String[] args){

    }
    /**
     * Return a {@link framework.interfaces.MoveMaker} that will modify the given GameState.
     * <p/>
     * <p>
     * This MoveMaker will be used by the tests to modify the GameState
     * that was given by getInitialState. The affected GameState is
     * included as a parameter so you can ensure that the MoveMaker will
     * operate on the correct GameState.
     * </p>
     *
     * @param state the GameState that the mover will apply changes to
     * @return a MoveMaker that will modify the given GameState
     */
    @Override
    public framework.interfaces.MoveMaker getMover(framework.interfaces.GameState state) {
        return new MoveMaker(controller);
    }

    /**
     * Instantiate a {@link framework.interfaces.GameState} object.
     * <p/>
     * <p>
     * The created GameState should be a mutable new instance as this is called
     * before each test is run.
     * </p>
     * <p/>
     * <p>
     * The state should be set in the initial condition as defined per:
     * TODO: add the crap that makes an initial state here.
     * </p>
     *
     * @return a GameState at the initial state
     */
    @Override
    public framework.interfaces.GameState getInitialState() {
        //create new players
        DelegatedPlayer standardDelegate = new DelegatedPlayer();
        DelegatingPlayer[] players = new DelegatingPlayer[2];
        players[0] = new DelegatingPlayer(standardDelegate);
        players[1] = new DelegatingPlayer(standardDelegate);
        //Create a new game
        currentGame = new Game(players);
        currentGame.getNames();
        try{
            currentGame.prepare();
        } catch (Deck.DeckEmptyException e){
            System.exit(1);
        }
        controller = new GameController(currentGame,standardDelegate,players);
        
        return new GameState(currentGame.getGameState());
    }
}
