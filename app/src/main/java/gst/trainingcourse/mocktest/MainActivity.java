package gst.trainingcourse.mocktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView revView;
    private RecyclerViewAdapter adapter;
    private FloatingActionButton fabAdd;
    private SQLiteHelper sqlHelper;
    private EditText edtPrice;
    private Button btnFilter;
    private Spinner spSort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter = new RecyclerViewAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        revView.setLayoutManager(manager);
        revView.setAdapter(adapter);

        sqlHelper = new SQLiteHelper(this);
        List<Vehicles> list =  sqlHelper.getAll();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intentAdd);
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price = Double.parseDouble(edtPrice.getText().toString());
                List<Vehicles> listFilter = new ArrayList<>();
                for (int i = 0; i<list.size(); i++){
                    if(list.get(i).getPrice() > price){
                        listFilter.add(list.get(i));
                    }
                }
                adapter.setListVehicles(listFilter);
                revView.setAdapter(adapter);
            }
        });
        spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Collections.sort(list, new Comparator<Vehicles>() {
                        @Override
                        public int compare(Vehicles o1, Vehicles o2) {
                            return (o1.getName().compareTo(o2.getName()));
                        }
                    });
                }
                else{
                    Collections.sort(list, new Comparator<Vehicles>() {
                        @Override
                        public int compare(Vehicles o1, Vehicles o2) {
                            return Double.compare(o1.getPrice(), o2.getPrice());
                        }
                    });
                }
                adapter.setListVehicles(list);
                revView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.mSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Vehicles> list = sqlHelper.getByName(newText);
                adapter.setListVehicles(list);
                revView.setAdapter(adapter);
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onResume() {
        List<Vehicles> list =  sqlHelper.getAll();
        adapter.setListVehicles(list);
        revView.setAdapter(adapter);
        super.onResume();
    }

    private void init() {
        revView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fltAdd);
        edtPrice = findViewById(R.id.edtDelete);
        btnFilter = findViewById(R.id.btnDelete);
        spSort = findViewById(R.id.spSort);
    }
}