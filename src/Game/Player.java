package Game;

import Cards.Card;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 6:41 PM
 * Game.Player class for game play interactions
 */
public class Player {
    private String name;
    private Scanner sc;
    private final CliPlayerPrinter cliPlayerPrinter;
    private PlayerView myView;
    
    public PlayerView getMyView() {
        return myView;
    }

    public Player() {
        System.out.println(CliPlayerPrinter.getBanner());
        System.out.println("Player please enter your name: ");
        this.sc = new Scanner(System.in);
        name = sc.nextLine();
        cliPlayerPrinter = new CliPlayerPrinter(this);
    }

    public void setPlayerView(PlayerView newView) {
        myView = newView;
    }

    public String getName() {
        return name;
    }
    
    public PlayerAction getNextActionInteraction(){
        PlayerAction returnValue;
        newPlayerAction playerAction = new newPlayerAction(this);
        returnValue = playerAction.getNextActionInteractionInnards();
        return returnValue;
    }

    public Card newCardChoiceInteraction(Card[] cardsToChoseFrom){
        Card[] returnSet = cardChooser("Please choose One of the following cards to put into your hand:\n",
                "",1,Arrays.asList(cardsToChoseFrom));
        if (returnSet != null){
            return returnSet[0];
        }else{
            return null;
        }

    }

    public boolean conditionalInteraction(String question, String trueChar, String falseChar){
        System.out.println(question);
        String truthValue = sc.next();
        while (!(truthValue.contains(trueChar) || truthValue.contains(falseChar))){
            System.out.println("\""+truthValue+"\"");
            truthValue = sc.next();
        }
        return truthValue.contains(trueChar);
    }
    public int integerInteraction(String question, int maxVal, int minVal){
        System.out.println(question);
        int number = sc.nextInt();
        while (!(number <= maxVal || number >= minVal)){
            System.out.println("\""+number+"\"");
            number = sc.nextInt();
        }
        return number;
    }

    public Card[] initialSwapCardsInteraction() {
        System.out.println(cliPlayerPrinter.getMyBanner());
        return cardChooser("Please please chose two cards to give to your opponent", "",
                2,
                myView.getHand());
    }
    public Card[] initialCardPlacementInteraction(){
        System.out.println(cliPlayerPrinter.getMyBanner());
        cliPlayerPrinter.printHand();
        return cardPlacer(myView.getHand(),
                "You need to place your %d cards on the field:\n",
                "Please choose where you would Like %s to go\n");
    }

    public Card[] cardChooser(String message, String emptyMessage,  int numCards, List<Card> cardsToChoseFrom){
        cliPlayerPrinter.printCards(cardsToChoseFrom, message, emptyMessage);
        Card[] returnValue = new Card[numCards];
        if(cardsToChoseFrom.size() == 0){
            returnValue = null;
        } else {
            for(int i=0; i< numCards; i++){
                int location = 0;
                while (!(location > 0 && location <= cardsToChoseFrom.size())){
                    location = sc.nextInt();
                }
                returnValue[i] =cardsToChoseFrom.get(location-1);
            }
            System.out.print('\n');
        }
        return returnValue;
    }

    public Card[] cardPlacer(List<Card> cards, String titleMessage, String perCardMessage){
        System.out.printf(titleMessage, cards.size());
        Card[] returnValue = new Card[Die.getMaxDiceValue()];
        for (Card card:cards){
            System.out.printf (perCardMessage, card.toString());
            int location = 0;
            while (!(location > 0 && location <= Die.getMaxDiceValue())){
                location = this.sc.nextInt();
                if(location > 0 && location <= Die.getMaxDiceValue())
                    returnValue[location-1] = card;
            }
        }
        System.out.print('\n');
        return returnValue;
    }

    public Die diceChooser(String message) {
        System.out.println(message);
        int diceId = 0;
        while (!(diceId > 0 && diceId <= myView.getDice().length)){
            diceId = this.sc.nextInt();
        }
        return myView.getDice(diceId-1);
    }

    public void printGameState(){
        cliPlayerPrinter.printGameState();
    }
}
