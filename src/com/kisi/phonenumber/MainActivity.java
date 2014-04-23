package com.kisi.phonenumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextView result;
    private Button computeSha,computeMD5;
    private EditText  Number;
    private String username,phoneNumber;
    private String SHAHash;
    public static int NO_OPTIONS=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//userName=(EditText)findViewById(R.id.userName);
        Number=(EditText)findViewById(R.id.number);
         
        computeSha=(Button)findViewById(R.id.btn1);
        computeMD5=(Button)findViewById(R.id.btn2);
         
        result= (TextView)findViewById(R.id.textView2);
         
        //get username and password entered
       // username= userName.getText().toString();
        phoneNumber= Number.getText().toString();  
     
        //Check if phone number is null
         
        if(  phoneNumber !=null && phoneNumber.equals("") )
        {
             
             
            computeSha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) 
                {
                    // TODO Auto-generated method stub
                    //call method to compute SHA hash
                    computeSHAHash(phoneNumber);
                }
            });
             
            computeMD5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) 
                {
                    // TODO Auto-generated method stub
                    //call method to compute MD5 hash
                    computeMD5Hash(phoneNumber);
                }
            });
             
             
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Enter your credentials..", Toast.LENGTH_LONG).show();
        }
             
         
     
	} // End of onCreate
	
	private static String convertToHex(byte[] data) throws java.io.IOException 
    {
            
            
           StringBuffer sb = new StringBuffer();
           String hex=null;
            
           hex=Base64.encodeToString(data, 0, data.length, NO_OPTIONS);
            
           sb.append(hex);
                        
           return sb.toString();
       }
    
	public void computeSHAHash(String password)
    {
        MessageDigest mdSha1 = null;
          try
          {
            mdSha1 = MessageDigest.getInstance("SHA-1");
          } catch (NoSuchAlgorithmException e1) {
            Log.e("myapp", "Error initializing SHA1 message digest");
          }
          try {
              mdSha1.update(password.getBytes("ASCII"));
          } catch (UnsupportedEncodingException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          byte[] data = mdSha1.digest();
          try {
              SHAHash=convertToHex(data);
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
           
          result.setText("SHA-1 hash generated is: " + " " + SHAHash);
      }
	public void computeMD5Hash(String password)
    {
 
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();
      
            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
                  
            result.setText("MD5 hash generated is: " + " " + MD5Hash);
             
            } 
            catch (NoSuchAlgorithmException e) 
            {
            e.printStackTrace();
            }
        
         
    }
     
 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
