import selenium.Facebook;
import selenium.Network;
import selenium.Twitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;

public class Main extends JFrame implements ActionListener {
    private Container c;
    private JButton jPost;
    private JTextField jUsername;
    private JPasswordField jPassword;
    private JRadioButton jFacebook;
    private JRadioButton jTwitter;
    private JLabel jLabel;
    private JTextArea jMessage;
    private ButtonGroup group;



    public Main(){

        setTitle("Template Method | Social Media Posting");
        setBounds(300, 90, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        jLabel = new JLabel("Username");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 10);
        c.add(jLabel);

        jUsername = new JTextField();
        jUsername.setFont(new Font("Arial", Font.PLAIN, 12));
        jUsername.setSize(200, 20);
        jUsername.setLocation(160, 10);
        c.add(jUsername);


        jLabel = new JLabel("Password");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 40);
        c.add(jLabel);

        jPassword = new JPasswordField();
        jPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        jPassword.setSize(200, 20);
        jPassword.setLocation(160, 40);
        c.add(jPassword);

        jLabel = new JLabel("Message");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 70);
        c.add(jLabel);

        jMessage = new JTextArea();
        jMessage.setLineWrap(true);
        jMessage.setFont(new Font("Arial", Font.PLAIN, 12));
        jMessage.setSize(200, 70);
        jMessage.setLocation(160, 70);
        c.add(jMessage);

        jLabel = new JLabel("Choose Sosmed");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel.setSize(140, 20);
        jLabel.setLocation(10, 150);
        c.add(jLabel);


        jFacebook = new JRadioButton("Facebook");
        jFacebook.setFont(new Font("Arial", Font.PLAIN, 12));
        jFacebook.setSize(200, 20);
        jFacebook.setLocation(160, 150);
        c.add(jFacebook);

        jTwitter = new JRadioButton("Twitter");
        jTwitter.setFont(new Font("Arial", Font.PLAIN, 12));
        jTwitter.setSize(200, 20);
        jTwitter.setLocation(160, 170);
        c.add(jTwitter);

        group = new ButtonGroup();
        group.add(jFacebook);
        group.add(jTwitter);

        jPost = new JButton("Post Message");
        jPost.setFont(new Font("Arial", Font.PLAIN, 12));
        jPost.setSize(120, 20);
        jPost.setLocation(200, 200);
        jPost.addActionListener(this);
        c.add(jPost);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        Network network = null;
        if (e.getSource() == jPost) {
            if (jUsername.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Username Harus Diisi!", "Informasi", JOptionPane.ERROR_MESSAGE);
            } else if (jPassword.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Password Harus Diisi!", "Informasi", JOptionPane.ERROR_MESSAGE);
            }else if (jMessage.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Pesan Harus Diisi!", "Informasi", JOptionPane.ERROR_MESSAGE);
            }else if(group.getButtonCount()==0){
                JOptionPane.showMessageDialog(null, "Social Media Harus Diisi!", "Informasi", JOptionPane.ERROR_MESSAGE);
            }else{
                if(jFacebook.isSelected()){
                    network = new Facebook(jUsername.getText(),new String(jPassword.getPassword()));
                    try {
                        network.post(jMessage.getText());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    network = new Twitter(jUsername.getText(),new String(jPassword.getPassword()));
                    try {
                        network.post(jMessage.getText());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
    Main main = new Main();
    }



}
