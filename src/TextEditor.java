import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea = new JTextArea();
    private JFileChooser fileChooser = new JFileChooser();
    private Font font = new Font("Arial", Font.PLAIN, 12);

    public TextEditor() {
        setTitle("Text Editor");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Soubor");
        menuBar.add(fileMenu);

        JMenuItem newFileItem = new JMenuItem("Nový");
        newFileItem.addActionListener(this);
        fileMenu.add(newFileItem);

        JMenuItem openFileItem = new JMenuItem("Otevřít");
        openFileItem.addActionListener(this);
        fileMenu.add(openFileItem);

        JMenuItem saveFileItem = new JMenuItem("Uložit");
        saveFileItem.addActionListener(this);
        fileMenu.add(saveFileItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

        textArea.setFont(font);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Nový")) {
            textArea.setText("");
        }
        else if (command.equals("Otevřít")) {
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = reader.readLine();

                    while (line != null) {
                        textArea.append(line + "\n");
                        line = reader.readLine();
                    }

                    reader.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (command.equals("Uložit")) {
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    PrintWriter writer = new PrintWriter(new FileWriter(file));
                    writer.println(textArea.getText());
                    writer.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (command.equals("Exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.setVisible(true);
    }
}