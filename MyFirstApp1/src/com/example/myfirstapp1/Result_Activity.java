package com.example.myfirstapp1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Result_Activity extends TabActivity 
{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp1.MESSAGE";
            /** Called when the activity is first created. */
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_result_);

                    // create the TabHost that will contain the Tabs
                    TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


                    TabSpec tab1 = tabHost.newTabSpec("First Tab");
                    TabSpec tab2 = tabHost.newTabSpec("Second Tab");
                    

                   // Set the Tab name and Activity
                   // that will be opened when particular Tab will be selected
                    
                    Intent intent = getIntent();
                    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                    
                    tab1.setIndicator("Basic Info");
                    Intent iTab1 =new Intent(this,Tab1Activity.class);
                    iTab1.putExtra(EXTRA_MESSAGE, message);
                    tab1.setContent(iTab1);
                    
                    
                    tab2.setIndicator("Zestimate");
                    Intent iTab2=new Intent(this,Tab2Activity.class);
                    iTab2.putExtra(EXTRA_MESSAGE, message);
                    tab2.setContent(iTab2);

                    
                    /** Add the tabs  to the TabHost to display. */
                    tabHost.addTab(tab1);
                    tabHost.addTab(tab2);

            }
} 