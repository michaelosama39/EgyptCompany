package com.example.egyptimportandexport.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.egyptimportandexport.R;
import com.example.egyptimportandexport.view.fragment.AddDataFragment;
import com.example.egyptimportandexport.view.fragment.DeleteDataFragment;
import com.squareup.leakcanary.LeakCanary;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_data_menu:
                AddDataFragment fragment = new AddDataFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.homeActivity_layout, fragment).addToBackStack(null).commit();
                return true;

            case R.id.delete_data_menu:
                DeleteDataFragment fragment0 = new DeleteDataFragment();
                FragmentTransaction transaction0 = getSupportFragmentManager().beginTransaction();
                transaction0.add(R.id.homeActivity_layout, fragment0).addToBackStack(null).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}