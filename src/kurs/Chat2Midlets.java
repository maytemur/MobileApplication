package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.util.*;
import java.io.*;
public class Chat2Midlets extends javax.microedition.midlet.MIDlet{
    ChatForm chatformu;
    public void startApp(){
        NickForm nickForm=new NickForm(this,"Chat Form");
        Display.getDisplay(this).setCurrent(nickForm);
    }
    public void pauseApp(){}
    public void destroyApp(boolean unconditional){}   
    public void setNick(String nick){
        ChatForm chatformu=new ChatForm("Chat Form",nick);
        Display.getDisplay(this).setCurrent(chatformu);
    }
}
class ChatForm extends Form implements CommandListener{
    String url="http://localhost:8084/KursProject/MultiMessagesServer.jsp";
    Command sendCommand=new Command("Send",Command.SCREEN,2);
    Command getCommand=new Command("Get",Command.SCREEN,1);
    TextField messageField;
    MessagesBox messagesBox;
    String nick="";
    public ChatForm(String title,String nick){
        super(title);
        this.nick=nick;
        messagesBox=new MessagesBox("","");
        messageField=new TextField("","",100,TextField.ANY);
        append(messageField);
        append(messagesBox);
        addCommand(sendCommand);
        addCommand(getCommand);
        setCommandListener(this);
    }
    public void commandAction(Command c, Displayable d){
        if(c==sendCommand){
            try{
                String s=messageField.getString();
                if(s!=null){
                    String result=send(s);
                    messagesBox.append(nick+">"+s);
                    messageField.setString("");
                }else{
                    messagesBox.append("String null");
                }
            }catch(Exception e){
                messagesBox.append("Exception"+e.getMessage());
            }
        }else if(c==getCommand){
            try{
                String s=get();
                if(s!=null){
                    messagesBox.append(s.trim());
                }else{
                    messagesBox.append("String null");
                }
            }catch(Exception e){
                messagesBox.append("Exception"+e.getMessage());
            }
        }
    }
    private String send(String message) {
        String st="" ;
        try {
            HttpConnection connecton=(HttpConnection)Connector.open(url+"?message="+message+"&nick="+nick+"&req=SEND");
            InputStream is=connecton.openInputStream();
            StringBuffer buffer=new StringBuffer();
            int ch;
            while ((ch=is.read())!=-1) {
                buffer.append((char)ch);
            }
            is.close();
            connecton.close();
            st=buffer.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return st;
    }
    private String get() throws Exception{
        StringBuffer sb=new StringBuffer();
        InputStream is=Connector.openInputStream(url+"?req=GET");
        int ch;
        while((ch = is.read())!=-1) {
            sb.append((char) ch);
        }
        return sb.toString();
    }
}
class MessagesBox extends StringItem{
    Vector messages=new Vector();
    public MessagesBox(String label, String text){
        super(label,text);
    }
    public void append(String message){
        messages.addElement(message);
        refresh();
    }
    public void refresh(){
        StringBuffer buffer=new StringBuffer();
        for(int i=(messages.size()-1);i>-1;i--){
            String message=(String)messages.elementAt(i);
            buffer.append(message+"\n");
        }
        setText(buffer.toString());
    }
}
class NickForm extends Form implements CommandListener{
    Command okCommand=new Command("Ok",Command.SCREEN,1);
    TextField field;
    Chat2Midlets midlet;
    public NickForm(Chat2Midlets midlet,String title){
        super(title);
        this.midlet=midlet;
        field=new TextField("Nick:","",100,TextField.ANY);
        append(field);
        addCommand(okCommand);
        setCommandListener(this);
    }
    public void commandAction(Command c, Displayable d){
        if(c==okCommand){
            String nick=field.getString();
            midlet.setNick(nick);
        }
    }
}