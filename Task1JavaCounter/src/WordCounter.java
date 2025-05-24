import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounter {
    public static void main(String[] args) {
    
        JFrame frame = new JFrame("Word Counter");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 440, 100);

       
        JButton countButton = new JButton("Count Words");
        countButton.setBounds(180, 140, 140, 30);

        
        JLabel resultLabel = new JLabel("Word Count: 0");
        resultLabel.setBounds(20, 200, 300, 30);


        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText().trim();
                if (text.isEmpty()) {
                    resultLabel.setText("Word Count: 0");
                } else {
                    String[] words = text.split("\\s+"); // Split text by spaces
                    resultLabel.setText("Word Count: " + words.length);
                }
            }
        });

        
        frame.setLayout(null); 
        frame.add(scrollPane);
        frame.add(countButton);
        frame.add(resultLabel);

       
        frame.setVisible(true);
    }
}
