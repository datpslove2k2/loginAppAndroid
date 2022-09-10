package com.example.android_lesson9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMonHoc;
    Button btnThem;
    EditText editMonHoc;
    Button btnCapNhat;
    Button btnDelete;

    int position = -1;
    ArrayList<String> arrayCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvMonHoc = (ListView) findViewById(R.id.monhoc);
        btnThem = (Button) findViewById(R.id.btnAdd);
        btnCapNhat = (Button) findViewById(R.id.btnEdit) ;
        btnDelete = (Button) findViewById(R.id.btnXoa);
        editMonHoc = (EditText) findViewById(R.id.editTextMonHoc) ;
        arrayCourse = new ArrayList<>();

        arrayCourse.add("PHP");
        arrayCourse.add("JS");
        arrayCourse.add("HTML");
        arrayCourse.add("MySQL");
        arrayCourse.add("CSS");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayCourse);
        lvMonHoc.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monhoc = editMonHoc.getText().toString();
                arrayCourse.add(monhoc);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Add Success!", Toast.LENGTH_SHORT).show();
            }
        });
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                editMonHoc.setText(arrayCourse.get(i));
                position = i;
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Màn hình chi tiết")
                        .setMessage("Nội dung item: " + arrayCourse.get(i))
                        .show();
                return false;
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.set(position, editMonHoc.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Edit Success!", Toast.LENGTH_SHORT).show();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want delete " + arrayCourse.get(position) + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrayCourse.remove(position);
                                adapter.notifyDataSetChanged();
                                editMonHoc.setText("");
                                Toast.makeText(MainActivity.this, "Delete Success!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }
}