package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CommandTest extends javax.microedition.midlet.MIDlet implements CommandListener{
    Command exitCommand=new Command("EXIT",Command.EXIT,1);
    Command okCommand=new Command("OK",Command.OK,2);
    Command nekomut=new Command("HELP",Command.HELP,3);
    TextBox box;
    public void startApp() {
        box=new TextBox("Test","MIDDLET Command ve Command Listener Testi bu yeni textbox",100,TextField.ANY);
        box.addCommand(exitCommand);
        box.addCommand(okCommand);
        box.addCommand(nekomut);
        box.setCommandListener(this);
        Display.getDisplay(this).setCurrent(box);
    }
    public void commandAction(Command c,Displayable d){
        if(c==exitCommand){
            destroyApp(false);
            notifyDestroyed();
        }else if(c==okCommand){
            box.setString("ok e bastiniz");
        }else if(c==nekomut){
            box.setString("help e bastýnýz");
        }
    }
    public void pauseApp() {}
    public void destroyApp(boolean unconditional) {
    }
}