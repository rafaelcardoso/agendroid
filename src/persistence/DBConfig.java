package persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConfig extends SQLiteOpenHelper {
	
	public static final int DB_VERSION = 7;
	public static final String DB_NAME = "db_contacts";
	public static final String DB_TABLE_CONTACT = "contacts";
	
	public static final String DB_TABLE_CONTACT_COLUMN_ID = "contactId";
	public static final String DB_TABLE_CONTACT_COLUMN_ID_DEFINITION = "INTEGER PRIMARY KEY AUTOINCREMENT";
	
	public static final String DB_TABLE_CONTACT_COLUMN_NAME = "contactName";
	public static final String DB_TABLE_CONTACT_COLUMN_NAME_DEFINITION = "TEXT";
	
	public static final String DB_TABLE_CONTACT_COLUMN_PHONE = "contactPhone";
	public static final String DB_TABLE_CONTACT_COLUMN_PHONE_DEFINITION = "TEXT";
	
	public static final String DB_TABLE_CONTACT_COLUMN_EMAIL = "contactEmail";
	public static final String DB_TABLE_CONTACT_COLUMN_EMAIL_DEFINITION = "TEXT";
	
	public static final String DB_TABLE_CONTACT_COLUMN_GENDER = "contactGender";
	public static final String DB_TABLE_CONTACT_COLUMN_GENDER_DEFINITION = "TEXT";
	
	public static final String TABLE_CONTACT_FIELDS = "( "
		+DB_TABLE_CONTACT_COLUMN_ID+" "+DB_TABLE_CONTACT_COLUMN_ID_DEFINITION+" ,"
		+DB_TABLE_CONTACT_COLUMN_NAME+" "+DB_TABLE_CONTACT_COLUMN_NAME_DEFINITION+" ,"
		+DB_TABLE_CONTACT_COLUMN_PHONE+" "+DB_TABLE_CONTACT_COLUMN_PHONE_DEFINITION+" ,"
		+DB_TABLE_CONTACT_COLUMN_EMAIL+" "+DB_TABLE_CONTACT_COLUMN_EMAIL_DEFINITION+" ,"
		+DB_TABLE_CONTACT_COLUMN_GENDER+" "+DB_TABLE_CONTACT_COLUMN_GENDER_DEFINITION+
	" );";
	
	public static final String CREATE_TALBE_CONTACT = "CREATE TABLE "+DB_TABLE_CONTACT+" "+TABLE_CONTACT_FIELDS;
	public static final String UPDATE_TABLE_CONTACT = "DROP TABLE IF EXISTS "+ DB_TABLE_CONTACT+";";
	
	//colocar o ponto e virgula depois de fechar o parenteses
	
	public DBConfig(Context context, String name, int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TALBE_CONTACT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(UPDATE_TABLE_CONTACT);
		onCreate(db);
		
	}

	
	

}
