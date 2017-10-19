package com.ieeelautechsb.studentactivityreportingtool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ReportView extends Activity {

	TextView body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportview_activity);
        initComp();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.form_page, menu);
        return true;
    }
    private void initComp()
    {
    	body = (TextView) findViewById(R.id.report_view_body);
    	
    	SharedPreferences settings = getSharedPreferences("REPORT",0);
    	body.setText(settings.getString("BODY", ""));
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
        else if(id == R.id.home)
        {
        	startActivity(new Intent(ReportView.this, MenuPage.class));
        }
        else if(id == R.id.refresh)
        {
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
