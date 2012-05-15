package card.Character;

import card.Card;
import card.CardAction;
import Game.CardView;
import Game.Die;
import Game.Disk;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class LegionariusCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Legionarius";
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
        return 4;
    }

    //returns defence value of the card
    public int getDefence(){
        return 5;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView input){
        Disk myIndex = input.getCardKey(this);
        Collection<Card> toAttackCollection = new HashSet<Card>();
        for(int i=0 ; i< input.getMyPlayerView().getNoPlayers();i++){
            if(input.getMyPlayerView().getField(i).containsKey(myIndex)){
                toAttackCollection.add(input.getMyPlayerView().getField(i).get(myIndex));
            }
        }
        Die BattleDie = new Die(new Random());
        Collection<Card> toAttackSet = input.getPlayer().cardChooser(
                "Please Choose one of these cards to attack",
                "You cannot attack a card",
                1,
                toAttackCollection);
        CardAction returnValue = new CardAction();
        if(!toAttackSet.isEmpty()) {
            Card toAttack = toAttackSet.iterator().next();
            if(toAttack.getDefence() <= BattleDie.getDieValue()){
                Set<Card> killedSet = new HashSet<Card>();
                killedSet.add(toAttack);
                returnValue.setDestroyCards(killedSet);
            }
        }
        return returnValue;
    }

    //returns description of card
    public String getCardOracle(){
        return "attacks the opponent's card which is directly opposite, whether it is a character or a building card.";
    }
}
