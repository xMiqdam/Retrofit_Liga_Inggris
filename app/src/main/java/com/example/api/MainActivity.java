package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";
    private RecyclerView rvInggris;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvInggris = findViewById(R.id.rvLigaInggris);
        rvInggris.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TeamService service = retrofit.create(TeamService.class);
        Call<TeamRespone> call = service.getTeams();
        call.enqueue(new Callback<TeamRespone>() {
            @Override
            public void onResponse(Call<TeamRespone> call, Response<TeamRespone> response) {
//                if (response.isSuccessful()) {
//                    TeamRespone teamRespone = response.body();
//                    if (teamRespone != null) {
//                        List<Team> teams = teamRespone.getTeams();
//                        adapter = new Adapter( MainActivity.this, teams);
//                        rvInggris.setAdapter(adapter);
//                    }

                if(response.isSuccessful()){
                    TeamRespone teams = response.body();
                    if(teams != null){
                        List<Team> tim = teams.getTeams();
                        adapter = new Adapter(MainActivity.this, tim);
                        rvInggris.setAdapter(adapter);
                    }
                }

                else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<TeamRespone> call, Throwable t) {
                // Handle network failures
            }
        });
    }
        }