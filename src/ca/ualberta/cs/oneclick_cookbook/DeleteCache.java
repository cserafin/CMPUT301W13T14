package ca.ualberta.cs.oneclick_cookbook;

/**
 * Class that first asks the user if they do want to delete the entire cache before 
 * proceeding to delete the entire cache associated with this app.
 * 
 * @author Kim Kramer
 * 
 */
import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.widget.Toast;
//code partially borrowed from;
//http://stackoverflow.com/questions/2848879/how-do-i-take-advantage-of-androids-clear-cache-button


public class DeleteCache extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
	        AlertDialog.Builder adb=new AlertDialog.Builder(DeleteCache.this);
	        adb.setMessage("Do you want to clear your app cache?");
	        adb.setNegativeButton("No", null);
	        adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	deleteCache();
	            }});
	        adb.show();
    }
    
    //method called on to actually delete the cache returned by getCacheDir()
    public void deleteCache(){
    	boolean test;
    	File file =	DeleteCache.this.getCacheDir();
    	
    	test = file.delete();
    		if(test != true){
                Toast.makeText(
                        DeleteCache.this, 
                        "Error! Could not delete cache.", 
                        Toast.LENGTH_SHORT).show();
    		}
    		else{
    			Toast.makeText(DeleteCache.this, 
    					"Cache cleared!", 
    					Toast.LENGTH_SHORT).show();
    		}
    }
}
