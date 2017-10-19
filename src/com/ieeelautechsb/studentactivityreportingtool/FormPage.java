package com.ieeelautechsb.studentactivityreportingtool;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class FormPage extends Activity implements OnClickListener {

	EditText reporting_officer, officer_position, event_title, event_description, event_expense, event_venue, event_country, event_section, event_volunteer_phone, event_volunteer_email, spo_id, registration_no, sb_chairman_name, sb_chairman_phone, sb_chairman_email, sb_advisor_phone, sb_advisor_email;
	String officer, position, unit, title, type, description, expense, mga, venue, country, date, section, vPhone, vEmail, spo, registration, cName, cPhone, cEmail, aPhone, aEmail, body;
	Spinner reporting_unit, event_type;
	LinearLayout layout;
	DatePicker date_picker;
	ImageButton send, reset;
	RadioGroup mgaSelection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);
        initComp();
    }
    private void initComp()
    {
    	reporting_officer = (EditText) findViewById(R.id.reporting_officer); 
    	officer_position = (EditText) findViewById(R.id.officer_position);
    	event_title = (EditText) findViewById(R.id.event_title);
    	event_description = (EditText) findViewById(R.id.event_description);
    	event_expense = (EditText) findViewById(R.id.event_expense);
    	event_venue = (EditText) findViewById(R.id.event_venue);
    	event_country = (EditText) findViewById(R.id.event_country);
    	event_section = (EditText) findViewById(R.id.event_section);
    	event_volunteer_phone = (EditText) findViewById(R.id.event_volunteer_phone);
    	event_volunteer_email = (EditText) findViewById(R.id.event_volunteer_email);
    	spo_id = (EditText) findViewById(R.id.spo_id);
    	registration_no = (EditText) findViewById(R.id.registration_number);
    	sb_chairman_name = (EditText) findViewById(R.id.sb_chairman_name);
    	sb_chairman_phone = (EditText) findViewById(R.id.sb_chairman_phone);
    	sb_chairman_email = (EditText) findViewById(R.id.sb_chairman_email);
    	sb_advisor_phone = (EditText) findViewById(R.id.sb_advisor_phone);
    	sb_advisor_email = (EditText) findViewById(R.id.sb_advisor_email);
    	
    	reporting_unit = (Spinner) findViewById(R.id.reporting_uit);
    	event_type = (Spinner) findViewById(R.id.event_type);
    	
    	layout = (LinearLayout) findViewById(R.id.sb_layout);
    	
    	date_picker = (DatePicker) findViewById(R.id.event_date);
    	
    	send = (ImageButton) findViewById(R.id.send);
    	reset = (ImageButton) findViewById(R.id.reset);
    	
    	mgaSelection = (RadioGroup) findViewById(R.id.mga);
    	
    	reporting_unit.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2 == 0)
				layout.setVisibility(LinearLayout.VISIBLE);
				else
					layout.setVisibility(LinearLayout.GONE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	send.setOnClickListener(this);
    	reset.setOnClickListener(this);
    }
    private void getInput()
    {
    	officer = reporting_officer.getText().toString();
    	position = officer_position.getText().toString();
    	unit = reporting_unit.getSelectedItem().toString();
    	title = event_title.getText().toString(); 
    	type = event_type.getSelectedItem().toString();
    	description = event_description.getText().toString();
    	expense = event_expense.getText().toString();
    	mga = (mgaSelection.getCheckedRadioButtonId() == R.id.yes ? "Required ": "Not Required");
    	venue = event_venue.getText().toString();
    	country = event_country.getText().toString();
    	date = date_picker.getYear() + " " + date_picker.getMonth() + " " + date_picker.getDayOfMonth();
    	section = event_section.getText().toString();
    	vPhone = event_volunteer_phone.getText().toString();
    	vEmail = event_volunteer_email.getText().toString();
    	spo = spo_id.getText().toString();
    	registration = registration_no.getText().toString(); 
    	cName = sb_chairman_name.getText().toString();
    	cPhone = sb_chairman_phone.getText().toString();
    	cEmail = sb_chairman_email.getText().toString();
    	aPhone = sb_advisor_phone.getText().toString(); 
    	aEmail = sb_advisor_email.getText().toString();
    	
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
    }
    private void reset()
    {
    	reporting_officer.setText("");
    	officer_position.setText("");
    	event_title.setText("");
    	event_description.setText("");
    	event_expense.setText("");
    	event_venue.setText("");
    	event_country.setText("");
    	event_section.setText("");
    	event_volunteer_phone.setText("");
    	event_volunteer_email.setText("");
    	spo_id.setText("");
    	registration_no.setText("");
    	sb_chairman_name.setText("");
    	sb_chairman_phone.setText("");
    	sb_chairman_email.setText("");
    	sb_advisor_phone.setText("");
    	sb_advisor_email.setText("");
    	
    	reporting_unit.setSelection(0);
    	event_type.setSelection(0);
    }
    private void addTodb()
    {
    	SQLiteDatabase db = openOrCreateDatabase("SARTDB", MODE_PRIVATE, null);
    	db.execSQL("CREATE TABLE IF NOT EXISTS report(REPORTING_OFFICER VARCHAR, OFFICER_POSITION VARCHAR, REPORTING_UNIT VARCHAR, EVENT_TITLE VARCHAR, EVENT_TYPE VARCHAR, EVENT_DESCRIPTION VARCHAR, EVENT_EXPENSE VARCHAR, MGA VARCHAR, EVENT_VENUE VARCHAR, EVENT_COUNTRY VARCHAR, EVENT_DATE VARCHAR, EVENT_SECTION VARCHAR, EVENT_VOLUNTEER_PHONE VARCHAR, EVENT_VOLUNTEER_EMAIL VARCHAR, SPO_ID VARCHAR, REGISTRATION_NO VARCHAR, SB_CHAIRMAN_NAME VARCHAR, SB_CHAIRMAN_PHONE VARCHAR, SB_CHAIRMAN_EMAIL VARCHAR, SB_ADVISOR_PHONE VARCHAR, SB_ADVISOR_EMAIL);");
    	db.execSQL("INSERT INTO report VALUES('" + officer + "', '"  +  position + "', '"  +  unit + "', '"  +  title + "', '"  +  type + "', '"  +  description + "', '"  +  expense + "', '"  +  mga + "', '"  +  venue + "', '"  +  country + "', '"  +  date + "', '"  +  section + "', '"  +  vPhone + "', '"  +  vEmail + "', '"  +  spo + "', '"  +  registration + "', '"  +  cName + "', '"  +  cPhone + "', '"  +  cEmail + "', '"  +  aPhone + "', '"  +  aEmail+ "');");
    	db.close();
    
    }
    private void sendMail()
    {
    	String [] mail = {"ieeenigeria@ieee.org","josiah.adebisi812@gmail.com","orismail@student.lautech.edu.ng"};
    	Intent email = new Intent(android.content.Intent.ACTION_SEND);
    	email.putExtra(android.content.Intent.EXTRA_EMAIL, mail);
    	email.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
    	email.setType("message/rfc822");
    	email.putExtra(android.content.Intent.EXTRA_TEXT, body);
    	startActivity(email);
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
        	startActivity(new Intent(FormPage.this, MenuPage.class));
        }
        else if(id == R.id.refresh)
        {
        	reset();
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		
		if(id == R.id.send)
		{
			String name = reporting_officer.getText().toString();
			String title = event_title.getText().toString();
			String desc = event_description.getText().toString();
			if(!(name.equals("") || title.equals("") || desc.equals("")))
			{
				// get input from fields
				getInput();
				
				//save to db
				addTodb();
				
				//send to email
				sendMail();
				
				//reset all fields
				reset();
				
			}
			else
			{
				Toast t = Toast.makeText(FormPage.this, "One or More Important field empty", Toast.LENGTH_LONG);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}
		}
		else if(id == R.id.reset)
		{
			reset();
		}
	}
	
}
