package myPieces;

public class DB_Quality
{
	String quality;
	int karmaCost;
	String notes;
	boolean positive;
	
	public DB_Quality(String q, int kc, String n, boolean p)
	{
		quality = q;
		karmaCost = kc;
		notes = n;
		positive = p;
	}
	
	public String display()
	{
		return "Quality: " + quality + " | Karma Cost: " + karmaCost;
	}
	
	public String getQuality()
	{
		return quality;
	}
	public int getKarmaCost()
	{
		return karmaCost;
	}
	public String getNotes()
	{
		return notes;
	}
	public boolean isPositive()
	{
		return positive;
	}
}
