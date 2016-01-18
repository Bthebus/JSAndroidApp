package jewellerystore.com.example.jewellerystore.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import jewellerystore.com.example.jewellerystore.model.Employee;

import jewellerystore.com.example.jewellerystore.R;
import jewellerystore.com.example.jewellerystore.model.Name;
import jewellerystore.com.example.jewellerystore.services.implementation.EmployeeServiceImpl;

public class Employee_List extends AppCompatActivity {

    private List<Employee> employee;
    private boolean delete = false;
    private int pos = -1;
    ArrayList<HashMap<String, String>> columns;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee__list);

        columns = new ArrayList<HashMap<String, String>>();
        hashMap = new HashMap<String, String>();
        viewEmployees();
        addEmployeeButton();


        final Button btnAddNewEmployee = (Button) findViewById(R.id.btnAddEmployee);
        final Button btnBack = (Button) findViewById(R.id.btnBackEmployee);


        btnAddNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intentAddEmployee = new Intent(Employee_List.this, jewellerystore.com.example.jewellerystore.View.Employee.class);
                startActivity(intentAddEmployee);*/
            }
        });

         btnBack.setOnClickListener(new View.OnClickListener() {
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
        System.out.println("Menu 1");
        if(view.getId() == R.id.listView2)
        {
            System.out.println("Menu 2");
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);//xml file
            System.out.println("Menu 3");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        switch (menuItem.getItemId())
        {
            case R.id.editItem: editEmployee();
                return true;
            case R.id.deleteItem: deleteEmployee();
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    private void addEmployeeButton()
    {
        final Button addEmployeeBtn = (Button) findViewById(R.id.btnAddEmployee);
        addEmployeeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Employee_List.this, Employee.class);
                    i.putExtra("context", "add");
                    startActivity(i);



                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void viewEmployees() {

        final ListView employeesList = (ListView) findViewById(R.id.listView2);
        registerForContextMenu(employeesList);
/*
        employee = new ArrayList<Employee>();

        Employee emp1 = new Employee(new Name("Michael","David","Jansen"),"MJ","MJ");

        employee.add(emp1);
*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
                System.out.println("before find all");
                employee = employeeService.findAll();



                if(employee == null)
                {
                    System.out.println("employee is null");
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Employee_List.this);
                    alertDialog.setMessage("There are no employees to display");
                    alertDialog.show();
                }

                else {
                    System.out.println("employee is NOT null");

                    for (Employee employee2 : employee) {
                        hashMap.put("name", employee2.getName().getFirstName() + " " +  employee2.getName().getSurname());
                        hashMap.put("username", employee2.getUsername());
                        hashMap.put("password", String.valueOf(employee2.getPassword()));
                        columns.add(hashMap);
                        hashMap = new HashMap<String,String>();
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

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, columns,R.layout.display_employees, new String[]{"username", "name"}, new int[]{R.id.headerUsername, R.id.headerEmpName});
        simpleAdapter.notifyDataSetChanged();
        employeesList.setAdapter(simpleAdapter);

        //=====================================
        employeesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                openContextMenu(employeesList);
                return true;
            }
        });

    }

    public void editEmployee()
    {
        if(pos > -1) {
            System.out.println("Position: " + pos);
            Long id = employee.get(pos).getId();
            System.out.println("ID: " + id);

            Intent i = new Intent(Employee_List.this, Employee.class);
            i.putExtra("context", "edit");
            i.putExtra("id", id.toString());
            startActivity(i);

            pos = -1;
        }

        else if (pos == -1)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Employee_List.this);
            dialog.setMessage("No employee was selected for edit");
            dialog.show();
        }
    }

    public void deleteEmployee()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Employee_List.this);

        dialog.setTitle("Delete Employee");
        dialog.setMessage("Are you sure you want to delete this employee?");
        dialog.setNegativeButton("No", null);
        dialog.setPositiveButton("Yes", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int p) {

                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Long employeeID = employee.get(pos).getId();
                        System.out.println(employeeID);
                        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
                        Employee dltEmployee = employeeService.findById(employeeID);
                        System.out.println(dltEmployee.getName());
                        employeeService.delete(dltEmployee);
                        delete = true;
                        System.out.println("employee service delete");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_employee__list, menu);
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
