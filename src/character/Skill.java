/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Skill implements Serializable
{
	String skill;
	Integer rating;

	public String getSkill()
	{
		return skill;
	}

	public void setSkill(String skill)
	{
		this.skill = skill;
	}

	public Integer getRating()
	{
		return rating;
	}

	public void setRating(Integer rating)
	{
		this.rating = rating;
	}

	Skill(String name, Integer rating)
	{
		this.skill = name;
		this.rating = rating;
	}

	String prepareForDB()
	{
		String skillToReturn = this.skill + ":" + this.rating;

		return skillToReturn;
	}

	ArrayList<Skill> loadFromDB(String skillsFromDB)
	{
		ArrayList<Skill> skillsToReturn = new ArrayList<Skill>();
		String skills[];// = new ArrayList();

		skills = skillsFromDB.split(";");

		for (int i = 0; i < skills.length; i++)
		{
			String skillsSplit[];
			skillsSplit = skills[i].split(":");
			Skill skill = new Skill(skillsSplit[0], Integer.parseInt(skillsSplit[1]));
			skillsToReturn.add(skill);
		}
		return skillsToReturn;
	}

}
