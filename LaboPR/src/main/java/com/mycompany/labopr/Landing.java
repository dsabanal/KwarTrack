package com.mycompany.labopr;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Landing extends JFrame {

    public Landing() {
        setTitle("KwarTrack - Landing Page");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x7ed957));
        setLayout(new BorderLayout());

        // ✅ Set Window Icon
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/KTrack Logo.png")); // ensure file is in resources folder
            setIconImage(logoIcon.getImage());
        } catch (Exception e) {
            System.out.println("Logo icon not found for window.");
        }

        // ✅ Top Navigation Bar
        JPanel topNav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        topNav.setBackground(new Color(0x7ed957));
        topNav.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Navigation Buttons
        JButton homeBtn = createNavButton("Home");
        JButton aboutBtn = createNavButton("About");
        JButton contactBtn = createNavButton("Contact");

        // ✅ Action Listeners for Buttons
        homeBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "<html><h2>Home</h2><p>This is the Home page draft.</p></html>",
                "Home",
                JOptionPane.INFORMATION_MESSAGE);
        });

        aboutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "<html><h2>About</h2><p>KwarTrack helps you manage and track your finances easily.</p></html>",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
        });

        contactBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "<html><h2>Contact</h2><p>Email: support@kwartrack.com<br>Phone: +123-456-7890</p></html>",
                "Contact",
                JOptionPane.INFORMATION_MESSAGE);
        });

        // Add buttons to top navigation bar
        topNav.add(homeBtn);
        topNav.add(aboutBtn);
        topNav.add(contactBtn);

        // ✅ Main Content Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // ✅ Load Logo (Displayed on GUI)
        JLabel logoLabel = new JLabel();
        try {
            ImageIcon logoImage = new ImageIcon(getClass().getResource("/KTrack Logo.png")); // ensure in resources
            Image scaledLogo = logoImage.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledLogo));
        } catch (Exception e) {
            logoLabel.setText("Logo not found");
            logoLabel.setForeground(Color.WHITE);
        }
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label1 = new JLabel("Welcome to KwarTrack!");
        label1.setForeground(Color.white);
        label1.setFont(new Font("Poppins", Font.BOLD, 56));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("Your personal finance tracker and manager");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Poppins", Font.PLAIN, 20));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(100));
        centerPanel.add(logoLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(label1);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(label2);

        // ✅ Add to Frame
        add(topNav, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ✅ Helper Method to Create Navigation Buttons
    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(0x7ed957));
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Landing::new);
    }
}
