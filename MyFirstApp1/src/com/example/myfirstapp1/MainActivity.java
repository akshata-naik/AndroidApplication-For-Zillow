package com.example.myfirstapp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.AppEventsLogger;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp1.MESSAGE";

	
	@Override
	protected void onResume() {
	  super.onResume();

	  // Logs 'install' and 'app activate' App Events.
	  AppEventsLogger.activateApp(this);
	}
	
	int temp = 0;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       View hr = (View)findViewById(R.id.hr);
       hr.setVisibility(View.VISIBLE);

    	   //Write spinner and arrayAdapter for list the String stored in array 
    	  final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
    	  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
    	  this, R.array.State, android.R.layout.simple_spinner_item);
    	  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	  spinner.setAdapter(adapter);
    	  
    	  ImageView zill= (ImageView) findViewById(R.id.zillowimg);
    	  zill.setOnClickListener(new View.OnClickListener() {
     		    public void onClick(View v) 
     		    {
     		    	Intent intzw = new Intent(Intent.ACTION_VIEW);
     	            Uri data = null;
					data = Uri.parse("http://http://www.zillow.com/");
     	            intzw.setData(data);
     	            startActivity(intzw);
     		    }});
    	  
    	  final EditText StreetEditText = (EditText) findViewById(R.id.editText1);
	    	
	    	
	    	
	    	   	
	    	
	    	StreetEditText.addTextChangedListener(new TextWatcher()
	    	{
	    		
				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void afterTextChanged(Editable s) {
					String strt_text = StreetEditText.getText().toString();
		    		TextView error1= (TextView) findViewById(R.id.Error_State);
					if(strt_text.matches(""))
					{
						error1.setText("This field is required.");
					}
					else
					{
						error1.setText("");
					}
				}

			});
	    	
	    	final EditText cityEditText  = (EditText) findViewById(R.id.editText2);
	    	cityEditText.addTextChangedListener(new TextWatcher(){
	            
				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void afterTextChanged(Editable s) {
					String city_text = cityEditText.getText().toString();
					TextView error2= (TextView) findViewById(R.id.Error_city);
					if(city_text.matches(""))
					{
						
						error2.setText("This field is required.");
					}
					else
					{
						error2.setText("");
					}
					
				}

			});
    	  
	    	final Spinner spin=(Spinner) findViewById(R.id.spinner1);
	    	TextView error3= (TextView) findViewById(R.id.Error_state);
	    	

	    	spin.setOnItemSelectedListener(new OnItemSelectedListener() {

	    	        public void onItemSelected(AdapterView<?> arg0, View arg1,
	    	                int arg2, long arg3) {
	    	            // TODO Auto-generated method stub
	    	        	String state_text = spin.getSelectedItem().toString();
	    	            if(state_text.length()!=2 && temp==0)
	    	            {
	    	               temp=1;
	    	            }
	    	            else if(state_text.length()!=2 && temp!=0){
	    	            	TextView error3= (TextView) findViewById(R.id.Error_state);
	    	            	error3.setText("This field is required."); 
	    	            }
	    	            
	    	        }

	    	        @Override
	    	        public void onNothingSelected(AdapterView<?> arg0) {
	    	            // TODO Auto-generated method stub
	    	        }
	    	});

	    	
	    	
    	  Button button = (Button) findViewById(R.id.button1);
    	  
    	  button.setOnClickListener(new View.OnClickListener() {
    		    public void onClick(View v) {
    		    	
    		    	
    		    	EditText StreetEditText = (EditText) findViewById(R.id.editText1);
    		    	TextView error1= (TextView) findViewById(R.id.Error_State);
    		    	EditText cityEditText = (EditText) findViewById(R.id.editText2);
      		    	TextView error2= (TextView) findViewById(R.id.Error_city);
      		    	Spinner spin=(Spinner) findViewById(R.id.spinner1);
      		    	TextView error3= (TextView) findViewById(R.id.Error_state);
      		    	
    		    	String strt_text = StreetEditText.getText().toString();
    		    	String city_text = cityEditText.getText().toString();
    		    	String state_text = spin.getSelectedItem().toString();
    		    	
    		    	if (strt_text.matches("") && city_text.matches("") && state_text.matches("Choose State") ) {
    		    	    error1.setText("This field is required.");
    		    	    error2.setText("This field is required.");
    		    	    error3.setText("This field is required.");    		    
    		    	}
    		    	else if ( strt_text.matches("") && city_text.matches("") ) {
      		    	    error2.setText("This field is required.");
      		    	    error1.setText("This field is required.");
      		    	    
      		    	    }
    		    	else if (city_text.matches("") && state_text.matches("Choose State"))
    		    	{
    		    		error2.setText("This field is required.");
    		    	    error3.setText("This field is required."); 
    		    	   
    		    	}
    		    	else if (strt_text.matches("") && state_text.matches("Choose State"))
    		    	{
    		    		error1.setText("This field is required.");
    		    		error3.setText("This field is required.");
    		    		
    		    	}
    		    	else if (strt_text.matches(""))
    		    	{
    		    		error1.setText("This field is required.");
    		    	}
    		    	else if (city_text.matches(""))
    		    	{
    		    		error2.setText("This field is required.");
    		    	}
    		    	else if (state_text.matches("Choose State"))
    		    	{
    		    		error3.setText("This field is required.");
    		    	}
    		    	else
    		    	{
    		    		error1.setText("");
    		    	    error2.setText("");
    		    	    error3.setText(""); 
    		    	    Uri.Builder builder= Uri.parse("http://csci571homeworkNAIK-env.elasticbeanstalk.com").buildUpon();
    		    	    final String URL=builder.build().toString();
    		    	    
    		    	    
    		    		  class SendPostReqAsyncTask extends AsyncTask<String,Void,String> 
    		    		 {
    		    			 @Override
    		    		     protected String doInBackground(String...params) 
    		    		     {
    		    		    	 String strt =params[0];
    		    		    	 String city =params[1];
    		    		    	 String st=params[2];
    		    		    	 String finalResult ="";
    		    		    	 
    		    		    	 HttpClient httpClient=new DefaultHttpClient();
    		    		    	 HttpPost httpPost =new HttpPost(URL);
    		    		    	 BasicNameValuePair street_value = new BasicNameValuePair("address",strt);
    		    		    	 BasicNameValuePair city_value = new BasicNameValuePair("city",city);
    		    		    	 BasicNameValuePair state_value = new BasicNameValuePair("add",st);
    		    		    	 
    		    		    	 List<NameValuePair>nameValuePairList =new ArrayList<NameValuePair>();
    		    		    	 nameValuePairList.add(street_value);
    		    		    	 nameValuePairList.add(city_value);
    		    		    	 nameValuePairList.add(state_value);
    		    		    	 try
    		    		    	 {
    		    		    		 UrlEncodedFormEntity urlEncodedFormEntity=new UrlEncodedFormEntity(nameValuePairList,HTTP.UTF_8);
    		    		    		 httpPost.setEntity(urlEncodedFormEntity);
    		    		    		 try
    		    		    		 {
    		    		    			 HttpResponse httpResponse = httpClient.execute(httpPost);
    		    		    			 InputStream inputStream = httpResponse.getEntity().getContent();
    		    		    			 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    		    		    			 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    		    		                 StringBuilder stringBuilder = new StringBuilder();

    		    		                 String bufferedStrChunk = null;

    		    		                 while((bufferedStrChunk = bufferedReader.readLine()) != null)
    		    		                 {
    		    		                    stringBuilder.append(bufferedStrChunk);
    		    		                  }
    		    		                  finalResult = stringBuilder.toString();
    		    		                  return stringBuilder.toString();
    		    		               } 
    		    		    		   catch (ClientProtocolException cpe) 
    		    		    		   {
    		    		                   System.out.println("First Exception of HttpResponese :" + cpe);
    		    		                   cpe.printStackTrace();
    		    		               } 
    		    		    		    catch (IOException ioe) 
    		    		    		   {
    		    		                    System.out.println("Second Exception of HttpResponse :" + ioe);
    		    		                    ioe.printStackTrace();
    		    		                }

    		    		            }
    		    		    	 catch (UnsupportedEncodingException uee) 
    		    		    	 {
    		    		              System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
    		    		              uee.printStackTrace();
    		    		         }

    		    		           return finalResult;
    		    		        }

    		    		        @Override
    		    		        protected void onPostExecute(String result) 
    		    		        {
    		    		        	{
    		    		                super.onPostExecute(result);       
    		    		         	    try
    		    		         	    {
    		    		         	    	JSONObject jObject = new JSONObject(result);
											String Output_code = jObject.get("code").toString();
											if(!Output_code.equals("0"))
											{
											TextView not_found= (TextView) findViewById(R.id.Invalid_input);
	    		    		         	    not_found.setText("No exact match found -- Verify that the given address is correct.");
											}
											else
											{
												TextView not_found= (TextView) findViewById(R.id.Invalid_input);
												not_found.setText("");
												displayData(result);
											}
																					
										} 
    		    		         	    catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
    		    		                

    		    		            } 
    		    		        }   
    		    		    			 
    		    		  }
    		    		SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
      		    	    sendPostReqAsyncTask.execute(strt_text,city_text,state_text);
      		    	    
    		    		    	
    			}
			}});
    
    }
   
    protected void displayData(String result) {
    	Intent intent = new Intent(this, Result_Activity.class);
    	intent.putExtra(EXTRA_MESSAGE, result);
    	startActivity(intent);		
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
    
}
