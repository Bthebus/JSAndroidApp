package jewellerystore.com.example.jewellerystore.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jewellerystore.com.example.jewellerystore.model.*;
import jewellerystore.com.example.jewellerystore.Hash;
import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.services.implementation.EmployeeServiceImpl;

public class Employee extends AppCompatActivity {

    EditText txtName;
    EditText txtSurname;
    EditText txtUsername;
    EditText txtPassword;
    EditText txtConfirmPassword;

    private String context;
    private String id;
    private Long employeeID;
    private jewellerystore.com.example.jewellerystore.model.Employee employeeEdit;
    private jewellerystore.com.example.jewellerystore.model.Employee employee2;

    boolean  validFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee);

        final Button btnSave = (Button) findViewById(R.id.btnSaveEmployee);
        final Button btnCancel = (Button) findViewById(R.id.btnCancelEmployee);

        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText) findViewById(R.id.txtSurname);
        txtUsername = (EditText) findViewById(R.id.txtEmployeeUsername);
        txtPassword = (EditText) findViewById(R.id.txtEmployeePassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);


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
                employeeID =  Long.parseLong(bundle.getString("id"));
                System.out.println("Edit ID: " + employeeID);

                editItem();
                //saveNewItem();
            }

            //saveNewItem();

        }

        cancelNewItem();




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields())
                {
                    saveNewItem();
                    Toast.makeText(getApplicationContext(),"Save Successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    public void cancelNewItem()
    {
        final Button btnCancel = (Button) findViewById(R.id.btnCancelEmployee);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setText(null);
                txtSurname.setText(null);
                txtUsername.setText(null);
                txtPassword.setText(null);
                txtConfirmPassword.setText(null);

                finish();

            }
        });
    }

    public void editItem(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
                employeeEdit = employeeService.findById(employeeID);
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Item edit" + employeeEdit.getName().getFirstName());

        txtName.setText(employeeEdit.getName().getFirstName());
        txtSurname.setText(employeeEdit.getName().getSurname());
        txtUsername.setText(employeeEdit.getUsername());
        //txtPassword.setText(employeeEdit.getPassword());
        //txtConfirmPassword.setText(employeeEdit.getName().getFirstName());

    }

    public void saveNewItem()
    {
        final Button btnSaveItem = (Button) findViewById(R.id.btnSaveEmployee);

        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (validateFields()) {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

                            employee2 = new jewellerystore.com.example.jewellerystore.model.Employee(new Name(txtName.getText().toString(),null,txtSurname.getText().toString()),txtUsername.getText().toString(),txtPassword.getText().toString());

                            System.out.println("employee service implementation");

                            if (context.equals("add")) {

                                System.out.println("Add context to save new employee");
                                employeeService.save(employee2);
                            } else if (context.equals("edit")) {
                                System.out.println("Attempting to save edited employee");
                                employeeService.update(employee2);
                            }
                            System.out.println("save employee");

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


                } else {
                    Toast.makeText(getApplicationContext(), "Ensure that all fields have been filled in", Toast.LENGTH_LONG).show();
                    /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(ItemDetails.this);
                    alertDialog.setMessage("Ensure that all fields have been filled in");
                    alertDialog.show();*/
                }
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee, menu);
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

    private boolean validateFields()
    {
       validFields = false;

       if((txtName.getText().toString().isEmpty()))
       {
            Toast.makeText(getApplicationContext(),"Please fill in name", Toast.LENGTH_SHORT).show();
       }
       else if((txtSurname.getText().toString().isEmpty()))
       {
           Toast.makeText(getApplicationContext(),"Please fill in surname", Toast.LENGTH_SHORT).show();
       }
       else if (txtUsername.getText().toString().isEmpty())
       {
           Toast.makeText(getApplicationContext(),"Please fill in username", Toast.LENGTH_SHORT).show();
       }
       else if (txtPassword.getText().toString().isEmpty())
       {
           Toast.makeText(getApplicationContext(),"Please fill in password", Toast.LENGTH_SHORT).show();
       }
       else if (txtConfirmPassword.getText().toString().isEmpty())
       {
           Toast.makeText(getApplicationContext(),"Please confirm entered password", Toast.LENGTH_SHORT).show();
       }
       else if(!((txtConfirmPassword.getText().toString()).equals(txtPassword.getText().toString())))
       {
           Toast.makeText(getApplicationContext(),"Please enter the correct password", Toast.LENGTH_SHORT).show();
       }
       else
       {
           validFields = true;
       }

        return validFields;
    }
}
