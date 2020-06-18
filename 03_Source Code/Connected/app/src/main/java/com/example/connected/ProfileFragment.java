package com.example.connected;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.connected.Feed.FeedMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

public class ProfileFragment extends Fragment {

    FeedFragment fg = new FeedFragment();

    public FeedFragment getFg() {
        return fg;
    }

    Profile selecetedProfile = null;


    private ArrayList<Profile> list;
    Spinner s;
    Button buttonSave, buttonDel;
    EditText line1, line2;
    ArrayList arrList;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        s = rootView.findViewById(R.id.spinner);
        line1 = rootView.findViewById(R.id.profileName);
        line2 = rootView.findViewById(R.id.twitterName);
        loadData();

        buttonSave = rootView.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {
            if (!list.isEmpty()) {
                Optional o = list.stream().filter(e -> e.getProfileName().equals(line1.getText().toString())).findFirst();
                if (o.isPresent()) {
                    list.remove(o.get());
                }
            }
            list.add(new Profile(line1.getText().toString(), line2.getText().toString()));
            saveData();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false);
            }
            ft.detach(this).attach(this).commit();
        });

        buttonDel = rootView.findViewById(R.id.button_remove);
        buttonDel.setOnClickListener(v -> {
            removeData();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false);
            }
            ft.detach(this).attach(this).commit();
        });

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Profile p = list.stream().filter(e -> e.getProfileName().equals(item.toString())).findFirst().get();
                    Bundle bundle = new Bundle();
                    bundle.putString("profile", new Gson().toJson(p));
                    fg.setArguments(bundle);
                    line1.setText(p.getProfileName());
                    line2.setText(p.getTwitterName());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        saveSpinner(s);
        return rootView;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Profile>>() {
        }.getType();
        list = gson.fromJson(json, type);
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    private void removeData(){
        SharedPreferences pref = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("task list");
        editor.apply();
    }

    private void saveSpinner(Spinner s) {
        arrList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            arrList.add(list.get(i).getProfileName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        // attaching data adapter to spinner
        s.setAdapter(dataAdapter);
    }
}
