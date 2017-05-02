/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.io.Serializable;

/**
 *
 * @author c
 */
public class Quality implements Serializable
{
	private String name;
	private String note;
	private String positive;

	Quality(String name, String note, String positive)
	{
		this.name = name;
		this.note = note;
		this.positive = positive;
	}

	String prepareForDB()
	{
		return name + ":" + note + ":" + positive;
	}

	public String getName()
	{
		return this.name;
	}

	public String getNote()
	{
		return note;
	}

	public String isPositive()
	{
		return positive;
	}

}
