package myPieces;

public class DB_Metatype
{
	String name;
	int karmaCost;
	boolean basic;

	public DB_Metatype(String name, int karmaCost, boolean basic)
	{
		this.name = name;
		this.karmaCost = karmaCost;
		this.basic = basic;
	}

	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	public String getType()
	{
		return name;
	}
	public int getKarmaCost()
	{
		return karmaCost;
	}
	public boolean isBasic()
	{
		return basic;
	}

}
