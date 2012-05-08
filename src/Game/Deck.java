package Game;

import Cards.Card;

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
    private Stack<Cards.Card> deck;

    //Constructor function to create the deck and fill with game cards
    public Deck(){

        //create new stack
        deck = new Stack<Card>();

        //push all of the cards onto the stack/deck
        for (int i = 0; i < 5; i++) {
            FillDeckWithStandardCards();
        }

        //shuffle the deck
        this.shuffle();
    }

    private void FillDeckWithStandardCards() {
        deck.push(new Cards.SicariusCard());
        deck.push(new Cards.ArchitectusCard());
        deck.push(new Cards.ArchitectusCard());
        deck.push(new Cards.ConsiliariusCard());
        deck.push(new Cards.ConsiliariusCard());
        deck.push(new Cards.LegatCard());
        deck.push(new Cards.LegatCard());
        deck.push(new Cards.GladiatorCard());
        deck.push(new Cards.GladiatorCard());
        deck.push(new Cards.MercatorCard());
        deck.push(new Cards.TribunusPlebusCard());
        deck.push(new Cards.TribunusPlebusCard());
    }

    /*public Deck(Stack<Cards.Card> deck){
        this.deck = deck;
    } */

    //Returns top card from deck and removes it from deck
    public Cards.Card drawCard() throws DeckEmptyException{
        if(deck.empty()){
            throw new Deck.DeckEmptyException();
        }
        return deck.pop();
    }

    public Cards.Card[] drawCard(int count) throws DeckEmptyException{
        Cards.Card[] cardsDrawn = new Cards.Card[count];
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
