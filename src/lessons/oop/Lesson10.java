package lessons.oop;

/* TOPIC: MiniGame
 *  */

import org.apache.commons.lang3.ArrayUtils;

/* Info on how to import java-libraries: http://www.newthinktank.com/2012/01/how-to-install-java-libraries/
*
* Summary:
* Download the Library
* Create a new folder in the project called "lib"
* Right click on the new folder and click on "import"
* Import Wizard: Select General -> File System
* Select all jar files from the downloaded library
* Right click on Project, click on Properties
* Select "Java Build Path"  -> The Java build path is used while compiling a Java project to discover dependent classes 
* Select "Libraries" and then "ClassPath" ->  ClassPath is a list of either folders or jar files to consider (in order) when resolving classes to be loaded. 
* click "Add jars"
* Select all jars from the "lib" folder and click apply
* 
*/

import lessons.oop.lesson_10_materials.BattleBoard;
import lessons.oop.lesson_10_materials.Monster;

public class Lesson10 {
	
	public static void main(String[] args) {
		
		BattleBoard.buildBattleBoard();
		
		char[][] tempBattleBoard = new char[10][10];
		
		Monster[] Monsters = new Monster[4];
		
		Monsters[0] = new Monster(1000, 20, 1, "Frank");
		Monsters[1] = new Monster(500, 40, 2, "Drac");
		Monsters[2] = new Monster(1000, 20, 1, "Paul");
		Monsters[3] = new Monster(1000, 20, 1, "George");
		
		BattleBoard.redrawBoard();
		
		for (Monster m : Monsters) {
			
			if (m.getAlive()) {
				
				int arrayItemIndex = ArrayUtils.indexOf(Monsters, m);
				m.moveMonster(Monsters, arrayItemIndex);
				
			}
			
		}
		
		BattleBoard.redrawBoard();
		
	}
	
}
