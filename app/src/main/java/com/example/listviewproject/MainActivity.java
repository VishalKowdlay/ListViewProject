package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<VishyGames> gameList;
    TextView description;
    int currentlySelected=-1;
    TextView horDesc;
    int i = 0;
    public final static String ROTATE_INT_VALUE = "KEY";
    public final static String LIST_KEY = "KEY2";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ROTATE_INT_VALUE, currentlySelected);


        outState.putParcelableArrayList(LIST_KEY, gameList);
        Log.d("Test", "onSaveInstanceState: "+gameList.toString());
        Log.d("Test", "Hello!");
        for(VishyGames v: gameList)
            Log.d("Test", v.getName());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentlySelected=savedInstanceState.getInt(ROTATE_INT_VALUE);
        Log.d("Test", "onRestoreInstance gameId: "+currentlySelected);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels) {
            if (currentlySelected != -1)
                horDesc.setText(gameList.get(currentlySelected).getDescription());
        }

        gameList = savedInstanceState.getParcelableArrayList(LIST_KEY);
        for(VishyGames v: gameList)
            Log.d("Test", v.getName());
        ListAdapter listAdapter = new ListAdapter(this, R.layout.adapter_list, gameList);
        listView.setAdapter(listAdapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_list);
        gameList = new ArrayList<VishyGames>();
        description = findViewById(R.id.id_desc);


        gameList.add(new VishyGames("Call of Duty: Warzone", "Call of Duty: Warzone was released in 2020 by Infinity Ward. In this game, join a team of 2, 3, or 4, or go by yourself, and enter the warzone. Fight all other teams and be the last one standing!", "2009", R.drawable.cod));
        gameList.add(new VishyGames("Valorant", "Valorant was released in 2020 by Riot Games Inc. In this game, join a party of 5 members and battle 5 members. Knock down all other members of the opposing team to win! Plant spikes, unlock ultimate powers, and upgrade your loadout to become better!", "2009",R.drawable.val));
        gameList.add(new VishyGames("CS:GO", "CS:GO was released in 2012 by Valve Corporation. In this game, join a team of 5-10 people and battle another team of a similar player quantity, and kill all opposing team members to win!", "2009",R.drawable.csgo));
        gameList.add(new VishyGames("Minecraft", "Minecraft was released in 2009 by Mojang Studios. In this game, join a new world or create your own! You must survive by building shelters, finding food, and surviving out in the wild and making it into your new home. You can play in various settings from Peaceful to Nightmare and Creative or Survival Game Modes.","2009", R.drawable.mc));
        gameList.add(new VishyGames("Overwatch", "Overwatch was released in 2015 by Blizzard Entertainment. In this game, join a team and select a character with a custom loadout and set of skills and attack the other team. Get a higher score than the other team by securing more points and getting more kills to win!", "2009",R.drawable.owatch));
        gameList.add(new VishyGames("Read Dead Redemption II", "Red Dead Redemption II was released in 2018 by Rockstar Games. In this game, you are a part of fictional characters in a story! Fight alongside allies to complete quests and finish the story!","2009", R.drawable.red));


        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels)
        {
            Log.d("Test", "onCreate gameId: "+currentlySelected);
            horDesc = findViewById(R.id.id_desc);
            horDesc.setTypeface(ResourcesCompat.getFont(MainActivity.this, R.font.fugazone));
            if(currentlySelected!=-1)
                horDesc.setText(gameList.get(currentlySelected).getDescription());
        }


        ListAdapter listAdapter = new ListAdapter(this, R.layout.adapter_list, gameList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getResources().getDisplayMetrics().widthPixels<getResources().getDisplayMetrics().heightPixels)
                {
                    Toast.makeText(MainActivity.this, gameList.get(position).getDescription(), Toast.LENGTH_LONG).show();
                }
                else{
                    horDesc.setText(gameList.get(position).getDescription());
                }
                currentlySelected = position;
                Log.d("Test", "ItemOnClick");
            }
        });

    }

    public class ListAdapter extends ArrayAdapter<VishyGames>{

        private final Context mainContext;
        private final int xml;
        private final List<VishyGames> list;

        public ListAdapter(@NonNull Context context, int resource, @NonNull List<VishyGames> objects) {
            super(context, resource, objects);
            mainContext = context;
            xml = resource;
            list = objects;

        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(xml, null);

            //TextView textView = adapterView.findViewById(R.id.id_desc);
            TextView textView2 = adapterView.findViewById(R.id.id_name);
            //textView.setText(list.get(position).getDescription());
            textView2.setText(list.get(position).getName());
            textView2.setTypeface(ResourcesCompat.getFont(MainActivity.this, R.font.fugazone));
            Button button = adapterView.findViewById(R.id.id_remove);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });


            button.setText("Remove");
            //button2.setText("info");
            ImageView imageView = adapterView.findViewById(R.id.id_image);
            imageView.setImageResource(list.get(position).getGameNum());
            return adapterView;

        }
    }

}