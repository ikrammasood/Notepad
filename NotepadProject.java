/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notepadproject;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Salman
 */
public class NotepadProject implements ActionListener{
    JMenuBar menu = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");
    
    JMenuItem newfile = new JMenuItem("New");
    JMenuItem openfile = new JMenuItem("Open");
    JMenuItem savefile = new JMenuItem("Save");
    JMenuItem printfile = new JMenuItem("Print");
    JMenuItem exitfile = new JMenuItem("Exit");
    
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectall = new JMenuItem("SelectAll");
    
    JMenuItem about = new JMenuItem("About");
    JTextArea text = new JTextArea();
    
public JFrame jframe;
    public NotepadProject() {
    jframe=new JFrame("Notepad");
    jframe.setVisible(true);
    jframe.setSize(700,700);
//    jframe.setLayout(null);
    jframe.setLayout(new GridLayout() );
    jframe.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    jframe.setLocationRelativeTo(null);
    
        
        
        menu.add(file);
        menu.add(edit);
       
        jframe.setJMenuBar(menu);
        
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(printfile);
        file.add(exitfile);
        
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        
   
        
        JScrollPane scroll = new JScrollPane(text);
        jframe.add(scroll);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        printfile.addActionListener(this);
        exitfile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        
        
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        printfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        exitfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK));
        
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        
        

        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
      NotepadProject ns = new NotepadProject();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equalsIgnoreCase("New")){
           text.setText(null);
       }else if(e.getActionCommand().equalsIgnoreCase("Open")){
           JFileChooser filechoose = new JFileChooser();
           FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Files(.txt)","txt");
           filechoose.setAcceptAllFileFilterUsed(false);
           filechoose.addChoosableFileFilter(textfilter);
           int action = filechoose.showSaveDialog(null);
           if (action!=JFileChooser.APPROVE_OPTION){
               return;
           }else {
               try {
                       BufferedReader reader = new BufferedReader(new FileReader(filechoose.getSelectedFile()));
                       text.read(reader, null);
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
           
           }
           
           
       }else if(e.getActionCommand().equalsIgnoreCase("Save")){
           JFileChooser filechoose = new JFileChooser();
           FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Files(.txt)","txt");
           filechoose.setAcceptAllFileFilterUsed(false);
           filechoose.addChoosableFileFilter(textfilter);
           int action = filechoose.showSaveDialog(null);
           if (action!=JFileChooser.APPROVE_OPTION){
               return;
           }else {
               String filename = filechoose.getSelectedFile().getAbsolutePath().toString();
               if (!filename.contains(".txt"))
                   filename=filename+".txt";
                   
                   try {
                       BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                       text.write(writer);
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
                   
               }
           
               
                   
       }else if(e.getActionCommand().equalsIgnoreCase("Print")){
           try {
               text.print();
           } catch (PrinterException ex) {
               Logger.getLogger(NotepadProject.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }else if(e.getActionCommand().equalsIgnoreCase("Exit")){
           System.exit(0);
       }else if(e.getActionCommand().equalsIgnoreCase("Cut")){
           text.cut();
       }else if(e.getActionCommand().equalsIgnoreCase("Copy")){
           text.copy();
       }else if(e.getActionCommand().equalsIgnoreCase("Paste")){
           text.paste();
       }else if(e.getActionCommand().equalsIgnoreCase("SelectAll")){
           text.selectAll();
       }
           
       
    }
    
}