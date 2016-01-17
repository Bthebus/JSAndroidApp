package jewellerystore.com.example.jewellerystore.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jewellerystore.com.example.jewellerystore.Hash;
import jewellerystore.com.example.jewellerystore.model.Employee;

import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.*;
import jewellerystore.com.example.jewellerystore.services.implementation.EmployeeServiceImpl;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private List<Employee> users;
    private Employee user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnExit = (Button) findViewById(R.id.btnExit);
        final Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        username = (EditText)findViewById(R.id.txtEmployeeUsername);
        password = (EditText)findViewById(R.id.txtEmployeePassword);

/*        users = new ArrayList<Employee>();

        users.add(new Employee(new Name("Michael","David","Jansen"),"Mike101","e10adc3949ba59abbe56e057f20f883e"));
*/
       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
                System.out.println("before find all");
                users = employeeService.findAll();
                  }
                });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                        if(username.getText().toString().equals("") || password.getText().toString().equals(""))
                        {
                            username.setText(username.getText().toString());
                            Toast.makeText(getApplicationContext(), "Username AND password Required!", Toast.LENGTH_SHORT).show();
                        }
                        /*
                        *
                        * Add code to handle user login here
                        *
                        * */
                        else
                        {
                            for(Employee tempUser:users)
                            {
                                if(username.getText().toString().equalsIgnoreCase(tempUser.getUsername()))
                                {
                                    if(tempUser.getPassword().equals(Hash.md5(password.getText().toString())))
                                    {
                                        username.setText("");
                                        password.setText("");
                                        Intent intent = new Intent(view.getContext(), main_menu.class);
                                        startActivityForResult(intent, 0);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Incorrect Login credentials entered", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Incorrect Login credentials entered", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //destroys the application completely
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    System.exit(1);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
