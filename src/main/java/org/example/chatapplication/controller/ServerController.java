package org.example.chatapplication.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.net.ServerSocketFactory;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    FileOutputStream fileOutputStream;

    @FXML
    private Button btnSend;

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField txtText;

    @FXML
    private ImageView imageView;

    @FXML
    void btnSend(ActionEvent event) throws IOException {
        String newMessage = txtText.getText();
        messageArea.appendText("Server : " + newMessage + "\n");

        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(newMessage);
        dataOutputStream.flush();

        txtText.clear();
    }
    @FXML
    void btnCloseChat(ActionEvent event) throws IOException {
        messageArea.setText("\nServer Closed");
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
        System.exit(0);
    }

    @FXML
    void downloadBtn(ActionEvent event) {
        Image image = imageView.getImage();

        File file = new File("C:\\Users\\lahir\\Documents\\IJSE\\2nd Sem\\Network Programing\\chat-application\\src\\main\\java\\org\\example\\chatapplication\\assets\\image.jpg");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();

            try {
                serverSocket = serverSocketFactory.createServerSocket(5000);
                socket = serverSocket.accept();
                System.out.println("Client Connected");

                dataInputStream = new DataInputStream(socket.getInputStream());
                while (true){
                    String message = dataInputStream.readUTF();

                    if (message.equals("IMAGE")){
                        int length = dataInputStream.readInt();
                        byte[] imageByte = new byte[length];
                        dataInputStream.readFully(imageByte, 0, length);
                        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
                        Image image = new Image(bais);
                        imageView.setImage(image);

//                        BufferedImage imageBuffer = ImageIO.read(bais);
//                        String path = "C:\\Users\\lahir\\Downloads\\image.jpg";
//                        File file = new File(path);
//                        ImageIO.write(imageBuffer, "jpg", file);

                        messageArea.appendText("Client : " + "image received" + "\n");
                    }else {
                        if (!message.isEmpty()) {
                            messageArea.appendText("Client : " + message + "\n");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
