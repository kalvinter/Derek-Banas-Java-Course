package lessons.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import java.awt.event.*;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.tree.*;


/* TOPIC: Tree-Element
 * 
 * */

public class Lesson27 extends JFrame {

	JButton button1;
	String outputString = "";
	
	JTree theTree;
	
	DefaultMutableTreeNode documents, work, games, emails;
	
	// Root Node
	DefaultMutableTreeNode fileSystem = new DefaultMutableTreeNode("C Drive");
	
	public static void main(String[] args) {
		new Lesson27();
	}
	
	public Lesson27() {
	
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("My Sixth Frame");
		
		JPanel thePanel = new JPanel();
		
		// ---- BUTTON ---- ---- ---- ----
		button1 = new JButton("Get Answer");
		
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		
		thePanel.add(button1);
		
		// ---- JTREE ---- ---- ---- ----
		// Create JTree(DefaultMutableTreeNode root_node) -> pass root-node!
		theTree = new JTree(fileSystem);
		
		// Set to only allow single element selection
		theTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);;
		
		// Only show 8 nodes at a time
		theTree.setVisibleRowCount(8);
		
		// addAFile(NodeName, parent-node)
		// documents is the node-version of the String "Docs"
		// documents is a child-node of fileSystem
		documents = addAFile("Docs", fileSystem);
		
		/* We add new elements to documents.
		 * Just entries -> we discard the return-value because we do not need
		 * the returned node-version to add new child-nodes 
		 * */
		addAFile("Taxes.xlsx", documents);
		addAFile("Taxes.xlsx", documents);
		addAFile("schedule.txt", documents);
		
		emails = addAFile("Emails", documents);
		addAFile("CallBob.mail", emails);
		addAFile("AW:NewStroy.mail", emails);
		
		work = addAFile("Work Application", fileSystem);
		addAFile("Calculations.xlsx", work);
		addAFile("Contract.docx", work);
		
		games = addAFile("Game Application", fileSystem);
		addAFile("GTA3.exe", games);
		addAFile("PacMan.exe", games);
		
		JScrollPane scrollBox = new JScrollPane(theTree);
		
		Dimension d = scrollBox.getPreferredSize();
		d.width = 200;
		
		scrollBox.setPreferredSize(d);
		
		thePanel.add(scrollBox);
		
		// ---- FINAL OPERATIONS ---- ---- ---- ---- 
		this.add(thePanel);
		this.setVisible(true);
		
	}
	
	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == button1 ) {
				// Get selected node
				Object treeObject = theTree.getLastSelectedPathComponent();

				// Turn it to DefaultMutableTreeNode (so that we can work with it)
				DefaultMutableTreeNode theFile = (DefaultMutableTreeNode) treeObject;
				
				// .getUserObject -> returns the Object stored at this node by the user
				String treeNode = (String) theFile.getUserObject();
				
				outputString += "The selected Node: " + treeNode + "\n";
				outputString += "Nr of Children: " + theFile.getChildCount() + "\n";
				outputString += "Nr of Siblings: " + theFile.getSiblingCount() + "\n";
				outputString += "Parent: " + theFile.getParent() + "\n";
				outputString += "Next Node: " + theFile.getNextNode() + "\n";
				outputString += "Previous Node: " + theFile.getPreviousNode() + "\n";
				outputString += "\nChildren of Node: \n";
				
				/* DefaultMutableTreeNode.children() -> Creates and returns a forward-order enumeration of this node's children. 
				 *  This is a normal for-loop (therefore, two ; are use) BUT the initial value is 
				 *  an enumeration -> returns a new value in each cycle. Similar to an iterator (?)
				 * */
				for ( Enumeration<TreeNode> enumValue = theFile.children() ; enumValue.hasMoreElements();) {
					outputString += enumValue.nextElement() + "\n";
				}
				
				outputString += "\nPath:";
				
				// Get full path to the selected Node
				TreeNode[] pathNodes = theFile.getPath();
				for ( TreeNode indivNodes : pathNodes) {
					outputString += indivNodes + "\\";
				}
				
				
				JOptionPane.showMessageDialog(Lesson27.this, outputString, "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}
	
	// fileName -> node, parentName -> parent-node
	private DefaultMutableTreeNode addAFile(String fileName, DefaultMutableTreeNode parentFolder) {
		// Create a node (DefaultMutableTreeNode)
		DefaultMutableTreeNode newFile = new DefaultMutableTreeNode(fileName);
		
		// Add this node to parent-node (i.e. add node as child-node to parent-node)
		parentFolder.add(newFile);
		
		return newFile;
	}
	
}
