package com.example.bd01;

import java.util.ArrayList;

import persistence.ContactDAO;

import model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button btRegisterContact;
	private Button btSearchContact;
	private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        
        btRegisterContact = (Button) findViewById(R.id.registerContact);
        btSearchContact = (Button) findViewById(R.id.searchContact);
        
        btRegisterContact.setOnClickListener(this);
        btSearchContact.setOnClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		
		ContactDAO user = new ContactDAO(MainActivity.this);
		
		
		switch (v.getId()) {
		case R.id.registerContact:
			
			intent = new Intent(MainActivity.this, RegisterUserActivity.class);
			startActivity(intent);
			
			/*boolean result = user.insert("rafael");
			if(result){
				Log.i("msg","inseriu com sucesso");
			}else{
				Log.i("msg","não inseriu");
			}*/
			
			
			
		break;
		case R.id.searchContact:
			
			intent = new Intent(MainActivity.this,SearchContact.class);
			startActivity(intent);
			
			
			/*Log.i("msg", "ok");
			ArrayList<User> users = user.getUser();
			for (User u : users) {
				Log.i("msg", u.getCod()+" - "+u.getName());
			}*/
			break;
//			case R.id.updateContact:
//				if(user.update(1,"rafael")){
//					Log.i("msg","funcionou");
//				}else{
//					Log.i("msg","não funcionou");
//				}
//			break;
//			case R.id.deleteContact:
//				if(user.delete(1)){
//					Log.i("msg","funcionou");
//				}else{
//					Log.i("msg","não funcionou");
//				}
//			break;
		default:
			break;
		}
		
	}
    
}
