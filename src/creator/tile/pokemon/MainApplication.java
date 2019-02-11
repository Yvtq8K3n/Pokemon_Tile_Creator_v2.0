package creator.tile.pokemon;

import com.sun.source.util.Plugin;
import creator.tile.pokemon.MEH.IO.BankLoader;
import org.zzl.minegaming.GBAUtils.*;
//import us.plxhack.MEH.IO.MapIO;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static creator.tile.pokemon.ApplicationManager.INSTANCE;

public class MainApplication {
    private JPanel advenceTileView;
    private JButton loadRomButton;
    private JButton uploadButton;
    private JTabbedPane tabbedPane1;

    public JTree mapBanks;
    private JLabel statusLabel;

    final JFileChooser fileChooser = new JFileChooser();

    public MainApplication() {
        loadRomButton.addActionListener(new LoadRom());
        uploadButton.addActionListener(new ImageUpload());
    }

    private class LoadRom implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int romState = GBARom.loadRom();
            switch (romState){
                case -2 :
                    JOptionPane.showMessageDialog(null, "The ROM could not be opened.\nIt may be open in another program.", "Error opening ROM", JOptionPane.WARNING_MESSAGE);
                    break;
                case -1: return;
                default: startLoadingRom();
            }
        }
    }

    private void startLoadingRom(){
        String s = Plugin.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(s);

        /*if (MapIO.DEBUG)
            System.out.println(s);*/
        INSTANCE.dataStore = new DataStore("MEH.ini", ROMManager.currentROM.getGameCode());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(null);
        DefaultTreeModel mapTree = new DefaultTreeModel(root);
        DefaultMutableTreeNode byBank = new DefaultMutableTreeNode("Maps by Tileset");
        root.add(byBank);
        mapBanks.setRootVisible(false);
        mapBanks.setModel(mapTree);


        statusLabel.setText("Loading...");

        //Change From BankLoader to Tiles loader, need understand how he loads tiles;_;
        BankLoader.reset();
        new BankLoader((int) DataStore.MapHeaders, ROMManager.getActiveROM(), statusLabel, mapBanks, byBank).start();
    }

    private class ImageUpload implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Bitmap", "bmp"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Png", "png"));
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
            int returnVal = fileChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try{
                    INSTANCE.setBufferedImage(ImageIO.read(file));
                    displayImage();
                }catch (Exception ex){
                    System.out.println("Unable to convert file into a bitmap image");
                }

            }
        }
    }

    //Temporary
    public void displayImage(){
        BufferedImage img=INSTANCE.getBufferedImage();
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img.getWidth(),img.getHeight());
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("MainApplication");
                frame.setTitle("Advance Pokemon Tile Creator");
                frame.setContentPane(new MainApplication().advenceTileView);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                //Sets window Icon
                URL url = ClassLoader.getSystemResource("creator/tile/pokemon/resources/icon.png");
                frame.setIconImage(Toolkit.getDefaultToolkit().createImage(url));
            }
        });
    }
}
