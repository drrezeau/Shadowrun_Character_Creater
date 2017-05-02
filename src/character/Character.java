/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author David
 */
public class Character implements Serializable
{
	private static final long serialVersionUID = -9137514380009861845L;

	// necessary information about the character
	// handled with a new character.
	private String player;
	private String name;
	private String alias;
	private String metatype;
	private Integer age;
	private Integer heightFeet;
	private Integer heightInch;
	private Integer weight;
	private String gender;
	private Integer karma;
	private boolean hasMagic;

	// Everything else should have some value assigned at character creation
	private String lifestyle;
	private Integer nuyen;// money
	private ArrayList<License> licenses;

	// the character attributes
	private Integer body;
	private Integer agility;
	private Integer reaction;
	private Integer strength;
	private Integer willpower;
	private Integer logic;
	private Integer intuition;
	private Integer charisma;
	private Integer edge;

	private Integer essence;
	private Integer magic;

	private CyberDeck cyberdeck;

	// The different things to remember for a character
	final private ArrayList<Skill> skills;
	final private ArrayList<Quality> qualities;
	final private ArrayList<Contact> contacts;
	final private ArrayList<MeleeWeapon> meleeWeapons;
	final private ArrayList<RangedWeapon> rangedWeapons;
	final private ArrayList<Armor> armor;
	final private ArrayList<Augmentation> augmentations;
	final private ArrayList<Spell> spells;
	final private ArrayList<Gear> gear;
	final private ArrayList<AdeptPower> adeptPowers;
	private ArrayList<Vehicle> vehicles;

	private String associatedUser;

	private boolean characterLoaded = false;

	public Character()
	{
		name = null;
		licenses = new ArrayList<License>();
		skills = new ArrayList<Skill>();
		qualities = new ArrayList<Quality>();
		contacts = new ArrayList<Contact>();
		meleeWeapons = new ArrayList<MeleeWeapon>();
		rangedWeapons = new ArrayList<RangedWeapon>();
		armor = new ArrayList<Armor>();
		gear = new ArrayList<Gear>();
		this.adeptPowers = new ArrayList<AdeptPower>();
		this.augmentations = new ArrayList<Augmentation>();
		this.spells = new ArrayList<Spell>();

		body = 1;
		agility = 1;
		reaction = 1;
		strength = 1;
		willpower = 1;
		logic = 1;
		intuition = 1;
		charisma = 1;
		edge = 1;
		setMagic(0);
		essence = 0;
		setLifestyle("");
		setNuyen(0);

		this.setVehicles(new ArrayList<Vehicle>());
		this.cyberdeck = new CyberDeck();
	}

	public Character(String player, String characterName, String alias, int age, int weight, int feet, int inch,
			String gender, boolean hasMagic, String metatype, String associatedUser)
	{
		this.setPlayer(player);
		this.name = characterName;
		this.setAlias(alias);
		this.setHeightFeet(feet);
		this.setHeightInch(inch);
		this.setWeight(weight);
		this.setMetatype(metatype);
		this.setAge(age);
		this.setGender(gender);
		this.setHasMagic(hasMagic);
		this.setAssociatedUser(associatedUser);

		this.setLifestyle("Street");
		this.setNuyen(0);
		licenses = new ArrayList<License>();
		setKarma(0);

		skills = new ArrayList<Skill>();
		qualities = new ArrayList<Quality>();
		contacts = new ArrayList<Contact>();
		meleeWeapons = new ArrayList<MeleeWeapon>();
		rangedWeapons = new ArrayList<RangedWeapon>();
		armor = new ArrayList<Armor>();
		gear = new ArrayList<Gear>();
		this.spells = new ArrayList<Spell>();

		body = 1;
		agility = 1;
		reaction = 1;
		strength = 1;
		willpower = 1;
		logic = 1;
		intuition = 1;
		charisma = 1;
		edge = 1;
		setMagic(0);

		cyberdeck = new CyberDeck();
		this.setVehicles(new ArrayList<Vehicle>());
		this.adeptPowers = new ArrayList<AdeptPower>();
		this.augmentations = new ArrayList<Augmentation>();
	}

