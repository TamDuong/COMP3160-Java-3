/* Class representing a sloth.  Because sloths.
 */
public class Sloth
{
    private int numToes;	// two-toed?  three-toed?
    private double armorWeight;	// apparently sloths wear armor...
    
    public int getNumToes()
    {
        return numToes;
    }

    public void setNumToes(int numToes)
    {
        this.numToes = numToes;
    }

    public double getArmorWeight()
    {
        return armorWeight;
    }

    public void setArmorWeight(double armorWeight)
    {
        this.armorWeight = armorWeight;
    }

    // Constructor
    public Sloth(int numToes, double armorWeight)
    {
	this.numToes = numToes;
	this.armorWeight = armorWeight;
    }

    public String toString()
    {
	return "Sloth (toes = " + numToes + ", armorWeight = " + armorWeight + ")";
    }
}
