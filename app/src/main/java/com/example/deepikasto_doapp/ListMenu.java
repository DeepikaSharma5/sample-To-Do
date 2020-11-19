package com.example.deepikasto_doapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListMenu extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);


        listView = (ListView) findViewById(R.id.listMenu);
        ArrayList<TodoLists> lists = new ArrayList<>();
        lists.add(new TodoLists("Home", "blue"));
        lists.add(new TodoLists("Work/School", "blue"));
        CustumAdapter custumAdapter = new CustumAdapter(lists);
        listView.setAdapter(custumAdapter);
    }

    public class CustumAdapter extends BaseAdapter {
        ArrayList<TodoLists> lists;

        public CustumAdapter(ArrayList lists) {
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activity_menu_item, null);


            final TextView title = view.findViewById(R.id.listName);
            title.setText(lists.get(i).getName());
            ImageButton button = view.findViewById(R.id.selectList);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listSelected(title.getText().toString());
                }
            });

            return view;
        }

        void listSelected(String listName) {
            Intent intent = new Intent(getApplicationContext(), TodoList.class);
            intent.putExtra("listName", listName);
            startActivity(intent);
        }
    }
}