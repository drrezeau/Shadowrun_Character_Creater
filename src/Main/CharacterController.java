package Main;

import java.math.BigDecimal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import myPieces.DB_Metatype;
import myPieces.NumberSpinner;

public class CharacterController
{
	static boolean doNotChangeKarma = false;
	
	public static boolean checkForMagic(String name)
	{
		switch (name)
		{
		case "Centaur":
		case "Naga":
		case "Pixie":
		case "Sasquatch":
			return true;
		default:
			return false;
		}
	}

	public static void setAttributeValues(String attribute)
	{
		doNotChangeKarma = true;
		
		for (DB_Metatype meta : Shadowrun_Globals.metatypes)
		{
			if (meta.getType().equals(attribute))
			{
				MainMenuController.bodyRating.setMinMax(meta.getBaseBody(), meta.getEndBody());
				MainMenuController.agilityRating.setMinMax(meta.getBaseAgility(), meta.getEndAgility());
				MainMenuController.reactionRating.setMinMax(meta.getBaseReaction(), meta.getEndReaction());
				MainMenuController.strengthRating.setMinMax(meta.getBaseStrength(), meta.getEndStrength());
				MainMenuController.willpowerRating.setMinMax(meta.getBaseWillpower(), meta.getEndWillpower());
				MainMenuController.logicRating.setMinMax(meta.getBaseLogic(), meta.getEndLogic());
				MainMenuController.intuitionRating.setMinMax(meta.getBaseIntuition(), meta.getEndIntuition());
				MainMenuController.charismaRating.setMinMax(meta.getBaseCharisma(), meta.getEndCharisma());
				MainMenuController.edgeRating.setMinMax(meta.getBaseEdge(), meta.getEndEdge());

				MainMenuController.bodyRating.setNumber(new BigDecimal(meta.getBaseBody()));
				MainMenuController.agilityRating.setNumber(new BigDecimal(meta.getBaseAgility()));
				MainMenuController.reactionRating.setNumber(new BigDecimal(meta.getBaseReaction()));
				MainMenuController.strengthRating.setNumber(new BigDecimal(meta.getBaseStrength()));
				MainMenuController.willpowerRating.setNumber(new BigDecimal(meta.getBaseWillpower()));
				MainMenuController.logicRating.setNumber(new BigDecimal(meta.getBaseLogic()));
				MainMenuController.intuitionRating.setNumber(new BigDecimal(meta.getBaseIntuition()));
				MainMenuController.charismaRating.setNumber(new BigDecimal(meta.getBaseCharisma()));
				MainMenuController.edgeRating.setNumber(new BigDecimal(meta.getBaseEdge()));

				MainMenuController.maxBodyLabel.setText("/" + meta.getEndBody());
				MainMenuController.maxAgilLabel.setText("/" + meta.getEndAgility());
				MainMenuController.maxReaLabel.setText("/" + meta.getEndReaction());
				MainMenuController.maxStrLabel.setText("/" + meta.getEndStrength());
				MainMenuController.maxWillLabel.setText("/" + meta.getEndWillpower());
				MainMenuController.maxLogLabel.setText("/" + meta.getEndLogic());
				MainMenuController.maxIntLabel.setText("/" + meta.getEndIntuition());
				MainMenuController.maxCharLabel.setText("/" + meta.getEndCharisma());
				MainMenuController.maxEdgeLabel.setText("/" + meta.getEndEdge());
				if (checkForMagic(meta.getType()))
				{
					MainMenuController.magicRating.setDisable(false);
					MainMenuController.magicRating.setMinMax(1, 6);
					MainMenuController.edgeRating.setNumber(new BigDecimal(1));
					MainMenuController.maxMagicLabel.setText("/6");
				} else
				{
					MainMenuController.magicRating.setDisable(true);
				}
			}
		}
		doNotChangeKarma = false;

	}

	public static void karmaCost()
	{
		int currentRatingCost = getAttributeCost(1);
		int newRatingCost = getAttributeCost(2);

		int totalCost = newRatingCost - currentRatingCost;
		
		ThingsToTrack.overallKarma = ThingsToTrack.overallKarma - totalCost;
		
//		return totalCost;
	}

	// The cost of going from 1 to passed in rating
	public static int getAttributeCost(int rating)
	{
		switch (rating)
		{
		case 2:
			return 10;
		case 3:
			return 25;
		case 4:
			return 45;
		case 5:
			return 70;
		case 6:
			return 100;
		case 7:
			return 135;
		case 8:
			return 175;
		case 9:
			return 225;
		case 10:
			return 275;
		case 11:
			return 330;
		default:
			return 0;
		}
	}
}
