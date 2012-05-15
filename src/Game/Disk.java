package Game;

import java.security.PublicKey;
import java.util.*;

/**
 * This File is liscensed under the CC/PD 1.0 liscense. and is in the package Game
 * <p/>
 * User: ates466
 * Date: 5/15/12
 * Time: 3:26 PM
 */
public class Disk {
    Integer deligate;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 7;
    public static final int BRIBE = 7;
    public static final Disk BRIBE_DISK = new Disk(BRIBE);

    public Disk(int i) {
        deligate = i;
    }

    public Disk(java.lang.String s) throws java.lang.NumberFormatException {
        if (s.compareTo("BRIBE") == 0){
            deligate = new Integer(BRIBE);
        }else{
            deligate = new Integer(s);
        }
    }

    public int intValue() { return deligate.intValue();}

    public java.lang.String toString() { return (deligate.intValue() == BRIBE)? "BRIBE":deligate.toString();}

    public int hashCode() { return deligate.hashCode(); }

    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (!(o instanceof Disk)) return false;
        Disk d = (Disk)o;                       
        return deligate.equals(d.deligate);
    }
    public static Set<Disk> diskSet(){
        Set<Disk> returnValue = new HashSet<Disk>(diskList());
        return returnValue;
    }
    public static List<Disk> diskList(){
        List<Disk> returnValue = new ArrayList<Disk>();
        for(int i=MIN_VALUE; i<= MAX_VALUE; i++){
            returnValue.add(new Disk(i));
        }
        return returnValue;        
    }
    public Set<Disk> getBeside(){
        Set<Disk> returnValue = new HashSet<Disk>();
        if(intValue() > MIN_VALUE) returnValue.add(new Disk(intValue() - 1));
        if(intValue() < MAX_VALUE) returnValue.add(new Disk(intValue() + 1));
        return returnValue;
    } 

    public int compareTo(java.lang.Integer integer) { return deligate.compareTo(integer);}
}
