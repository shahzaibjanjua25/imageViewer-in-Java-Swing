/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author shahz
 */
public class ImageViewer {
    
    private JFrame frame;
    private ImagePanel imagePanel;
    private File[] imageFiles;
    private int currentIndex;

    public ImageViewer() {
       



        frame = new JFrame("Image Viewer");
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the program?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }
});

        frame.setLayout(new BorderLayout());
         frame.getContentPane().setBackground(Color.LIGHT_GRAY);
//         frame.setcolor
        
        imagePanel = new ImagePanel();
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Image Viewer");
        titleLabel.setFont(new Font("TImes New Roman", Font.BOLD, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setBackground(Color.white);
        frame.add(imagePanel, BorderLayout.CENTER);

//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0, 10, 10));

//          buttonPanel.setBackground(Color.WHITE);

        JButton openFolderButton = createIconButton("open.png", 30);
        openFolderButton.addActionListener(new OpenFolderActionListener());
        buttonPanel.add(openFolderButton);

        JButton rotateLeftButton = createIconButton("left.jpg", 30);
        rotateLeftButton.addActionListener(new RotateLeftActionListener());
        buttonPanel.add(rotateLeftButton);
      

        JButton rotateRightButton = createIconButton("right.jpg", 30);
        rotateRightButton.addActionListener(new RotateRightActionListener());
        buttonPanel.add(rotateRightButton);

        JButton zoomInButton = createIconButton("zoomin.jpg", 30);
        zoomInButton.addActionListener(new ZoomInActionListener());
        buttonPanel.add(zoomInButton);

        JButton zoomOutButton = createIconButton("zoomout.jpg", 30);
        zoomOutButton.addActionListener(new ZoomOutActionListener());
        buttonPanel.add(zoomOutButton);

        JButton slideshowButton = createIconButton("slidshow.jpg", 30);
        slideshowButton.addActionListener(new SlideshowActionListener());
        buttonPanel.add(slideshowButton);
        

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JButton createIconButton(String iconName, int size) {
        String imagePath = "icons/" + iconName;
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JButton button = new JButton(icon);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(size + 10, size + 10));


        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        button.setFocusPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255,255,255));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


    }
    public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setBackground(UIManager.getColor("control"));
    }
});
  button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setCursor(Cursor.getDefaultCursor());
        }
    });

        return button;
    }
public void loadImagesFromFolder(File folder) {
    if (folder.isDirectory()) {
        imageFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg") ||
                        lowercaseName.endsWith(".png") || lowercaseName.endsWith(".gif");
            }
        });

        currentIndex = 0;
        if (imageFiles.length > 0) {
            showImage(imageFiles[currentIndex]);
        } else {
            // Exception handling for "No images found"
            try {
                throw new Exception("No images found in the selected folder.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        }
    }
}


    private void showImage(File file) {
        Image image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
        MediaTracker mediaTracker = new MediaTracker(imagePanel);
        mediaTracker.addImage(image, 0);
        imagePanel.setBackground(Color.BLACK);


        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (mediaTracker.isErrorID(0)) {
            JOptionPane.showMessageDialog(frame, "Error loading image.");
        } else {
            imagePanel.setImage(image);
            frame.setTitle("Image Viewer - " + file.getName());
        }
    }


       

        private class RotateLeftActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.rotationAngle -= 90.0;
                imagePanel.repaint();
            }
        }

        private class RotateRightActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.rotationAngle += 90.0;
                imagePanel.repaint();
            }
        }

        private class ZoomInActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.zoomFactor += 0.1;
                imagePanel.repaint();
            }
        }

    private class ZoomOutActionListener implements ActionListener {
@Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.zoomFactor -= 0.1;
                imagePanel.repaint();
            }
}


        private class SlideshowActionListener implements ActionListener {
            private Timer slideshowTimer;
            private int delay = 2000; // 2 seconds delay between images

            @Override
            public void actionPerformed(ActionEvent e) {
                if (imageFiles.length > 1) {
                    if (slideshowTimer != null && slideshowTimer.isRunning()) {
                        slideshowTimer.stop();
                    } else {
                        slideshowTimer = new Timer(delay, new SlideshowTimerActionListener());
                        slideshowTimer.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Insufficient images for slideshow mode.");
                }
            }
        }

        private class SlideshowTimerActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % imageFiles.length;
                showImage(imageFiles[currentIndex]);
            }
        }

        private class OpenFolderActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = fileChooser.getSelectedFile();
                    loadImagesFromFolder(selectedFolder);
                }
            }
        }}