	public Character(Character copy)
	{
		this.setPlayer(copy.getPlayer());
		this.name = copy.name;
		this.setAlias(copy.getAlias());
		this.setMetatype(copy.getMetatype());
		this.setAge(copy.getAge());
		this.setGender(copy.getGender());
		this.setWeight(copy.getWeight());
		this.setHeightFeet(copy.getHeightFeet());
		this.setHeightInch(copy.getHeightInch());
		this.setHasMagic(copy.isHasMagic());
		this.setAssociatedUser(copy.getAssociatedUser());

		this.setLifestyle(copy.getLifestyle());
		this.setNuyen(copy.getNuyen());
		this.licenses = copy.getLicenses();
		this.setKarma(copy.getKarma());

		skills = copy.getSkills();
		qualities = copy.getQualities();
		contacts = copy.getContacts();
		meleeWeapons = copy.getMeleeWeapons();
		rangedWeapons = copy.getRangedWeapons();
		armor = copy.getArmor();
		gear = copy.getGear();

		body = copy.getBody();
		agility = copy.getAgility();
		reaction = copy.getReaction();
		strength = copy.getStrength();
		willpower = copy.getWillpower();
		logic = copy.getLogic();
		intuition = copy.getIntuition();
		charisma = copy.getCharisma();
		edge = copy.getEdge();
		setMagic(copy.getMagic());

		cyberdeck = new CyberDeck(copy.getCyberdeck());
		this.setVehicles(new ArrayList<Vehicle>(copy.getVehicle()));
		this.adeptPowers = copy.getAdeptPowers();
		this.augmentations = copy.getAugmentations();
		this.spells = copy.getSpells();
	}

	public boolean isSet()
	{
		boolean set = true;
		if (name.equals(null))
			set = false;
		return set;
	}

	public void editAttributes(int myBody, int myAgility, int myReaction, int myStrength, int myWillpower, int myLogic,
			int myIntuition, int myCharisma, int myEdge)
	{
		body = myBody;
		agility = myAgility;
		reaction = myReaction;
		strength = myStrength;
		willpower = myWillpower;
		logic = myLogic;
		intuition = myIntuition;
		charisma = myCharisma;
		edge = myEdge;
	}

	/*******************************************************
	 *********** Functions for Adding new Traits ***********
	 *******************************************************/
	// Used in the JavaFX project
	public void addLicense(String name, Integer rating)
	{
		License id = new License(name, rating);
		licenses.add(id);
	}

	public void addLifestyleMoney(String myLifestyle, Integer myNuyen)
	{
		setLifestyle(myLifestyle);
		setNuyen(myNuyen);
	}

	public void addSkill(String skillName, int rating)
	{
		Skill skill = new Skill(skillName, rating);
		skills.add(skill);
	}

	public void addQuality(String qualityName, String notes, String positive)
	{
		Quality quality = new Quality(qualityName, notes, positive);
		qualities.add(quality);
	}

	public void addContact(String name, int loyalty, int connection, String favor)
	{
		Contact contact = new Contact(name, loyalty, connection, favor);
		contacts.add(contact);
	}

	public void addArmor(String name, int rating, String notes)
	{
		Armor armor = new Armor(name, rating, notes);
		this.armor.add(armor);
	}

	public void addMeleeWeapon(String name, int reach, int damage, int accuracy, int armorPierce, boolean stun)
	{
		MeleeWeapon mw = new MeleeWeapon(name, reach, damage, accuracy, armorPierce, stun);
		this.meleeWeapons.add(mw);
	}

	public void addRangedWeapon(String name, String mode, int damage, int accuracy, int armorPierce, int recoil,
			String notes)
	{
		RangedWeapon rw = new RangedWeapon(name, mode, damage, accuracy, armorPierce, recoil, notes);
		this.rangedWeapons.add(rw);
	}

	public void addGear(String name, int rating, String notes)
	{
		Gear gear1 = new Gear(name, rating, notes);
		gear.add(gear1);
	}

	public void addAdeptPower(String name, int rating, String notes)
	{
		AdeptPower AP = new AdeptPower(name, notes, rating);
		adeptPowers.add(AP);
	}

	public void addAugmentation(String name, int rating, String notes, float essence)
	{
		Augmentation aug = new Augmentation(name, notes, rating, essence);
		augmentations.add(aug);
	}

	public void addSpell(String name, String type, String range, String duration, int drain)
	{
		Spell spell = new Spell(name, type, range, duration, drain);
		spells.add(spell);
	}

	/*******************************************************
	 *********** Functions for Deleting Traits *************
	 *******************************************************/
	public void deleteLicense(String name)
	{
		name = name.toLowerCase();
		for (int i = 0; i < licenses.size(); i++)
		{
			if (name.equals(licenses.get(i).getName().toLowerCase()))
			{
				licenses.remove(i);
				break;
			}
		}
	}

	public void deleteSkill(String name)
	{
		String skillName = name.toLowerCase();

		for (int i = 0; i < skills.size(); i++)
		{
			if (skillName.equals(skills.get(i).getSkill().toLowerCase()))
			{
				skills.remove(i);
				break;
			}
		}
	}

