package com.example.clipserver;

interface IClipService {
    void playClip(int clipIndex);
    void pauseClip();
    void resumeClip();
    void stopClip();
    int getClipCount();
}
