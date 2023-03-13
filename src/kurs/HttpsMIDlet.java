package kurs;
import java.io.*;
import javax.microedition.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.pki.*;

public class HttpsMIDlet extends MIDlet implements CommandListener, Runnable {
  private Display mDisplay;
  private Form mForm;
  public void startApp() {
    mDisplay = Display.getDisplay(this);
    if (mForm == null) {
      mForm = new Form("HttpsMIDlet");
      mForm.addCommand(new Command("Exit", Command.EXIT, 0));
      mForm.addCommand(new Command("Send", Command.SCREEN, 0));
      mForm.setCommandListener(this);
    }
    mDisplay.setCurrent(mForm);
  }
  public void pauseApp() {}
  public void destroyApp(boolean unconditional) {}
  public void commandAction(Command c, Displayable s) {
    if (c.getCommandType() == Command.EXIT) notifyDestroyed();
    else {
      Form waitForm = new Form("Connecting...");
      mDisplay.setCurrent(waitForm);
      Thread t = new Thread(this);
      t.start();
    }
  }
  public void run() {
    String url ="http://localhost:8084/Odev_13_18_2/index.jsp";
    try {
      // Query the server and retrieve the response.
      HttpsConnection hc = (HttpsConnection)Connector.open(url);
      SecurityInfo si = hc.getSecurityInfo();
      Certificate c = si.getServerCertificate();
      String subject = c.getSubject();

      String s = "Server certificate subject: \n" + subject;
      Alert a = new Alert("Result", s, null, null);
      a.setTimeout(Alert.FOREVER);
      mDisplay.setCurrent(a, mForm);
      hc.close();
    }
    catch (IOException ioe) {
      Alert a = new Alert("Exception", ioe.toString(), null, null);
      a.setTimeout(Alert.FOREVER);
      mDisplay.setCurrent(a, mForm);
    }
  }
} 