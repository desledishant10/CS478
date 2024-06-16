package com.example.gopherhunting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private GridView playerOneGrid, playerTwoGrid;
    private TextView statusTextView;
    private Button startButton, stopButton;
    private Handler uiHandler;
    private WorkerThread playerOneThread, playerTwoThread;

    private int[][] playerOneGuesses = new int[10][10];
    private int[][] playerTwoGuesses = new int[10][10];
    private boolean isGameRunning = false;
    private Random random = new Random();
    private int gopherX, gopherY;  // Gopher's position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerOneGrid = findViewById(R.id.playerOneGrid);
        playerTwoGrid = findViewById(R.id.playerTwoGrid);
        statusTextView = findViewById(R.id.statusTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        playerOneGrid.setAdapter(new CustomAdapter(this, playerOneGuesses));
        playerTwoGrid.setAdapter(new CustomAdapter(this, playerTwoGuesses));

        setupHandlers();
        setupButtons();
    }

    private void setupHandlers() {
        uiHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int player = msg.arg1;
                int guessX = msg.arg2;
                int guessY = msg.getData().getInt("y");
                int status = msg.getData().getInt("status");

                updateUI(player, guessX, guessY, status);
            }
        };
    }

    private void setupButtons() {
        startButton.setOnClickListener(v -> {
            if (!isGameRunning) {
                startGame();
                isGameRunning = true;
            }
        });

        stopButton.setOnClickListener(v -> {
            if (isGameRunning) {
                stopGame();
                isGameRunning = false;
            }
        });
    }

    private void startGame() {
        // Set random position for the gopher
        gopherX = random.nextInt(10);
        gopherY = random.nextInt(10);

        // Reset grids
        resetGrids();
        playerOneThread = new WorkerThread(uiHandler, 1, playerOneGuesses, gopherX, gopherY);
        playerTwoThread = new WorkerThread(uiHandler, 2, playerTwoGuesses, gopherX, gopherY);
        playerOneThread.start();
        playerTwoThread.start();
        statusTextView.setText("Game Started: Good luck!");
    }

    private void stopGame() {
        if (playerOneThread != null) playerOneThread.stopThread();
        if (playerTwoThread != null) playerTwoThread.stopThread();
        statusTextView.setText("Game Stopped");
    }

    private void resetGrids() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerOneGuesses[i][j] = 0;
                playerTwoGuesses[i][j] = 0;
            }
        }
        ((CustomAdapter) playerOneGrid.getAdapter()).notifyDataSetChanged();
        ((CustomAdapter) playerTwoGrid.getAdapter()).notifyDataSetChanged();
    }

    private void updateUI(int player, int guessX, int guessY, int status) {
        int[][] guesses = (player == 1) ? playerOneGuesses : playerTwoGuesses;
        guesses[guessX][guessY] = status;
        GridView gridView = (player == 1) ? playerOneGrid : playerTwoGrid;
        ((CustomAdapter) gridView.getAdapter()).notifyDataSetChanged();
        statusTextView.setText(String.format("Player %d guessed at (%d, %d)", player, guessX, guessY));
        if (status == 3) { // Hit
            statusTextView.setText(String.format("Player %d wins!", player));
            stopGame();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopGame(); // Ensure all threads are stopped when activity is destroyed
    }
}
