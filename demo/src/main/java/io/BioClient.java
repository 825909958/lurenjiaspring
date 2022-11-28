package io;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BioClient {

        private Socket socket;

        /*
              Socket实例化时需要传入两个参数
              参数1:服务端的地址信息
              参数2:服务端打开的服务端口
              客户端通过服务端的地址找到网络上的服务器计算机,通过端口可以连接上该机器上运行的服务端应用程序.
           */
        public BioClient() {

            try {
                System.out.println("正在连接服务端...");
                //创建一个新的接口来获取一个接口
                socket = new Socket("localhost", 10000);//链接的地址与，端口的地址
                System.out.println("与服务端建立连接!");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

        public void start() {

            //客户端向服务端发送数据,则需要使用socket获取输出流
            try {
                OutputStream out = socket.getOutputStream();//建立一个输出的流
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);//转换字符与字节
                BufferedWriter bw = new BufferedWriter(osw);//加速
                PrintWriter pw = new PrintWriter(bw, true);//写出并行刷新
                //自动行刷新功能

                Scanner scan = new Scanner(System.in);//导入窗口下的输入器
                while (true) {
                    String line = scan.nextLine();
                    if ("exit".equals(line)) {
                        break;
                    }
                    pw.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {//必定执行一次用于关闭
                try {
                /*
                Socketd额close方法里封装了服务端的四次挥手操作
                close可以在内部将通过socket的输入流与输出流关闭
                 */
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            BioClient client = new BioClient();//创建一个新的服务端
            client.start();//用来调用上方的start方法
        }


}
