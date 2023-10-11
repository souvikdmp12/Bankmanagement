package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    
    JButton deposit,withdrawl,ministatement,pinchange,fastcash,balanceenquiry,back;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber=pinnumber;
        
        
        setLayout(null);
        
        
        ImageIcon i1=new ImageIcon  (ClassLoader.getSystemResource("icons/atm3.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        JLabel text=new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(300,270,600,30);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System",Font.BOLD,18));
        image.add(text);
        
        deposit = new JButton("RS 100");
        deposit.setBounds(170,320,150,30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdrawl = new JButton("RS 500");
        withdrawl.setBounds(584,320,150,30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);
        
        fastcash = new JButton("RS 1000");
        fastcash.setBounds(170,370,150,30);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        
        ministatement = new JButton("RS 2000");
        ministatement.setBounds(584,370,150,30);
        ministatement.addActionListener(this);
        image.add(ministatement);
        
        
        pinchange = new JButton("RS 5000");
        pinchange.setBounds(170,417,150,30);
        deposit.addActionListener(this);
        image.add( pinchange);
        
        balanceenquiry = new JButton("RS 10000");
        balanceenquiry.setBounds(584,417,150,30);
        balanceenquiry.addActionListener(this);
        image.add( balanceenquiry);
        
        
        back= new JButton("BACK");
        back.setBounds(584,465,150,30);
        back.addActionListener(this);
        image.add( back);
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
        else  {
            String amount =((JButton)ae.getSource()).getText().substring(3);//Rs 500
            Conn c=new Conn();
            try{
                ResultSet rs=c.s.executeQuery("select * from bank where pinnumber='"+pinnumber+"'");
                int balance =0;{
                while(rs.next())
                    if(rs.getString("type").equals("Deposit")){
                    balance +=Integer.parseInt(rs.getString("amount")); 
                }
                else
                   
                {
                    balance -=Integer.parseInt(rs.getString("amount"));
                }
                }
            
                if(ae.getSource()!=back && balance <Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                Date date =new Date();
                String query="Insert into bank values('"+pinnumber+"', '"+date+"', 'withdrawl' '"+amount+"',)";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+amount+" Debited Successfully");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
     
    }
    
    public static void main(String[] args){
        new FastCash("");
    }
}

