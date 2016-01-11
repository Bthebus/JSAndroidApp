package jewellerystore.com.example.jewellerystore.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jewellerystore.com.example.jewellerystore.R;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnExit = (Button) findViewById(R.id.btnExit);
        final Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        username = (EditText)findViewById(R.id.txtUsername);
        password = (EditText)findViewById(R.id.txtPassword);

        /*
        *
        * OnClickListener to handle login code goes here
        *
        *
        * */
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
                            username.setText("");
                            password.setText("");
                            Intent intent = new Intent(view.getContext(), Menu.class);
                            startActivityForResult(intent, 0);
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
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
