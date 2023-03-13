package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
public class HttpConnectionTest extends MIDlet {
    public final static String URL="http://localhost:8084/KursProject/EkleUserForm.jsp";
    public void startApp() {
            String result=openConnection();
            System.out.println("result: "+result);
            TextBox box=new TextBox ("Hello",result,1000,TextField.ANY);
            Display.getDisplay(this).setCurrent(box); 
    }
    public void pauseApp() {    }
    public void destroyApp(boolean boolean0) {    }
    public String openConnection(){
        try {
            //HttpsConnection c=(HttpsConnection)Connector.open(URL);
            StreamConnection c=(StreamConnection)Connector.open(URL);            
            InputStream is=c.openInputStream();
            StringBuffer buffer=new StringBuffer();
            int ch;
            while((ch=is.read()) !=-1){
                buffer.append((char) ch);
            }
            is.close();
            return buffer.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}