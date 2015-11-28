package gui;

import java.io.File;

/**
 * @author 
 * @since Nov 29, 2015
 */
public class NewClass {
	public static void main(String[] args) {
				File folder = new File("data");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
			System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
	}
}
