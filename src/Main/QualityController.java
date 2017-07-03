package Main;

import myPieces.DB_Quality;

public class QualityController
{

	enum qualitiesWithMultipleTimes
	{
		STRING_ONE("Focused Concentration"), STRING_TWO("High Pain Tolerance"), STRING_THREE("Indomitable"), STRING_FOUR(
				"Magical Resistance"), STRING_FIVE("Will To Live"), STRING_SIX("Gremlins");

		public String text;

		qualitiesWithMultipleTimes(String text)
		{
			this.text = text;
		}
	}

	public static DB_Quality findQuality(String quality, boolean positive)
	{
		if (positive)
		{
			for (DB_Quality q : Shadowrun_Globals.posQualities)
		{
			if (q.getQuality().equals(quality))
			{
				return q;
			}
		}
		}
		else
		{
			for (DB_Quality q : Shadowrun_Globals.negQualities)
			{
				if (q.getQuality().equals(quality))
				{
					return q;
				}
			}
		}

		return null;
	}

	public static int getMaxTimes(String qual)
	{
		switch (qual)
		{
		case "Focused Concentration":
			return 6;
		case "High Pain Tolerance":
			return 3;
		case "Indomitable":
			return 3;
		case "Magical Resistance":
			return 4;
		case "Focus Will to Live":
			return 3;
		case "Gremlins":
			return 4;
		default:
		}

		return -1;
	}
}
