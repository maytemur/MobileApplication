package kurs;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.io.*;
import java.io.*;
public class InvokeJSPMidlet extends MIDlet implements CommandListener {
  Display display = null;
  // name field   
  TextField name = null;
  Form form;
  String url = "http://localhost:8084/KursProject/Today.jsp";
  static final Command callCommand = new Command("date?", Command.OK, 2);
  static final Command clearCommand = new Command("clear", Command.STOP, 2);
  String myname;
  public InvokeJSPMidlet() {
   display = Display.getDisplay(this);
   name = new TextField("Name:", " ", 25, TextField.ANY);
   form = new Form("Invoke JSP");
  }
  public void startApp() throws MIDletStateChangeException {
   form.append(name);
   form.addCommand(clearCommand);
   form.addCommand(callCommand);
   form.setCommandListener(this);
   display.setCurrent(form);
  }
  public void pauseApp() {  }
  public void destroyApp(boolean unconditional) {
   notifyDestroyed();   }
  void invokeJSP(String url) {
    HttpConnection c = null;
    InputStream is = null;
    OutputStream os = null;
    StringBuffer b = new StringBuffer();
    TextBox t = null;
    try {
     c = (HttpConnection)Connector.open(url);
     c.setRequestMethod(HttpConnection.POST);
     c.setRequestProperty("IF-Modified-Since", "25 Nov 2001 15:17:19 GMT");
     c.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
     c.setRequestProperty("Content-Language", "en-CA");
     c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
     os = c.openOutputStream();
     os.write(("name="+myname).getBytes());
     os.flush();
     is = c.openDataInputStream();
     int ch;
     while ((ch = is.read()) != -1) {
      b.append((char) ch);
      System.out.print((char)ch);
     }
     t = new TextBox("Date", b.toString(), 1024, 0);
     t.setCommandListener(this);
    }catch(IOException ex){
    
    } 
    
    finally {
     if(is!= null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
     }
     if(os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
     }
     if(c != null) {
                try {
                    c.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
     }
    }
    display.setCurrent(t);
  }
public void commandAction(Command c, Displayable d) {
   String label = c.getLabel();
   if(label.equals("clear")) {
    destroyApp(true);
   } else if (label.equals("date?")) {
     myname = name.getString();
     try {
     invokeJSP(url);
     }catch(Exception e) {}
   }
}
}