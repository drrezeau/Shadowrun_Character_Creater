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
public class Ammo implements Serializable
{
	String type;
	Integer ammount;

	public String getType()
	{
		return type;
	}

	public Integer getAmmount()
	{
		return ammount;
	}

}
