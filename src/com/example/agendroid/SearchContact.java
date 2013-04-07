package com.example.agendroid;

import help.StableArrayAdapter;

import java.util.ArrayList;

import model.Contact;
import persistence.ContactDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import controller.Controller;

public class SearchContact extends Activity implements TextWatcher, OnItemSelectedListener, OnItemLongClickListener, OnItemClickListener {
	
	private EditText edName;
	private TextView tvName;
	private TextView tvId;
	private ListView lvResult;
	private ArrayList<Contact> contacts = new ArrayList<Contact>(); 
	private StableArrayAdapter adapter;
	private ContactDAO contactDAO = new ContactDAO(SearchContact.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact);
		edName = (EditText) findViewById(R.id.searchContactName);
		edName.addTextChangedListener(this);
		lvResult = (ListView) findViewById(R.id.lvResult);
		lvResult.setOnItemSelectedListener(this);
		lvResult.setOnItemLongClickListener(this);
		lvResult.setOnItemClickListener(this);
		
		adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, contactDAO.getAllContacts());
	    lvResult.setAdapter(adapter);
	    //contactDAO.fill();
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_contact, menu);
		return true;
	}

	@Override
	public void afterTextChanged(Editable arg0) {

		

	    adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, getContactsByName(edName.getText().toString()));
	    lvResult.setAdapter(adapter);
	    
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

		//Log.i("msg","beforeTextChanged");
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		
		//Log.i("msg","onTextChanged");
		
	}

	@Override
	public void onItemSelected(AdapterView<?> adapter, View v, int arg2, long arg3) {
		
		Log.i("msg","item selecionado");
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adpter, View v, final int position, long arg3) {
		Log.i("msg","click longo");
		AlertDialog.Builder builder = new AlertDialog.Builder(SearchContact.this);
		builder.setTitle("title");
	    builder.setMessage("message");
	    builder.setPositiveButton("Edit", new DialogInterface.OnClickListener(){
	        
	    	@Override
	        public void onClick(DialogInterface dialog, int whichButton)
	        {
	    		
	    		Contact c = (Contact) adapter.getItem(position);
	    		Controller.getInstance().setContact(c);
	    		Intent i = new Intent(SearchContact.this, EditContactActivity.class);   
	    		startActivity(i);
	        }
	        
	    });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener(){
	        
	    	@Override
	        public void onClick(DialogInterface dialog, int whichButton){
	    		Contact c = (Contact) adapter.getItem(position);
	    		boolean result = contactDAO.delete(c.getContactId());
	        	AlertDialog.Builder builder = new AlertDialog.Builder(SearchContact.this);
	        	if(result){
	        		builder.setMessage("Contact deleted successfully");
	        		adapter = new StableArrayAdapter(SearchContact.this, android.R.layout.simple_list_item_1, contactDAO.getAllContacts());
	        	    lvResult.setAdapter(adapter);
	        		
	        	}else{
	        		builder.setMessage("Contact could not be deleted");
	        	}
	        	builder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        		public void onClick(DialogInterface dialog, int id) {
	        			Toast.makeText(SearchContact.this, "OK", Toast.LENGTH_SHORT).show();
	        		}
    		     });
	        	AlertDialog alert = builder.create();
     		    alert.show();
     		    
	        }
	        
	    });
	    builder.show();
	    
	    
		
		return false;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

		Contact c = adapter.getItem(position);
		Log.i("msg",c.getContactName());
		
	}
	
	public ArrayList<Contact> getContactsByName(String name){
		return contactDAO.getContacts(name);
	}

}


