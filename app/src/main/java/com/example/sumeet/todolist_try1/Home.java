package com.example.sumeet.todolist_try1;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;


public class Home extends AppCompatActivity {
    ListView listView;
    List<dataContents> list;
    AdapterList adapter;
    dataContents selected;
    int selectedId;
    dbHelper db = new dbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ListView listView = (ListView) findViewById(R.id.lv_home);
        list = db.getAllTodo();
        Collections.sort(list);
        adapter = new AdapterList(this, list);
        listView.setAdapter(adapter);
        refrehsList();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
                selected = list.get(position);

                selectedId = selected.getKey_id();

                //android.support.v4.app.FragmentManager fmg = getSupportFragmentManager();
                //   android.app.FragmentManager fmg1 = getFragmentManager();

                FragmentManager fmg = getFragmentManager();
                DialogSetup dialog = new DialogSetup();


                Bundle bund = new Bundle();
                bund.putInt("Ins/del", 1);
                bund.putInt("selectid", selectedId);
                dialog.setArguments(bund);
                Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
                dialog.show(fmg, "test");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {

                selected = list.get(position);
                selectedId = selected.getKey_id();
                dbHelper db = new dbHelper(Home.this);
                db.taskDone(selectedId);
                refrehsList();
                return true;
            }
        });

    }

    public void refrehsList() {
        list = db.getAllTodo();
        adapter.updatedList(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                android.app.FragmentManager fm = getFragmentManager();
                DialogSetup dlg = new DialogSetup();

                Bundle bun = new Bundle();
                bun.putInt("Ins/del", 0);
                dlg.setArguments(bun);
                dlg.show(getSupportFragmentManager(), "test");
                return true;

            case R.id.action_completed:
                Toast.makeText(this, "cpmletd selected", Toast.LENGTH_SHORT)
                        .show();
                refrehsListDone();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refrehsList();
    }

    public void refrehsListDone() {
        list = db.getAllDoneTodo();
        adapter.updatedList(list);
    }


}
