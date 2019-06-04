package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spn;
    EditText etTask;
    Button btnAdd , btnDelete , btnClear;
    ListView lvTasks;

    ArrayList<String> taskList;
    ArrayAdapter<String> aaTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.spinner);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvTasks = findViewById(R.id.listViewTask);

        taskList = new ArrayList<>();
        aaTask = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,taskList);

        lvTasks.setAdapter(aaTask);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        btnClear.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnClear.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                taskList.add(task);
                etTask.setText("");
                aaTask.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etTask.getText().toString());
                if(taskList.size() == 0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
                else if (pos > taskList.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else if (!(taskList.get(pos).isEmpty())) {
                    etTask.setText("");
                    taskList.remove(pos);
                    aaTask.notifyDataSetChanged();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                aaTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });






    }
}
