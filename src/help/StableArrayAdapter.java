package help;

import java.util.HashMap;
import java.util.List;

import model.Contact;

import android.content.Context;
import android.widget.ArrayAdapter;

  public class StableArrayAdapter extends ArrayAdapter<Contact> {

    HashMap<Contact, Integer> mIdMap = new HashMap<Contact, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,   List<Contact> objects) {
    	
      super(context, textViewResourceId, objects);
      
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
      
      
    }

    @Override
    public long getItemId(int position) {
      Contact item = getItem(position);
      return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

  }

 