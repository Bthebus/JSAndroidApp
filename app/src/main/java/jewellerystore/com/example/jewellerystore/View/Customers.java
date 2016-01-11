package jewellerystore.com.example.jewellerystore.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.Customer;
import jewellerystore.com.example.jewellerystore.services.implementation.CustomerServiceImpl;

public class Customers extends AppCompatActivity {

    List<Customer> customerList;
    ArrayList<HashMap<String, String>> columns;
    HashMap<String,String> hashMap;
    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        hashMap = new HashMap<String, String>();
        columns = new ArrayList<HashMap<String, String>>();

        final ListView listViewCustomers = (ListView) findViewById(R.id.customerListView);
        registerForContextMenu(listViewCustomers);

        closeCustomers();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                CustomerServiceImpl customerService = new CustomerServiceImpl();
                customerList = customerService.findAll();

                if(customerList == null)
                {}

                else
                {
                    for(Customer readCustomer: customerList)
                    {
                        System.out.println("Customer Name" + readCustomer.getName().getFirstName());
                        hashMap.put("CustomerName", readCustomer.getName().getFirstName() + " " + readCustomer.getName().getSurname());
                        hashMap.put("CompanyName", readCustomer.getCompanyName());
                        columns.add(hashMap);
                        hashMap = new HashMap<String, String>();
                    }
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(customerList != null)
        {
            System.out.println("Customer list is not null!!");
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, columns,R.layout.display_customer, new String[]{"CustomerName", "CompanyName"}, new int[]{R.id.headerCustomerName, R.id.headerCompany});
            listViewCustomers.setAdapter(simpleAdapter);
        }

        listViewCustomers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                openContextMenu(listViewCustomers);
                return true;
            }
        });
    }

    public void closeCustomers()
    {
        final Button btnCloseCustomers = (Button) findViewById(R.id.btnCloseCustomers);

        btnCloseCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {

        super.onCreateContextMenu(menu, view, menuInfo);
        if(view.getId() == R.id.customerListView)
        {
            System.out.println("Menu 2");
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_customer, menu);//xml file
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        switch (menuItem.getItemId())
        {
            case R.id.viewCustomerDetails:
                Intent i = new Intent(Customers.this, CustomerDetails.class);
                Long customerID = customerList.get(pos).getId();
                i.putExtra("customerID", customerID.toString());
                startActivity(i);
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Items/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
