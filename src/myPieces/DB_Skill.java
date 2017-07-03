package myPieces;

public class DB_Skill
{
	String skill;
	String associatedAttr;
	String skillGroup;

	public DB_Skill(String skill2, String associatedAttr2, String skillGroup)
	{
		setSkill(skill2);
		setAssociatedAttr(associatedAttr2);
		this.skillGroup = skillGroup;
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

	public String getSKillGroup()
	{
		return skillGroup;
	}
	public void setSkillGroup(String sg)
	{
		skillGroup = sg;
	}
}
