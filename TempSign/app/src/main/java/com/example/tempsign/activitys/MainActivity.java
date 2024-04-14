package com.example.tempsign.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.Navigation;

import com.example.tempsign.NavigationListener;
import com.example.tempsign.R;

public class MainActivity extends AppCompatActivity implements NavigationListener{
    private FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentContainerView = findViewById(R.id.fragmentContainerView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_navigation, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.star){
            Toast.makeText(this,"star",Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.fav){
            Toast.makeText(this,"heart",Toast.LENGTH_SHORT).show();
        }
        return true;
    }*/

    @Override
    public void navigateToInfoParking(View v) {
        Navigation.findNavController(v)
                .navigate(R.id.action_global_fragmentInfoParking);
    }


}
