package com.cpmpany;
//package com.cpmpany;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Rating {
    JFrame rating=new JFrame();
    private JPanel ratingPanel;
    private JTextArea textArea1;
    private JSlider slider1;
    private JButton SUBMITButton;
    private JLabel RATEUSLabel;
    private JTextField textField1;
    private JLabel NAMELabel;
    private JLabel FEEDBACKLabel;
    private JLabel rate;

    public  Rating(){
        rating.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rating.setContentPane(ratingPanel);
        rating.pack();
        rating.setLocationRelativeTo(null);
        rating.setVisible(true);

        slider1.setMinimum(0);
        slider1.setMaximum(5);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(1);

        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().equals("")|| textField1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"please Fill Name and FeedBack to submit"  );

                } else{
                    try{
                        String sql = "insert into rate" + "(Name,Rating,FeedBack)" + "values (?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/SurveySystem?useSSL=false" , "root","archita");
                        PreparedStatement statement=connection.prepareStatement(sql);

                        statement.setString(1,textField1.getText());
                        statement.setString(2,String.valueOf(slider1.getValue()));
                        statement.setString(3,textArea1.getText());

                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"RATED SUCCESFULLY");

                        textField1.setText("");
                        textArea1.setText("");
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
        });
slider1.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseAdapter e){
        rate.setText(String.valueOf(slider1.getValue()) + " Star");
    }
});

    }

}
