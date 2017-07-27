package Main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import myPieces.DB_Metatype;
import myPieces.DB_Skill;

public class ShadowrunCharacterCreater extends Application {

    static character.Character character = new character.Character();
    static String associatedUser = null;

    public static Stage primaryStage;

    public static MenuBar menu;

    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
	// TODO Auto-generated method stub
	primaryStage = stage;
	scene = new Scene(new Group());
	primaryStage.setResizable(false);
	primaryStage.show();
	centerStage();
	
	showMainMenu();

    }

    public static void main(String[] args) {
   	 DatabaseConnection.loadMetatypes();
   	 DatabaseConnection.loadSkills();
   	 DatabaseConnection.loadQualities();
   	 
   	 
//   	 for (DB_Metatype m : Shadowrun_Globals.metatypes)
//   	 {
//   		 m.display();
//   	 }
//   	 for(DB_Skill s : Shadowrun_Globals.skills)
//   	 {
//   		 System.out.println(s.getSkill() + " " + s.getAssociatedAttr());
//   	 }
   	 
   	 launch(args);
    }

    public static void showMainMenu() throws IOException {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(ShadowrunCharacterCreater.class.getResource("mainMenu.fxml"));
	Parent main = (Parent) loader.load();

	scene.setRoot(main);
	scene.getStylesheets().add("Styles/myStyles.css");

	primaryStage.setScene(scene);

	centerStage();
    }
    
    public static String getWhichTab()
    {
	for (Tab tab : ((TabPane) scene.getRoot().getChildrenUnmodifiable().get(1)).getTabs())
	{
	    if(tab.isSelected())
	    {
		return tab.getId();
	    }
	}
	return "";
    }
    
    private static void centerStage()
    {
	Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
	primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}
