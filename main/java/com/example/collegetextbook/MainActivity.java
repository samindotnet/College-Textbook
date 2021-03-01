package com.example.collegetextbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtTitle,txtAuthor,txtBookID;
    Button btnAddBook,btnUpdateBook,btnDeleteBook,btnShowBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        txtTitle=(EditText)findViewById(R.id.EditTextTitle);
        txtAuthor=(EditText)findViewById(R.id.EditTextAuthor);
        txtBookID=(EditText)findViewById(R.id.EditTextID);
        btnAddBook=(Button)findViewById(R.id.btnInsert);
        btnUpdateBook=(Button)findViewById(R.id.btnUpdate);
        btnDeleteBook=(Button)findViewById(R.id.btnDelete);
        btnShowBook=(Button)findViewById(R.id.btnShow);
        ShowBook();
        btnAdd();
        UpdateBook();
        DeleteBook();
    }
    private void ShowBook(){
        btnShowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor acura = db.ShowData();
                if(acura.getCount()==0){
                    showMessage("Error","No Textbooks found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(acura.moveToNext()){
                   buffer.append("BookID :"+acura.getString(0)+"\n");
                   buffer.append("Title :"+acura.getString(1)+"\n");
                   buffer.append("Author :"+acura.getString(2)+"\n\n");
                }
                showMessage("TextBooks",buffer.toString());
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
        builder.setCancelable(true);
    }
    public void btnAdd(){
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bookadded = db.InsertData(txtTitle.getText().toString(),txtAuthor.getText().toString());
                if(bookadded ==true){
                    Toast.makeText(MainActivity.this,"TextBook is added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"TextBook is not added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void UpdateBook(){
        btnUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bookchange = db.UpdateData(txtBookID.getText().toString(),txtTitle.getText().toString(),txtAuthor.getText().toString());
                if(bookchange == true){
                    Toast.makeText(MainActivity.this,"Textbook is Updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Textbook is not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void DeleteBook(){
        btnDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer bookdeleted = db.DeleteData(txtBookID.getText().toString());
                if(bookdeleted >0){
                    Toast.makeText(MainActivity.this,"Textbook is deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Textbook is not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}