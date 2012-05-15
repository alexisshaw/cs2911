package Game.field;

import card.Card;
import Game.Disk;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package Game.field
 * <p/>
 * User: ates466
 * Date: 5/15/12
 * Time: 8:40 PM
 */
public class FieldKeySet implements Set<Disk> {
    Set<Disk> delegate;
    Field field;
    FieldKeySet(Set<Disk> delegate, Field field){
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
    public Iterator<Disk> iterator() {
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
    public boolean add(Disk diskCard) {
        return delegate.add(diskCard);
    }

    @Override
    public boolean remove(Object o) {
        return delegate.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return delegate.containsAll(objects);
    }

    @Override
    public boolean addAll(Collection<? extends Disk> entries) {
        return delegate.addAll(entries);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return delegate.retainAll(objects);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return delegate.removeAll(objects);
    }
    public boolean removeAll(Collection<?> objects, boolean toDiscard){
        boolean hasChanged = false;
        for(Object o:objects){
            Card oldValue = field.remove(((Map.Entry)o).getKey(),toDiscard);
            if(oldValue!=null){
                hasChanged=true;
            }
        }
        return hasChanged;
    }

    @Override
    public void clear() {
        delegate.clear();
    }
}
