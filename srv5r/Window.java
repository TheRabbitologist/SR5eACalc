/*
 * Copyright (c) 2015 TheRabbitologist.
 * This file is licensed under the 4-clause BSD License.
 *
 * For more info, see LICENSE.txt.
 */
package srv5r;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class TextAreaOutputStream extends OutputStream {
    private final JTextArea ta;
    
    public TextAreaOutputStream(JTextArea ta) {
        this.ta = ta;
    }
    
    @Override
    public void write(int c) throws IOException {
        ta.append(String.valueOf((char)c));
    }  
}

/**
 *
 * @author amayas
 */
public class Window {
    private JFrame frame;
    private JTextField jtf;
    private StringBuilder input;
    private JScrollPane jsp;
    private JTextArea ta;
    public Window() {
        frame = new JFrame();
        frame.setTitle(Main.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        input = new StringBuilder();
        
        ta = new JTextArea();
        ta.setEditable(false);
        PrintStream ps = new PrintStream(new TextAreaOutputStream(ta));
        System.setOut(ps);
        jsp = new JScrollPane(ta);
        
        jtf = new JTextField(24);
        jtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (input) {
                    input.append(jtf.getText());
                    input.notify();
                }
            }
        });
        jtf.disable();

        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridheight = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbl.setConstraints(jsp, gbc);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbl.setConstraints(jtf, gbc);
        frame.add(jsp);
        frame.add(jtf);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(640, 320);
    }
    public String input() {
        jtf.enable();
        jtf.grabFocus();
        input.delete(0, input.length());
        try {
            synchronized (input) {
                while (input.length() == 0)
                    input.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        jtf.setText("");
        jtf.disable();
        return input.toString();
    }
    public void scroll() {
        JScrollBar h = jsp.getHorizontalScrollBar();
        if(h != null)
            h.setValue(h.getMinimum());
        JScrollBar v = jsp.getVerticalScrollBar();
        if(v != null)
            v.setValue(v.getMaximum());
    }
    public void clear() {
        ta.setText("");
    }
}
