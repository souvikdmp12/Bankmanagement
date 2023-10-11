
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    
    String pinnumber;
    MiniStatement(String pinnumber){
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel text= new JLabel();
        add(text);
        
        JLabel bank =new JLabel("Mobile Bank");
        bank.setBounds(150,20,100,20);
        add(bank);
        
        JLabel card=new JLabel();
        card.setBounds(30,80,300,20);
        add(card);
        
        JLabel balance=new JLabel("Balance");
        balance.setBounds(20,400,300,20);
        add(balance);
        
        try{
            Conn conn=new Conn();
             ResultSet rs = conn.s.executeQuery("select * from login where pin ='"+pinnumber+"'");
             while(rs.next()){
                 card.setText("Card Number: "+ rs.getString("cardno").substring(0,4)+"XXXXXXXX"+rs.getString("cardno").substring(12));
             }
            
        }catch(Exception e){
            System.out.println(e);
        }
        try{
            Conn conn=new Conn();
            int bal =0;
             ResultSet rs = conn.s.executeQuery("select * from bank where pinnumber= '"+pinnumber+"'");
             while(rs.next()){
                 text.setText(text.getText()+"<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("amount")+"<br><br>");
             
                    if(rs.getString("type").equals("Deposit")){
                    bal +=Integer.parseInt(rs.getString("amount")); 
                }
                    else   
                {
                    bal -=Integer.parseInt(rs.getString("amount"));
                }
             }
             balance.setText("Your current account balance is RS "+bal);
            
        }catch(Exception e){
            System.out.println(e);  
        }
        text.setBounds(20,140,350,200);
        
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }
    
    
    public static void main(String args[]) {
        new MiniStatement("");
        
    }
}
