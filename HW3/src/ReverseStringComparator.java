/* Example of a Comparator.
 * This one compares strings alphabetically, but in reverse order.  For example,
 *  the string "cat" is considered "larger than" the string "dog".
 */

import java.util.Comparator;

public class ReverseStringComparator implements Comparator<String>
{
    @Override
    public int compare(String o1, String o2)
    {
	if (o1.compareTo(o2) < 0)
	    return 1;
	else if (o1.compareTo(o2) > 0)
	    return -1;
	else
	    return 0;
    }
}
