package Game;

import card.*;
import card.Buildings.*;
import card.Character.*;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: ates466
 * Date: 3/12/12
 * Time: 9:10 PM
 * Object to hold all of the cards while they are in the deck.
 */
public class Deck {
    public class DeckEmptyException extends Exception{
        public DeckEmptyException(){
            super();
        }
    }

    //Stack to hold all of cards that are in the deck
    private Stack<card.Card> deck;

    //Constructor function to create the deck and fill with game cards
    public Deck(){

        //create new stack
        deck = new Stack<Card>();

        //push all of the cards onto the stack/deck
        FillDeckWithStandardCards();

        //shuffle the deck
        this.shuffle();
    }

    private void FillDeckWithStandardCards() {
        deck.push(new SicariusCard());
        deck.push(new ArchitectusCard());
        deck.push(new ArchitectusCard());
        deck.push(new ConsiliariusCard());
        deck.push(new ConsiliariusCard());
        deck.push(new LegatCard());
        deck.push(new LegatCard());
        deck.push(new GladiatorCard());
        deck.push(new GladiatorCard());
        deck.push(new MercatorCard());
        deck.push(new ConsulCard());
        deck.push(new ConsulCard());
        deck.push(new LegionariusCard());
        deck.push(new LegionariusCard());
        deck.push(new LegionariusCard());
        deck.push(new NeroCard());
        deck.push(new PraetorianusCard());
        deck.push(new PraetorianusCard());
        deck.push(new ScaenicusCard());
        deck.push(new ScaenicusCard());
        deck.push(new HaruspexCard());
        deck.push(new HaruspexCard());
        deck.push(new SenatorCard());
        deck.push(new SenatorCard());
        deck.push(new VelitesCard());
        deck.push(new VelitesCard());
        deck.push(new EssedumCard());
        deck.push(new EssedumCard());
        deck.push(new TribunusPlebusCard());
        deck.push(new TribunusPlebusCard());
        deck.push(new CenturioCard());
        deck.push(new CenturioCard());
        deck.push(new AesculapinumCard());
        deck.push(new AesculapinumCard());
        deck.push(new BasilicaCard());
        deck.push(new BasilicaCard());
        deck.push(new MachinaCard());
        deck.push(new MachinaCard());
        deck.push(new ForumCard());
        deck.push(new ForumCard());
        deck.push(new ForumCard());
        deck.push(new ForumCard());
        deck.push(new ForumCard());
        deck.push(new ForumCard());
        deck.push(new MercatusCard());
        deck.push(new MercatusCard());
        deck.push(new OnagerCard());
        deck.push(new OnagerCard());
        deck.push(new TempulmCard());
        deck.push(new TempulmCard());
        deck.push(new TurrisCard());
        deck.push(new TurrisCard());
    }

    /*public Deck(Stack<card.Card> deck){
        this.deck = deck;
    } */

    //Returns top card from deck and removes it from deck
    public card.Card drawCard() throws DeckEmptyException{
        if(deck.empty()){
            throw new Deck.DeckEmptyException();
        }
        return deck.pop();
    }

    public card.Card[] drawCard(int count) throws DeckEmptyException{
        card.Card[] cardsDrawn = new card.Card[count];
        int i;
        for (i=0; (i< count) && !deck.empty(); i++){
            cardsDrawn[i] = deck.pop();
        }
        //if(!(i == count)){
        //    throw new DeckEmptyException();
        //}
        return cardsDrawn;
    }

    //Shuffles the deck for the start of the game
    public void shuffle(){
        Collections.shuffle(deck);
    }

    //returns if there are still cards in the deck
    public boolean empty(){
        return deck.empty();
    }

    public Stack<Card> getDeck(){
        return this.deck;
    }
}
