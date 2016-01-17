package jewellerystore.com.example.jewellerystore.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.Customer;
import jewellerystore.com.example.jewellerystore.services.implementation.CustomerServiceImpl;

public class CustomerDetails extends AppCompatActivity {

    Long customerID;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Bundle bundle = getIntent().getExtras();

        final Button btnCloseCustomerDetails = (Button) findViewById(R.id.btnCloseCustomerDetails);

        if(bundle != null)
        {
            customerID = Long.parseLong(bundle.getString("customerID"));

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    CustomerServiceImpl customerService = new CustomerServiceImpl();
                    customer = customerService.findById(customerID);
                }
            });

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(customer != null) {

                final TextView txtName = (TextView) findViewById(R.id.txtCustomerName);
                final TextView txtCompany = (TextView) findViewById(R.id.txtCustomerCompany);
                final TextView txtEmail = (TextView) findViewById(R.id.txtCustomerEmail);
                final TextView txtTel = (TextView) findViewById(R.id.txtCustomerTel);
                final TextView txtCell = (TextView) findViewById(R.id.txtCustomerCell);
                final TextView txtAddress = (TextView) findViewById(R.id.txtAddress);

                txtName.setText(customer.getName().getFirstName() + " " + customer.getName().getMiddleName() + " " + customer.getName().getSurname());
                txtCompany.setText(customer.getCompanyName());
                txtEmail.setText(customer.getContactInformation().getEmail());
                txtTel.setText(customer.getContactInformation().getTelephone());
                txtCell.setText(customer.getContactInformation().getCellphone());
                txtAddress.setText(customer.getAddress());
                /*txtAddressStreet.setText(customer.getAddress().getTown());
                txtAddressProvince.setText(customer.getAddress().getProvince());
                txtAddressCode.setText(customer.getAddress().getPostalCode());*/
            }
        }
        btnCloseCustomerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_details, menu);
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
