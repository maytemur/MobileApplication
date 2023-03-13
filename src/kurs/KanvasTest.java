package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class KanvasTest extends MIDlet implements CommandListener{
    Command exitCommand=new Command("EXIT",Command.EXIT,1);
    
    public void startApp() {
        CanvasClassi kanvas=new CanvasClassi();
        kanvas.addCommand(exitCommand);
        kanvas.setCommandListener(this);
        Display.getDisplay(this).setCurrent(kanvas);
    }
    
    public void commandAction(Command c,Displayable d){
        if(c==exitCommand){
            destroyApp(true);
            notifyDestroyed();
        }
    }
    public void pauseApp() {    }
    
    public void destroyApp(boolean unconditional) {    }
    
    public class CanvasClassi extends Canvas{
        public void paint(Graphics g){
            g.drawRect(10,10,50,50);
        }
    }
}