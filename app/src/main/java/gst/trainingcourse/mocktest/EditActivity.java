package gst.trainingcourse.mocktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText edtNameEdit, edtPriceEdit;
    private TextView txtIdEdit;
    private Spinner spTypeEdit;
    private Button btnEdit, btnBack, btnDelete;
    private SQLiteHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        sqlHelper = new SQLiteHelper(this);
        Intent i = getIntent();
        Vehicles vehicles = (Vehicles) i.getSerializableExtra("vehicles");
        txtIdEdit.setText(vehicles.getId()+"");
        edtNameEdit.setText(vehicles.getName());
        edtPriceEdit.setText(vehicles.getPrice() + "");
        switch (vehicles.getType()){
            case "Công Cộng":
                spTypeEdit.setSelection(0);
                break;
            case "Cá Nhân":
                spTypeEdit.setSelection(1);
                break;
            default:
                spTypeEdit.setSelection(0);
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vehicles vehicles= new Vehicles();
                vehicles.setId(Integer.parseInt(txtIdEdit.getText().toString()));
                vehicles.setName(edtNameEdit.getText().toString());
                vehicles.setPrice(Double.parseDouble(edtPriceEdit.getText().toString()));
                vehicles.setType(spTypeEdit.getSelectedItem().toString());
                sqlHelper.updateVehicles(vehicles);
                Intent i = new Intent(EditActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtIdEdit.getText().toString());
                sqlHelper.deleteVehicles(id);
                Intent i = new Intent(EditActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }
    private void init() {
        edtNameEdit = findViewById(R.id.edtNameEdit);
        txtIdEdit = findViewById(R.id.txtIdEdit);
        spTypeEdit = findViewById(R.id.spinTypeEdit);
        edtPriceEdit = findViewById(R.id.edtPriceEdit);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
    }
}
