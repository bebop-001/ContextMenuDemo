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
package com.example.contextmenudemo

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(findViewById(R.id.imageView))
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View,
                                     menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        Log.d("onCreateContextMenu", "menuInfo:" +
                (menuInfo?.toString() ?: "NULL"))
        val inflater = menuInflater
        inflater.inflate(R.menu.speed_select, menu)
        menu.getItem(animationSpeed).isChecked = true
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        var rv = true
        when (itemId) {
            R.id.animate_slow -> animationSpeed = 0
            R.id.animate_normal -> animationSpeed = 1
            R.id.animate_fast -> animationSpeed = 2
            else -> {
                Log.d("onContextItemSelected", String.format(
                        "menu item unhandled:0x%08x", itemId))
                rv = false
            }
        }
        return rv
    }

    companion object {
        private var animationSpeed = 0
    }
}