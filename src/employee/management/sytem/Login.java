package employee.management.sytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField tusername;
    JPasswordField tpassword;
    JButton login,back;

    //constructor
    Login(){

        JLabel username = new JLabel("username:");
        username.setBounds(40,20,100,30);
        add(username);

        tusername=new JTextField();
        tusername.setBounds(150,20,150,30);
        add(tusername);


        JLabel password = new JLabel("Password:");
        password.setBounds(40,70,100,30);
        add(password);

        tpassword=new JPasswordField();
        tpassword.setBounds(150,70,150,30);
        add(tpassword);

        login = new JButton("LOGIN");
        login.setBounds(150,140,150,30);
        login.setForeground(Color.black);
        login.addActionListener(this);
        add(login);

        back = new JButton("BACK");
        back.setBounds(150,180,150,30);
        back.setForeground(Color.black);
        back.addActionListener(this);
        add(back);

        ImageIcon i11= new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i22=i11.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i33=new ImageIcon(i22);
        JLabel img1= new JLabel(i33);
        img1.setBounds(350,10,600,400);
        add(img1);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2=i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img= new JLabel(i3);
        img.setBounds(0,0,600,300);
        add(img);

        setSize(600,300);
        setLocation(450,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            try {
                String username = tusername.getText();
                String password = tpassword.getText();

                // Create an instance of the Connect class
                Connect connect = new Connect();

                // Check if the connection was successful
                if (connect.connection != null) {
                    // Use PreparedStatement to avoid SQL injection
                    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                    PreparedStatement preparedStatement = connect.connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        setVisible(false);
                        new Main_class(); // Assuming this is your main application class
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }

                    // Close resources
                    resultSet.close();
                    preparedStatement.close();
                    connect.connection.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Database connection not established.");
                }

            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

        else if(e.getSource()==back){
        System.exit(143);
    }
    }

    public static void main(String[] args) {
        new Login(); //object
    }
}
