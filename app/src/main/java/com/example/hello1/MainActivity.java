package com.example.hello1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;

    ArrayList<SectionDataModel> allSampleData;
    RecyclerViewDataAdapter adapter;
    RecyclerView my_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //createDummyData();

            my_recycler_view = findViewById(R.id.my_recycler_view);

            my_recycler_view.setHasFixedSize(true);

            my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            allSampleData = new ArrayList<>();
            adapter=new RecyclerViewDataAdapter(this,allSampleData);
            my_recycler_view.setAdapter(adapter);

        reference= FirebaseDatabase.getInstance().getReference().child("physics");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                allSampleData.add(dataSnapshot.getValue(SectionDataModel.class));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle("Section " + i);

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
}