	public void deleteQuality(String name)
	{
		String qualityName = name;

		for (int i = 0; i < qualities.size(); i++)
		{
			if (qualityName.toLowerCase().equals(qualities.get(i).getName().toLowerCase()))
			{
				qualities.remove(i);
				break;
			}
		}
	}

	public void deleteContact(String name)
	{
		String contactName = name;

		for (int i = 0; i < contacts.size(); i++)
		{
			if (contactName.toLowerCase().equals(contacts.get(i).getName().toLowerCase()))
			{
				contacts.remove(i);
				break;
			}
		}
	}

	public void deleteArmor(String name)
	{
		String contactName = name;

		for (int i = 0; i < armor.size(); i++)
		{
			if (contactName.toLowerCase().equals(armor.get(i).getName().toLowerCase()))
			{
				armor.remove(i);
				break;
			}
		}
	}

	public void deleteMeleeWeapon(String name)
	{
		String meleeWeapon = name;

		for (int i = 0; i < meleeWeapons.size(); i++)
		{
			if (meleeWeapon.equals(meleeWeapons.get(i).getName()))
			{
				meleeWeapons.remove(i);
				break;
			}
		}
	}

	public void deleteRangedWeapon(String name)
	{
		String rangedWeapon = name.toLowerCase();

		for (int i = 0; i < rangedWeapons.size(); i++)
		{
			if (rangedWeapon.equals(rangedWeapons.get(i).getName().toLowerCase()))
			{
				rangedWeapons.remove(i);
				break;
			}
		}
	}

	public void deleteGear(String name)
	{
		String tmp = name.toLowerCase();

		for (int i = 0; i < gear.size(); i++)
		{
			if (tmp.equals(gear.get(i).getName().toLowerCase()))
			{
				gear.remove(i);
				break;
			}
		}
	}

	public void deleteAdeptPower(String name)
	{
		String tmp = name.toLowerCase();

		for (int i = 0; i < adeptPowers.size(); i++)
		{
			if (tmp.equals(adeptPowers.get(i).getName().toLowerCase()))
			{
				adeptPowers.remove(i);
				break;
			}
		}
	}

	public void deleteAugmentation(String name)
	{
		String tmp = name.toLowerCase();

		for (int i = 0; i < augmentations.size(); i++)
		{
			if (tmp.equals(augmentations.get(i).getName().toLowerCase()))
			{
				augmentations.remove(i);
				break;
			}
		}
	}

	public void deleteSpell(String name)
	{
		String tmp = name.toLowerCase();

		for (int i = 0; i < spells.size(); i++)
		{
			if (tmp.equals(spells.get(i).getName().toLowerCase()))
			{
				spells.remove(i);
				break;
			}
		}
	}

	public void deleteVehicle(String name)
	{
		for (int i = 0; i < vehicles.size(); i++)
		{
			if (name.equals(vehicles.get(i).getName()))
			{
				vehicles.remove(i);
				break;
			}
		}
	}
	/*******************************************************
	 *********** Functions for Stingifying Traits ***********
	 *******************************************************/
	public String creataLicenseString()
	{
		String rtn = "";
		int length = this.licenses.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + licenses.get(i).getName() + " | Rating: " + licenses.get(i).getRating() + '\n';
		}

