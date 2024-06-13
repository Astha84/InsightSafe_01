package com.example.firstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
                    Log.d("SDDS","queer");
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

    }
    private  void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}


