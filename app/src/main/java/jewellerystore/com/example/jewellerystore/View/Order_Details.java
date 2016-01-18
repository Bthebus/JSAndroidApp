package jewellerystore.com.example.jewellerystore.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.*;
import jewellerystore.com.example.jewellerystore.services.implementation.CustomerServiceImpl;
import jewellerystore.com.example.jewellerystore.services.implementation.ItemServiceImpl;
import jewellerystore.com.example.jewellerystore.services.implementation.OrderServiceImpl;
import jewellerystore.com.example.jewellerystore.services.implementation.OrderlineServiceImpl;

public class Order_Details extends AppCompatActivity {

    List<jewellerystore.com.example.jewellerystore.model.Orders> ordersList;
    List<OrderLine> orderLineList;
    List<Customer> customerList;
    List<Item> itemList;
    List<Long> itemIdS;
    List<Integer> itemQuantities;
    List<String> itemNames;
    ArrayList<HashMap<String, String>> columns;
    HashMap<String,String> hashMap;
    Long orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__details);

        ListView orderDetailsList = (ListView) findViewById(R.id.orderDetailsListView);
        hashMap = new HashMap<String, String>();
        columns = new ArrayList<HashMap<String, String>>();

        final Button btnCloseOrderDetails = (Button) findViewById(R.id.btnCloseOrderDetails);
        final TextView lblOrderIdDetails = (TextView) findViewById(R.id.lblOrderIdDetails);
        btnCloseOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                OrderServiceImpl orderService = new OrderServiceImpl();
                OrderlineServiceImpl orderlineService = new OrderlineServiceImpl();
                CustomerServiceImpl customerService = new CustomerServiceImpl();
                ItemServiceImpl itemService = new ItemServiceImpl();

                ordersList = orderService.findAll();
                orderLineList = orderlineService.findAll();
                customerList = customerService.findAll();
                itemList = itemService.findAll();

            }
        });

        thread.start();

        try {
            thread.join();
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        Bundle bundle = getIntent().getExtras();
        orderID = Long.parseLong(bundle.getString("orderID"));
        lblOrderIdDetails.setText(orderID.toString());
        itemIdS = new ArrayList<Long>();
        itemQuantities = new ArrayList<Integer>();
        itemNames = new ArrayList<String>();

        for(OrderLine orderLine : orderLineList)
        {
            if((orderLine.getOrderId()).equals(orderID))
            {
                itemIdS.add(orderLine.getItemId());
                itemQuantities.add(orderLine.getQuantity());
            }
        }

        for(Long i : itemIdS) {
            for (Item item : itemList) {

                if(item.getId().equals(i))
                {
                    itemNames.add(item.getName());
                }

            }
        }

        int x = 0;

        while (x < itemNames.size())
        {
            hashMap.put("itemName", itemNames.get(x));
            hashMap.put("quantity", itemQuantities.get(x).toString());
            columns.add(hashMap);
            hashMap = new HashMap<String,String>();
            x++;
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, columns,R.layout.display_order_details, new String[]{"itemName", "quantity"}, new int[]{R.id.headerOrderItemName, R.id.headerOrderItemQuantity});
        orderDetailsList.setAdapter(simpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order__details, menu);
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
