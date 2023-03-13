package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class ListAndCommandTest extends MIDlet implements CommandListener{
    List list;
    public void startApp() {
        list=new List("Test Listesi",List.IMPLICIT);
        list.append("A",null);
        list.append("B",null);
        list.append("C",null);
        list.setCommandListener(this);
        Display.getDisplay(this).setCurrent(list);
    }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c,Displayable d){
        if(c==List.SELECT_COMMAND){
            int index=list.getSelectedIndex();
            String choice=list.getString(index);
            Alert alert=new  Alert("Uyari Alert",choice,null,null);
            alert.setTimeout(Alert.FOREVER);
            Display.getDisplay(this).setCurrent(alert);
        }
    }
}