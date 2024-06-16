package com.example.gopherhunting;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.Random;

public class WorkerThread extends Thread {
    private Handler uiHandler;
    private int playerNumber;
    private int[][] guesses;
    private int gridWidth = 10;
    private int gridHeight = 10;
    private boolean running = true;
    private int gopherX, gopherY;
    private Random random = new Random();

    public WorkerThread(Handler uiHandler, int playerNumber, int[][] guesses, int gopherX, int gopherY) {
        this.uiHandler = uiHandler;
        this.playerNumber = playerNumber;
        this.guesses = guesses;
        this.gopherX = gopherX;
        this.gopherY = gopherY;
    }

    @Override
    public void run() {
        try {
            while (running) {
                int guessX = random.nextInt(gridWidth);
                int guessY = random.nextInt(gridHeight);

                if (guesses[guessX][guessY] == 0) { // Only guess if not already guessed
                    guesses[guessX][guessY] = 1; // Mark this cell as guessed
                    int status = evaluateGuess(guessX, guessY);
                    sendMessage(guessX, guessY, status);
                    Thread.sleep(2000); // Wait for 2 seconds before the next guess
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int evaluateGuess(int x, int y) {
        if (x == gopherX && y == gopherY) {
            return 3; // Hit
        } else if (Math.abs(x - gopherX) <= 1 && Math.abs(y - gopherY) <= 1) {
            return 2; // Near miss
        } else if (Math.abs(x - gopherX) <= 2 && Math.abs(y - gopherY) <= 2) {
            return 1; // Close guess
        } else {
            return 0; // Complete miss
        }
    }

    private void sendMessage(int x, int y, int status) {
        Message msg = Message.obtain();
        msg.arg1 = playerNumber;
        msg.arg2 = x;
        Bundle data = new Bundle();
        data.putInt("y", y);
        data.putInt("status", status);
        msg.setData(data);
        uiHandler.sendMessage(msg);
    }

    public void stopThread() {
        running = false;
        this.interrupt();
    }
}
