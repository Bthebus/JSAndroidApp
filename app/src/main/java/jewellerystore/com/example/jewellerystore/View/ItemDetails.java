package jewellerystore.com.example.jewellerystore.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.Item;
import jewellerystore.com.example.jewellerystore.services.implementation.ItemServiceImpl;

/**
 * Created by Braedy Thebus on 2015-11-03.
 */
public class ItemDetails extends AppCompatActivity {

    private boolean notEmpty = false;
    EditText itemName;
    EditText descr;
    EditText price ;
    EditText quantity;
    private String context;
    private String id;
    private Long itemID;
    private Item itemEdit;
    private Item item2;
    private boolean validQuantity;
    private boolean validPrice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            context = bundle.getString("context");
            id = bundle.getString("id");

            if(context.equals("add"))
            {
                System.out.println("Context:" + bundle.getString("context"));

            }


            else if(context.equals("edit"))
            {
                itemID =  Long.parseLong(bundle.getString("id"));
                System.out.println("Edit ID: " + itemID);

                editItem();
                //saveNewItem();
            }

            saveNewItem();

        }

        cancelNewItem();




        }

    public void cancelNewItem()
    {
        final Button btnCancel = (Button) findViewById(R.id.btnCancelItem);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemName = (EditText) findViewById(R.id.txtItemName);
                descr = (EditText) findViewById(R.id.txtItemDescription);
                price = (EditText) findViewById(R.id.txtItemPrice);
                quantity = (EditText) findViewById(R.id.txtItemQuantity);

                itemName.setText(null);
                descr.setText(null);
                price.setText(null);
                quantity.setText(null);

                finish();

            }
        });
    }

    public void editItem(){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    ItemServiceImpl itemService = new ItemServiceImpl();
                    itemEdit = itemService.findById(itemID);
                }
            });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Item edit" + itemEdit.getName());

        itemName = (EditText) findViewById(R.id.txtItemName);
        descr = (EditText) findViewById(R.id.txtItemDescription);
        price = (EditText) findViewById(R.id.txtItemPrice);
        quantity = (EditText) findViewById(R.id.txtItemQuantity);

        itemName.setText(itemEdit.getName());
        descr.setText(itemEdit.getDescription());
        price.setText(String.valueOf(itemEdit.getPrice()));
        quantity.setText(String.valueOf(itemEdit.getQuantity_on_hand()));

    }

    public void saveNewItem()
    {
        final Button btnSaveItem = (Button) findViewById(R.id.btnSaveItem);

        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notEmpty = emptyFields();

                if (notEmpty == true) {

                    validPrice = priceField();
                    validQuantity = quantityField();

                    if (validPrice && validQuantity)
                    {

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                ItemServiceImpl itemService = new ItemServiceImpl();

                                itemName = (EditText) findViewById(R.id.txtItemName);
                                descr = (EditText) findViewById(R.id.txtItemDescription);
                                price = (EditText) findViewById(R.id.txtItemPrice);
                                quantity = (EditText) findViewById(R.id.txtItemQuantity);

                                System.out.println("assigned field values to variables");
                                Double dPrice = Double.parseDouble(price.getText().toString());
                                Integer dQuantity = Integer.parseInt(quantity.getText().toString());
                                System.out.println("Conversions");

                                item2 = new Item(itemID, itemName.getText().toString(), descr.getText().toString(), dPrice, dQuantity);
                                System.out.println(item2.getId());
                                System.out.println(item2.getName());
                                System.out.println(item2.getDescription());
                                System.out.println("assign values to item");

                                System.out.println("item service implementation");

                                if (context.equals("add")) {

                                    System.out.println("Add context to save new item");
                                    itemService.save(item2);
                                }

                                else if (context.equals("edit")) {
                                    System.out.println("Attempting to save edited item");
                                    itemService.update(item2);
                                }
                                System.out.println("save item");

                                /*itemName.setText(null);
                                descr.setText(null);
                                price.setText(null);
                                quantity.setText(null);*/

                                finish();

                            }
                        });


                    thread.start();

                    try {

                        thread.join();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ensure that all fields have been filled in", Toast.LENGTH_LONG).show();
                    /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(ItemDetails.this);
                    alertDialog.setMessage("Ensure that all fields have been filled in");
                    alertDialog.show();*/
                }
            }
        });

    }

    private boolean emptyFields(){

        itemName = (EditText) findViewById(R.id.txtItemName);
        descr = (EditText) findViewById(R.id.txtItemDescription);
        price = (EditText) findViewById(R.id.txtItemPrice);
        quantity = (EditText) findViewById(R.id.txtItemQuantity);

        if((itemName.getText().toString().isEmpty()) || (descr.getText().toString().isEmpty()) || price.getText().toString().isEmpty() || quantity.getText().toString().isEmpty())
        {

        }
        else
        {

            notEmpty = true;
        }

        return notEmpty;
    }

    private boolean priceField()
    {
        price = (EditText) findViewById(R.id.txtItemPrice);

        if(Double.parseDouble(price.getText().toString()) <= 0)
        {
            Toast.makeText(getApplicationContext(), "Price cannot be less than 0", Toast.LENGTH_LONG).show();
            return false;
        }

        else
        {
            return true;
        }
    }

    private boolean quantityField()
    {
        quantity = (EditText) findViewById(R.id.txtItemQuantity);

        if(Integer.parseInt(quantity.getText().toString()) <= 0)
        {
            Toast.makeText(getApplicationContext(), "Quantity cannot be 0 or less", Toast.LENGTH_LONG).show();
            return false;
        }

        else
        {
            return true;
        }
    }

    }

