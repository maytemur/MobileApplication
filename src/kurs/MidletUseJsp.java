package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;
public class MidletUseJsp extends MIDlet{
    NetworkTextBox box;
    public void startApp(){
        box=new NetworkTextBox("Test:","",120,TextField.ANY);
        Display.getDisplay(this).setCurrent(box);
    }
    public void exit(){}
    public void pauseApp(){}
    public void destroyApp(boolean unconditional){}
}
class NetworkTextBox extends TextBox implements CommandListener{
    String url="http://localhost:8084/KursProject/data.jsp"; /**** This is work in normal project ***/
    Command sendCommand=new Command("Send",Command.SCREEN,1);
    public NetworkTextBox(String title,String text,int maxSize,int constraints){
        super(title,text,maxSize,constraints);
        addCommand(sendCommand);
        setCommandListener(this);
    }
    public void commandAction(Command c, Displayable d){
        if(c==sendCommand){
            try{
                String s=getString();
                if(s!=null){
                    String result=sendData(s);
                    setString("sonuc "+result);
                }else{
                    setString("String null");
                }
            }catch(Exception e){
                e.printStackTrace();
                setString("Exception"+e.getMessage());
            }
        }
    }
    private String sendData(String data) throws Exception{
        url=url+"?data="+data;
        HttpConnection connecton=(HttpConnection)Connector.open(url);
        InputStream is=connecton.openInputStream();
        StringBuffer buffer=new StringBuffer();
        int ch;
        while ((ch=is.read())!=-1) {
            buffer.append((char)ch);
        }
        is.close();
        connecton.close();
        return buffer.toString();
    }
}