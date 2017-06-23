/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import myPieces.DB_Metatype;
import myPieces.DB_Quality;
import myPieces.DB_Skill;
import myPieces.NumberSpinner;

/**
 *
 * @author david_000
 */
public class MainMenuController implements Initializable
{
	SimpleStringProperty startingKarma = new SimpleStringProperty();

	@FXML
	VBox skillsList;
	@FXML
	VBox qualitiesList;
	@FXML
	VBox meleeWeaponsList;
	@FXML
	VBox rangedWeaponsList;
	@FXML
	VBox armorList;
	@FXML
	VBox AugmentationsList;
	@FXML
	VBox vehiclesList;

	@FXML
	VBox characterSkillsList;
	@FXML
	VBox characterQualitiesList;
	@FXML
	VBox characterMeleeWeaponsList;
	@FXML
	VBox characterRangedWeaponsList;
	@FXML
	VBox characterArmorList;
	@FXML
	VBox characterAugmentationsList;
	@FXML
	VBox characterVehiclesList;

	///////////////////
	// Character Tab //
	///////////////////
	@FXML
	TextField playerName;
	@FXML
	TextField characterName;
	@FXML
	TextField characterAlias;
	@FXML
	ComboBox<String> metatypeBox;

	@FXML
	Label karmaLeftOver;

	/////////////////
	// Used By All //
	/////////////////
	public VBox getList(String tabId)
	{
		switch (tabId)
		{
		case "Skills":
			return characterSkillsList;
		case "Qualities":
			return characterQualitiesList;
		}
		return null;
	}

	private void selectAll(VBox thingsToSelect)
	{
		for (int i = 0; i < thingsToSelect.getChildren().size(); i++)
		{
			((CheckBox) ((HBox) thingsToSelect.getChildren().get(i)).getChildren().get(0)).setSelected(true);
		}
	}

	private void deselectAll(VBox thingsToDeselect)
	{
		for (int i = 0; i < thingsToDeselect.getChildren().size(); i++)
		{
			((CheckBox) ((HBox) thingsToDeselect.getChildren().get(i)).getChildren().get(0)).setSelected(false);
		}
	}

	public void SelectAll()
	{
		VBox vb = getList(ShadowrunCharacterCreater.getWhichTab());
		selectAll(vb);
	}

	public void deselectAll()
	{
		VBox vb = getList(ShadowrunCharacterCreater.getWhichTab());
		deselectAll(vb);
	}

	////////////
	// Skills //
	////////////
	private void addSkill(DB_Skill skill)
	{
		HBox hb = new HBox();
		hb.setPadding(new Insets(5, 5, 5, 5));
		CheckBox cb = new CheckBox();
		hb.getChildren().add(cb);
		HBox.setMargin(cb, new Insets(0, 5, 0, 5));

		Label lb = new Label(skill.getSkill());
		lb.setAlignment(Pos.CENTER);
		hb.getChildren().add(lb);
		HBox.setMargin(lb, new Insets(0, 5, 0, 5));

		hb.setPrefWidth(484);
		if (Shadowrun_Globals.skills.indexOf(skill) % 2 == 0)
		{
			hb.setId("even");
		}
		else
		{
			hb.setId("odd");
		}
		skillsList.getChildren().add(hb);

	}

	private void addSkillWithRatingBox(String skill)
	{
		HBox hb = new HBox();
		hb.setPadding(new Insets(5, 5, 5, 5));
		CheckBox cb = new CheckBox();
		hb.getChildren().add(cb);
		HBox.setMargin(cb, new Insets(0, 5, 0, 5));

		Label lb = new Label(skill);
		lb.setTooltip(new Tooltip(skill));
		lb.setAlignment(Pos.CENTER);
		lb.setPrefWidth(280);
		hb.getChildren().add(lb);
		HBox.setMargin(lb, new Insets(0, 5, 0, 5));

		NumberSpinner ns = new NumberSpinner(1, 6);
		hb.getChildren().add(ns);
		HBox.setMargin(cb, new Insets(0, 5, 0, 5));

		hb.setPrefWidth(504);
		if (characterSkillsList.getChildren().size() >= 16)
		{
			characterSkillsList.setPrefWidth(508);
		}
		if (characterSkillsList.getChildren().size() % 2 == 0)
		{
			hb.setId("even");
		}
		else
		{
			hb.setId("odd");
		}
		characterSkillsList.getChildren().add(hb);
	}

	@FXML
	public void addSkillsToCharacter()
	{
		for (int i = 0; i < skillsList.getChildren().size(); i++)
		{
			if (((CheckBox) ((HBox) skillsList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{
				addSkillWithRatingBox(((Label) ((HBox) skillsList.getChildren().get(i)).getChildren().get(1)).getText());
			}
		}
	}

	@FXML
	public void removeSkillsFromCharacter()
	{
		ArrayList<HBox> myCol = new ArrayList<HBox>();
		int size = characterSkillsList.getChildren().size();
		for (int i = 0; i < size; i++)
		{
			if (((CheckBox) ((HBox) characterSkillsList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{
				myCol.add(((HBox) characterSkillsList.getChildren().get(i)));
			}
		}
		characterSkillsList.getChildren().removeAll(myCol);
	}

	///////////////
	// Qualities //
	///////////////
	private void addQuality(DB_Quality quality)
	{
		HBox hb = new HBox();
		hb.setPadding(new Insets(5, 5, 5, 5));
		CheckBox cb = new CheckBox();
		hb.getChildren().add(cb);
		HBox.setMargin(cb, new Insets(0, 5, 0, 5));

		Label lb = new Label(quality.getQuality());
		lb.setTooltip(new Tooltip(quality.getNotes()));
		Label cost = new Label(Integer.toString(quality.getKarmaCost()));
		lb.setPrefWidth(400);
		hb.getChildren().addAll(lb, cost);
		HBox.setMargin(lb, new Insets(0, 5, 0, 5));

		hb.setPrefWidth(500);
		if (Shadowrun_Globals.qualities.indexOf(quality) % 2 == 0)
		{
			hb.setId("even");
		}
		else
		{
			hb.setId("odd");
		}
		qualitiesList.getChildren().add(hb);
	}

	//////////////
	// Next one //
	//////////////

	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));

		for (DB_Metatype m : Shadowrun_Globals.metatypes)
		{
			metatypeBox.getItems().add(m.getType() + " " + m.getKarmaCost());
		}
		metatypeBox.setPromptText("Select Metatype");
		metatypeBox.valueProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue ov, String t, String t1)
			{
				int spot = metatypeBox.getItems().indexOf(t1);
				ThingsToTrack.overallKarma = ThingsToTrack.overallKarma + ThingsToTrack.metatypeKarmaCost;
				ThingsToTrack.metatypeKarmaCost = Shadowrun_Globals.metatypes.get(spot).getKarmaCost();
				ThingsToTrack.overallKarma = ThingsToTrack.overallKarma - ThingsToTrack.metatypeKarmaCost;
				startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
			}
		});

		for (DB_Skill s : Shadowrun_Globals.skills)
		{
			System.out.println(s.getSkill() + " " + s.getAssociatedAttr());
			addSkill(s);
		}

		for (DB_Quality q : Shadowrun_Globals.qualities)
		{
			System.out.println(q.display());
			addQuality(q);
		}

		karmaLeftOver.textProperty().bind(startingKarma);
	}

}
