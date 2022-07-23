package com.sid.firenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sid.firenotes.model.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navView;
    RecyclerView noteList;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteList = findViewById(R.id.noteList);

        drawerLayout = findViewById(R.id.drawer);
        navView = findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

//        This is for only sample data Display
        List<String> titles = new ArrayList<>();
        List<String> content = new ArrayList<>();

        titles.add("First note Samples");
        content.add("First note samples created by Mr. Siddharth Appa Sonawane");

        titles.add("Second Note Title");
        content.add("Second note content for the sample. contribution for the project is made by the mr. Suyog D. Raurale");
        titles.add("Third note Samples");
        content.add("First note samples created by Mr." +
                "sfdsdf" +
                "sdfdsf" +
                "" +
                " Siddharth Appa Sonawane");
        titles.add("Fourth note Samples");
        content.add("First note samples created by" +
                "" +
                "" +
                "" +
                "" +
                " Mr. Siddharth Appa Sonawane");


        adapter = new Adapter(titles,content);
        noteList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteList.setAdapter(adapter);


    }

//    To get click handle of the menu bar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.addNote:
                startActivity(new Intent(this,addNote.class));
                break;

            default:
                Toast.makeText(this, "Coming Soon....!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
//    This is for three dot menu of  setting

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.setting){
            Toast.makeText(this, "Setting Menu is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}