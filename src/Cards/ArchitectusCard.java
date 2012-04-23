package Cards;

import Game.CardView;
import Game.Die;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class ArchitectusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Architectus";
    }

    //returns if the card is a building or not
    public boolean isBuilding(){
        return false;
    }

    //returns the amount of dice required to activate card
    public int getNumberOfDiceRequired(){
        return 1;
    }

    //returns dollar value of placing card
    public int getPrice(){
        return 3;
    }

    //returns defence value of the card
    public int getDefence(){
        return 4;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView input){
        Card[] location = new Card[Die.getMaxDiceValue()];
        Vector<Card> currentHand = new Vector<Card>(input.getMyPlayerView().getHand());
        while(!currentHand.isEmpty() && input.getPlayer().conditionalInteraction("Do You Wish to lay a Card (Y/N)", "Y", "N", new Scanner(System.in))){
            Card[] chosenCard = input.getPlayer().cardChooser("Please Choose a card to place on the field",
                    "You cannot place a card",
                    1,
                    currentHand);
            currentHand.removeAll(Arrays.asList(chosenCard));
            Card[] tempLocation = input.getPlayer().cardPlacer(Arrays.asList(chosenCard),
                    "","Please choose where you wish to place this card\n");
            for(int i=0; i<location.length ; i++){
                if(tempLocation[i] != null){
                    location[i] = tempLocation[i];
                }
            }
        }
        CardAction myAction = new CardAction();
        myAction.setLayCards(location);
        return myAction;
    }

    //returns description of card
    public String getCardOracle(){
        return "enables the player to lay as many building cards as they wish free of charge. The player is allowed to cover any cards";
    }
}
