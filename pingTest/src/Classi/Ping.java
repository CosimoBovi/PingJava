package Classi;

import java.net.InetAddress;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;


public class Ping implements Runnable{

    private boolean stop;
    private DefaultListModel<String> model;
    private String indirizzo;
    
    public Ping(String indirizzo, DefaultListModel<String> model){
        stop=false;
        this.model=model;
        this.indirizzo=indirizzo;
    }
    
    @Override
    public void run() { 
        try{
        	Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            while(!stop){
        
           
                InetAddress address = InetAddress.getByName(indirizzo);
                
                long finish = 0;
                long start = new GregorianCalendar().getTimeInMillis();
 
                if (address.isReachable(5000)){
                  finish = new GregorianCalendar().getTimeInMillis();
                  model.addElement("Ping:" + address.getHostName() + " Time:" + (finish-start) + "ms");
                } else {
                   model.addElement(address.getHostName() + " NOT reachable.");
                }
 
                Thread.sleep(1000);
          
            }
        } catch (Exception e){
            model.addElement(indirizzo + " NOT found");
        } 
    }
    
    public void stop(){
          stop=true;
    }
    
    
        
}
