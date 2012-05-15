package Game.field;

import card.Card;
import Game.Disk;

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
    
    public Field(Collection<Card> discard){
        this.discard = discard;
    }

    public Field(Field field) {
        super(field);
        this.discard = field.discard;
    }    
    @Override
    public Card remove(Object o) {
        return remove(o,true);
    }
    public Card remove(Object o, boolean toDiscard){
        Card previous = super.remove(o);
        if(toDiscard && previous != null){
            discard.add(previous);            
        }
        return previous;
    }

    @Override
    public ValueKeySet values() {
        return new ValueKeySet(super.values(), this);
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
        if(previous!= null) discard.add(previous);
        return previous;
    }

    @Override
    public void putAll(Map<? extends Disk, ? extends Card> map) {
        for (Map.Entry<? extends Disk, ? extends Card> e:map.entrySet()){
            this.put(e.getKey(),e.getValue());            
        }
    }
}

