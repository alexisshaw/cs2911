package Game;

import Cards.Card;

import java.util.*;

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

    public Card newCardChoiceInteraction(Collection<Card> cardsToChoseFrom){
        Collection<Card> returnSet = cardChooser("Please choose One of the following cards to put into your hand:\n",
                "",1,cardsToChoseFrom);
        if (!returnSet.isEmpty()){
            return returnSet.iterator().next();
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

    public Collection<Card> initialSwapCardsInteraction() {
        System.out.println(cliPlayerPrinter.getMyBanner());
        return cardChooser("Please please chose two cards to give to your opponent", "",
                2,
                myView.getHand());
    }
    public Map<Integer,Card> initialCardPlacementInteraction(){
        System.out.println(cliPlayerPrinter.getMyBanner());
        cliPlayerPrinter.printHand();
        return cardPlacer(myView.getHand(),
                "You need to place your %d cards on the field:\n",
                "Please choose where you would Like %s to go\n");
    }

    public Collection<Card> cardChooser(String message, String emptyMessage,  int numCards, Collection<Card> cardsToChoseFromIn){
        List<Card> cardsToChoseFrom = new ArrayList<Card>(cardsToChoseFromIn);
        cliPlayerPrinter.printCards(cardsToChoseFrom, message, emptyMessage);
        Collection<Card> returnValue = new HashSet<Card>();
        if(cardsToChoseFrom.size() != 0){
            for(int i=0; i< numCards && !cardsToChoseFrom.isEmpty(); i++){
                int location = 0;
                while (!(location > 0 && location <= cardsToChoseFrom.size())){
                    location = sc.nextInt();
                }
                returnValue.add(cardsToChoseFrom.get(location-1));
                cardsToChoseFrom.remove(cardsToChoseFrom.get(location - 1));
            }
            System.out.print('\n');
        }
        return returnValue;
    }
    public Map<Integer, Card> cardPlacer(Collection<Card> cards, String titleMessage, String perCardMessage){
        System.out.printf(titleMessage, cards.size());
        Map<Integer, Card> returnValue = new HashMap<Integer, Card>();
        for (Card card:cards){
            System.out.printf (perCardMessage, card.toString());
            int location = 0;
            while (!(location > 0 && location <= Die.getMaxDiceValue())){
                location = this.sc.nextInt();
                if(location > 0 && location <= Die.getMaxDiceValue())
                    returnValue.put(location - 1, card);
            }
        }
        System.out.print('\n');
        return returnValue;
    }

    public Die diceChooser(String message) {
        System.out.println(message);
        int diceId = 0;
        while (!(diceId > 0 && diceId <= myView.getDice().size())){
            diceId = this.sc.nextInt();
        }
        return myView.getDice(diceId-1);
    }

    public void printGameState(){
        cliPlayerPrinter.printGameState();
    }
    public Map<Integer, Card> cardMultiPlacer(Collection<Card> toChooseFrom, boolean mustPlaceAll){
        Map<Integer, Card> location = new  HashMap<Integer, Card>();
        while(!toChooseFrom.isEmpty() && (mustPlaceAll || conditionalInteraction("Do You Wish to lay a Card (Y/N)", "Y", "N"))){
            Collection<Card> chosenCard = cardChooser("Please Choose a card to place on the field",
                    "You cannot place a card",
                    1,
                    toChooseFrom);
            toChooseFrom.removeAll(chosenCard);
            location.putAll(cardPlacer(chosenCard,
                    "", "Please choose where you wish to place this card\n"));
        }
        return location;
    }
}
