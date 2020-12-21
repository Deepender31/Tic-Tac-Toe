package com.example.tictactoe;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button [][] buttons = new Button[3][3];
    private Boolean player_1_turn =true;
    private int roundcount;
    private int player1point;
    private int player2point;
    private TextView textviewplayer1;
    private TextView textviewplayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewplayer1= findViewById(R.id.P1);
        textviewplayer2=findViewById(R.id.P2);
        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                String BID="B"+i+j;
                int Resid=getResources().getIdentifier(BID, "id", getPackageName() );
                buttons [i][j] =findViewById(Resid);
                buttons [i][j].setOnClickListener(this);

            }
        }
        Button bor=findViewById(R.id.R1);
        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;}else{
         if (player_1_turn){
            ((Button)v).setText("X");
        }else {
            ((Button)v).setText("o");
        } }
        roundcount++;
        if(checkforwin()){
            if(player_1_turn){
                player1win();
            }else{
                player2win();
            }
        }else {if (roundcount==9)
            drow();
        else {player_1_turn=!player_1_turn;

        }}
    }
    private  Boolean checkforwin() {
        String [][] fields= new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                fields[i][j]=buttons[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++)
        {
            if (fields[i][0].equals(fields[i][1])&&fields[i][0].equals(fields[i][2])&&!fields[i][0].equals(""))
            {
                return true;
            }
        }
        for (int i=0;i<3;i++)
        {
            if (fields[0][i].equals(fields[1][i])&&fields[0][i].equals(fields[2][i])&&!fields[0][i].equals(""))
            {
                return true;
            }
        }
        if (fields[0][0].equals(fields[1][1])&&fields[0][0].equals(fields[2][2])&&!fields[0][0].equals(""))
        {
            return true;
        }
        if (fields[0][2].equals(fields[1][1])&&fields[0][2].equals(fields[2][0])&&!fields[0][2].equals(""))
        {
            return true;
        }
        return false;
    }
    private void player1win(){
        player1point++;
        Toast.makeText(this,"Player 1 win!",Toast.LENGTH_SHORT).show();
        updatePlayerpoint();
        resetbourd();
    }
    private void player2win(){
        player2point++;
        Toast.makeText(this,"Player 2 win!",Toast.LENGTH_SHORT).show();
        updatePlayerpoint();
        resetbourd();
    }
    private void drow(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetbourd();
    }
    private void updatePlayerpoint(){
        textviewplayer1.setText("Player 1:"+player1point);
        textviewplayer2.setText("Player 2:"+player2point);
    }
    private void resetbourd(){
        for (int i=0;i<3;i++){
            for(int j =0;j<3;j++){
                buttons[i][j].setText("");
            }

        }
        roundcount=0;
        player_1_turn=true;
    }
    private void resetgame(){
        player1point=0;
        player2point=0;
        updatePlayerpoint();
        resetbourd();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundcount",roundcount);
        outState.putInt("player1point",player1point);
        outState.putInt("player2point",player2point);
        outState.putBoolean("player_1_turn",player_1_turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundcount = savedInstanceState.getInt("roundcount");
        player1point = savedInstanceState.getInt("player1point");
        player2point = savedInstanceState.getInt("player2point");
        player_1_turn = savedInstanceState.getBoolean("player_1_turn");
    }
}
