package beatboxClient;

import java.util.Comparator;
import javax.swing.JCheckBox;

/**
 *
 * @author Balázs
 */
public class CheckBoxComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        JCheckBox cb1 = (JCheckBox) o1;
        JCheckBox cb2 = (JCheckBox) o2;
        
        if((cb1.getLocation().getY() - cb2.getLocation().getY()) == 0){
            return (int) (cb1.getLocation().getX() - cb2.getLocation().getX());
        }
        return (int) (cb1.getLocation().getY() - cb2.getLocation().getY());
    }
}
