package kurs;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;
public class FormTest extends javax.microedition.midlet.MIDlet implements CommandListener{
    Command okCommand=new Command("OK",Command.OK,1);
    TextField field;
    ChoiceGroup group;
    DateField df;
    public void startApp() {
        Form form=new Form("Test Form");
        field=new TextField("Test","",1000,TextField.ANY);
        group=new ChoiceGroup("Deneme Grubu",ChoiceGroup.EXCLUSIVE);
        df=new DateField("Tarih Alani",DateField.DATE);
        group.append("A",null);
        group.append("B",null);
        group.append("C",null);
        form.append(field);
        form.append(group);
        form.append(df);
        form.addCommand(okCommand);
        form.setCommandListener(this);
        Display.getDisplay(this).setCurrent(form);
    }
    public void pauseApp() {    }
    public void destroyApp(boolean unconditional) {    }
    public void commandAction(Command c,Displayable d){
        if (c==okCommand){
            String text=field.getString();
            int index=group.getSelectedIndex();
            String selected=group.getString(index);
            Date date=df.getDate();
            System.out.println(text);
            System.out.println(selected);
            System.out.println(date);
        }
    }
}