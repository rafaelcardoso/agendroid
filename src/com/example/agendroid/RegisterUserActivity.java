package com.example.agendroid;

import java.util.ArrayList;

import model.Contact;
import controller.Controller;

import persistence.ContactDAO;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressLint("NewApi")
public class RegisterUserActivity extends Activity implements OnClickListener {
	
	private EditText edName;
	private EditText edPhone;
	private EditText edEmail;
	private Button btSave;
	private RadioGroup rgGender;
	private ArrayList<EditText> edTexts = new ArrayList<EditText>();
	private ArrayList<String> msgs = new ArrayList<String>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_contact);
		edName  = (EditText) findViewById(R.id.contactName);
		edPhone = (EditText) findViewById(R.id.contactPhone);
		edEmail = (EditText) findViewById(R.id.contactEmail);
		btSave = (Button) findViewById(R.id.save);
		rgGender = (RadioGroup) findViewById(R.id.contactGenderGroup);
		edName.setOnClickListener(this);
		edPhone.setOnClickListener(this);
		edEmail.setOnClickListener(this);
		btSave.setOnClickListener(this);
		edTexts.add(edName);
		edTexts.add(edPhone);
		edTexts.add(edEmail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_user, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			
			case R.id.save:
				
				int buttonId = rgGender.getCheckedRadioButtonId();
				String sexo = ((RadioButton) findViewById(buttonId)).getText().toString();
				
				Log.i("msg",sexo);
				
				
				ContactDAO c = new ContactDAO(RegisterUserActivity.this);
				boolean result = c.insert(edName.getText().toString(), edPhone.getText().toString(), edEmail.getText().toString(), sexo);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
			    builder.setCancelable(false);
			    builder.setPositiveButton("ok", null);

				if(result){
					builder.setTitle("Success");
					builder.setMessage("User Registered Successfully");
					edName.setText("");
					edPhone.setText("");
					edEmail.setText("");
					rgGender.clearCheck();
				}else{
					builder.setTitle("Error");
					builder.setMessage("User could not be Registered");
				}
				builder.show();
			break;
			

		default:
			break;
		}
		
	}
	
	public void validateData() {
		
		for (EditText edText : edTexts) {
			String text = edText.getText().toString();
			if(text == null||text.trim().isEmpty()) {
				
			}
		}
	}


}
