package com.example.melg.trackit;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by melg on 6/14/18.
 */

public class BluetoothConnectionService {
    private static final String TAG = "BluetoothConnectionService";
    private static final String appName = "Linked";
    private ConnectThread connectThread;

    private ConnectedThread connectedThread;
    private BluetoothDevice myDevice;
    private UUID deviceUUID;

    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66"); // id used to connect to phones


    private final BluetoothAdapter bluetoothAdapter;
    private Context context;

    private AcceptThread acceptThread;

    public BluetoothConnectionService(Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.context = context;
    }


    /*
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */
    private class AcceptThread extends Thread {

        //needs bluetoothserver socket and constructor

        private final BluetoothServerSocket localserverSocket;

        public AcceptThread() {
            BluetoothServerSocket serverSocket = null;


            try {
                serverSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(appName, MY_UUID_INSECURE);
            } catch (IOException e) {
                e.printStackTrace();
            }

            localserverSocket = serverSocket;


        }


        @SuppressLint("LongLogTag")
        public void run() {

            // your call will sit and wait until there is a connection or failure
            BluetoothSocket socket = null;

            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                Log.d(TAG, "run: RFCOM server socket start.....");

                socket = localserverSocket.accept();

                Log.d(TAG, "run: RFCOM server socket accepted connection.");

            } catch (IOException e) {
                Log.e(TAG, "AcceptThread: IOException: " + e.getMessage());
            }

            //talk about this is in the 3rd
            if (socket != null) {
                connected(socket, myDevice);
            }

            Log.i(TAG, "END mAcceptThread ");
        }

        @SuppressLint("LongLogTag")
        public void cancel() {
            Log.d(TAG, "cancel: Canceling AcceptThread.");
            try {
                localserverSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "cancel: Close of AcceptThread ServerSocket failed. " + e.getMessage());
            }
        }

    }


    private class ConnectThread extends Thread {
        private BluetoothSocket BTsocket;

        public ConnectThread(BluetoothDevice device, UUID uuid) {
            myDevice = device;
            deviceUUID = uuid;

        }

        @SuppressLint("LongLogTag")
        public void run() {
            BluetoothSocket tempSocket = null;

            //taking our tempSocket and making a RFCOMM socket
            try {
                tempSocket = myDevice.createInsecureRfcommSocketToServiceRecord(deviceUUID);
            } catch (IOException e) {
                Log.e(TAG, "ConnectThread: Could not create InsecureRfcommSocket " + e.getMessage());
            }

            BTsocket = tempSocket;

            bluetoothAdapter.cancelDiscovery(); //cancels the search once connection is successful

            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                BTsocket.connect();

                Log.d(TAG, "run: ConnectThread connected.");
            } catch (IOException e) {
                // Close the socket
                try {
                    // if there is an exception close the socket
                    BTsocket.close();
                    Log.d(TAG, "run: Closed Socket.");
                } catch (IOException e1) {
                    Log.e(TAG, "mConnectThread: run: Unable to close connection in socket " + e1.getMessage());
                }
                Log.d(TAG, "run: ConnectThread: Could not connect to UUID: " + MY_UUID_INSECURE);
            }

            //will talk about this in the 3rd video
            connected(BTsocket, myDevice);
        }

        @SuppressLint("LongLogTag")
        public void cancel() {
            Log.d(TAG, "cancel: Canceling AcceptThread.");
            try {
                BTsocket.close();
            } catch (IOException e) {
                Log.e(TAG, "cancel: Close of AcceptThread ServerSocket failed. " + e.getMessage());
            }
        }
    }


    @SuppressLint("LongLogTag")
    public synchronized void initAcceptThread() {
        Log.d(TAG, "start");

        // Cancel any thread attempting to make a connection
        if (connectThread != null) {
            connectThread.cancel();
            connectThread = null;
        }
        if (acceptThread == null) {
            acceptThread = new AcceptThread();
            acceptThread.start();
        }

    }

    public void initConnectThread(BluetoothDevice device, UUID uuid) {

        //TODO dialog box that says connecting ...
        connectThread = new ConnectThread(device, uuid);
        connectThread.start();

    }

    private class ConnectedThread extends Thread {
        //every class needs a bluetooth socket
        // and a constructer
        private final BluetoothSocket CTsocket;
        private InputStream inputStream;
        private OutputStream outputStream;

        @SuppressLint("LongLogTag")
        public ConnectedThread(BluetoothSocket socket) {

            Log.d(TAG, "Connecting to Thread: ");

            CTsocket = socket;
            InputStream tempInput = null;
            OutputStream tempOutput = null;

            try {
                tempInput = CTsocket.getInputStream();
                tempOutput = CTsocket.getOutputStream();
            } catch (IOException e) {
                Log.d(TAG, "ConnectedThread: connecting went wrong "+ e.getMessage());
            }

            inputStream = tempInput;
            outputStream = tempOutput;


        }

        //method turns the bytes into a string format
        @SuppressLint("LongLogTag")
        public void run() {
            byte[] buffer = new byte[1024]; // byte array will store all bytes from the message created.
            int bytes;


            while (true) {
                try {
                    bytes = inputStream.read(buffer);
                    String incomingMessage = new String(buffer, 0, bytes);
                    Log.d(TAG, "incoming message: " + incomingMessage);

                } catch (IOException e) {
                    Log.d(TAG, "error reading inputStream" + e.getMessage());
                    break;
                }


            }
        }


        @SuppressLint("LongLogTag")
        public void write (byte[] bytes){

            String incomingMessageOnOtherDev = new String(bytes, Charset.defaultCharset());

            Log.d(TAG, "write method - outputstream" + incomingMessageOnOtherDev);

            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                Log.d(TAG, "writing error found with outputstream " + e.getMessage());
            }

        }

        // cancels connection if socket has not connected to another device's socket
        @SuppressLint("LongLogTag")
        public void cancel() {
            Log.d(TAG, "cancel: Canceling AcceptThread.");
            try {
                CTsocket.close();
            } catch (IOException e) {
                Log.e(TAG, "cancel: Close of AcceptThread ServerSocket failed. " + e.getMessage());
            }
        }

    }
    @SuppressLint("LongLogTag")
    public void connected (BluetoothSocket socket, BluetoothDevice device){
        Log.d(TAG, "connection starting: " );

        connectedThread = new ConnectedThread(socket);
        connectedThread.start();


    }
    // same write method in connectedthread
    @SuppressLint("LongLogTag")
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread tempCThread;

        // Synchronize a copy of the ConnectedThread
        Log.d(TAG, "write: Write Called.");
        //perform the write
        connectedThread.write(out);
    }

}



