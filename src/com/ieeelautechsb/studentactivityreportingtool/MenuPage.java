package com.ieeelautechsb.studentactivityreportingtool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MenuPage extends Activity implements OnClickListener{

	ImageButton newReport, report, aboutApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
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
    	newReport = (ImageButton) findViewById(R.id.new_report);
    	report = (ImageButton) findViewById(R.id.form);
    	aboutApp = (ImageButton) findViewById(R.id.about_app);
    	
    	newReport.setOnClickListener(this);
    	report.setOnClickListener(this);
    	aboutApp.setOnClickListener(this);
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		
		switch(id)
		{
		case R.id.new_report : startActivity(new Intent(MenuPage.this, FormPage.class));
		break;
		case R.id.about_app : startActivity(new Intent(MenuPage.this, About.class));
		break;
		case R.id.form : startActivity(new Intent(MenuPage.this, Report.class));
		break;
		
		}
	}
}
