/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Main.SkillsController.SKILL_GROUPS;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	SimpleStringProperty	startingKarma	= new SimpleStringProperty();
	SimpleStringProperty	skillKarma		= new SimpleStringProperty();
	SimpleStringProperty	posQualityKarma	= new SimpleStringProperty();
	SimpleStringProperty	negQualityKarma	= new SimpleStringProperty();

	@FXML
	VBox					skillsList;
	@FXML
	VBox					posQualitiesList;
	@FXML
	VBox					negQualitiesList;
	@FXML
	VBox					meleeWeaponsList;
	@FXML
	VBox					rangedWeaponsList;
	@FXML
	VBox					armorList;
	@FXML
	VBox					AugmentationsList;
	@FXML
	VBox					vehiclesList;

	@FXML
	VBox					characterSkillsList;
	@FXML
	VBox					characterPosQualitiesList;
	@FXML
	VBox					characterNegQualitiesList;
	@FXML
	VBox					characterMeleeWeaponsList;
	@FXML
	VBox					characterRangedWeaponsList;
	@FXML
	VBox					characterArmorList;
	@FXML
	VBox					characterAugmentationsList;
	@FXML
	VBox					characterVehiclesList;

	///////////////////
	// Character Tab //
	///////////////////
	@FXML
	TextField				playerName;
	@FXML
	TextField				characterName;
	@FXML
	TextField				characterAlias;
	@FXML
	ComboBox<String>		metatypeBox;

	////////////////
	// Skills Tab //
	////////////////
	@FXML
	ComboBox<String>		sortSkills;

	/////////////////////
	// Things To Track //
	/////////////////////
	@FXML
	Label					karmaLeftOver;
	@FXML
	Label					karmaSpentSkills;
	@FXML
	Label					karmaSpentPosQualities;
	@FXML
	Label					karmaSpentNegQualities;

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
			return characterPosQualitiesList;
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
	public void addSkill(DB_Skill skill)
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
		} else
		{
			hb.setId("odd");
		}
		skillsList.getChildren().add(hb);

	}

	public void addSkillWithRatingBox(DB_Skill skill)
	{
		HBox hb = new HBox();
		hb.setPadding(new Insets(5, 5, 5, 5));
		CheckBox cb = new CheckBox();
		hb.getChildren().add(cb);
		HBox.setMargin(cb, new Insets(0, 5, 0, 5));

		Label lb = new Label(skill.getSkill());
		lb.setTooltip(new Tooltip(skill.getSkill()));
		lb.setAlignment(Pos.CENTER);
		lb.setPrefWidth(280);
		hb.getChildren().add(lb);
		HBox.setMargin(lb, new Insets(0, 5, 0, 5));

		NumberSpinner ns = new NumberSpinner(1, 6);

		ns.getNumberField().textProperty().addListener(new ChangeListener<Object>()
		{
			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2)
			{
				System.out.println(arg1);
				System.out.println(arg2);
				int oldRating = Integer.parseInt((String) arg1);
				int newRating = Integer.parseInt((String) arg2);

				int karmaCost = SkillsController.updateKarma(newRating) - SkillsController.updateKarma(oldRating);

				ThingsToTrack.karmaSpentOnSkills += karmaCost;
				skillKarma.set(Integer.toString(ThingsToTrack.karmaSpentOnSkills));
				ThingsToTrack.overallKarma -= karmaCost;
				startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
			}
		});

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
		} else
		{
			hb.setId("odd");
		}
		characterSkillsList.getChildren().add(hb);
	}

	@FXML
	public void addSkillsToCharacter()
	{
		System.out.println("adding skill...");
		for (int i = 0; i < skillsList.getChildren().size(); i++)
		{
			if (((CheckBox) ((HBox) skillsList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{
				int karmaCost = SkillsController.updateKarma(1);

				ThingsToTrack.karmaSpentOnSkills += karmaCost;
				skillKarma.set(Integer.toString(ThingsToTrack.karmaSpentOnSkills));
				ThingsToTrack.overallKarma -= karmaCost;
				startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));

				DB_Skill skillToAdd = Shadowrun_Globals.skills.get(i);
				addSkillWithRatingBox(skillToAdd);
				((CheckBox) ((HBox) skillsList.getChildren().get(i)).getChildren().get(0)).setSelected(false);
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
				int value = ((NumberSpinner) ((HBox) characterSkillsList.getChildren().get(i)).getChildren().get(2)).getNumber().intValue();

				int karmaCost = SkillsController.updateKarma(value);

				ThingsToTrack.karmaSpentOnSkills -= karmaCost;
				skillKarma.set(Integer.toString(ThingsToTrack.karmaSpentOnSkills));
				ThingsToTrack.overallKarma += karmaCost;
				startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				myCol.add(((HBox) characterSkillsList.getChildren().get(i)));
			}
		}
		characterSkillsList.getChildren().removeAll(myCol);
	}

	@FXML
	public void sortSkills()
	{
		String skillGroup = sortSkills.getValue();
		System.out.println(skillGroup);

		if (skillGroup.equals("ALL"))
		{
			skillsList.getChildren().clear();
			for (DB_Skill s : Shadowrun_Globals.skills)
			{
				addSkill(s);
			}
			return;
		}

		skillsList.getChildren().clear();
		for (DB_Skill s : Shadowrun_Globals.skills)
		{
			if (s.getSKillGroup().equals(skillGroup))
			{
				addSkill(s);
			}
		}
	}

	///////////////
	// Qualities //
	///////////////
	public void addQuality(DB_Quality quality, boolean positive)
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
		if (positive)
		{
			if (Shadowrun_Globals.posQualities.indexOf(quality) % 2 == 0)
			{
				hb.setId("even");
			} else
			{
				hb.setId("odd");
			}
			posQualitiesList.getChildren().add(hb);
		} else
		{
			if (Shadowrun_Globals.negQualities.indexOf(quality) % 2 == 0)
			{
				hb.setId("even");
			} else
			{
				hb.setId("odd");
			}
			negQualitiesList.getChildren().add(hb);
		}
	}

	public void addQualityToCharacter(DB_Quality quality)
	{
		HBox hb = new HBox();
		hb.setPadding(new Insets(5, 5, 5, 5));
		CheckBox cb = new CheckBox();
		hb.getChildren().add(cb);
		HBox.setMargin(cb, new Insets(0, 5, 0, 5));

		Label lb = new Label(quality.getQuality());
		lb.setTooltip(new Tooltip(quality.getNotes()));
		Label cost = new Label(Integer.toString(quality.getKarmaCost()));
		lb.setPrefWidth(280);
		cost.setPrefWidth(40);
		hb.getChildren().addAll(lb, cost);
		HBox.setMargin(lb, new Insets(0, 5, 0, 5));

		for (QualityController.qualitiesWithMultipleTimes q : QualityController.qualitiesWithMultipleTimes.values())
		{
			if (q.text.equals(quality.getQuality()))
			{
				NumberSpinner ns = new NumberSpinner(1, QualityController.getMaxTimes(quality.getQuality()), 100);
				// ns.setWidth(100);
				ns.getNumberField().textProperty().addListener(new ChangeListener<Object>()
				{
					@SuppressWarnings("rawtypes")
					@Override
					public void changed(ObservableValue arg0, Object arg1, Object arg2)
					{
						System.out.println(arg1);
						System.out.println(arg2);
						int oldRating = Integer.parseInt((String) arg1);
						int newRating = Integer.parseInt((String) arg2);

						int karmaCost = newRating * quality.getKarmaCost() - oldRating * quality.getKarmaCost();

						if (quality.isPositive())
						{
							ThingsToTrack.positiveQualitiesKarmaCost += karmaCost;
							posQualityKarma.set(Integer.toString(ThingsToTrack.positiveQualitiesKarmaCost));
							ThingsToTrack.overallKarma -= karmaCost;
							startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
						} else
						{
							ThingsToTrack.negativeQualitiesKarmaCost += karmaCost;
							negQualityKarma.set(Integer.toString(ThingsToTrack.negativeQualitiesKarmaCost));
							ThingsToTrack.overallKarma += karmaCost;
							startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
						}

						if (ThingsToTrack.positiveQualitiesKarmaCost + karmaCost > 25)
						{
							ns.getIncrementButton().setDisable(true);
						} else
						{
							ns.getIncrementButton().setDisable(false);
						}

						if (ThingsToTrack.negativeQualitiesKarmaCost + karmaCost > 25)
						{
							ns.getIncrementButton().setDisable(true);
						} else
						{
							ns.getIncrementButton().setDisable(false);
						}
					}
				});

				if (ThingsToTrack.positiveQualitiesKarmaCost + quality.getKarmaCost() > 25)
				{
					ns.getIncrementButton().setDisable(true);
				}
				if (ThingsToTrack.negativeQualitiesKarmaCost + quality.getKarmaCost() > 25)
				{
					ns.getIncrementButton().setDisable(true);
				}
				hb.getChildren().add(ns);
			}
		}

		hb.setPrefWidth(500);

		if (quality.isPositive())
		{
			if (Shadowrun_Globals.posQualities.indexOf(quality) % 2 == 0)
			{
				hb.setId("even");
			} else
			{
				hb.setId("odd");
			}
			characterPosQualitiesList.getChildren().add(hb);
		} else
		{
			if (Shadowrun_Globals.negQualities.indexOf(quality) % 2 == 0)
			{
				hb.setId("even");
			} else
			{
				hb.setId("odd");
			}
			characterNegQualitiesList.getChildren().add(hb);

		}
	}

	@FXML
	public void addQualitiesToCharacter()
	{
		System.out.println("adding positive quality...");
		for (int i = 0; i < posQualitiesList.getChildren().size(); i++)
		{
			if (((CheckBox) ((HBox) posQualitiesList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{
				DB_Quality qualityToAdd = Shadowrun_Globals.posQualities.get(i);

				int karmaCost = qualityToAdd.getKarmaCost();

				if (ThingsToTrack.positiveQualitiesKarmaCost + karmaCost > 25)
				{
					break;
				}

				if (qualityToAdd.isPositive())
				{
					ThingsToTrack.positiveQualitiesKarmaCost += karmaCost;
					posQualityKarma.set(Integer.toString(ThingsToTrack.positiveQualitiesKarmaCost));
					ThingsToTrack.overallKarma -= karmaCost;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				} else
				{
					ThingsToTrack.negativeQualitiesKarmaCost += karmaCost;
					negQualityKarma.set(Integer.toString(ThingsToTrack.negativeQualitiesKarmaCost));
					ThingsToTrack.overallKarma += karmaCost;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				}

				addQualityToCharacter(qualityToAdd);
				((CheckBox) ((HBox) posQualitiesList.getChildren().get(i)).getChildren().get(0)).setSelected(false);
			}
		}

		System.out.println("adding negative quality...");
		for (int i = 0; i < negQualitiesList.getChildren().size(); i++)
		{
			if (((CheckBox) ((HBox) negQualitiesList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{
				DB_Quality qualityToAdd = Shadowrun_Globals.negQualities.get(i);

				int karmaCost = qualityToAdd.getKarmaCost();

				if (ThingsToTrack.negativeQualitiesKarmaCost + karmaCost > 25)
				{
					break;
				}

				if (qualityToAdd.isPositive())
				{
					ThingsToTrack.positiveQualitiesKarmaCost += karmaCost;
					posQualityKarma.set(Integer.toString(ThingsToTrack.positiveQualitiesKarmaCost));
					ThingsToTrack.overallKarma -= karmaCost;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				} else
				{
					ThingsToTrack.negativeQualitiesKarmaCost += karmaCost;
					negQualityKarma.set(Integer.toString(ThingsToTrack.negativeQualitiesKarmaCost));
					ThingsToTrack.overallKarma += karmaCost;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				}

				addQualityToCharacter(qualityToAdd);
				((CheckBox) ((HBox) negQualitiesList.getChildren().get(i)).getChildren().get(0)).setSelected(false);
			}
		}

	}

	@FXML
	public void removeQualitiesFromCharacter()
	{
		ArrayList<HBox> myCol = new ArrayList<HBox>();
		int size = characterPosQualitiesList.getChildren().size();
		for (int i = 0; i < size; i++)
		{
			if (((CheckBox) ((HBox) characterPosQualitiesList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{

				String quality = ((Label) ((HBox) characterPosQualitiesList.getChildren().get(i)).getChildren().get(1)).getText();
				DB_Quality qualityToRemove = QualityController.findQuality(quality, true);

				if (qualityToRemove == null)
					return;
				int karmaCost = qualityToRemove.getKarmaCost();
				int times = 1;

				for (QualityController.qualitiesWithMultipleTimes q : QualityController.qualitiesWithMultipleTimes.values())
				{
					if (q.text.equals(qualityToRemove.getQuality()))
					{
						times = ((NumberSpinner) ((HBox) characterPosQualitiesList.getChildren().get(i)).getChildren().get(3)).getNumber()
								.intValue();
					}
				}

				if (qualityToRemove.isPositive())
				{
					ThingsToTrack.positiveQualitiesKarmaCost -= karmaCost * times;
					posQualityKarma.set(Integer.toString(ThingsToTrack.positiveQualitiesKarmaCost));
					ThingsToTrack.overallKarma += karmaCost * times;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				} else
				{
					ThingsToTrack.negativeQualitiesKarmaCost -= karmaCost * times;
					negQualityKarma.set(Integer.toString(ThingsToTrack.negativeQualitiesKarmaCost));
					ThingsToTrack.overallKarma -= karmaCost * times;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				}
				myCol.add(((HBox) characterPosQualitiesList.getChildren().get(i)));
			}
		}
		characterPosQualitiesList.getChildren().removeAll(myCol);

		size = characterNegQualitiesList.getChildren().size();
		for (int i = 0; i < size; i++)
		{
			if (((CheckBox) ((HBox) characterNegQualitiesList.getChildren().get(i)).getChildren().get(0)).isSelected())
			{

				String quality = ((Label) ((HBox) characterNegQualitiesList.getChildren().get(i)).getChildren().get(1)).getText();
				DB_Quality qualityToRemove = QualityController.findQuality(quality, false);

				if (qualityToRemove == null)
					return;
				int karmaCost = qualityToRemove.getKarmaCost();
				int times = 1;

				for (QualityController.qualitiesWithMultipleTimes q : QualityController.qualitiesWithMultipleTimes.values())
				{
					if (q.text.equals(qualityToRemove.getQuality()))
					{
						times = ((NumberSpinner) ((HBox) characterNegQualitiesList.getChildren().get(i)).getChildren().get(3)).getNumber()
								.intValue();
					}
				}

				if (qualityToRemove.isPositive())
				{
					ThingsToTrack.positiveQualitiesKarmaCost -= karmaCost * times;
					posQualityKarma.set(Integer.toString(ThingsToTrack.positiveQualitiesKarmaCost));
					ThingsToTrack.overallKarma += karmaCost * times;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				} else
				{
					ThingsToTrack.negativeQualitiesKarmaCost -= karmaCost * times;
					negQualityKarma.set(Integer.toString(ThingsToTrack.negativeQualitiesKarmaCost));
					ThingsToTrack.overallKarma -= karmaCost * times;
					startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
				}
				myCol.add(((HBox) characterNegQualitiesList.getChildren().get(i)));
			}
		}
		characterNegQualitiesList.getChildren().removeAll(myCol);
	}

	//////////////
	// Next one //
	//////////////

	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		startingKarma.set(Integer.toString(ThingsToTrack.overallKarma));
		skillKarma.set(Integer.toString(ThingsToTrack.karmaSpentOnSkills));
		posQualityKarma.set(Integer.toString(ThingsToTrack.positiveQualitiesKarmaCost));
		negQualityKarma.set(Integer.toString(ThingsToTrack.negativeQualitiesKarmaCost));

		for (SKILL_GROUPS sg : SkillsController.SKILL_GROUPS.values())
		{
			sortSkills.getItems().add(sg.toString());
		}
		
		sortSkills.setValue("ALL");

		for (DB_Metatype m : Shadowrun_Globals.metatypes)
		{
			metatypeBox.getItems().add(m.getType() + " " + m.getKarmaCost());
		}
		metatypeBox.setPromptText("Select Metatype");
		metatypeBox.valueProperty().addListener(new ChangeListener<String>()
		{
			@SuppressWarnings("rawtypes")
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

		for (DB_Quality q : Shadowrun_Globals.posQualities)
		{
			addQuality(q, true);
		}

		for (DB_Quality q : Shadowrun_Globals.negQualities)
		{
			addQuality(q, false);
		}

		karmaLeftOver.textProperty().bind(startingKarma);
		karmaSpentSkills.textProperty().bind(skillKarma);
		karmaSpentPosQualities.textProperty().bind(posQualityKarma);
		karmaSpentNegQualities.textProperty().bind(negQualityKarma);
	}

}
