package com.example.bd01;

import model.Contact;
import persistence.ContactDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import controller.Controller;

public class EditContactActivity extends Activity implements OnClickListener {
	
	private EditText edName;
	private EditText edPhone;
	private EditText edEmail;
	private Button btSave;
	private RadioGroup rgSexo;
	private RadioButton rbMale;
	private RadioButton rbFemale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		edName  = (EditText) findViewById(R.id.editContactName);
		edPhone = (EditText) findViewById(R.id.editContactPhone);
		edEmail = (EditText) findViewById(R.id.editContactEmail);
		btSave = (Button) findViewById(R.id.editContactsave);
		rgSexo = (RadioGroup) findViewById(R.id.editContactGenderGroup);
		rbMale = (RadioButton) findViewById(R.id.editContactMale);
		rbFemale = (RadioButton) findViewById(R.id.editContactFemale);
		
		edName.setOnClickListener(this);
		edPhone.setOnClickListener(this);
		edEmail.setOnClickListener(this);
		btSave.setOnClickListener(this);
		
		
		
		Contact c = Controller.getInstance().getContact();
		edName.setText(c.getContactName());
		edPhone.setText(c.getContactPhone());
		edEmail.setText(c.getContactEmail());
		Log.i("msg",c.getContactGender());
		if(c.getContactGender().equalsIgnoreCase("Male")){
			//rgSexo.check(rbMale.getId());
			Log.i("msg","veio Male");
			rbMale.setChecked(true);
		}else if(c.getContactGender().equalsIgnoreCase("Female")){
			rbFemale.setChecked(true);
			Log.i("msg","veio Female");
			//rgSexo.check(rbFemale.getId());
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_contact, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.editContactsave:
			
			String name = edName.getText().toString();
			String phone = edPhone.getText().toString();
			String email = edEmail.getText().toString();
			
			String sexo = null;
			
			int buttonId = rgSexo.getCheckedRadioButtonId();
			if(buttonId!=-1){
				 sexo = ((RadioButton) findViewById(buttonId)).getText().toString();
				 Log.i("msg","sexo "+sexo);
			}
			
			
			
			Contact contact = Controller.getInstance().getContact();
			ContactDAO c = new ContactDAO(EditContactActivity.this);
			
			boolean result = c.update(contact.getContactId(), name, phone, email, sexo);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(EditContactActivity.this);
		    builder.setCancelable(false);
		    builder.setPositiveButton("ok", null);
				
			if(result){
				builder.setTitle("Success");
				builder.setMessage("Contact edited Successfully");
			}else{
				builder.setTitle("Error");
				builder.setMessage("Contact could not be edited");
			}
			builder.show();
			break;

		default:
			break;
		}
		
	}

}
