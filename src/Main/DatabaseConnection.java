package Main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import myPieces.DB_Metatype;
import myPieces.DB_Quality;
import myPieces.DB_Skill;

import java.sql.Connection;

public class DatabaseConnection
{

	private static final String	LAPTOP	= "jdbc:h2:C:/Users/david_000/Google Drive/Shadowrun_Character_Creater/shadowrun_character_creater";
	private static final String	DESKTOP	= "jdbc:h2:C:/Users/c/Google Drive/Shadowrun_Character_Creater/shadowrun_character_creater";

	public static void loadQualities()
	{
		try
		{
			Connection conn = getConn();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from qualities");
			while (rs.next())
			{
				String quality = rs.getString("quality");
				int karmaCost = rs.getInt("karmaCost");
				String notes = rs.getString("notes");
				boolean positive = rs.getBoolean("positive");
				if (positive)
					Shadowrun_Globals.posQualities.add(new DB_Quality(quality, karmaCost, notes, positive));
				else
					Shadowrun_Globals.negQualities.add(new DB_Quality(quality, karmaCost, notes, positive));
			}

			conn.close();

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadSkills()
	{
		try
		{
			Connection conn = getConn();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from skills");
			while (rs.next())
			{
				String skill = rs.getString("skill");
				String associatedAttr = rs.getString("associatedattr");
				String skillGroup = rs.getString("skillGroup");
				Shadowrun_Globals.skills.add(new DB_Skill(skill, associatedAttr, skillGroup));
			}

			conn.close();

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadMetatypes()
	{
		try
		{
			Connection conn = getConn();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from metatypes");
			while (rs.next())
			{
				String type = rs.getString("type");
				int karmaCost = rs.getInt("karmaCost");
				boolean basic = rs.getBoolean("basic");
				Shadowrun_Globals.metatypes.add(new DB_Metatype(type, karmaCost, basic));
			}

			conn.close();

		} catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws ClassNotFoundException, SQLException
	{

		String db = "";

		if (Shadowrun_Globals.DESKTOP)
		{
			db = DESKTOP;
		} else
		{
			db = LAPTOP;
		}
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(db, "sa", "");
		return conn;
	}

}
