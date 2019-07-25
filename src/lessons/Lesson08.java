package lessons;

import lessons.lesson08_materials.*;

/* Info on how to import java-libraries: http://www.newthinktank.com/2012/01/how-to-install-java-libraries/
*
* Summary:
* Download the Library
* Create a new folder in the project called "lib"
* Right click on the new folder and click on "import"
* Import Wizard: Select General -> File System
* Select all jar files from the downloaded library
* Right click on Project, click on Properties
* Select "Java Build Path"
* Select "Libraries", "ModulePath" and click "Add jars"
* Select all jars from the "lib" folder and click apply
* 
*/

/* The Goal of this tutorial is to make a game like this
------------------------------
|*||*||*||*||*||*||*||*||*||*|
|*||*||*||*||*||*||*||*||*||*|
|*||*||*||*||*||*||*||*||*||*|
|*||M||F||*||*||*||*||*||*||*|
|*||*||*||*||*||*||*||*||*||*|
|*||*||*||*||*||*||*||*||*||*|
|*||*||*||*||*||*||*||*||*||*|
|P||*||*||*||*||*||*||*||*||*|
|*||*||*||*||D||*||*||*||*||*|
|*||*||*||*||*||*||*||*||*||*|
------------------------------
Monsters move around on the board. If they touch each other, they fight
until only one is left.
 */

public class Lesson08 {

	public static void main(String[] args) {
		BattleBoard.buildBattleBoard();
		
		char[][] tempBattleBoard = new char[10][10];
		
		Monster[] Monsters = new Monster[4];
		
		Monsters[1] = new Monster(1000, 20, 1, "Frank");
		Monsters[1] = new Monster(500, 40, 2, "Drac");
		Monsters[1] = new Monster(1000, 20, 1, "Paul");
		Monsters[1] = new Monster(1000, 20, 1, "George");
		
		BattleBoard.redrawBoard();

	}
	
}
