package Game.field;

import card.Card;
import Game.Disk;

import java.util.Collection;
import java.util.Iterator;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package Game.field
 * <p/>
 * User: ates466
 * Date: 5/15/12
 * Time: 8:39 PM
 */
public class ValueKeySet implements Collection<Card> {
    Collection<Card> delegate;
    Field field;
    public ValueKeySet(Collection<Card> delegate, Field field){
        this.delegate = delegate;
        this.field = field;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<Card> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return delegate.toArray(ts);
    }

    @Override
    public boolean add(Card diskCard) {
        return delegate.add(diskCard);
    }

    @Override
    public boolean remove(Object o) {
        return delegate.remove(o);
    }
    public boolean remove(Object o, boolean toDiscard){
        boolean hasChanged = false;
        for(Disk d: field.keySet()){
            if(field.get(d) == o){
                hasChanged = true;
                field.remove(o,toDiscard);
            }
        }
        return hasChanged;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return delegate.containsAll(objects);
    }

    @Override
    public boolean addAll(Collection<? extends Card> entries) {
        return delegate.addAll(entries);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return delegate.retainAll(objects);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return removeAll(objects,true);
    }
    public boolean removeAll(Collection<?> objects, boolean toDiscard){
        boolean hasChanged = false;

        for(Object o:objects){
            Disk toRemove = null;
            boolean areRemoving = false;
            for(Disk d: field.keySet()){
                if(field.get(d) == o){
                    hasChanged = true;
                    areRemoving = true;
                    toRemove = d;
                }
            }
            if(areRemoving) field.remove(toRemove,toDiscard);
        }
        return hasChanged;
    }

    @Override
    public void clear() {
        delegate.clear();
    }
}
