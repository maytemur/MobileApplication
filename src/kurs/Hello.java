package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class Hello extends MIDlet {
    public void startApp() {
        TextBox box=new TextBox("Hello","Hello MIDlet",100,TextField.ANY);
        Display.getDisplay(this).setCurrent(box);
    }    
    public void pauseApp() {
    }    
    public void destroyApp(boolean unconditional) {
    }
}