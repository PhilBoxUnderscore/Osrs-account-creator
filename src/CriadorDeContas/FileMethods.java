package CriadorDeContas;


import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMethods {

	private BufferedReader in;
	private BufferedWriter out;
	private String filepath;
	private String scriptName;

	public FileMethods(String scriptName){
		this(scriptName, false);
	}
	public FileMethods(String filePath, boolean force){
		if(force){
			this.filepath = filePath+System.getProperty("file.separator");
		}
		else{
			this.filepath = System.getProperty("scripts.path");
			//this.filepath = "C:\\Users\\Public\\Nezz\\";
			this.scriptName = filePath;
			this.filepath+=scriptName + System.getProperty("file.separator");
			System.out.println("FILE PATH: " + filepath);
		}
	}

	public void writeFile(String[] toWrite, String filename){
		filename+=".txt";
		try {
			File theDir = new File(filepath);
			// if the directory does not exist, create it
			if (!theDir.exists()) {
				boolean result = theDir.mkdirs();  
				if(result) {    
					System.out.println("DIR created");  
				}
			}
			out = new BufferedWriter(new FileWriter(filepath+filename));
			//Write out the specified string to the file
			for(int i = 0; i < toWrite.length; i++){
				if(i != toWrite.length - 1){
					out.write(toWrite[i]);
					out.newLine();
				}
				else{
					out.write(toWrite[i]);
				}
			}
			//flushes and closes the stream
			out.close();
		}catch(IOException e){
			System.out.println("There was a problem:" + e);
		}

	}
	
	public void writeFile(List<String> toWrite, String filename){
		filename+=".txt";
		try {
			File theDir = new File(filepath);
			// if the directory does not exist, create it
			if (!theDir.exists()) {
				boolean result = theDir.mkdirs();  
				if(result) {    
					System.out.println("DIR created");  
				}
			}
			out = new BufferedWriter(new FileWriter(filepath+filename));
			//Write out the specified string to the file
			for(int i = 0; i < toWrite.size(); i++){
				if(i != toWrite.size() - 1){
					out.write(toWrite.get(i));
					out.newLine();
				}
				else{
					out.write(toWrite.get(i));
				}
			}
			//flushes and closes the stream
			out.close();
		}catch(IOException e){
			System.out.println("There was a problem:" + e);
		}

	}
	
}
