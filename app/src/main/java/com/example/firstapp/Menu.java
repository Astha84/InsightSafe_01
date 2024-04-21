package com.example.firstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
//import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstapp.databinding.ActivityHomeBinding;
import com.example.firstapp.databinding.ActivityMenuBinding;
import com.example.firstapp.fragments.AddFragment;
import com.example.firstapp.fragments.AlertFragment;
import com.example.firstapp.fragments.ChatbotFragment;
import com.example.firstapp.fragments.HomeFragment;
import com.example.firstapp.fragments.ReportsFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMenuBinding binding;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle[] actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//        }



        // to make the Navigation drawer icon always appear on the action bar

//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemId =  item.getItemId();

                if (menuItemId ==  R.id.home) {
                    replaceFragment(new HomeFragment());
                    return true;
                }
                else if (menuItemId ==  R.id.report_menu) {
                    replaceFragment(new ReportsFragment());
                    return true;
                }
                else if (menuItemId ==  R.id.favorites) {
                    replaceFragment(new AddFragment());

                    Intent myIntent = new Intent(Menu.this, Add_activity.class);
//                    myIntent.putExtra("key", value); //Optional parameters
                    Menu.this.startActivity(myIntent);

                    return true;
                }
                else if (menuItemId ==  R.id.notification_menu) {
                    replaceFragment(new AlertFragment());
                    return true;
                }

                else if (menuItemId ==  R.id.profile) {
                    replaceFragment(new ChatbotFragment());
                    return true;
                }


                return true;
            }
        });



//        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawerLayout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open,
//                R.string.nav_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private  void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
//        new NavigationBarView(this);



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//
//            case R.id.nav_account:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
//                break;
//            case R.id.nav_settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChatbotFragment()).commit();
//                break;
////            case R.id.nav_share:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
////                break;
//            case R.id.nav_logout:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddFragment()).commit();
//                break;
////            case R.id.nav_logout:
////                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
////                break;
//        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}


