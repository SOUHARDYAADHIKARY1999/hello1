package com.example.hello1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;

    ArrayList<SectionDataModel> allSampleData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);

        allSampleData = new ArrayList<SectionDataModel>();

        /*if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("G PlayStore");
        }*/
            //createDummyData();
        this.allSampleData = new ArrayList();
        RecyclerViewDataAdapter recyclerViewDataAdapter = new RecyclerViewDataAdapter(this.getApplicationContext(), this.allSampleData);
        FirebaseDatabase.getInstance().getReference().child("Yoga").addChildEventListener(new ChildEventListener(this.getApplicationContext(),recyclerViewDataAdapter){
            MainActivity mainActivity;
            RecyclerViewDataAdapter recyclerViewDataAdapter;

            public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
            }

            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String string) {
                this.mainActivity.allSampleData.add(dataSnapshot.getValue(SectionDataModel.class));
                RecyclerViewDataAdapter recyclerViewDataAdapter = this.recyclerViewDataAdapter;
                recyclerViewDataAdapter.notifyItemInserted(-1 + recyclerViewDataAdapter.getItemCount());
            }

            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String string) {
            }

            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String string) {
            }

            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {
            }
        });
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }

}
