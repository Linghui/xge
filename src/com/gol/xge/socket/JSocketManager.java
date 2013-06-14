package com.gol.xge.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.gol.xge.socket.listener.InOutPutInterface;

/*
 * deal with socket message sending out.
 * caller does not care anything complicated, implement interface SocketInterface
 * then just call sendMessageToAuthServer() or sendMessageToWorldServer()
 */

public class JSocketManager {

	private static final String TAG = "SocketManager";

	static private Socket worldSocket = null;

	private ServerListener sendListener = null;

	private InOutPutInterface inOutPutInterface = null;

	private final int socketSleepTime = 100;

	public JSocketManager(String HOST, int PORT) {
		connectToServer(HOST, PORT);
	}

	public JSocketManager() {

	}

	public class ServerListener implements Runnable {

		private static final String TAG = "ServerListener";

		private Socket listeningSocket = null;

		public OutputStream output = null;
		public InputStream input = null;

		private boolean isRunnable = true;

		private ServerListener(Socket inSocket) {
			listeningSocket = inSocket;
			// System.out.println(TAG + ":new Client Socket listening port : "
			// + listeningSocket.getPort());

			try {
				output = inSocket.getOutputStream();
				input = inSocket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// set this value to false to stop listener
			isRunnable = true;
		}

		public void run() {
			System.out.println(TAG + ":listener started !");
			try {

				while (isRunnable) {
					if (inOutPutInterface == null) {
						continue;
					}
					if (input.available() > 0) {
						inOutPutInterface.onRead(input);
					}
					inOutPutInterface.onWrite(output);
					// Thread.sleep(socketSleepTime);

				}
				input.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					input.close();
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(TAG + ":listener run end !");
		}

		public void stopListener() {
			isRunnable = false;
		}

	}

	public void setInOutPutInterface(InOutPutInterface inOutPutInterface) {
		this.inOutPutInterface = inOutPutInterface;
	}

	private void connectToServer(String HOST, int PORT) {

		sendListener = null;
		System.out.println(TAG + ": ConnectToServer start !");
		try {
			worldSocket =new Socket(HOST, PORT);
			System.out.println(TAG + ": Connected ToServer " + HOST);

			// if world listener is not running, start it
			if (sendListener == null) {
				sendListener = new ServerListener(worldSocket);
				new Thread(sendListener).start();
				System.out.println(TAG + ":serverListener start !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(TAG + ": ConnectToServer end !");

	}

	public boolean status() {
		if (worldSocket == null) {
			return false;
		}
		return worldSocket.isConnected();
	}

	public void disConnectToWorldServer() {
		System.out.println(TAG + ":disConnectToWorldServer start !");

		if (sendListener != null) {
			sendListener.stopListener();
		}

		if (worldSocket != null) {
			// authSocket.finishConnect();
			try {
				worldSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(TAG + ":disConnectToWorldServer END !");
	}

}
