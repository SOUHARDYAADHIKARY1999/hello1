package com.example.hello1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    DatabaseReference reference;

    ArrayList<SectionDataModel> allSampleData;
    RecyclerViewDataAdapter adapter;
    RecyclerView my_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //createDummyData();

            my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

            my_recycler_view.setHasFixedSize(true);

            my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            allSampleData = new ArrayList<SectionDataModel>();


        reference= FirebaseDatabase.getInstance().getReference().child("physics");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    SectionDataModel sectionDataModel=dataSnapshot1.getValue(SectionDataModel.class);
                    allSampleData.add(sectionDataModel);
                    String name=dataSnapshot1.getKey();
                    if(dataSnapshot1.getKey()!=null){
                        sectionDataModel.setHeaderTitle(dataSnapshot1.getKey());
                    }
                }
                adapter=new RecyclerViewDataAdapter(MainActivity.this,allSampleData);
                my_recycler_view.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opps.... Something bad happened", Toast.LENGTH_SHORT).show();
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
