package lessons.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/* TOPIC: Files Creation/Deletion, getting Info & FileChooserWidget
 * 
 * */

public class Lesson31 extends JFrame {

	static String filePath, parentDirectory;
	
	/* File -> an abstract representation of file AND directory pathnames
	 * You can name / manipulate Files AND Directories with it
	 * 
	 * These files are just created in Memory. 
	 * Only after calling the appropriate method (.mkdir(), .createNewFile() etc.) 
	 * -> the virtual files are stored on the disk
	 * */
	static File randomDir, randomFile, randomFile2;  
	
	JLabel fileLabel;
	JButton chooseFileButton;
	JFileChooser fileChosser;
	
	public static void main(String[] args) {
		
		// Path defaults to project directory
		//  src/ -> means the src-folder in the project-directory 
		randomDir = new File("src/resources/Lesson31");
		randomDir.mkdir();
		System.out.println("Directory created");
		
		randomFile = new File("Lesson31_random.txt");  // Without path -> 
		randomFile2 = new File("src/resources/Lesson31/random2.txt");
		
		try {
			// Create Files
			randomFile.createNewFile();
			randomFile2.createNewFile();
			
			// Get files absolute Path -> has to be inside try / catch IOException
			filePath = randomFile.getCanonicalPath();  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (randomFile.exists()) {
			System.out.println("RandomFile exists");
			System.out.println("RandomFile is a File: " + randomFile.isFile());
			System.out.println("RandomFile Readable: " + randomFile.canRead());
			System.out.println("RandomFile Writeable: " + randomFile.canWrite());
			System.out.println("RandomFile Executable: " + randomFile.canExecute());
			System.out.println("RandomFile Location: " + filePath);
			System.out.println("RandomFile Name: " + randomFile.getName());
			
			/* This returns null  
			 * Why?
			 * -> Because the used pathname ("Lesson31_random.txt") did not specify 
			 * a parent directory. This methods only takes into account the specified
			 * path!
			 * */
			System.out.println("RandomFile Parent: " + randomFile.getParent());
		
		} else {
			System.out.println("randomFile does not exist");
		}
		System.out.println();
		
		if (randomFile2.exists()) {
			System.out.println("RandomFile2 is a File: " + randomFile2.isFile());
			System.out.println("RandomFile2 is hidden: " + randomFile2.isHidden());
			
			// Returns last modified datetime in milliseconds
			System.out.println("RandomFile2 Last Modified: " + randomFile2.lastModified());
			
			// Format the lastModified-milliseconds to Datetime-String
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			System.out.println("RandomFile2 Last Modified (Formatted): " + sdf.format(randomFile2.lastModified()));
			
			System.out.println("RandomFile2 Parent: " + randomFile2.getParent());
			System.out.println("RandomFile2 is Directoy?: " + randomFile2.isDirectory());
			System.out.println("RandomFile2 Size?: " + randomFile2.length());
			
			/* Rename a File by using .renameTo() -> needs new File-object */
			randomFile2.renameTo(new File(randomFile2.getParentFile() + "/renamed_random2.txt"));
		
		} else {
			System.out.println("randomFile2 does not exist");
		}
		System.out.println();

		if (randomDir.exists()) {
			System.out.println("RandomDir is Directoy?: " + randomDir.isDirectory());
			
			String[] filesInDir = randomDir.list();
			System.out.println("Files in Directory:");
			
			for (String fileName : filesInDir ) {
				System.out.println("- " + fileName);
				
			}
			
		} else {
			System.out.println("Directory does not exist");
		}
		
		// Why in if-statement? Returns true if successfully deleted, false otherwise
		if (randomFile.delete()) {
			System.out.println("RandomFile deleted!");
		} else {
			System.out.println("Could not delete RandomFile");
		}
		
		// You can only delete a directory if it is empty!
		// Delete all files in it first
		File[] filesInDir = randomDir.listFiles();
		for ( File fileName : filesInDir) {
			fileName.delete();
		}
		if ( randomDir.delete()) {
			System.out.println("Directory deleted!");
		} else {
			System.out.println("Could not delete directory");
		}
		
		// Open GUI
		new Lesson31();
		
	}  // END OF main
	
	/* Open a new Frame with a button "Select File"
	 * Upon click, the JFileChooser-dialog opens
	 * */
	public Lesson31() {
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel thePanel = new JPanel();
		
		fileLabel = new JLabel("Please select a File");
		chooseFileButton = new JButton("Select File");
		ListenForButton lForButton = new ListenForButton();
		chooseFileButton.addActionListener(lForButton);
		
		thePanel.add(fileLabel);
		thePanel.add(chooseFileButton);
		
		
		
		fileChosser = new JFileChooser(randomDir);
		
		
		this.add(thePanel);
		this.pack();
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == chooseFileButton) {
				fileChosser.showOpenDialog(Lesson31.this);
			}
			
		}
		
	}
	
}
