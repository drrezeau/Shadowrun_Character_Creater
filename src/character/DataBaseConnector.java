package character;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Label;

public class DataBaseConnector
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static final String DB_URL = "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/heroku_77540a9be8a2f22"; // **HEROKU
//	 static final String DB_URL = "jdbc:mysql://localhost/sys"; // **Local

	// Database credentials
	static final String USER = "b120a816248bc1"; // ** Heroku
	// static final String USER = "root"; // **Local
	static final String PASS = "4821acef"; // ** Heroku
	// static final String PASS = "QWERTY"; // **Local

	public static boolean connectionStatus = true;

	public static boolean saveToDB(Character character)
	{
		String licensesToSave = "";
		String skillsToSave = "";
		String qualitiesToSave = "";
		String contactsToSave = "";
		String meleeWeaponsToSave = "";
		String armorToSave = "";
		String rangedWeaponsToSave = "";
		String gearToSave = "";
		String deckToSave = "";
		String spellsToSave = "";
		String augmentationsToSave = "";
		String adeptPowersToSave = "";
		String vehiclesToSave = "";
		int hasMagicInt = 0;

		if (character.isHasMagic())
		{
			hasMagicInt = 1;
		}

		// Create the string to save for skills
		for (int i = 0; i < character.getLicenses().size(); i++)
		{
			licensesToSave += character.getLicenses().get(i).prepareForDB() + ";";
		}
		for (int i = 0; i < character.getSkills().size(); i++)
		{
			skillsToSave += character.getSkills().get(i).prepareForDB() + ";";
		}
		// Create the string to save for qualities
		for (int i = 0; i < character.getQualities().size(); i++)
		{
			qualitiesToSave += character.getQualities().get(i).prepareForDB() + ";";
		}
		// Create the string to save for contacts
		for (int i = 0; i < character.getContacts().size(); i++)
		{
			contactsToSave += character.getContacts().get(i).prepareForDB() + ";";
		}
		// Create the string to save for armor
		for (int i = 0; i < character.getArmor().size(); i++)
		{
			armorToSave += character.getArmor().get(i).prepareForDB() + ";";
		}
		// Create the string to save for melee weapons
		for (int i = 0; i < character.getMeleeWeapons().size(); i++)
		{
			meleeWeaponsToSave += character.getMeleeWeapons().get(i).prepareForDB() + ";";
		}
		// Create the string to save for ranged weapons
		for (int i = 0; i < character.getRangedWeapons().size(); i++)
		{
			rangedWeaponsToSave += character.getRangedWeapons().get(i).prepareForDB() + ";";
		}
		// Create the string to save for gear
		for (int i = 0; i < character.getGear().size(); i++)
		{
			gearToSave += character.getGear().get(i).prepareForDB() + ";";
		}
		// Create the string to save for adept powers
		for (int i = 0; i < character.getAdeptPowers().size(); i++)
		{
			adeptPowersToSave += character.getAdeptPowers().get(i).prepareForDB() + ";";
		}
		// Create the string to save for adept powers
		for (int i = 0; i < character.getAugmentations().size(); i++)
		{
			augmentationsToSave += character.getAugmentations().get(i).prepareForDB() + ";";
		}
		// Create the string to save for spells
		for (int i = 0; i < character.getSpells().size(); i++)
		{
			spellsToSave += character.getSpells().get(i).prepareForDB() + ";";
		}

		for (int i = 0; i < character.getVehicles().size(); i++)
		{
			vehiclesToSave += character.getVehicles().get(i).prepareForDB() + ";";
		}

		deckToSave = character.getCyberdeck().prepareForDB();
		// vehicleToSave = vehicle.prepareForDB();

		String sql = new String();
		Connection conn = makeConn();

		int height = (character.getHeightFeet() * 12) + character.getHeightInch();
		// The SQL String needed to update the database
		if (character.isCharacterLoaded())
		{ // updating a character already in the DB
			sql = "UPDATE characters SET player='" + character.getPlayer() + "', characterName='" + character.getName()
					+ "', alias='" + character.getAlias() + "', age=" + character.getAge() + ", height=" + height
					+ ", weight=" + character.getWeight() + ", metatype='" + character.getMetatype() + "', gender='"
					+ character.getGender() + "',karma=" + character.getKarma() + ", body=" + character.getBody()
					+ ", agility=" + character.getAgility() + ", reaction=" + character.getReaction() + ", strength="
					+ character.getStrength() + ", willpower=" + character.getWillpower() + ", logic=" + character.getLogic()
					+ ", intuition=" + character.getIntuition() + ", charisma=" + character.getCharisma() + ", edge="
					+ character.getEdge() + ", magic=" + character.getMagic() + ", hasMagic='" + hasMagicInt
					+ "', lifestyle='" + character.getLifestyle() + "', nuyen=" + character.getNuyen() + ", licenses='"
					+ licensesToSave + "', skills='" + skillsToSave + "', qualities='" + qualitiesToSave + "', contacts='"
					+ contactsToSave + "', meleeWeapons='" + meleeWeaponsToSave + "', armor='" + armorToSave
					+ "', rangedWeapons='" + rangedWeaponsToSave + "', gear='" + gearToSave + "', cyberdeck='" + deckToSave
					+ "', vehicle='" + vehiclesToSave + "', otherPowers='" + adeptPowersToSave + "', augmentations='"
					+ augmentationsToSave + "', spells='" + spellsToSave + "', user='" + character.getAssociatedUser()
					+ "' WHERE characterName='" + character.getName() + "';";

		} else
		{ // saving a new character into the database
			sql = "INSERT INTO characters (player, characterName, alias, metatype, age, height, weight, gender, karma, body,"
					+ "agility, reaction, strength, willpower, logic, intuition, charisma, edge, magic, hasMagic, lifestyle,"
					+ "nuyen, licenses, skills, qualities, contacts, otherPowers, augmentations, spells, "
					+ "meleeWeapons, armor, rangedWeapons, gear, cyberdeck, vehicle, user) VALUES ('" + character.getPlayer()
					+ "', '" + character.getName() + "', '" + character.getAlias() + "', '" + character.getMetatype() + "', "
					+ character.getAge() + ", " + height + ", '" + character.getWeight() + "', '" + character.getGender()
					+ "', " + character.getKarma() + ", " + character.getBody() + ", " + character.getAgility() + ", "
					+ character.getReaction() + ", " + character.getStrength() + ", " + character.getWillpower() + ", "
					+ character.getLogic() + ", " + character.getIntuition() + ", " + character.getCharisma() + ", "
					+ character.getEdge() + ", " + character.getMagic() + ", " + hasMagicInt + ", '"
					+ character.getLifestyle() + "', " + character.getNuyen() + ", '" + licensesToSave + "', '"
					+ skillsToSave + "', '" + qualitiesToSave + "', '" + contactsToSave + "', '" + adeptPowersToSave + "', '"
					+ augmentationsToSave + "', '" + spellsToSave + "', '" + meleeWeaponsToSave + "', '" + armorToSave
					+ "', '" + rangedWeaponsToSave + "', '" + gearToSave + "', '" + deckToSave + "', '" + vehiclesToSave
					+ "', '" + character.getAssociatedUser() + "');";

			character.setCharacterLoaded(true);
		}

		try
		{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return true;
	}

	public static int loadFromDB(String name, Character character)
	{
		// Creating the variables to put strings that need to be parsed
		character.setCharacterLoaded(true);
		String licensesFromDB = new String();
		String licensesTemp[];
		String skillsFromDB = new String();
		String skillsTemp[];
		String qualitiesFromDB = new String();
		String qualitiesTemp[];
		String contactsFromDB = new String();
		String contactsTemp[];
		String armorFromDB = new String();
		String armorTemp[];
		String meleeWeaponsFromDB = new String();
		String meleeWeaponsTemp[];
		String rangedWeaponsFromDB = new String();
		String rangedWeaponsTemp[];
		String gearFromDB = new String();
		String gearTemp[];
		String deckFromDB = new String();
		String deckTemp[];
		String vehicleFromDB = new String();
		String vehicleTemp[];
		String adeptPowersFromDB = new String();
		String adeptPowersTemp[];
		String augmentationsFromDB = new String();
		String augmentationsTemp[];
		String spellsFromDB = new String();
		String spellsTemp[];

		// Lets the user pick which character to load from the database
		String characterName = name;
		int height;
		Connection conn = makeConn();

		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM characters WHERE characterName='" + characterName + "'";
			ResultSet rs = stmt.executeQuery(sql);

			// If the character requested is not found in the DB
			if (!rs.isBeforeFirst())
			{
				return 0;
			}

			rs.next();

			// Gather all the variables and save them into their spots
			character.setPlayer(rs.getString("player"));
			character.setName(rs.getString("characterName"));
			character.setAlias(rs.getString("alias"));
			character.setMetatype(rs.getString("metatype"));
			character.setAge(rs.getInt("age"));
			height = rs.getInt("height");
			character.setWeight(rs.getInt("weight"));
			character.setGender(rs.getString("gender"));
			character.setKarma(rs.getInt("karma"));
			character.setBody(rs.getInt("body"));
			character.setAgility(rs.getInt("agility"));
			character.setReaction(rs.getInt("reaction"));
			character.setStrength(rs.getInt("strength"));
			character.setWillpower(rs.getInt("willpower"));
			character.setLogic(rs.getInt("logic"));
			character.setIntuition(rs.getInt("intuition"));
			character.setCharisma(rs.getInt("charisma"));
			character.setEdge(rs.getInt("edge"));
			character.setMagic(rs.getInt("magic"));
			character.setHasMagic(rs.getBoolean("hasMagic"));
			character.setLifestyle(rs.getString("lifestyle"));
			character.setNuyen(rs.getInt("nuyen"));
			character.setAssociatedUser(rs.getString("user"));

			licensesFromDB = rs.getString("licenses");
			skillsFromDB = rs.getString("skills");
			qualitiesFromDB = rs.getString("qualities");
			contactsFromDB = rs.getString("contacts");
			armorFromDB = rs.getString("armor");
			meleeWeaponsFromDB = rs.getString("meleeWeapons");
			rangedWeaponsFromDB = rs.getString("rangedWeapons");
			deckFromDB = rs.getString("cyberdeck");
			vehicleFromDB = rs.getString("vehicle");
			adeptPowersFromDB = rs.getString("otherPowers");
			augmentationsFromDB = rs.getString("augmentations");
			spellsFromDB = rs.getString("spells");

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();// did not succeed in doing the query
			return 2;
		}
		finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		character.setHeightFeet(height / 12);
		character.setHeightInch(height % 12);

		// Parsing through the Strings to save into the vectors
		if (!(licensesFromDB.isEmpty()))
		{
			licensesTemp = licensesFromDB.split(";");

			for (int i = 0; i < licensesTemp.length; i++)
			{
				String temp[] = licensesTemp[i].split(":");
				License l = new License(temp[0], Integer.parseInt(temp[1]));
				character.getLicenses().add(l);
			}
		}
		// Skills
		if (!(skillsFromDB.isEmpty()))
		{
			skillsTemp = skillsFromDB.split(";");

			for (int i = 0; i < skillsTemp.length; i++)
			{
				String temp[] = skillsTemp[i].split(":");
				Skill skilltemp = new Skill(temp[0], Integer.parseInt(temp[1]));
				character.getSkills().add(skilltemp);
			}
		}
		// Qualities
		if (!(qualitiesFromDB.isEmpty()))
		{
			qualitiesTemp = qualitiesFromDB.split(";");

			for (int i = 0; i < qualitiesTemp.length; i++)
			{
				String temp[] = qualitiesTemp[i].split(":");
				Quality qualitytemp = new Quality(temp[0], temp[1], temp[2]);
				character.getQualities().add(qualitytemp);
			}
		}
		// Contacts
		if (!(contactsFromDB.isEmpty()))
		{
			contactsTemp = contactsFromDB.split(";");

			for (int i = 0; i < contactsTemp.length; i++)
			{
				String temp[] = contactsTemp[i].split(":");
				Contact contactTemp;
				if (temp.length < 4)
				{
					contactTemp = new Contact(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), "");
				} else
				{
					contactTemp = new Contact(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[3]);
				}
				character.getContacts().add(contactTemp);
			}
		}
		if (!(armorFromDB.isEmpty()))
		{
			armorTemp = armorFromDB.split(";");

			for (int i = 0; i < armorTemp.length; i++)
			{
				String temp[] = armorTemp[i].split(":");
				Armor armor;
				if (temp.length < 3)
				{
					armor = new Armor(temp[0], Integer.parseInt(temp[1]), "");
				} else
				{
					armor = new Armor(temp[0], Integer.parseInt(temp[1]), temp[3]);
				}
				character.getArmor().add(armor);
			}
		}

		if (!(meleeWeaponsFromDB.isEmpty()))
		{
			meleeWeaponsTemp = meleeWeaponsFromDB.split(";");

			for (int i = 0; i < meleeWeaponsTemp.length; i++)
			{
				String temp[] = meleeWeaponsTemp[i].split(":");

				MeleeWeapon mw = new MeleeWeapon(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
						Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Boolean.parseBoolean(temp[5]));
				character.getMeleeWeapons().add(mw);
			}
		}
		if (!(rangedWeaponsFromDB.isEmpty()))
		{
			rangedWeaponsTemp = rangedWeaponsFromDB.split(";");

			for (int i = 0; i < rangedWeaponsTemp.length; i++)
			{
				String temp[] = rangedWeaponsTemp[i].split(":");
				RangedWeapon rw;
				if (temp.length == 6)
				{
					rw = new RangedWeapon(temp[0], temp[4], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[5]), "");
				} else
				{
					rw = new RangedWeapon(temp[0], temp[4], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
							Integer.parseInt(temp[3]), Integer.parseInt(temp[5]), temp[6]);
				}
				character.getRangedWeapons().add(rw);
			}
		}
		if (!(gearFromDB.isEmpty()))
		{
			gearTemp = gearFromDB.split(";");

			for (int i = 0; i < gearTemp.length; i++)
			{
				String temp[] = gearTemp[i].split(":");
				Gear gear;
				if (temp.length == 2)
				{
					gear = new Gear(temp[0], Integer.parseInt(temp[1]), "");
				} else
				{
					gear = new Gear(temp[0], Integer.parseInt(temp[1]), temp[2]);
				}
				character.getGear().add(gear);
			}
		}
		if (!(adeptPowersFromDB.isEmpty()))
		{
			adeptPowersTemp = adeptPowersFromDB.split(";");

			for (int i = 0; i < adeptPowersTemp.length; i++)
			{
				String temp[] = adeptPowersTemp[i].split(":");
				AdeptPower ap;
				if (temp.length == 2)
				{
					ap = new AdeptPower(temp[0], "", Integer.parseInt(temp[1]));
				} else
				{
					ap = new AdeptPower(temp[0], temp[2], Integer.parseInt(temp[1]));
				}
				character.getAdeptPowers().add(ap);
			}
		}
		if (!(spellsFromDB.isEmpty()))
		{
			spellsTemp = spellsFromDB.split(";");

			for (int i = 0; i < spellsTemp.length; i++)
			{
				String temp[] = spellsTemp[i].split(":");
				Spell spell;

				spell = new Spell(temp[0], temp[1], temp[2], temp[3], Integer.parseInt(temp[4]));
				character.getSpells().add(spell);
			}
		}
		if (!(augmentationsFromDB.isEmpty()))
		{
			augmentationsTemp = augmentationsFromDB.split(";");

			for (int i = 0; i < augmentationsTemp.length; i++)
			{
				String temp[] = augmentationsTemp[i].split(":");
				Augmentation aug;

				if (temp.length == 3)
				{
					aug = new Augmentation(temp[0], "", Integer.parseInt(temp[1]), Float.parseFloat(temp[2]));
				} else
				{
					aug = new Augmentation(temp[0], temp[2], Integer.parseInt(temp[1]), Float.parseFloat(temp[3]));
				}
				character.getAugmentations().add(aug);
			}
		}
		if (!deckFromDB.isEmpty())
		{
			deckTemp = deckFromDB.split(":");
			character.getCyberdeck().setCyberDeck(deckTemp[0], Integer.parseInt(deckTemp[1]),
					Integer.parseInt(deckTemp[2]), Integer.parseInt(deckTemp[3]), Integer.parseInt(deckTemp[4]),
					Integer.parseInt(deckTemp[5]), Integer.parseInt(deckTemp[6]));
			for (int i = 7; i < deckTemp.length; i++)
			{
				character.getCyberdeck().addProgram(deckTemp[i]);
			}
		}

		if (!vehicleFromDB.isEmpty())
		{
			vehicleTemp = vehicleFromDB.split(";");

			for (int i = 0; i < vehicleTemp.length; i++)
			{
				String[] vTemp = vehicleTemp[i].split(":");
				Vehicle v = new Vehicle();
				if (vehicleTemp.length > 8)
				{
					v.setVehicle(vTemp[0], Integer.parseInt(vTemp[1]), Integer.parseInt(vTemp[2]),
							Integer.parseInt(vTemp[3]), Integer.parseInt(vTemp[4]), Integer.parseInt(vTemp[5]),
							Integer.parseInt(vTemp[6]), Integer.parseInt(vTemp[7]), vTemp[8]);
				} else
				{
					v.setVehicle(vTemp[0], Integer.parseInt(vTemp[1]), Integer.parseInt(vTemp[2]),
							Integer.parseInt(vTemp[3]), Integer.parseInt(vTemp[4]), Integer.parseInt(vTemp[5]),
							Integer.parseInt(vTemp[6]), Integer.parseInt(vTemp[7]), "");
				}
				character.getVehicles().add(v);
			}
		}

		return 1;

	}

	public static boolean deleteFromDB(String name)
	{
		Connection conn = makeConn();
		String characterName = name.toLowerCase();
		boolean success = false;
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "delete FROM characters WHERE characterName='" + characterName + "'";
			int i = stmt.executeUpdate(sql);
			success = (i != 0);
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		if (!success)
		{
			return false;
		}
		return true;
	}

	public static ArrayList<String> getAllCharacterNames()
	{
		Connection conn = makeConn();
		ArrayList<String> myList = new ArrayList<String>();
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select characterName FROM characters";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				myList.add(rs.getString("characterName"));
			}
		} catch (Exception e)
		{

		} finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return myList;
	}

	public static Connection makeConn()
	{
		Connection conn = null;
		if (!connectionStatus)
			return null;

		try
		{
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception se)
		{
			// Handle errors for JDBC
			connectionStatus = false;
			se.printStackTrace();
		}
		return conn;
	}

	public static boolean login(String userName, Label errorMessage, String hashedPswdToCheck)
	{
		Connection conn = makeConn();
		String goodPswd;
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT pswd FROM users WHERE username='" + userName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst())
			{
//				ShadowRunApp.setErrorMessage("User does not exist", errorMessage);
				return false;
			}
			rs.next();
			goodPswd = rs.getString("pswd");

			if (!goodPswd.equals(hashedPswdToCheck))
			{
//				ShadowRunApp.setErrorMessage("Wrong Password", errorMessage);
				return false;
			}
			conn.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean setNewPassword(Label errorMessage, String userName, String newPswd)
	{
		Connection conn = makeConn();
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "UPDATE users SET pswd='" + newPswd + "' WHERE username='" + userName + "'";
			stmt.executeUpdate(sql);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return true;
	}

	public static String hashPassword(String pswd)
	{
		String hashedPswd = null;

		try
		{
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(pswd.getBytes());
			// get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes has bytes in decimal format
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			hashedPswd = sb.toString();

		} catch (Exception e)
		{

		}
		return hashedPswd;
	}

	public static boolean createNewUser(String userName, String passWord, Label errorMessage)
	{
		Connection conn = DataBaseConnector.makeConn();

		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select username FROM users";
			ResultSet rs = stmt.executeQuery(sql);

			// Checks the new user to current users
			while (rs.next())
			{
				String user = rs.getString("username");
				System.out.println(user);
				if (user.equals(userName))
				{
					errorMessage.setText("User Name Taken Already");
					stmt.close();
					conn.close();
					return false;
				}
			}
		} catch (Exception e)
		{
			try
			{
				conn.close();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			return false;
		}

		// If the new username is not found, add it to the users DB
		// NEED TO HASH PASSWORD
		String hashedPswd = DataBaseConnector.hashPassword(passWord);
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO users (username, pswd) VALUES ('" + userName + "', '" + hashedPswd + "');";
			stmt.executeUpdate(sql);

		} catch (Exception e)
		{
			return false;
		} finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return true;
	}

}
