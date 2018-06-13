package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.bluray.ui.event.HRcEvent;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HScreen;
import org.havi.ui.HStaticText;
import org.havi.ui.HStillImageBackgroundConfiguration;

import javax.tv.xlet.*;
import org.dvb.ui.*;
import java.awt.*;
import org.havi.ui.*;



//import + implement all abstract... (om ResourceClient te gebruiken)

import org.havi.ui.HVisible;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;

public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener,
UserEventListener{
    
 private HTextButton knop1,knop2;
 private HScene scene ;

 HScreen screen;  //Dit maakt enkel de reference, object aanmaken via new
                //Hier gezet om globale/klasse variable te hebben
 HBackgroundDevice bgDevice;
 HBackgroundConfigTemplate bgTemplate;
 HStillImageBackgroundConfiguration bgConfiguration;
 
 //C:\Program Files\TechnoTrend\TT-MHP-Browser\fileio\DSMCC\0.0.3
 HBackgroundImage image[]=new HBackgroundImage[13];
 int geladen =0;
 int huidig = 0;
 int aantal = 0;
 boolean item1 = false;
 boolean item2 = false;
 boolean item3 = false;
 boolean item4 = false;
 boolean item5 = false;
 boolean item6 = false;
 
 int aantalItem1 = 0;
 int aantalItem2 = 0;
 int aantalItem3 = 0;
 int aantalItem4 = 0;
 int aantalItem5 = 0;
 int aantalItem6 = 0;
 
    
    private HStaticText hst;
    private HStaticText winkelwagen;
    String winkelwagenItems;
    
    
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException{
      //Alle initialisaties
        screen = HScreen.getDefaultHScreen();
        bgDevice = screen.getDefaultHBackgroundDevice();
        if(bgDevice.reserveDevice(this)){

            System.out.println("Background image device has been reserved");

        }
        else{
            System.out.println("Background image device cannot be reserved");
        }      
       bgTemplate = new HBackgroundConfigTemplate();
       bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, 
               HBackgroundConfigTemplate.REQUIRED);
       
       bgConfiguration = (HStillImageBackgroundConfiguration) 
               bgDevice.getBestConfiguration(bgTemplate);
       
       try{
        bgDevice.setBackgroundConfiguration(bgConfiguration);
    }
       catch(Exception ex){
           ex.printStackTrace();
       }
       image[0]=new HBackgroundImage("BG1.jpg");
       image[1]=new HBackgroundImage("BG2.jpg");
       image[2]=new HBackgroundImage("BG3.jpg");
       image[3]=new HBackgroundImage("BG4.jpg");
       image[4]=new HBackgroundImage("BG5.jpg");
       image[5]=new HBackgroundImage("BG6.jpg");
       image[6]=new HBackgroundImage("BG7.jpg");
       image[7]=new HBackgroundImage("BG8.jpg");
       image[8]=new HBackgroundImage("C1.jpg");
       image[9]=new HBackgroundImage("C2.jpg");
        image[10]=new HBackgroundImage("CO1.jpg");
         image[11]=new HBackgroundImage("CO2.jpg");
          image[12]=new HBackgroundImage("END.jpg");
    
       for(int i=0;i<13;i++)
        { 
            image[i].load(this); 
        }
       
       UserEventRepository repo = new UserEventRepository("naam");
       repo.addAllArrowKeys();
       repo.addKey(HRcEvent.VK_ENTER);
       EventManager.getInstance().addUserEventListener(this, repo);
       //public class HelloTVXlet implements Xlet, ResourceClient, 
       //HBackgroundImageListener,UserEventListener{
       //import + implement all
       
       
       scene = HSceneFactory.getInstance().getDefaultHScene();
       //globaal:
       //  private HScene scene;
       //private HStaticText hst;
       
       hst = new HStaticText("",-390,500,1000,1000); //x,y,w,h
       hst.setVerticalAlignment(HStaticText.VALIGN_TOP);
       hst.setForeground(new DVBColor (0,0,0,255));
       
       winkelwagen = new HStaticText("",-300,150,1000,1000); //x,y,w,h
       winkelwagen.setVerticalAlignment(HStaticText.VALIGN_TOP);
       winkelwagen.setForeground(new DVBColor (0,0,0,255));
       winkelwagen.setVisible(false);
       //TO DO: gebruik string tekst = hst.getTextContent(HVisible.NORMAL_STATE);
       //en hst.setTextContent(text, HVisible.NORMAL_STATE); om de tekst aan te passen
       //in UserEventReceived
       
     
   
        winkelwagenItems = winkelwagen.getTextContent(HVisible.NORMAL_STATE) ;
       scene.add(hst);
       scene.add(winkelwagen);
       scene.validate();
       scene.setVisible(true);
       
    }
    
    
    public void imageLoaded(HBackgroundImageEvent e) {
        geladen++;
        System.out.println("Images geladen: " + geladen);
        if (geladen ==13){
            try{
                bgConfiguration.displayImage(image[0]);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void imageLoadFailed(HBackgroundImageEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void userEventReceived(UserEvent e) {
        
        
        if (huidig > 7){
            hst.setVisible(false);
            
        }
        else if (huidig < 7 && aantal > 0)
        {
            hst.setVisible(true);
        }
        
       if (huidig == 8 ||huidig == 9 )
       {
           winkelwagen.setVisible(true);
       }
       else
       {
             winkelwagen.setVisible(false);
       }
        
        if(e.getType()==HRcEvent.KEY_PRESSED){
            if(e.getCode()==HRcEvent.VK_ENTER){
               
                switch (huidig){
                   
                    case 0:
                            aantal++;
                            item1 = true;
                            aantalItem1++;
                            
                            break;
                    case 1:
                            aantal++;
                            item2 = true;
                            aantalItem2++;
                            
                            break;
                    case 2:
                            aantal++;
                            item3 = true;
                            aantalItem3++;
                           
                            break;
                    case 3:
                            aantal++;
                            item4 = true;
                            aantalItem4++;
                            
                            break;
                    case 4:
                            aantal++;
                            item5 = true;
                            aantalItem5++;
                            
                            break;
                    case 5:
                            aantal++;
                            item6 = true;
                            aantalItem6++;
                            
                            break;
                            
                    //6 is shoppingcart
                    case 6:
                            huidig = 8;
                            
                            
                                 if (item1 != false)
                                 {
                                     winkelwagenItems = winkelwagenItems + "Zwarte armband   Aantal: " + String.valueOf(aantalItem1) + "\n" ;
                                 }
                                 if (item2 != false)
                                 {
                                    winkelwagenItems = winkelwagenItems + "Bruine koord    Aantal: " + String.valueOf(aantalItem2) + "\n" ;
                                 }
                                 if (item3 != false)
                                 {
                                    winkelwagenItems = winkelwagenItems + "Groene koord    Aantal: " + String.valueOf(aantalItem3) + "\n" ;
                                 }
                                 if (item4 != false)
                                 {
                                 winkelwagenItems = winkelwagenItems + "Zwart lava    Aantal:" + String.valueOf(aantalItem4) + "\n" ;
                                 }
                                 if (item5 != false)
                                 {
                                   winkelwagenItems = winkelwagenItems + "Tijgerhout   Aantal: " + String.valueOf(aantalItem5) + "\n" ;
                                 }
                                 if (item6 != false)
                                 {
                                    winkelwagenItems = winkelwagenItems + "Zwart mat     Aantal: " + String.valueOf(aantalItem6) + "\n" ;
                                 }
                             
                             
                                 
                             
                             winkelwagen.setTextContent(winkelwagenItems, HVisible.NORMAL_STATE);
                             winkelwagen.setVisible(true);
                             winkelwagen.repaint();
                             
                             System.out.println(winkelwagen);
                            break;
                   
               
                    //7 is checkout
                    case 7:
                            if (aantal != 0)
                            {
                                huidig = 10;
                            hst.setVisible(false); 
                            }
                            
                            break;
                           
                    //shoppingcart checkout knop
                    case 8:
                            //naar checkout gaan
                        huidig = 10;
                        hst.setVisible(false); 
                            break;
                            
                    //shoppingcart keer terug knop
                    case 9:
                            huidig = 0;
                             hst.setVisible(false);
                             winkelwagenItems = "";
                             
                             
                            break;
                    case 10:
                        //ja bij checkout --> ga naar eindscene
                            huidig = 12;
                            break;
                    case 11:
                        huidig = 0;
                            winkelwagenItems = "";
                            break;
                    
                      case 12:
                        huidig = 0;
                            aantal = 0;
                            winkelwagenItems = "";
                            winkelwagen.setTextContent("", HVisible.NORMAL_STATE);
                            
                            item1 = false;
                            item2 = false;
                            item3 = false;
                            item4 = false;
                            item5 = false;
                            item6 = false;
                            aantalItem1 = 0;
                            aantalItem2 = 0;
                            aantalItem3 = 0;
                            aantalItem4 = 0;
                            aantalItem5 = 0;
                            aantalItem6 = 0;
                            break;      
                }
                System.out.println(winkelwagenItems);
               //System.out.println(winkelwagenItems);
              
                
              
             
               
           
               hst.setTextContent(String.valueOf(aantal), HVisible.NORMAL_STATE);
               
            }
            if(e.getCode()==HRcEvent.VK_RIGHT){
                
                
                switch (huidig){
                    case 0:
                            huidig++;
                            break;
                    case 1:
                            huidig++;
                            break;
                    case 2:
                            huidig++;
                            break;
                    case 3:
                            huidig++;
                            break;
                    case 4:
                            huidig++;
                            break;
                    case 5:
                            huidig++;
                            break;
                    case 6:
                            huidig++;
                            break;
                    case 7:
                            huidig = 0;
                            break;
                            
                    case 8:
                            huidig = 9;
                            break;
                    case 9:
                            huidig = 8;
                            break;
                    case 10:
                                huidig = 11;
                                break;
                    case 11:
                                huidig = 10;
                                break;
                }
                
                System.out.println(huidig);
            }
            if(e.getCode()==HRcEvent.VK_LEFT){
                
                
                switch (huidig){
                    case 0:
                            huidig = 7;
                            break;
                    case 1:
                            huidig--;
                            break;
                    case 2:
                            huidig--;
                            break;
                    case 3:
                            huidig--;
                            break;
                    case 4:
                            huidig--;
                            break;
                    case 5:
                            huidig--;
                            break;
                    case 6:
                            huidig--;
                            break;
                    case 7:
                            huidig--;
                            break;
                            
                     case 8:
                            huidig = 9;
                            break;
                    case 9:
                            huidig = 8;
                            break;
                             case 10:
                                huidig = 11;
                                break;
                    case 11:
                                huidig = 10;
                                break;
                }
                
                 System.out.println(huidig);
            }
            
             if(e.getCode()==HRcEvent.VK_DOWN){
                 
                 if ( huidig == 6 || huidig == 7 || huidig > 8)
                 {
                     huidig = huidig;
                 }
                 else if(huidig == 4){
                      huidig = 6;
                  }
                 else if(huidig == 5){
                      huidig = 6;
                  }
                  else
                  {
                      huidig = huidig + 3;
                  }
                 System.out.println(huidig);
            }
            
            if(e.getCode()==HRcEvent.VK_UP){
                
               if (huidig == 0 || huidig ==1 || huidig ==2 || huidig > 8)
               {
                   huidig = huidig;
               }
               else
               {
                   huidig = huidig - 3;
               }
                 System.out.println(huidig);
            }
            try{
                bgConfiguration.displayImage(image[huidig]);
            }
            catch(Exception ex){
                ex.printStackTrace();
        }
    }

}
    
    public void startXlet() {
        //Al de andere code
       
        
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        
    }

    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        return false;
    }

    public void release(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyRelease(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
    