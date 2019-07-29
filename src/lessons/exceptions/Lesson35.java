package lessons.exceptions;

/* TOPIC: Common Java Errors
 * 
 * 1) Forgetting imports -> Eclipse warns you
 * 2) Forgetting to close curly braces OR closing the wrong curly brace
 * 		-> Always type a comment at the end of each class, method or larger loop 
 * 		   (e.g. // END OF main)
 * 		-> Improves documentation and ensures that you have closed it.
 * 3) Making a class private
 * 		-> the class can be public, abstract or final. Only sub-classes can be private
 * 4) There can only be one public class per file!
 * 5) Cannot make a static reference to a non-static method
 * 		-> e.g. If a static function wants to use a non-static value
 * 		-> static methods can only use static fields and other static methods
 * 6) Wrong capitalizations, e.g. "Int number = 2" or "If (...)"
 * 7) Error no main method
 * 		-> Probably the signature of the main method is wrong 
 * 		-> (e.g. capitalization error)
 * 8) Incorrect casting e.g. 
 * 		   "int number = 12;
 *          String anotherNumber = number;"
 * 		-> Use transforming methods! 
 * 		-> String anotherNumber = Integer.toString(number);
 * 9) A class "Can't be resolved to a type".
 * 		-> Probably the associated library or class was not correctly imported!
 * 10) Calling a method that does not exist.
 * 		-> Usually, misspelling or capitalization error. 
 * 		-> Also always use the documentation!
 * 11) "Method is not applicable for the arguments"
 * 		"public void getStuff(double number){};
 * 		 getStuff(1);"
 * 		-> 1 is interpreted as an int! You have to write 1.0
 * 12) Local variable may not have been initialized
 * 		"String howMany;
 * 		 System.out.println(howMany);"
 * 		-> Set default values. "String howMany = "";
 * 13) Prefix vs postfix-operators
 * 		"int xInt = 1, yInt = 1;
 * 		 xInt = yInt++;"
 * 		xInt would be 1! The increment is applied after the operation!
 * 		xInt would only be 2 if the line would be "xInt = ++yInt;"
 * 		-> Ideal solution: put yInt++ on an extra line: "yInt++; xInt = yInt;"
 * 14) Switch-Statement -> do not forget the break statements at the end of each case
 * */

public class Lesson35 {
	
	public static void main(String[] args)
	{

		
	} // END OF main
	
}  // END OF CLASS Lesson35
