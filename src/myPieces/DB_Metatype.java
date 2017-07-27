package myPieces;

public class DB_Metatype
{
	String	name;
	int		karmaCost;
	boolean	basic;

	int		baseBody, baseAgility, baseReaction, baseStrength, baseWillpower, baseLogic, baseIntuition, baseCharisma, baseEdge;
	String	racialTraits;

	int		endBody, endAgility, endReaction, endStrength, endWillpower, endLogic, endIntuition, endCharisma, endEdge;

	public DB_Metatype(String name, int karmaCost, boolean basic)
	{
		this.name = name;
		this.karmaCost = karmaCost;
		this.basic = basic;
	}

	public DB_Metatype(String name, int karmaCost, boolean basic, int baseBody, int baseAgility, int baseReaction, int baseStrength,
			int baseWillpower, int baseLogic, int baseIntuition, int baseCharisma, int baseEdge, String racialTraits, int endBody,
			int endAgility, int endReaction, int endStrength, int endWillpower, int endLogic, int endIntuition, int endCharisma,
			int endEdge)
	{
		this.name = name;
		this.karmaCost = karmaCost;
		this.basic = basic;
		this.baseBody = baseBody;
		this.baseAgility = baseAgility;
		this.baseReaction = baseReaction;
		this.baseStrength = baseStrength;
		this.baseWillpower = baseWillpower;
		this.baseLogic = baseLogic;
		this.baseIntuition = baseIntuition;
		this.baseCharisma = baseCharisma;
		this.baseEdge = baseEdge;
		this.racialTraits = racialTraits;
		this.endBody = endBody;
		this.endAgility = endAgility;
		this.endReaction = endReaction;
		this.endStrength = endStrength;
		this.endWillpower = endWillpower;
		this.endLogic = endLogic;
		this.endIntuition = endIntuition;
		this.endCharisma = endCharisma;
		this.endEdge = endEdge;
	}

	public void display()
	{
		System.out.println(getType() + " | " + getKarmaCost() + " | " + isBasic() + " | " + getBaseBody() + " | " + getBaseAgility() + " | "
				+ getBaseReaction() + " | " + getBaseStrength() + " | " + getBaseWillpower() + " | " + getBaseLogic() + " | "
				+ getBaseIntuition() + " | " + getBaseCharisma() + " | " + getBaseEdge());
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getBaseBody()
	{
		return baseBody;
	}

	public void setBaseBody(int baseBody)
	{
		this.baseBody = baseBody;
	}

	public int getBaseAgility()
	{
		return baseAgility;
	}

	public void setBaseAgility(int baseAgility)
	{
		this.baseAgility = baseAgility;
	}

	public int getBaseReaction()
	{
		return baseReaction;
	}

	public void setBaseReaction(int baseReaction)
	{
		this.baseReaction = baseReaction;
	}

	public int getBaseStrength()
	{
		return baseStrength;
	}

	public void setBaseStrength(int baseStrength)
	{
		this.baseStrength = baseStrength;
	}

	public int getBaseWillpower()
	{
		return baseWillpower;
	}

	public void setBaseWillpower(int baseWillpower)
	{
		this.baseWillpower = baseWillpower;
	}

	public int getBaseLogic()
	{
		return baseLogic;
	}

	public void setBaseLogic(int baseLogic)
	{
		this.baseLogic = baseLogic;
	}

	public int getBaseIntuition()
	{
		return baseIntuition;
	}

	public void setBaseIntuition(int baseIntuition)
	{
		this.baseIntuition = baseIntuition;
	}

	public int getBaseCharisma()
	{
		return baseCharisma;
	}

	public void setBaseCharisma(int baseCharisma)
	{
		this.baseCharisma = baseCharisma;
	}

	public int getBaseEdge()
	{
		return baseEdge;
	}

	public void setBaseEdge(int baseEdge)
	{
		this.baseEdge = baseEdge;
	}

	public String getRacialTraits()
	{
		return racialTraits;
	}

	public void setRacialTraits(String racialTraits)
	{
		this.racialTraits = racialTraits;
	}

	public int getEndBody()
	{
		return endBody;
	}

	public void setEndBody(int endBody)
	{
		this.endBody = endBody;
	}

	public int getEndAgility()
	{
		return endAgility;
	}

	public void setEndAgility(int endAgility)
	{
		this.endAgility = endAgility;
	}

	public int getEndReaction()
	{
		return endReaction;
	}

	public void setEndReaction(int endReaction)
	{
		this.endReaction = endReaction;
	}

	public int getEndStrength()
	{
		return endStrength;
	}

	public void setEndStrength(int endStrength)
	{
		this.endStrength = endStrength;
	}

	public int getEndWillpower()
	{
		return endWillpower;
	}

	public void setEndWillpower(int endWillpower)
	{
		this.endWillpower = endWillpower;
	}

	public int getEndLogic()
	{
		return endLogic;
	}

	public void setEndLogic(int endLogic)
	{
		this.endLogic = endLogic;
	}

	public int getEndIntuition()
	{
		return endIntuition;
	}

	public void setEndIntuition(int endIntuition)
	{
		this.endIntuition = endIntuition;
	}

	public int getEndCharisma()
	{
		return endCharisma;
	}

	public void setEndCharisma(int endCharisma)
	{
		this.endCharisma = endCharisma;
	}

	public int getEndEdge()
	{
		return endEdge;
	}

	public void setEndEdge(int endEdge)
	{
		this.endEdge = endEdge;
	}

	public void setKarmaCost(int karmaCost)
	{
		this.karmaCost = karmaCost;
	}

	public void setBasic(boolean basic)
	{
		this.basic = basic;
	}

}
