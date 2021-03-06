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
import jewellerystore.com.example.jewellerystore.services.implementation.OrderServiceImpl;

public class Orders extends AppCompatActivity {

    List<jewellerystore.com.example.jewellerystore.model.Orders> orderList;
    ArrayList<HashMap<String, String>> columns;
    HashMap<String,String> hashMap;
    Customer customer;
    List<Customer> customerList;
    List<Long> customerIDList;
    Long customerID;

    int pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        final Button btnCloseOrders = (Button) findViewById(R.id.btnCloseOrderList);
        customerIDList = new ArrayList<Long>();

        final ListView ordersListView = (ListView) findViewById(R.id.OrdersListView);
        registerForContextMenu(ordersListView);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                OrderServiceImpl orderService = new OrderServiceImpl();
                CustomerServiceImpl customerService = new CustomerServiceImpl();
                System.out.println("In orders view");
                orderList = orderService.findAll();
                System.out.println("Number of orders" + orderList.size());
                customerList = customerService.findAll();
                System.out.println("Number of customers" + customerList.size());

            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(orderList == null || customerList == null)
        {

        }

        else
        {
            hashMap = new HashMap<String, String>();
            columns = new ArrayList<HashMap<String, String>>();

            for(jewellerystore.com.example.jewellerystore.model.Orders orders : orderList)
            {
                customerID = orders.getCustomerId();

                for(Customer customer : customerList)
                {
                    if(customer.getId().equals(customerID))
                    {
                        hashMap.put("customerName", customer.getCompanyName());
                        hashMap.put("orderDate", orders.getOrderDate());
                        columns.add(hashMap);
                        hashMap = new HashMap<String, String>();
                    }
                }
            }

            System.out.println("Size of hashmap" + columns.size());

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, columns,R.layout.display_orders, new String[]{"customerName", "orderDate"}, new int[]{R.id.headerCustomerOrders, R.id.headerOrderDate});
            ordersListView.setAdapter(simpleAdapter);
            ordersListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    pos = position;
                    openContextMenu(ordersListView);
                    return true;
                }
            });

        }

        btnCloseOrders.setOnClickListener(new View.OnClickListener() {
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
        if(view.getId() == R.id.OrdersListView);
        {

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_orders, menu);//xml file
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        switch (menuItem.getItemId())
        {
            case R.id.viewOrderDetails:
                Intent orderDetailsIntent = new Intent(Orders.this, Order_Details.class);
                orderDetailsIntent.putExtra("orderID", orderList.get(pos).getId().toString());
                startActivity(orderDetailsIntent);
                return true;

            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
