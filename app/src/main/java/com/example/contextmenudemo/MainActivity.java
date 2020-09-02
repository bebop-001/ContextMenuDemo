/*
 *  Copyright 2020 Steven Smith kana-tutor.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied.
 *
 *  See the License for the specific language governing permissions
 *  and limitations under the License.
 */
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
                    "menu item unhandled:0x%08x", itemId));
                rv = false;
        }
        return rv;
    }
}
