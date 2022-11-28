package io;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 服务器端逻辑线程
 */
public class ServiceThread extends Thread {
    Socket socket;
    InputStream is;
    OutputStream os;
    public ServiceThread(Socket socket){
        this.socket = socket;
        start(); //启动线程
    }

    public void run(){
        byte[] b = new byte[1024];
        try{
            //初始化流
            os = socket.getOutputStream();
            is = socket.getInputStream();
            for(;;){
                //读取数据
                int n = is.read(b);
                //字节用string读取就可以了
                String s = new String(b, StandardCharsets.UTF_8);
                System.out.println(s);
                ////逻辑处理
                //byte[] response = logic(b,0,n);
                ////反馈数据
                //os.write(response);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close();
        }
    }

    /**
     * 关闭流和连接
     */
    private void close(){
        try{
            //关闭流和连接
            os.close();
            is.close();
            socket.close();
        }catch(Exception e){}
    }

    /**
     * 逻辑处理方法,实现echo逻辑
     * @param b 客户端发送数据缓冲区
     * @param off 起始下标
     * @param len 有效数据长度
     * @return
     */
    private byte[] logic(byte[] b,int off,int len){
        byte[] response = new byte[len];
        //将有效数据拷贝到数组response中
        System.arraycopy(b, 0, response, 0, len);
        return response;
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        //监听端口号
        int port = 10000;
        try {
            //建立连接
            serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动：");
            while(true){
                //获得连接
                socket = serverSocket.accept();
                //启动线程
                new ServiceThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                //关闭连接
                serverSocket.close();
            }catch(Exception e){}
        }
    }
}


