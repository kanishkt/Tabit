package com.tabituiuc.tabit;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;
import android.os.AsyncTask;
import edu.emory.mathcs.jtransforms.fft.*;
import edu.emory.mathcs.utils.*;


public class TunerModule {

    protected int frequency;

    public TunerModule(){

        new readFrequencies().execute();
    }

    private class readFrequencies extends AsyncTask<Void,Integer,Integer> {

        @Override
        protected Integer doInBackground(Void... arg0) {
            AudioRecord recorder = null;
            int bufferSize = 0;
            boolean recording = true;

            int rate = 8000;
            short audioFormat = AudioFormat.ENCODING_PCM_16BIT;
            short channelConfig = AudioFormat.CHANNEL_IN_MONO;

            try {
                bufferSize = AudioRecord.getMinBufferSize(rate,channelConfig, audioFormat);

                recorder = new AudioRecord(AudioSource.DEFAULT, rate,
                        channelConfig, audioFormat, bufferSize);

                if (recorder.getState() == AudioRecord.STATE_INITIALIZED) {
                    /*
                     *  Android 4.1.2
                     * 
                     in recorder_id = recorder.getAudioSessionId();
                    if (NoiseSuppressor.isAvailable()) NoiseSuppressor.create(recorder_id);
                    */
                }
                else {
                    // do nothing
                }
            } catch (Exception e) {}

            short[] audioData = new short[bufferSize];

            if (recorder != null) {
                while (recording) {
                    if (recorder.getRecordingState() == AudioRecord.RECORDSTATE_STOPPED) {
                        recorder.startRecording();
                    }
                    else {
                        int numshorts = recorder.read(audioData,0,audioData.length);
                        if ((numshorts != AudioRecord.ERROR_INVALID_OPERATION) &&
                                (numshorts != AudioRecord.ERROR_BAD_VALUE)) {


                            double[] preRealData = new double[bufferSize];
                            double PI = 3.14159265359;
                            for (int i = 0; i < bufferSize; i++) {
                                double multiplier = 0.5 * (1 - Math.cos(2*PI*i/(bufferSize-1)));
                                preRealData[i] = multiplier * audioData[i];
                            }

                            DoubleFFT_1D fft = new DoubleFFT_1D(bufferSize);
                            double[] realData = new double[bufferSize * 2];

                            for (int i=0;i<bufferSize;i++) {
                                realData[2*i] = preRealData[i];
                                realData[2*i+1] = 0;
                            }
                            fft.complexForward(realData);

                            double magnitude[] = new double[bufferSize / 2];

                            for (int i = 0; i < magnitude.length; i++) {
                                double R = realData[2 * i];
                                double I = realData[2 * i + 1];

                                magnitude[i] = Math.sqrt(I*I + R*R);
                            }

                            int maxIndex = 0;
                            double max = magnitude[0];
                            for(int i = 1; i < magnitude.length; i++) {
                                if (magnitude[i] > max) {
                                    max = magnitude[i];
                                    maxIndex = i;
                                }
                            }

                            int frequency = rate * maxIndex / bufferSize;
                            publishProgress(frequency);
                        }
                        else {

                            return -1;
                        }
                    }
                }

                if (recorder.getState() == AudioRecord.RECORDSTATE_RECORDING)
                    recorder.stop(); //stop the recorder before ending the thread
                recorder.release();
                recorder=null;
            }
            return 0;
        }

        protected void onProgressUpdate(Integer... f) {
           // do nothing
        }

        protected void onPostExecute(Integer f) {
            frequency = f.intValue();
        }


    }

    public int getFrequency(){
        return frequency;
    }
}