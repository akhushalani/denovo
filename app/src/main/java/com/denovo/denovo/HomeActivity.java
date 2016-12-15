package com.denovo.denovo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        findViewById(R.id.back).setVisibility(View.GONE);
        findViewById(R.id.next).setVisibility(View.GONE);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PageAdapter adapter = new PageAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_account);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.donate_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DonateActivity.class);
                startActivity(i);
                /* writeNewItem(new Item("Book", "book.png", "Dulaney FBLA", "Abhinav Khushalani", 1.5, 4,
                        "What did Harry" +
                                " Potter know about magic? He was stuck with the decidedly un-magical Dursleys, " +
                                "who hated him. He slept in a closet and ate their leftovers. But an owl " +
                                "messenger changes all that, with an invitation to attend the Hogwarts School for" +
                                " Wizards and Witches, where it turns out Harry is already famous.",
                        new ArrayList<Question>())); */
            }
        });
    }

    private void writeNewItem(Item item) {
        DatabaseReference childRef = mDatabase.child("items").push();
        childRef.setValue(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
