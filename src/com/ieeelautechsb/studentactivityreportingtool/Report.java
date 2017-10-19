package com.ieeelautechsb.studentactivityreportingtool;

import java.util.*;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Report extends ActionBarActivity implements OnItemClickListener {

	ListView listview;
	List<String> reportTitle, reportDate;
	List<String[]> reportData;
	String officer, position, unit, title, type, description, expense, mga, venue, country, date, section, vPhone, vEmail, spo, registration, cName, cPhone, cEmail, aPhone, aEmail, body;
	String [] month = {"January", "February","March","April","May","June","July","August", "September","Octomber","November","December"};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        initComp();
    }
    private void initComp()
    {
    	listview = (ListView) findViewById(R.id.report_listview);
    	reportTitle = new ArrayList<String>();
    	reportDate = new ArrayList<String>();
    	reportData = new ArrayList<String[]>();
    	
    	SQLiteDatabase db = openOrCreateDatabase("SARTDB", MODE_PRIVATE, null);
    	db.execSQL("CREATE TABLE IF NOT EXISTS report(REPORTING_OFFICER VARCHAR, OFFICER_POSITION VARCHAR, REPORTING_UNIT VARCHAR, EVENT_TITLE VARCHAR, EVENT_TYPE VARCHAR, EVENT_DESCRIPTION VARCHAR, EVENT_EXPENSE VARCHAR, MGA VARCHAR, EVENT_VENUE VARCHAR, EVENT_COUNTRY VARCHAR, EVENT_DATE VARCHAR, EVENT_SECTION VARCHAR, EVENT_VOLUNTEER_PHONE VARCHAR, EVENT_VOLUNTEER_EMAIL VARCHAR, SPO_ID VARCHAR, REGISTRATION_NO VARCHAR, SB_CHAIRMAN_NAME VARCHAR, SB_CHAIRMAN_PHONE VARCHAR, SB_CHAIRMAN_EMAIL VARCHAR, SB_ADVISOR_PHONE VARCHAR, SB_ADVISOR_EMAIL);");
    	
    	Cursor c = db.rawQuery("SELECT * FROM report", null);
    	String [] record;
    	while(c.moveToNext())
    	{
    		record = new String[21];
    		
    		record[0] = c.getString(0);
    		record[1] = c.getString(1);
    		record[2] = c.getString(2);
    		record[3] = c.getString(3);
    		record[4] = c.getString(4);
    		record[5] = c.getString(5);
    		record[6] = c.getString(6);
    		record[7] = c.getString(7);
    		record[8] = c.getString(8);
    		record[9] = c.getString(9);
    		record[10] = c.getString(10);
    		record[11] = c.getString(11);
    		record[12] = c.getString(12);
    		record[13] = c.getString(13);
    		record[14] = c.getString(14);
    		record[15] = c.getString(15);
    		record[16] = c.getString(16);
    		record[17] = c.getString(17);
    		record[18] = c.getString(18);
    		record[19] = c.getString(19);
    		record[20] = c.getString(20);
    		
    		String date = month[Integer.parseInt(record[10].split(" ")[1]) - 1] + " "+ record[10].split(" ")[2] + ", " + record[10].split(" ")[0];
    		
    		reportDate.add(date);
    		reportTitle.add(record[3]);
    		reportData.add(record);
    	}
    	db.close();
    	
    	if(reportTitle.size() > 0)
    	{
	    	//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_list, R.id.list_textview, reportTitle);
	    	listview.setAdapter(new MyAdapter(this, R.layout.custom_list, R.id.list_textview, reportTitle));
	    	listview.setOnItemClickListener(this);
    	}
    	else
    	{
    		Toast t = Toast.makeText(this, "You have no report",  Toast.LENGTH_LONG);
    		t.setGravity(Gravity.CENTER,0,0);
    		t.show();
    	}
    }
    private void setData(int index)
    {
    	officer = reportData.get(index)[0];
    	position = reportData.get(index)[1]; 
    	unit = reportData.get(index)[2]; 
    	title = reportData.get(index)[3]; 
    	type = reportData.get(index)[4]; 
    	description = reportData.get(index)[5]; 
    	expense = reportData.get(index)[6]; 
    	mga = reportData.get(index)[7]; 
    	venue = reportData.get(index)[8]; 
    	country = reportData.get(index)[9]; 
    	date = reportData.get(index)[10]; 
    	section = reportData.get(index)[11]; 
    	vPhone = reportData.get(index)[12]; 
    	vEmail = reportData.get(index)[13]; 
    	spo = reportData.get(index)[14]; 
    	registration = reportData.get(index)[15]; 
    	cName = reportData.get(index)[16]; 
    	cPhone = reportData.get(index)[17]; 
    	cEmail = reportData.get(index)[18]; 
    	aPhone = reportData.get(index)[19]; 
    	aEmail = reportData.get(index)[20]; 
    	
    	String sb = "" ;
    	if(unit.equals("Student Branch"))
    	{
    		sb = "\nSPO ID.: " + 
    			  spo + 
    			  "\nRegistration Number: " + 
    			  registration + 
    			  "\nSB. Chairman Name: " + 
    			  cName + 
    			  "\nSB> Chairman Phone Number: " + 
    			  cPhone + 
    			  "\nSB. Chairman Email Address: " +
    			  cEmail + 
    			  "\nSB. Advisor Phone Number: " + 
    			  aPhone + 
    			  "\nSB. Advisor Email Address: " + 
    			  aEmail;
    	}
    	
    	body = "Activity/Event Report\nReporting Officer: " +
    			officer +
    			"\nOfficer Position: " + 
    			position + 
    			"\nReporting Unit: " + 
    			unit + 
    			sb +
    			"\nEvent Title: " + 
    			title + 
    			"\nEvent Type: " + 
    			type + 
    			"\nEvent Description/Aims: " + 
    			description + 
    			"\nEvent Expense: " + 
    			expense + 
    			"\nMGA Material: " + 
    			mga + 
    			"\nEvent Venue: " + 
    			venue + 
    			"\nEvent Country: " + 
    			country + 
    			"\nEvent Date: " + 
    			date + 
    			"\nEvent Section: " + 
    			section + 
    			"\nVolunteer Phone number(s): " +
    			vPhone + 
    			"\nVolunteer Email Address(s): " + 
    			vEmail;
    	
    	SharedPreferences settings = getSharedPreferences("REPORT",0);
    	SharedPreferences.Editor editor = settings.edit();
		editor.putString("BODY",body);
		editor.commit();
		
		startActivity(new Intent(this, ReportView.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.form_page, menu);
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
        else if(id == R.id.home)
        {
        	startActivity(new Intent(Report.this, MenuPage.class));
        }
        else if(id == R.id.refresh)
        {
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		setData(arg2);
	}
	
	private class MyAdapter extends ArrayAdapter<String>
	{

		public MyAdapter(Context context, int resource, int textViewResourceId,
				List<String> objects) {
			super(context, resource, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflate.inflate(R.layout.custom_list, parent, false);
			
			TextView title = (TextView) row.findViewById(R.id.list_textview);
			TextView date = (TextView) row.findViewById(R.id.list_textviewdate);
			
			title.setText(reportTitle.get(position));
			date.setText("Event Date: "+ reportDate.get(position));
			
			return row;
		}
		
		
	}
}
