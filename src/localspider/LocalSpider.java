/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package localspider;

/**
 *
 * @author bilal
 */
import java.io.File;
import java.util.*;

public class LocalSpider extends Thread{

    /**
     * @param args the command line arguments
     */
    
    static String fileName;
    static String result;
    Map<String, String> mapping ;

    public LocalSpider(){
        mapping = new HashMap<String, String>();
    }
    
    public String getFileNameToSearch(){
	return fileName;
    }
    
    public void searchDirectory (File directory ){
        
	if (directory.isDirectory()){
	    search(directory);
	} 
        else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}
    }
    
    private void search(File file){
 
	if (file.isDirectory()){
            
	    if (file.canRead()){
                
		for (File temp : file.listFiles()){
                    int index=temp.toString().lastIndexOf('\\');
                    String key = temp.toString().substring(index+1,temp.toString().length());
                    
                    mapping.put(key, file.getAbsoluteFile().toString());
                    
		    if(temp.isDirectory()){
			search(temp);
		    }
                }
            }
        } 
        else{
            System.out.println(file.getAbsoluteFile() + "Permission Denied");
        }
    }
   
    @Override
    public void run(){
        searchDirectory(new File("E:\\bilal university\\6th sem\\AP\\labs"));
        String output;
        
        if (mapping.containsKey(getFileNameToSearch())){
            output = "File found    Path:  "+mapping.get(getFileNameToSearch()) +"\\"+ getFileNameToSearch();
        }
        else{
            output = getFileNameToSearch()+"  not found";
        }
        result = output;
        
    }
}