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
public class FieldEntrySet implements Set<Map.Entry<Disk, Card>> {
    Set<Map.Entry<Disk, Card>> deligate;
    Field field;
    FieldEntrySet(Set<Map.Entry<Disk, Card>> deligate, Field field){
        this.deligate = deligate;
        this.field = field;
    }

    @Override
    public int size() {
        return deligate.size();
    }

    @Override
    public boolean isEmpty() {
        return deligate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return deligate.contains(o);
    }

    @Override
    public Iterator<Map.Entry<Disk, Card>> iterator() {
        return deligate.iterator();
    }

    @Override
    public Object[] toArray() {
        return deligate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return deligate.toArray(ts);
    }

    @Override
    public boolean add(Map.Entry<Disk, Card> diskCardEntry) {
        return deligate.add(diskCardEntry);
    }

    @Override
    public boolean remove(Object o) {
        return deligate.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return deligate.containsAll(objects);
    }

    @Override
    public boolean addAll(Collection<? extends Map.Entry<Disk, Card>> entries) {
        return deligate.addAll(entries);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return deligate.retainAll(objects);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return deligate.removeAll(objects);
    }
    public boolean removeAll(Collection<?> objects, boolean toDiscard){
        boolean hasChanged = false;
        for(Object o:objects){
            if(o.getClass().isAssignableFrom(Map.Entry.class) && deligate.contains(o)){
                field.remove(((Map.Entry)o).getKey(),toDiscard);
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    @Override
    public void clear() {
        deligate.clear();
    }
}
