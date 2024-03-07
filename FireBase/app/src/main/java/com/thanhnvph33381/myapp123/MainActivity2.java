package com.thanhnvph33381.myapp123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {

    TextView tvKQ;
    FirebaseFirestore database;
    Context context=this;
    String strKQ="";
    Todo todo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ=findViewById(R.id.tvKQ);
        database=FirebaseFirestore.getInstance();//khoi tao
//        insert();
//        update();
//        select();
        delete();
    }
    void insert(){
        String id = UUID.randomUUID().toString();//lay 1 chuoi ngau nhien
        todo=new Todo(id , "title 11","content 11");//tao doi tuong moi de insert
        database.collection("TODO")//truy cap den bang du lieu
                .document(id) //truy cap den dong du lieu
                .set(todo.convertHashMapTo())//du du lieu vao dong
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "insert Sucess" , Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "insert fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    void  update(){
        String id="1787a41e-42fe-456c-8216-9be4aaff5a0d";//copy id vao day
        todo=new Todo(id,"title 11 update","content 11 update");//noi dung can update
        database.collection("TODO")//lay bang du lieu
                .document(id)//lay id
                .update(todo.convertHashMapTo())//thuc hien update
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "update sucess", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "update fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    void delete(){
        String id="1787a41e-42fe-456c-8216-9be4aaff5a0d";
        database.collection("TODO")
                .document(id)//truy cap vao id
                .delete()//thuc hien xoa
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "delete sucess", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "delete fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    ArrayList<Todo> select(){
        ArrayList<Todo> list=new ArrayList<>();
        database.collection("TODO")//truy cap vao bang du lieu
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            strKQ="";
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                Todo t=doc.toObject(Todo.class);//chuyen du lieu doc duoc sang ToDo
                                list.add(t);
                                strKQ+="id:"+t.getId()+"\n";
                                strKQ+="title:"+t.getTitle()+"\n";
                                strKQ+="content:"+t.getContent()+"\n";
                            }
                            tvKQ.setText(strKQ);

                        }else {
                            Toast.makeText(context, "select fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return list;
    }
}