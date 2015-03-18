package com.example.myfirstapp1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class Tab2Activity  extends Activity
{
		ImageView Chart1= null;
		
		
		private static final String[] TEXTS = { "Historical Zestimate for the past 1 year", "Historical Zestimate for the past 5 years", "Historical Zestimate for the past 10 years" };
		private int mPosition = 0;
        private ImageSwitcher mImageSwitcher ;
    	private TextSwitcher mTextSwitcher;
    	Button buttonNext;
    	Button buttonPrev;
    	Bitmap[] images =new Bitmap[3];
    	Animation slide_in_left, slide_out_right,fade_in, fade_out;
    	
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tab2);
            Intent iTab2 = getIntent();
            String message = iTab2.getStringExtra(Result_Activity.EXTRA_MESSAGE);
          
            try
            {
         	   JSONObject jObject = new JSONObject(message);
         	   final JSONObject Info_results = jObject.getJSONObject("result");
         	   TextView add= (TextView) findViewById(R.id.addr);
         	   add.setText(Info_results.get("address").toString());   
            }
            catch (JSONException e)
            {
         	   Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            
            buttonNext = (Button) findViewById(R.id.next);
            buttonPrev = (Button) findViewById(R.id.prev);
            
            slide_in_left = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
    		slide_out_right = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
    		
    		mTextSwitcher = (TextSwitcher) findViewById(R.id.texts);            
            mTextSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
    		mTextSwitcher.setOutAnimation(this, android.R.anim.slide_in_left);
    		
    		mImageSwitcher = (ImageSwitcher) findViewById(R.id.charts);
    		mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
    		mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
    		
    		mImageSwitcher.setFactory(new ViewFactory() 
    		{
    			@Override
    			public View makeView() 
    			{
    				ImageView imageView = new ImageView(Tab2Activity.this);
    				imageView.setAdjustViewBounds(true);
    				return imageView;
    			}
    		});
    		mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
    		mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
            
            
            mTextSwitcher.setFactory(new ViewFactory()
            {
            	@Override
            public View makeView() {
                TextView textView = new TextView(Tab2Activity.this);
                textView.setTextSize(17);
                textView.setTextColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                return textView;
            }});
            
    		
    		class DownloadImagesTask extends AsyncTask<String, Void, Bitmap[]> 
            {
            	protected Bitmap[] doInBackground(String... params) 
            	{
            		String[] chartURLs = new String[3];
    				chartURLs[0] = params[0];
    				chartURLs[1] = params[1];
    				chartURLs[2] =params[2];
    			
    				for(int i=0; i<3; i++)
    				{
    				try {
    					URL imgUrl = new URL(chartURLs[i]);
    					try {
    						URLConnection connection = imgUrl.openConnection();
    						connection.connect();
    						BufferedInputStream imgRead = new BufferedInputStream(imgUrl.openStream());
    						images[i]=BitmapFactory.decodeStream(imgRead);
    						imgRead.close();
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					
    				} 
    				catch (MalformedURLException e) 
    				{
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}    			
    			return images;
            }
            protected void onPostExecute(Bitmap[] imgcharts) 
    	     {
            	super.onPostExecute(imgcharts);
            	
            	Drawable y1 =  new BitmapDrawable(getResources(),imgcharts[0]);
            	Drawable y2 = new BitmapDrawable(getResources(),imgcharts[1]);
            	Drawable y3 = new BitmapDrawable(getResources(),imgcharts[2]);
            	final Drawable IMAGES[]={ y1, y2,y3};
            	mTextSwitcher.setText(TEXTS[mPosition]);
             	mImageSwitcher.setImageDrawable(IMAGES[mPosition]);
             	
             	buttonNext.setOnClickListener(new OnClickListener() {
             	   @Override
             	   public void onClick(View arg0) 
             	   {
             		   if(mPosition == TEXTS.length-1)
             		   {
             			   mPosition = 0;
             			   mTextSwitcher.setText(TEXTS[mPosition]);
             			   mImageSwitcher.setImageDrawable(IMAGES[mPosition]);
             		   }
             		   else
             		   {
             			   ++mPosition;
             			   mTextSwitcher.setText(TEXTS[mPosition]);
             			   mImageSwitcher.setImageDrawable(IMAGES[mPosition]);
             		   }
             	   }
             	 });
             
             buttonPrev.setOnClickListener(new OnClickListener() {
             	   @Override
             	   public void onClick(View arg0) 
             	   {
             		   if(mPosition == 0)
             		   {
             			   mPosition = TEXTS.length-1;
             			   mTextSwitcher.setText(TEXTS[mPosition]);
             			   mImageSwitcher.setImageDrawable(IMAGES[mPosition]);
             		   }
             		   else
             		   {
             			   --mPosition;
             			   mTextSwitcher.setText(TEXTS[mPosition]);
             			   mImageSwitcher.setImageDrawable(IMAGES[mPosition]);
             		   }
             	   }
             	 });
             	
    	     }
           	
        }
    		
        try{
            JSONObject jObject = new JSONObject(message);   
         	final JSONObject chart_results = jObject.getJSONObject("chart");
            String URL1= chart_results.get("year1").toString();
     	   	String URL2=chart_results.get("year5").toString();
     	   	String URL3=chart_results.get("year10").toString();
     	   	DownloadImagesTask DownloadImageTask =new DownloadImagesTask();
          	DownloadImageTask.execute(URL1,URL2,URL3);
              }
              catch (JSONException e)
              {
           	   Log.e("JSON Parser", "Error parsing data " + e.toString());
              }
            
            TextView z_footer= (TextView) findViewById(R.id.zillow_footer);
     	   z_footer.setText(Html.fromHtml("<label style='font-size:12px;color:white;'> &copy; Zillow, Inc., 2006-2014.<br> " 
     	   		+"Use is subject to <a href='http://www.zillow.com/corp/Terms.htm' style='color:#564AF6'>Terms of Use</a><br>" +
     	   		"<a href='http://www.zillow.com/zestimate/' style='color:#564AF6'>What's a Zestimate?</a></label>"));
     	   z_footer.setMovementMethod(LinkMovementMethod.getInstance());  
    		
    	}

        
        
        
}
        