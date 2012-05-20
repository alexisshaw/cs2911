package Game.testadapter;

import framework.interfaces.*;
import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcceptanceInterface implements framework.interfaces.AcceptanceInterface {
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
    public MoveMaker getMover(GameState state) {
        return new Game.testadapter.MoveMaker();
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
    public GameState getInitialState() {
        return new Game.testadapter.GameState();
    }
}
