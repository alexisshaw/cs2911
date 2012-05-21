package Game.field;

import Game.DiscardView;
import card.Card;
import Game.Disk;
import card.CardAction;
import card.DiscardActor;

import java.util.*;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package Game
 * <p/>
 * User: ates466
 * Date: 5/15/12
 * Time: 4:31 PM
 */
public class Field extends HashMap<Disk, Card> {
    private Collection<Card> discard;
    private Collection<DiscardActor> discardActors;
    private DiscardActivator discardActivator;
    public interface DiscardActivator{
        public void applyAction(CardAction c, Card card);
        public DiscardView getDiscardView(Card responsible, Card toDiscard,DiscardView.DiscardManor manor, Disk location);
    }

    public Field(List<Card> discard, Collection<DiscardActor> discardActors, DiscardActivator discardActivator){
        this.discard = discard;
        this.discardActors = discardActors;
        this.discardActivator = discardActivator;
    }

    public Field(Field field) {
        super(field);
        this.discard = field.discard;
        this.discardActors = field.discardActors;
        this.discardActivator = field.discardActivator;
    }
    @Override
    public Card remove(Object o){
        return remove(o,true);
    }
    public Card remove(Object o, boolean toDiscard){
        Card previous = super.remove(o);
        if(toDiscard && previous != null){
            discard.add(previous);
            activateAllDiscardActivator(previous, DiscardView.DiscardManor.NORMAL, (Disk)o);
        }
        return previous;
    }
    private void activateAllDiscardActivator(Card previous, DiscardView.DiscardManor manor,Disk location){
        for(DiscardActor a:discardActors){
            discardActivator.applyAction(a.getAction(discardActivator.getDiscardView(a.getCard(), previous, manor, location)), a.getCard());
        }
    }

    @Override
    public ValueKeySet values() {
        ValueKeySet toreturn =  new ValueKeySet(super.values(), this);
        return toreturn;
    }


    @Override
    public FieldKeySet keySet() {
        return new FieldKeySet(super.keySet(),this);
    }

    @Override
    public FieldEntrySet entrySet() {
        return new FieldEntrySet(super.entrySet(), this);
    }


    @Override
    public Object clone() {
        return new Field(this);
    }

    @Override
    public Card put(Disk disk, Card card) {
        Card previous = super.put(disk, card);
        if(previous!= null){
            discard.add(previous);
            activateAllDiscardActivator(previous, DiscardView.DiscardManor.COVER, disk);
        }
        return previous;
    }

    @Override
    public void putAll(Map<? extends Disk, ? extends Card> map) {
        for (Map.Entry<? extends Disk, ? extends Card> e:map.entrySet()){
            this.put(e.getKey(),e.getValue());
        }
    }
}

