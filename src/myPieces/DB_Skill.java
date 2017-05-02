package myPieces;

public class DB_Skill
{
	String skill;
	String associatedAttr;

	public DB_Skill(String skill2, String associatedAttr2)
	{
		setSkill(skill2);
		setAssociatedAttr(associatedAttr2);
	}
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	public String getSkill()
	{
		return skill;
	}
	public void setSkill(String skill)
	{
		this.skill = skill;
	}

	public String getAssociatedAttr()
	{
		return associatedAttr;
	}

	public void setAssociatedAttr(String associatedAttr)
	{
		this.associatedAttr = associatedAttr;
	}

}
