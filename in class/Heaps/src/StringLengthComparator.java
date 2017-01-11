/* Example of a Comparator.
 * This one compares strings by length; it considers shorter strings "smaller than"
 *  longer strings.
 */

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String>
{
    @Override
    public int compare(String o1, String o2)
    {
	int l1 = o1.length(), l2 = o2.length();
	
	if (l1 > l2)
	    return 1;
	else if (l1 < l2)
	    return -1;
	else
	    return 0;
    }
}
