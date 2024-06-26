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

        fragmentContainerView = findViewById(R.id.fragmentContainerView);
    }

    @Override
    public void navigateToInfoParking(View v) {
        Navigation.findNavController(v)
                .navigate(R.id.action_global_fragmentInfoParking);
    }


}
