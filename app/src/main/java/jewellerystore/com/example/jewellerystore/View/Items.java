package jewellerystore.com.example.jewellerystore.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.Item;
import jewellerystore.com.example.jewellerystore.services.implementation.ItemServiceImpl;

/**
 * Created by Braedy Thebus on 2015-11-03.
 */
public class Items extends AppCompatActivity {

    private List<Item> item;
    private boolean delete = false;
    private int pos = -1;
    ArrayList<HashMap<String, String>> columns;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        read();
        displayList();
        addItemButton();

        final Button btnBack = (Button)findViewById(R.id.btnBackItems);


        btnBack.setOnClickListener(new OnClickListener() {
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

    public void read()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ItemServiceImpl itemService = new ItemServiceImpl();
                item = itemService.findAll();

            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void displayList()
    {
        columns = new ArrayList<HashMap<String, String>>();
        hashMap = new HashMap<String, String>();
        final ListView itemsList = (ListView) findViewById(R.id.listView);

        registerForContextMenu(itemsList);

        if(item == null)
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Items.this);
            alertDialog.setMessage("There are no items to display");
            alertDialog.show();
        }

        else {

            for (Item item2 : item) {
                hashMap.put("name", item2.getName().toString());
                hashMap.put("quantity", String.valueOf(item2.getQuantity_on_hand()));
                hashMap.put("price", String.valueOf(item2.getPrice()));
                columns.add(hashMap);
                hashMap = new HashMap<String,String>();
            }
        }


        if(item != null) {

           /* if(delete == true)
            {
                columns.remove(pos);
                delete = false;
            }*/


            SimpleAdapter simpleAdapter = new SimpleAdapter(this, columns, R.layout.display_tems, new String[]{"name", "quantity", "price"}, new int[]{R.id.headerName, R.id.headerQuantity, R.id.headerPrice});
            itemsList.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
        }

        itemsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                openContextMenu(itemsList);
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {

        super.onCreateContextMenu(menu, view, menuInfo);

        if(view.getId() == R.id.listView)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);//xml file
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        switch (menuItem.getItemId())
        {
            case R.id.editItem: editItem();
                return true;
            case R.id.deleteItem: deleteItem();
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    private void addItemButton()
    {
        final Button addItemBtn = (Button) findViewById(R.id.btnAddItem);
        addItemBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Items.this, ItemDetails.class);
                    i.putExtra("context", "add");
                    startActivity(i);



                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void editItem()
    {
                if(pos > -1) {
                    Long id = item.get(pos).getId();
                    Intent i = new Intent(Items.this, ItemDetails.class);
                    i.putExtra("context", "edit");
                    i.putExtra("id", id.toString());
                    startActivity(i);

                    pos = -1;
                }

                else if (pos == -1)
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Items.this);
                    dialog.setMessage("No item was selected for edit");
                    dialog.show();
                }
    }

    public void deleteItem()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Items.this);

        dialog.setTitle("Delete Item");
        dialog.setMessage("Are you sure you want to delete this item?");
        dialog.setNegativeButton("No", null);
        dialog.setPositiveButton("Yes", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int p) {

                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Long itemID = item.get(pos).getId();
                        ItemServiceImpl itemService = new ItemServiceImpl();
                        Item dltItem = itemService.findById(itemID);
                        itemService.delete(dltItem);
                        delete = true;
                        item.remove(pos);
                        displayList();
                        //listChange();
                    }
                });

                thread1.start();

                try {

                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        dialog.show();
    }
}