		return rtn;
	}

	public String createSkillString()
	{
		String rtn = "";
		int length = skills.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + skills.get(i).getSkill() + " | Rating: " + skills.get(i).getRating() + '\n';
		}

		return rtn;
	}

	public String createQualityString()
	{
		String rtn = "";
		int length = qualities.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + qualities.get(i).getName() + " | Notes: " + qualities.get(i).getNote() + " | "
					+ qualities.get(i).isPositive() + '\n';
		}

		return rtn;
	}

	public String createContactString()
	{
		String rtn = "";
		int length = contacts.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + contacts.get(i).getName() + " | Loyalty: " + contacts.get(i).getLoyalty()
					+ " | Connection: " + contacts.get(i).getConnection() + " | Favor: " + contacts.get(i).getFavor() + '\n';
		}

		return rtn;
	}

	public String createArmorString()
	{
		String rtn = "";
		int length = armor.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + armor.get(i).getName() + " | Rating: " + armor.get(i).getRating() + " | Notes: "
					+ armor.get(i).getNotes() + '\n';
		}

		return rtn;
	}

	public String createMeleeWeaponsString()
	{
		String rtn = "";
		int length = meleeWeapons.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + meleeWeapons.get(i).getName() + " | Reach: " + meleeWeapons.get(i).getReach()
					+ " | Damage: " + meleeWeapons.get(i).getDamage() + ((meleeWeapons.get(i).isStun()) ? "S" : "P")
					+ " | Accuracy: " + meleeWeapons.get(i).getAccuracy() + " | Armor Piercing: "
					+ meleeWeapons.get(i).getArmorPierce() + '\n';
		}

		return rtn;
	}

	public String createRangedWeaponsString()
	{
		String rtn = "";
		int length = rangedWeapons.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + rangedWeapons.get(i).getName() + " | Damage: " + rangedWeapons.get(i).getDamage()
					+ " | Accuracy: " + rangedWeapons.get(i).getAccuracy() + " | Armor Piercing: "
					+ rangedWeapons.get(i).getArmorPierce() + " | Mode: " + rangedWeapons.get(i).getMode() + " | Recoil: "
					+ rangedWeapons.get(i).getRecoil() + " | Notes: " + rangedWeapons.get(i).getNotes() + '\n';
		}

		return rtn;
	}

	public String createGearString()
	{
		String rtn = "";
		int length = gear.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + gear.get(i).getName() + " | Rating: " + gear.get(i).getRating() + " | Notes: "
					+ gear.get(i).getNotes() + '\n';
		}

		return rtn;
	}

	public String createAdeptPowersString()
	{
		String rtn = "";
		int length = adeptPowers.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + adeptPowers.get(i).getName() + " | Rating: " + adeptPowers.get(i).getRating()
					+ " | Notes: " + adeptPowers.get(i).getNotes() + '\n';
		}

		return rtn;
	}

	public String createAugmentationsString()
	{
		String rtn = "";
		int length = augmentations.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + augmentations.get(i).getName() + " | Rating: " + augmentations.get(i).getRating()
					+ " | Notes: " + augmentations.get(i).getNotes() + " | Essence Cost: "
					+ augmentations.get(i).getEssenceCost() + '\n';
		}

		return rtn;
	}

	public String createSpellsString()
	{
		String rtn = "";
		int length = spells.size();
		for (int i = 0; i < length; i++)
		{
			rtn += (i + 1) + ": " + spells.get(i).getName() + " | Type: " + spells.get(i).getType() + " | Range: "
					+ spells.get(i).getRange() + " | Duration: " + spells.get(i).getDuration() + " | Drain: F "
					+ ((spells.get(i).getDrain() > 0) ? "- " : "+ ") + spells.get(i).getDrain() + '\n';
		}

		return rtn;
	}

	/*****************************************************
	 ************* Sorting ArrayLists ********************
	 *****************************************************/
	public void sortLicenses()
	{
		Collections.sort(licenses, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((License) one).getName().compareTo(((License) two).getName());
			}
		});
	}

	public void sortSkills()
	{
		Collections.sort(skills, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((Skill) one).getSkill().compareTo(((Skill) two).getSkill());
			}
		});
	}

	public void sortQualities()
	{
		Collections.sort(qualities, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((Quality) one).getName().compareTo(((Quality) two).getName());
			}
		});
	}

	public void sortContacts()
	{
		Collections.sort(contacts, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((Contact) one).getName().compareTo(((Contact) two).getName());
			}
		});
	}

	public void sortArmor()
	{
		Collections.sort(armor, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((Armor) one).getName().compareTo(((Armor) two).getName());
			}
		});
	}

	public void sortMeleeWeapons()
	{
		Collections.sort(meleeWeapons, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((MeleeWeapon) one).getName().compareTo(((MeleeWeapon) two).getName());
			}
		});
	}

	public void sortRangedWeapons()
	{
		Collections.sort(rangedWeapons, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((RangedWeapon) one).getName().compareTo(((RangedWeapon) two).getName());
			}
		});
	}

	public void sortGear()
	{
		Collections.sort(gear, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((Gear) one).getName().compareTo(((Gear) two).getName());
			}
		});
	}

	public void sortAdeptPowers()
	{
		Collections.sort(adeptPowers, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((AdeptPower) one).getName().compareTo(((AdeptPower) two).getName());
			}
		});
	}

	public void sortAugmentations()
	{
		Collections.sort(augmentations, new Comparator<Object>()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((Augmentation) one).getName().compareTo(((Augmentation) two).getName());
			}
		});
	}

	/*****************************************************
	 ************* Functions For Database ****************
	 *****************************************************/

	/*******************************************************
	 **************** Getters and Setters *******************
	 *******************************************************/

	public String getPlayer()
	{
		return player;
	}

	public String getAlias()
	{
		return alias;
	}

	public String getMetatype()
	{
		return metatype;
	}

	public Integer getAge()
	{
		return age;
	}

	public Integer getHeightFeet()
	{
		return heightFeet;
	}

	public Integer getHeightInch()
	{
		return heightInch;
	}

	public Integer getWeight()
	{
		return weight;
	}

	public String getGender()
	{
		return gender;
	}

	public Integer getKarma()
	{
		return karma;
	}

	public void setKarma(int karma)
	{
		this.karma = karma;
	}

	public Integer getEssence()
	{
		return essence;
	}

	public boolean isHasMagic()
	{
		return hasMagic;
	}

	public ArrayList<Skill> getSkills()
	{
		return skills;
	}

	public ArrayList<Quality> getQualities()
	{
		return qualities;
	}

	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}

	public ArrayList<MeleeWeapon> getMeleeWeapons()
	{
		return meleeWeapons;
	}

	public ArrayList<RangedWeapon> getRangedWeapons()
	{
		return rangedWeapons;
	}

	public ArrayList<Armor> getArmor()
	{
		return armor;
	}

	public ArrayList<License> getLicenses()
	{
		return licenses;
	}

	public CyberDeck getCyberdeck()
	{
		return cyberdeck;
	}

	public ArrayList<Vehicle> getVehicle()
	{
		return getVehicles();
	}

	public ArrayList<Gear> getGear()
	{
		return gear;
	}

	public ArrayList<AdeptPower> getAdeptPowers()
	{
		return adeptPowers;
	}

	public ArrayList<Augmentation> getAugmentations()
	{
		return augmentations;
	}

	public ArrayList<Spell> getSpells()
	{
		return spells;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getBody()
	{
		return body;
	}

	public void setBody(Integer body)
	{
		this.body = body;
	}

	public Integer getAgility()
	{
		return agility;
	}

	public void setAgility(Integer agility)
	{
		this.agility = agility;
	}

	public Integer getReaction()
	{
		return reaction;
	}

	public void setReaction(Integer reaction)
	{
		this.reaction = reaction;
	}

	public Integer getStrength()
	{
		return strength;
	}

	public void setStrength(Integer strength)
	{
		this.strength = strength;
	}

	public Integer getWillpower()
	{
		return willpower;
	}

	public void setWillpower(Integer willpower)
	{
		this.willpower = willpower;
	}

	public Integer getLogic()
	{
		return logic;
	}

	public void setLogic(Integer logic)
	{
		this.logic = logic;
	}

	public Integer getIntuition()
	{
		return intuition;
	}

	public void setIntuition(Integer intuition)
	{
		this.intuition = intuition;
	}

	public Integer getCharisma()
	{
		return charisma;
	}

	public void setCharisma(Integer charisma)
	{
		this.charisma = charisma;
	}

	public Integer getEdge()
	{
		return edge;
	}

	public void setEdge(Integer edge)
	{
		this.edge = edge;
	}

	public Integer getMagic()
	{
		return magic;
	}

	public String getLifestyle()
	{
		return lifestyle;
	}

	public Integer getNuyen()
	{
		return nuyen;
	}

	public ArrayList<Vehicle> getVehicles()
	{
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles)
	{
		this.vehicles = vehicles;
	}

	public boolean isCharacterLoaded()
	{
		return characterLoaded;
	}

	public void setCharacterLoaded(boolean characterLoaded)
	{
		this.characterLoaded = characterLoaded;
	}

	public String getAssociatedUser()
	{
		return associatedUser;
	}

	public void setAssociatedUser(String associatedUser)
	{
		this.associatedUser = associatedUser;
	}

	public void setPlayer(String player)
	{
		this.player = player;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public void setMetatype(String metatype)
	{
		this.metatype = metatype;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public void setWeight(Integer weight)
	{
		this.weight = weight;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public void setKarma(Integer karma)
	{
		this.karma = karma;
	}

	public void setMagic(Integer magic)
	{
		this.magic = magic;
	}

	public void setHasMagic(boolean hasMagic)
	{
		this.hasMagic = hasMagic;
	}

	public void setLifestyle(String lifestyle)
	{
		this.lifestyle = lifestyle;
	}

	public void setNuyen(Integer nuyen)
	{
		this.nuyen = nuyen;
	}

	public void setHeightFeet(Integer heightFeet)
	{
		this.heightFeet = heightFeet;
	}

	public void setHeightInch(Integer heightInch)
	{
		this.heightInch = heightInch;
	}

}
