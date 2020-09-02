package com.example.contextmenudemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static int animationSpeed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.imageView));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d("onCreateContextMenu", "menuInfo:" +
                ((menuInfo == null) ? "NULL" : menuInfo.toString()));
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.speed_select, menu);
        menu.getItem(animationSpeed).setChecked(true);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        boolean rv = true;
        switch (itemId) {
            case R.id.animate_slow:
                animationSpeed = 0;
                break;
            case R.id.animate_normal:
                animationSpeed = 1;
                break;
            case R.id.animate_fast:
                animationSpeed = 2;
                break;
            default:
                Log.d("onContextItemSelected", String.format(
                        "menu item unhandled:0x%08x", itemId)
                );
                rv = false;
        }
        return rv;
    }
}