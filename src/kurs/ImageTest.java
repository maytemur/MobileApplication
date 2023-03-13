package kurs;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ImageTest extends MIDlet {
    public void startApp(){
        try {
            Image image=Image.createImage("/MustafaAytemur.png");
            ImageCanvas canvas=new ImageCanvas(image);
            Display.getDisplay(this).setCurrent(canvas);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void pauseApp(){}
    public void destroyApp(boolean unconditional){}
    public class ImageCanvas extends Canvas{
        Image image;
        public ImageCanvas(Image image){
            this.image=image;
        }
        public void paint(Graphics g){
            g.drawImage(image,0,0,Graphics.TOP|Graphics.LEFT);
        }
    }
}