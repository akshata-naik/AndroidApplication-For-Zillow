package com.example.myfirstapp1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.model.GraphUser;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tab1Activity  extends Activity
{
	AlertDialog.Builder builder ;
	Facebook fb;
	Button fbShare;
	
    String message=null;
    
	
	     @SuppressWarnings("deprecation")
		@Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tab1);
            Intent iTab1 = getIntent();
            message = iTab1.getStringExtra(Result_Activity.EXTRA_MESSAGE);
            String APP_ID = getString(R.string.facebook_app_id);
            fb = new Facebook(APP_ID);
            
            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.example.myfirstapp1", 
                        PackageManager.GET_SIGNATURES);
                for (android.content.pm.Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                    }
            } catch (NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
            
           try
           {
        	   JSONObject jObject = new JSONObject(message);
        	   final JSONObject Info_results = jObject.getJSONObject("result");
        	   
        	   TextView add= (TextView) findViewById(R.id.address);
        	   add.setText(Info_results.get("address").toString());
        	   add.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        	   add.setOnClickListener(new View.OnClickListener() {
       		    public void onClick(View v) 
       		    {
       		    	Intent intent = new Intent(Intent.ACTION_VIEW);
       	            Uri data = null;
				try {
						data = Uri.parse(Info_results.get("homedetails").toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
       	            intent.setData(data);
       	            startActivity(intent);
       		    }});

        	  
        	  
        	   
        	   TextView prop= (TextView) findViewById(R.id.proptyV);
        	   prop.setText(Info_results.get("useCode").toString());
        	   
        	   TextView Yearbuilt= (TextView) findViewById(R.id.built);
        	   Yearbuilt.setText(Info_results.get("yearBuilt").toString());
        	   
        	   TextView Ltsiz= (TextView) findViewById(R.id.size);
        	   Ltsiz.setText(Info_results.get("lotSizeSqFt").toString());
        	   
        	   TextView area= (TextView) findViewById(R.id.area);
        	   area.setText(Info_results.get("finishedSqFt").toString());
        	   
        	   TextView broom= (TextView) findViewById(R.id.rooms);
        	   broom.setText(Info_results.get("bathrooms").toString());
        	   
        	   TextView bed= (TextView) findViewById(R.id.brooms);
        	   bed.setText(Info_results.get("bedrooms").toString());
        	   
        	   TextView taxY= (TextView) findViewById(R.id.Tyear);
        	   taxY.setText(Info_results.get("taxAssessmentYear").toString());
        	   
        	   TextView TaxAs= (TextView) findViewById(R.id.ass);
        	   TaxAs.setText(Info_results.get("taxAssessment").toString());
        	   
        	   TextView LastSP= (TextView) findViewById(R.id.price);
        	   LastSP.setText(Info_results.get("lastSoldPrice").toString());
        	   
        	   TextView LastSd= (TextView) findViewById(R.id.date);
        	   LastSd.setText(Info_results.get("lastSoldDate").toString());
        	   
        	   TextView ZLUpdate= (TextView) findViewById(R.id.Zest);
        	   ZLUpdate.setText("Zestimate ®Property Estimate as of "+Info_results.get("estimateLastUpdate").toString());
        	   
        	   TextView ZestAmt= (TextView) findViewById(R.id.Zamt);
        	   ZestAmt.setText(Info_results.get("estimateAmount").toString());
        	   
        	   
        	         	   
        	   
        	   
        	   TextView propVR= (TextView) findViewById(R.id.propchang);
        	   propVR.setText(Info_results.get("estimateValuationRange").toString());
        	   
        	   TextView RZAmt= (TextView) findViewById(R.id.RzAmt);
        	   RZAmt.setText(Info_results.get("restimateAmount").toString());
        	   
        	   TextView RZdate= (TextView) findViewById(R.id.Rzestim);
        	   RZdate.setText("Rent Zestimate ®Valuation as of "+Info_results.get("restimateLastUpdate").toString());
        	   
        	   
        	   TextView RzVC= (TextView) findViewById(R.id.Rzchange);
        	   
        	   String sign2=Info_results.get("restimateValueChangeSign").toString();
        	   if(sign2.equals("+"))
        	   {
        		   RzVC.setText(Info_results.get("restimateValueChange").toString());
        		   RzVC.setCompoundDrawablesWithIntrinsicBounds(R.drawable.up_g, 0, 0, 0); 
        		   
        	   }
        	   else if(sign2.equals("-"))
        	   {
        		   RzVC.setText(Info_results.get("restimateValueChange").toString());
        		   RzVC.setCompoundDrawablesWithIntrinsicBounds(R.drawable.down_r, 0, 0, 0); 
        	   }
        	   else
        	   {
        		   RzVC.setText(Info_results.get("restimateValueChange").toString());
        	   }
        	   RzVC.setText(Info_results.get("restimateValueChange").toString());
        	   
        	   
        	   TextView ZestVC= (TextView) findViewById(R.id.zchange);
        	   String sign1 = Info_results.get("estimateValueChangeSign").toString();
        	   
        	  if(sign1.equals("+"))
        	   {
        		  ZestVC.setCompoundDrawablesWithIntrinsicBounds(R.drawable.up_g,0,0,0); 
        		   ZestVC.setText(Info_results.get("estimateValueChange").toString());
        		   
        	   }
        	   else if(sign1.equals("-"))
        	   {
        		   ZestVC.setCompoundDrawablesWithIntrinsicBounds(R.drawable.down_r, 0, 0, 0); 
        		   ZestVC.setText(Info_results.get("estimateValueChange").toString());
        		   
        	   }
        	   else
        	   {
        		   ZestVC.setText(Info_results.get("estimateValueChange").toString());
        	   }
        	   TextView Rchange1= (TextView) findViewById(R.id.Rchange);
        	   Rchange1.setText(Info_results.get("restimateValuationRange").toString());
        	   
        	   TextView z_footer= (TextView) findViewById(R.id.zillow_footer);
        	   z_footer.setText(Html.fromHtml("<label style='font-size:12px;color:white;'> &copy; Zillow, Inc., 2006-2014.<br> " 
        	   		+"Use is subject to <a href='http://www.zillow.com/corp/Terms.htm' style='color:#564AF6'>Terms of Use</a><br>" +
        	   		"<a href='http://www.zillow.com/zestimate/' style='color:#564AF6'>What's a Zestimate?</a></label>"));
        	   z_footer.setMovementMethod(LinkMovementMethod.getInstance());      	
        	  
           } 
           catch (JSONException e)
           {
        	   Log.e("JSON Parser", "Error parsing data " + e.toString());
           }
           
           builder= new AlertDialog.Builder(this);
           Button fb_button = (Button) findViewById(R.id.fb);
           fb_button.setOnClickListener(new OnClickListener() {
        	   
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		     	    @Override
		     	    public void onClick(DialogInterface dialog, int which) {
		     	        switch (which){
		     	        case DialogInterface.BUTTON_POSITIVE:
		     	        {
		     	        	
		     	        	postToFb();
		     	        	break;
		 
		     	        }
		     	          case DialogInterface.BUTTON_NEGATIVE:
		     	          {
		     	        	Toast.makeText(Tab1Activity.this, "Post Cancelled", Toast.LENGTH_LONG).show();
		     	            break;
		     	          }
		     	        }
		     	    }
		     	};
		     	
		     	   
		     builder.setMessage("Post to Facebook").setPositiveButton("Post Property Details", dialogClickListener)
		        	    						   .setNegativeButton("Cancel", dialogClickListener).show();
			}
		});
        }
        
	     
	     @SuppressWarnings("deprecation")
		public void postToFb()
	     {
	    
	 		try {
	 			
	 			JSONObject fbb = new JSONObject(message);
 				//Extract the address from the JSON
 				final JSONObject Info_results = fbb.getJSONObject("result");	            
 	            String completeAddress= Info_results.get("address").toString();
 	            String homeDetailsLink = Info_results.get("homedetails").toString();
 	            
 	            String description = new String();
 	            description = "Last Sold Price:";
 	            description += Info_results.get("lastSoldPrice").toString()+ ", ";
 	            description += "30 day Overall Change: ";
 	            description += Info_results.get("estimateValueChangeSign").toString();
 	            description += Info_results.get("estimateValueChange").toString();
 	            
 	            JSONObject charts = fbb.getJSONObject("chart");
 	            String chartURL= charts.get("year1").toString();	            
 				
 		    		
        	Bundle params = new Bundle();
			params.putString("name", completeAddress);
        	params.putString("caption", "Property Information from Zillow.com");
        	params.putString("description", description);
        	params.putString("link", homeDetailsLink);
        	params.putString("picture", chartURL);
	 		

	 		if(!fb.isSessionValid())
	 		{
	 			fb.authorize(Tab1Activity.this, new DialogListener(){
	 		

	 				@Override
	 				public void onComplete(Bundle values) {
	 					// TODO Auto-generated method stub
	 					
	 				}

	 				@Override
	 				public void onFacebookError(FacebookError e) {
	 					// TODO Auto-generated method stub
	 					
	 				}

	 				@Override
	 				public void onError(DialogError e) {
	 					// TODO Auto-generated method stub
	 					
	 				}

	 				@Override
	 				public void onCancel() {
	 					// TODO Auto-generated method stub
	 					Toast.makeText(getApplicationContext(), "Post Cancelled", Toast.LENGTH_LONG).show();
	 				}
	 				
	 			});
	 		
	 		}
	 		fb.dialog(Tab1Activity.this, "feed", params, new DialogListener() {
	 			
	 			@Override
	 			public void onFacebookError(FacebookError e) {
	 				// TODO Auto-generated method stub
	 				Toast.makeText(getApplicationContext(), "Some Error occurred", Toast.LENGTH_LONG).show();
	 			}
	 			
	 			@Override
	 			public void onError(DialogError e) {
	 				// TODO Auto-generated method stub
	 				
	 			}
	 			
	 			@Override
	 			public void onComplete(Bundle values) {
	 				// TODO Auto-generated method stub
	 				final String postId = values.getString("post_id");
	                 if (postId != null) {
	                     Toast.makeText(Tab1Activity.this,
	                         "Posted story, id: "+postId,
	                         Toast.LENGTH_SHORT).show();
	                 }
	 				//Toast.makeText(getApplicationContext(), "Posted Story, ID: 8305869854571548_859754862536589652 ", Toast.LENGTH_LONG).show();
	 			}
	 			
	 			@Override
	 			public void onCancel() {
	 				// TODO Auto-generated method stub
	 				Toast.makeText(Tab1Activity.this.getApplicationContext(), "Post Cancelled", Toast.LENGTH_LONG).show();
	 			}
	 		});
	 		}
	 		catch (JSONException e1)
	 		{
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}	
	     }
	     
	     
        public void fbLogin()
    	{
    	
        Session.openActiveSession(this, true, new Session.StatusCallback() {

			  // callback when session changes state
			  @SuppressWarnings("deprecation")
			@Override
			  public void call(Session session, SessionState state, Exception exception) {
			    if (session.isOpened())
			    {
			    	Request.executeMeRequestAsync(session, new Request.GraphUserCallback() 
			    	{ 
			    	  public void onCompleted(GraphUser user, Response response) 
			    	  {
			          if (user != null) 
			          {
			        	  publishFeedDialog();
			          }
			        }

					
			      });
			    }
			  }
			}); 
    	}
        
        
        
        
        @SuppressWarnings("deprecation")
        private void publishFeedDialog() 
        {
       	 try 
             {
 				JSONObject fbb = new JSONObject(message);
 				//Extract the address from the JSON
 				final JSONObject Info_results = fbb.getJSONObject("result");	            
 	            String completeAddress= Info_results.get("address").toString();
 	            String homeDetailsLink = Info_results.get("homedetails").toString();
 	           
 	            String description = new String();
 	            description = "Last Sold Price:";
 	            description += Info_results.get("lastSoldPrice").toString()+ ", ";
 	            description += "30 day Overall Change: ";
 	            description += Info_results.get("estimateValueChangeSign").toString();
 	            description += Info_results.get("estimateValueChange").toString();
 	            
 	            JSONObject charts = fbb.getJSONObject("chart");
 	            String chartURL= charts.get("year1").toString();	            
 				
 		    		
        	Bundle params = new Bundle();
			params.putString("name", completeAddress);
        	params.putString("caption", "Property Information from Zillow.com");
        	params.putString("description", description);
        	params.putString("link", homeDetailsLink);
        	params.putString("picture", chartURL);
        	
        	
        	WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(Tab1Activity.this,Session.getActiveSession(), params))
                    .setOnCompleteListener(new OnCompleteListener() {

                        @Override
                        public void onComplete(Bundle values,FacebookException error) 
                        {
                            if (error == null) {
                                // When the story is posted, echo the success
                                // and the post Id.
                                final String postId = values.getString("post_id");
                                if (postId != null) {
                                    Toast.makeText(Tab1Activity.this, "Posted story, id: "+postId, Toast.LENGTH_SHORT).show();
                                } else {
                                    // User clicked the Cancel button
                                	Toast.makeText(Tab1Activity.this.getApplicationContext(), "Post Cancelled", Toast.LENGTH_LONG).show();
                                }
                            } 
                            else if (error instanceof FacebookOperationCanceledException) {
                                // User clicked the "x" button
                                Toast.makeText(Tab1Activity.this.getApplicationContext(), 
                                    "Publish cancelled", 
                                    Toast.LENGTH_SHORT).show();
                            } else {
                                // Generic, ex: network error
                                Toast.makeText(Tab1Activity.this.getApplicationContext(), 
                                    "Error posting story", 
                                    Toast.LENGTH_SHORT).show();
                            }
                        }

                    })
                    .build();
                feedDialog.show();
             }
       	catch (JSONException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
        }
    		 
        
        
}
