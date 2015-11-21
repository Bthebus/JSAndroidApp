package jewellerystore.com.example.jewellerystore;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Braedy Thebus on 2015-11-03.
 */
public class Home extends AppCompatActivity {

    private ArrayList<String> item;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        viewItems();

        final Button btnSignOut = (Button)findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                        finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewItems() {
        ListView itemsList = (ListView) findViewById(R.id.listView);
        item = new ArrayList<String>();

        //dummy items
        item.add("dummy_item1");
        item.add("dummy_item2");
        item.add("dummy_item3");
        item.add("dummy_item4");
        item.add("dummy_item5");
        item.add("dummy_item6");
        item.add("dummy_item7");
        item.add("dummy_item8");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, item);
        itemsList.setAdapter(adapter);

        //=====================================
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, final View v, final int position, long id) {
                dialog = new Dialog(Home.this);

                dialog.setContentView(R.layout.items);
                Window view = dialog.getWindow();

                TextView itemName = (TextView)view.findViewById(R.id.txtItemName);
                TextView description = (TextView)view.findViewById(R.id.txtDescription);
                TextView price = (TextView)view.findViewById(R.id.txtPrice);

                for(int i=0; i<item.size(); i++)
                {
                    itemName.setText(item.get(i));
                    description.setText(item.get(i));
                    price.setText(item.get(i));
                }
                dialog.setTitle("Item");
                dialog.show();
            }
        });
    }
}
