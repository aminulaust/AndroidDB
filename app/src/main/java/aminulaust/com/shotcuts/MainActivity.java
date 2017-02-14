package aminulaust.com.shotcuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import android.util.Log;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    private String mtext1 = null;
    private String mtext2 = null;
    Button insertbtn;
    EditText insertedittest, insertphone;
    ArrayList<String> contactlist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        insertbtn = (Button) findViewById(R.id.buttoninsert);
        insertedittest = (EditText) findViewById(R.id.editTextname);
        insertphone = (EditText) findViewById(R.id.editTextphone);
        insertbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insetIntoDB();
                insertedittest.setText(mtext1);
                insertphone.setText(mtext2);
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" , Name: " + cn.getName() + " , Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
            contactlist.add(log);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, contactlist);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

    }
    public void insetIntoDB(){
        DatabaseHandler db = new DatabaseHandler(this);

        final String _name=insertedittest.getText().toString();
        final String _phone=insertphone.getText().toString();
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact(_name, _phone));
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" , Name: " + cn.getName() + " , Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
           // Log.d("Name: ", log);
            contactlist.add(log);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, contactlist);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }

}
