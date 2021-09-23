package gst.trainingcourse.mocktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    private EditText edtNameAdd, edtPriceAdd;
    private Spinner spTypeAdd;
    private Button  btnAddAdd, btnBackAdd;
    private SQLiteHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        sqlHelper = new SQLiteHelper(AddActivity.this);
        btnAddAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vehicles vehicles = new Vehicles();
                vehicles.setName(edtNameAdd.getText().toString());
                vehicles.setType(spTypeAdd.getSelectedItem().toString());
                vehicles.setPrice(Double.parseDouble(edtPriceAdd.getText().toString()));
                sqlHelper.addVehicles(vehicles);
                Intent t = new Intent(AddActivity.this, MainActivity.class);
                startActivity(t);
            }
        });
        btnBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        edtNameAdd = findViewById(R.id.edtNameAdd);
        spTypeAdd = findViewById(R.id.spinTypeAdd);
        edtPriceAdd = findViewById(R.id.edtPriceAdd);
        btnAddAdd = findViewById(R.id.btnAddAdd);
        btnBackAdd = findViewById(R.id.btnBackAdd);
    }
}
