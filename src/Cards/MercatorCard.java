package Cards;

import Game.CardView;

import static java.lang.Math.min;

public class MercatorCard implements Card {
    //returns the name of the card
    public String toString(){
        return "Mercator";
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
        return 7;
    }

    //returns defence value of the card
    public int getDefence(){
        return 2;
    }

    //Returns this cards Card Action
    public CardAction getCardAction(CardView in){
        CardAction returnValue = new CardAction();
        int myMoney = in.getMyPlayerView().getMoney(in.getMyPlayerView().getPlayerId());
        int noVictoryPointsToAdd = 0;
        int[] noVictoryPointsToChange = new int[in.getMyPlayerView().getNoPlayers()];
        for(int i = 0; i < in.getMyPlayerView().getNoPlayers(); i++){
            if (i != in.getMyPlayerView().getPlayerId()){
                int noVictoryPointsAvailable = in.getMyPlayerView().getVictoryPoints(i);
                int internalVP = in.getPlayer().integerInteraction("Please Choose a number of victory points to buy from "
                        +in.getMyPlayerView().getPlayerName(i),
                        min(myMoney/3,noVictoryPointsAvailable),
                        0);
                noVictoryPointsToAdd += internalVP;
                myMoney -= internalVP*3;
                noVictoryPointsToChange[i] = -internalVP;
            }
        }
        noVictoryPointsToChange[in.getMyPlayerView().getPlayerId()] = noVictoryPointsToAdd;
        returnValue.setVictoryPointsChangeArray(noVictoryPointsToChange);
        returnValue.setMoneyToPay(noVictoryPointsToAdd*3);
                                                                                                
        return returnValue ;
    }

    //returns description of card
    public String getCardOracle(){
        return "For 2 sestertii each, the player can buy 1 victory point as long as there are money and victory points left. The opponent gets the money";
    }
}
