package creator.tile.pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static creator.tile.pokemon.ApplicationManager.INSTANCE;

public class MainApplication {
    private JPanel advenceTileView;
    private JButton uploadButton;
    private JTabbedPane tabbedPane1;

    final JFileChooser fileChooser = new JFileChooser();

    public MainApplication() {
        uploadButton.addActionListener(new ImageUpload());
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
