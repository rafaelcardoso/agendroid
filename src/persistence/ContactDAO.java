package persistence;

import java.util.ArrayList;

import model.Contact;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContactDAO {
	
	private DBConfig BDConfig;
	private SQLiteDatabase l;
	
	public ContactDAO(Context context){
		
		BDConfig = new DBConfig(context, DBConfig.DB_NAME, DBConfig.DB_VERSION);
		
	}
	
	public boolean insert(String contactName, String contactPhone, String contactEmail, String contactGender) {
		
		boolean result = false;
		ContentValues cv = new ContentValues();
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_NAME, contactName);
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_PHONE, contactPhone);
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_EMAIL, contactEmail);
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_GENDER, contactGender);
		l = BDConfig.getWritableDatabase();
		result = l.insert(BDConfig.DB_TABLE_CONTACT, null, cv)>0;
		return result;
		
	}
	
	public boolean update(int id, String contactName, String contactPhone, String contactEmail, String contactGender) {
		
		ContentValues cv = new ContentValues();
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_NAME, contactName);
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_EMAIL, contactPhone);
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_PHONE, contactEmail);
		cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_GENDER, contactGender);
		l = BDConfig.getWritableDatabase();
		return l.update(BDConfig.DB_TABLE_CONTACT, cv, BDConfig.DB_TABLE_CONTACT_COLUMN_ID+"="+id,null)>0;
		
	}
	
	public boolean delete(int cod) {
		boolean result = false;
		l = BDConfig.getWritableDatabase();
		result = l.delete(BDConfig.DB_TABLE_CONTACT, BDConfig.DB_TABLE_CONTACT_COLUMN_ID+"="+cod,null)>0;
		return result;
	}
	
	
	public ArrayList<Contact> getContacts(String name){
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		l = BDConfig.getReadableDatabase();
		
		Cursor c = l.query(BDConfig.DB_TABLE_CONTACT, null, "contactName LIKE '"+name+"%'", null, null, null, null);
		
		while(c.moveToNext()){

			
			Contact contact = new Contact();
			
			contact.setContactId(c.getInt(0));
			contact.setContactName(c.getString(1));
			contact.setContactPhone(c.getString(2));
			contact.setContactEmail(c.getString(3));
			contact.setContactGender(c.getString(4));
			contacts.add(contact);
		}
		
		return contacts;
		
	
		 
			
		
	}
	
	public ArrayList<Contact> getAllContacts(){
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		l = BDConfig.getReadableDatabase();
		
		Cursor c = l.query(BDConfig.DB_TABLE_CONTACT, null, null, null, null, null, null);
		
		while(c.moveToNext()){

			
			Contact contact = new Contact();
			
			contact.setContactId(c.getInt(0));
			contact.setContactName(c.getString(1));
			contact.setContactPhone(c.getString(2));
			contact.setContactEmail(c.getString(3));
			contact.setContactGender(c.getString(4));
			contacts.add(contact);
		}
		
		return contacts;
	}
	
	public void fill(){
		
		 String[] names =  new String[] {"Abel", "Abelardo", "Abílio", "Abraão", "Abrahão", "Abrão", "Ada", "Adalberto", "Adalgisa", "Adão", "Babette", "Balbina", "Balraj", "Baltazar", "Bárbara", "Barbie", "Barbra", "Bartolomeu", "Basílio", "Beata", "Cacilda", "Caetano", "Caio", "Calista", "Calixta", "Calixto", "Camélia", "Camellia", "Cameron", "Camile", "Dafne", "Dagmar", "Dagmara", "Daiana", "Daiane", "Daisy", "Dália", "Dalila", "Dalton", "Dalva", "Éder", "Edgar", "Édison", "Edite", "Edith.", "Edmundo", "Edna", "Édson", "Eduardo", "Elaine", "Fábia", "Fabiana", "Fabiano", "Fábio", "Fabíola", "Fabrícia", "Fabrício", "Fabrizio", "Fanny", "Fátima", "Gabriel", "Gabriela", "Gabriele", "Gabriella", "Gabrielle", "Gaetano", "Ganesh", "Genji", "George", "Georgia", "Hadrián", "Haidê", "Haideé", "Halima", "Hamilton", "Hannah", "Haydê", "Hebe", "Hector", "Heidi", "Iara", "Ícaro", "Idalina", "Ieda", "Iemanjá", "Ignácio", "Igor", "Ilsa", "Inácio", "Indra", "Jaci", "Jacira", "Jacó", "Jacob", "Jacqueline", "Jacques", "Jacy", "Jacyra", "Jade", "Jaime", "Kaio", "Kalil", "Kalila", "Kaori", "Karen", "Karim", "Karina", "Karine", "Karla", "Laércio", "Laerte", "Laila", "Lailah", "Laís", "Laísa", "Lana", "Lara", "Larisa", "Larissa", "Mabel", "Madalena", "Mafalda", "Magali", "Magda", "Magdalena", "Magno", "Maia", "Maiara", "Maíra", "Nádia", "Nadine", "Nadir", "Nadya", "Naila", "Nailah", "Nair", "Najma", "Nancy", "Naomi", "Octávia", "Octávio", "Odete", "Odette", "Odila", "Ofélia", "Olavo", "Olga", "Olímpia", "Olímpio", "Pablo", "Paco", "Paloma", "Pamela", "Paola", "Paolo", "Pascoal", "Pasqual", "Patrícia", "Patrício", "Quincas", "Quitéria", "Rachel", "Rafael", "Rafaela", "Rafaella", "Raimundo", "Raísa", "Raíssa", "Raj", "Rajesh", "Rani", "Sabrina", "Sacha", "Safira", "Salim", "Salma", "Salomão", "Salomé", "Salvador", "Sálvio", "Samanta", "Tábata", "Tabita", "Taciana", "Tadeu", "Taís", "Takashi", "Tales", "Talita", "Tamara", "Tancredo", "Ubaldo", "Ugo", "Ulisses", "Úlrica", "Ulrika", "Umberto", "Urbano", "Úrsula", "Vagner", "Valdemar", "Valdemir", "Valdir", "Valdo", "Valdomiro", "Valentim", "Valentina", "Valentino", "Wagner", "Waldemar", "Waldemir", "Waldemiro", "Waldir", "Waldo", "Waldomiro", "Waldyr", "Wallace", "Walmir", "Xavier", "Xaviera", "Yasmim", "Yasmin", "Yasmina", "Yasmine", "Yoko", "Yolanda", "Yoná", "Yone", "Yoshi", "Yukio", "Zacarias", "Zara", "Zélia", "Zenaide", "Zenon", "Zilda", "Zoe", "Zuleica", "Zuleika", "Zulmira"};
		 String[] emails = new String[] {"Abel@gmail.com", "Abelardo@gmail.com", "Abílio@gmail.com", "Abraão@gmail.com", "Abrahão@gmail.com", "Abrão@gmail.com", "Ada@gmail.com", "Adalberto@gmail.com", "Adalgisa@gmail.com", "Adão@gmail.com", "Babette@gmail.com", "Balbina@gmail.com", "Balraj@gmail.com", "Baltazar@gmail.com", "Bárbara@gmail.com", "Barbie@gmail.com", "Barbra@gmail.com", "Bartolomeu@gmail.com", "Basílio@gmail.com", "Beata@gmail.com", "Cacilda@gmail.com", "Caetano@gmail.com", "Caio@gmail.com", "Calista@gmail.com", "Calixta@gmail.com", "Calixto@gmail.com", "Camélia@gmail.com", "Camellia@gmail.com", "Cameron@gmail.com", "Camile@gmail.com", "Dafne@gmail.com", "Dagmar@gmail.com", "Dagmara@gmail.com", "Daiana@gmail.com", "Daiane@gmail.com", "Daisy@gmail.com", "Dália@gmail.com", "Dalila@gmail.com", "Dalton@gmail.com", "Dalva@gmail.com", "Éder@gmail.com", "Edgar@gmail.com", "Édison@gmail.com", "Edite@gmail.com", "Edith.@gmail.com", "Edmundo@gmail.com", "Edna@gmail.com", "Édson@gmail.com", "Eduardo@gmail.com", "Elaine@gmail.com", "Fábia@gmail.com", "Fabiana@gmail.com", "Fabiano@gmail.com", "Fábio@gmail.com", "Fabíola@gmail.com", "Fabrícia@gmail.com", "Fabrício@gmail.com", "Fabrizio@gmail.com", "Fanny@gmail.com", "Fátima@gmail.com", "Gabriel@gmail.com", "Gabriela@gmail.com", "Gabriele@gmail.com", "Gabriella@gmail.com", "Gabrielle@gmail.com", "Gaetano@gmail.com", "Ganesh@gmail.com", "Genji@gmail.com", "George@gmail.com", "Georgia@gmail.com", "Hadrián@gmail.com", "Haidê@gmail.com", "Haideé@gmail.com", "Halima@gmail.com", "Hamilton@gmail.com", "Hannah@gmail.com", "Haydê@gmail.com", "Hebe@gmail.com", "Hector@gmail.com", "Heidi@gmail.com", "Iara@gmail.com", "Ícaro@gmail.com", "Idalina@gmail.com", "Ieda@gmail.com", "Iemanjá@gmail.com", "Ignácio@gmail.com", "Igor@gmail.com", "Ilsa@gmail.com", "Inácio@gmail.com", "Indra@gmail.com", "Jaci@gmail.com", "Jacira@gmail.com", "Jacó@gmail.com", "Jacob@gmail.com", "Jacqueline@gmail.com", "Jacques@gmail.com", "Jacy@gmail.com", "Jacyra@gmail.com", "Jade@gmail.com", "Jaime@gmail.com", "Kaio@gmail.com", "Kalil@gmail.com", "Kalila@gmail.com", "Kaori@gmail.com", "Karen@gmail.com", "Karim@gmail.com", "Karina@gmail.com", "Karine@gmail.com", "Karla@gmail.com", "Laércio@gmail.com", "Laerte@gmail.com", "Laila@gmail.com", "Lailah@gmail.com", "Laís@gmail.com", "Laísa@gmail.com", "Lana@gmail.com", "Lara@gmail.com", "Larisa@gmail.com", "Larissa@gmail.com", "Mabel@gmail.com", "Madalena@gmail.com", "Mafalda@gmail.com", "Magali@gmail.com", "Magda@gmail.com", "Magdalena@gmail.com", "Magno@gmail.com", "Maia@gmail.com", "Maiara@gmail.com", "Maíra@gmail.com", "Nádia@gmail.com", "Nadine@gmail.com", "Nadir@gmail.com", "Nadya@gmail.com", "Naila@gmail.com", "Nailah@gmail.com", "Nair@gmail.com", "Najma@gmail.com", "Nancy@gmail.com", "Naomi@gmail.com", "Octávia@gmail.com", "Octávio@gmail.com", "Odete@gmail.com", "Odette@gmail.com", "Odila@gmail.com", "Ofélia@gmail.com", "Olavo@gmail.com", "Olga@gmail.com", "Olímpia@gmail.com", "Olímpio@gmail.com", "Pablo@gmail.com", "Paco@gmail.com", "Paloma@gmail.com", "Pamela@gmail.com", "Paola@gmail.com", "Paolo@gmail.com", "Pascoal@gmail.com", "Pasqual@gmail.com", "Patrícia@gmail.com", "Patrício@gmail.com", "Quincas@gmail.com", "Quitéria@gmail.com", "Rachel@gmail.com", "Rafael@gmail.com", "Rafaela@gmail.com", "Rafaella@gmail.com", "Raimundo@gmail.com", "Raísa@gmail.com", "Raíssa@gmail.com", "Raj@gmail.com", "Rajesh@gmail.com", "Rani@gmail.com", "Sabrina@gmail.com", "Sacha@gmail.com", "Safira@gmail.com", "Salim@gmail.com", "Salma@gmail.com", "Salomão@gmail.com", "Salomé@gmail.com", "Salvador@gmail.com", "Sálvio@gmail.com", "Samanta@gmail.com", "Tábata@gmail.com", "Tabita@gmail.com", "Taciana@gmail.com", "Tadeu@gmail.com", "Taís@gmail.com", "Takashi@gmail.com", "Tales@gmail.com", "Talita@gmail.com", "Tamara@gmail.com", "Tancredo@gmail.com", "Ubaldo@gmail.com", "Ugo@gmail.com", "Ulisses@gmail.com", "Úlrica@gmail.com", "Ulrika@gmail.com", "Umberto@gmail.com", "Urbano@gmail.com", "Úrsula@gmail.com", "Vagner@gmail.com", "Valdemar@gmail.com", "Valdemir@gmail.com", "Valdir@gmail.com", "Valdo@gmail.com", "Valdomiro@gmail.com", "Valentim@gmail.com", "Valentina@gmail.com", "Valentino@gmail.com", "Wagner@gmail.com", "Waldemar@gmail.com", "Waldemir@gmail.com", "Waldemiro@gmail.com", "Waldir@gmail.com", "Waldo@gmail.com", "Waldomiro@gmail.com", "Waldyr@gmail.com", "Wallace@gmail.com", "Walmir@gmail.com", "Xavier@gmail.com", "Xaviera@gmail.com", "Yasmim@gmail.com", "Yasmin@gmail.com", "Yasmina@gmail.com", "Yasmine@gmail.com", "Yoko@gmail.com", "Yolanda@gmail.com", "Yoná@gmail.com", "Yone@gmail.com", "Yoshi@gmail.com", "Yukio@gmail.com", "Zacarias@gmail.com", "Zara@gmail.com", "Zélia@gmail.com", "Zenaide@gmail.com", "Zenon@gmail.com", "Zilda@gmail.com", "Zoe@gmail.com", "Zuleica@gmail.com", "Zuleika@gmail.com", "Zulmira@gmail.com"};
		 String[] phones = new String[]{"8317827068", "8338621179", "8345658157", "8313864475", "8376027725", "8360761570", "8324022864", "8373896085", "8316139843", "8317000784", "8365698521", "8318612288", "8376528469", "8317648472", "8344387304", "8390166535", "8397527443", "8364050183", "8336171503", "8387097271", "8339107626", "8398307707", "8341241309", "8367157453", "8371491407", "8358991352", "8387938199", "8385730988", "8377948480", "8336609610", "8397188596", "8384664437", "8364119679", "8342846754", "8387417802", "8340147404", "8392497214", "8311440667", "8314043489", "8397525946", "8317330341", "8368630900", "8316138235", "8382747699", "8375168262", "8349414428", "8372914235", "8372695706", "8313464612", "8397974628", "8359792978", "8341461127", "8396282336", "8389923176", "8397507470", "8367773744", "8348914528", "8385445670", "8353504732", "8326863008", "8322055280", "8350693329", "8311527446", "8375063848", "8382428972", "8387834138", "8315211253", "8374926187", "8388163694", "8318143632", "8372452134", "8394382924", "8375663421", "8377479258", "8377130624", "8350831684", "8326893687", "8350044860", "8323527390", "8329247188", "8348019489", "8372209257", "8359597204", "8344301825", "8362132434", "8357104674", "8312075569", "8399935852", "8342550345", "8354469191", "8326798861", "8353494514", "8394051409", "8327215196", "8328558363", "8376480382", "8315049335", "8332658505", "8351406569", "8392101918", "8339691027", "8323858703", "8386484843", "8315354449", "8390226851", "8363615468", "8355075022", "8317120538", "8313660329", "8367491302", "8335256616", "8350568707", "8339700559", "8383742710", "8383759421", "8390721883", "8340847385", "8384723879", "8390657735", "8372286619", "8339193070", "8317456596", "8325781133", "8333244480", "8333560682", "8343228386", "8398613751", "8337498906", "8364775781", "8350020321", "8329600825", "8393355697", "8362767914", "8316085669", "8397599035", "8352994765", "8368590026", "8352674058", "8359004193", "8371139244", "8320165360", "8383149698", "8321707952", "8348754809", "8366892409", "8394356262", "8339476692", "8396628683", "8379080142", "8330134428", "8368915302", "8318273213", "8336479913", "8383585325", "8340406582", "8358929485", "8326813711", "8339020334", "8385317280", "8380478381", "8377929544", "8314918106", "8373834079", "8340697459", "8319892664", "8371433115", "8382581114", "8377371580", "8324107173", "8341585307", "8348510825", "8333161423", "8324735006", "8359107666", "8370805121", "8380516304", "8353463929", "8399170703", "8377144988", "8332544071", "8329305131", "8346060290", "8339706173", "8354673933", "8329645615", "8369001645", "8313603418", "8345348215", "8396910868", "8387809588", "8325826597", "8374840413", "8391616583", "8388549565", "8315537872", "8311509248", "8359982681", "8387007875", "8377769717", "8372978744", "8328593183", "8326280543", "8395029056", "8342217079", "8374277098", "8365834178", "8322733384", "8327741028", "8365004881", "8388767261", "8349173988", "8383198901", "8334827551", "8377769051", "8337872835", "8353362056", "8346770696", "8340365143", "8387599160", "8343681565", "8328174731", "8313425758", "8318521978", "8319791315", "8390864213", "8322948740", "8320189452", "8350846894", "8398845505", "8386848059", "8323825638", "8327438689", "8313128602", "8318854695", "8358544658", "8376294590", "8373577762", "8370166931", "8392924507", "8338582643"};
		 String[] sexos = new String[]{"Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female", "Female"};
		 

		 for (int i = 0; i < names.length; i++) {
			 	ContentValues cv = new ContentValues();
			 	cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_NAME, names[i]);
				cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_PHONE, phones[i]);
				cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_EMAIL, emails[i]);
				cv.put(DBConfig.DB_TABLE_CONTACT_COLUMN_GENDER, sexos[i]);
				l = BDConfig.getWritableDatabase();
				if(l.insert(BDConfig.DB_TABLE_CONTACT, null, cv)>0){
					Log.i("msg",names[i]+" cadastrado");
				}else{
					Log.i("msg","do not work");
				}
		}
		
	}
	
}
	


