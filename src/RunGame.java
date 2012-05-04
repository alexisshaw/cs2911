import Game.Game;
import Game.Player;
import Game.Deck;
import org.fusesource.jansi.AnsiConsole;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 6:40 PM
 * Main function for running of the program
 */
public class RunGame {
    static public void main(String[] args){
        AnsiConsole.systemInstall();

        //create new players
        Player[] players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        System.out.println("");
        System.out.println("");
        //Create a new game
        Game myGame = new Game(players);

        //Set up the game
        try{
         myGame.prepare();
        } catch (Deck.DeckEmptyException e){
            System.exit(1);
        }
        //Run the game
        myGame.runGame();

        //Finish and close
        System.out.println("Thankyou for playing\n");
    }
}
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               