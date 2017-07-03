package Main;

public class SkillsController
{
	enum SKILL_GROUPS
	{
		ALL, NONE, ACTING, ATHLETICS, BIOTECH, CLOSE_COMBAT, CONJURING, CRACKING, ELECTRONICS, ENCHANTING, FIREARMS, INFLUENCE, ENGINEERING, OUTDOORS, SORCERY, STEALTH, TASKING;
	}

	public static int updateKarma(int rating)
	{
		switch (rating)
		{
		case 1:
			return 2;
		case 2:
			return 6;
		case 3:
			return 12;
		case 4:
			return 20;
		case 5:
			return 30;
		case 6:
			return 42;
		default:

		}

		return -1;
	}

}
