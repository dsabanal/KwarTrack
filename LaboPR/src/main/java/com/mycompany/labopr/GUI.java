package com.mycompany.labopr;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
    private JButton loginButton;
    private JButton signUpButton;

    // ✅ Authentication dialogs
    public static class AuthDialogs {
        public static void handleLogin(JFrame parent) {
            String user = JOptionPane.showInputDialog(parent, "Enter username:");
            if (user == null) return;

            JPasswordField pf = new JPasswordField();
            int ok = JOptionPane.showConfirmDialog(parent, pf, "Enter password:", JOptionPane.OK_CANCEL_OPTION);
            if (ok != JOptionPane.OK_OPTION) return;

            String pass = new String(pf.getPassword());

            if (UserAuth.login(user, pass)) {
                JOptionPane.showMessageDialog(parent, "Login successful!");
                parent.dispose();
                new Landing().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(parent, "Invalid credentials.");
            }
        }

        public static void handleSignUp(JFrame parent) {
            String user = JOptionPane.showInputDialog(parent, "Choose a username:");
            if (user == null) return;

            JPasswordField pf = new JPasswordField();
            int ok = JOptionPane.showConfirmDialog(parent, pf, "Choose a password:", JOptionPane.OK_CANCEL_OPTION);
            if (ok != JOptionPane.OK_OPTION) return;

            String pass = new String(pf.getPassword());

            if (UserAuth.signUp(user, pass)) {
                JOptionPane.showMessageDialog(parent, "Sign up successful!");
            } else {
                JOptionPane.showMessageDialog(parent, "Username already exists!");
            }
        }
    }

    public GUI() {
        setTitle("Main Menu");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x7ed957));
        setLayout(new BorderLayout());

        // ✅ Top Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(0x7ed957));
        logoPanel.setBorder(new EmptyBorder(30, 0, 20, 0));
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/KTrack Logo.png"));

            Image scaledLogo = logoIcon.getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            JLabel fallbackLogo = new JLabel("KwarTrack");
            fallbackLogo.setFont(new Font("Poppins", Font.BOLD, 36));
            fallbackLogo.setForeground(Color.WHITE);
            logoPanel.add(fallbackLogo);
        }

        // ✅ Middle Welcome Message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(0x7ed957));
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to KwarTrack!");
        welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 56));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Your personal finance tracker and manager");
        subtitleLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        welcomePanel.add(subtitleLabel);
        welcomePanel.add(Box.createVerticalGlue());

        // ✅ Bottom Buttons (Vertical Layout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0x7ed957));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(30, 0, 50, 0));

        // ✅ Rounded Button Class
        class RoundedButton extends JButton {
            public RoundedButton(String text) {
                super(text);
                setOpaque(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setContentAreaFilled(false);
            }
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2d.dispose();
                super.paintComponent(g);
            }
        }

        loginButton = new RoundedButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(0x7ed957));
        loginButton.setFont(new Font("Poppins", Font.BOLD, 18));
        loginButton.setPreferredSize(new Dimension(250, 60));
        loginButton.setMaximumSize(new Dimension(250, 60));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        signUpButton = new RoundedButton("Sign Up");
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setForeground(new Color(0x7ed957));
        signUpButton.setFont(new Font("Poppins", Font.BOLD, 18));
        signUpButton.setPreferredSize(new Dimension(250, 60));
        signUpButton.setMaximumSize(new Dimension(250, 60));
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ✅ Button Actions
        loginButton.addActionListener(e -> AuthDialogs.handleLogin(this));
        signUpButton.addActionListener(e -> AuthDialogs.handleSignUp(this));

        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(signUpButton);

        // ✅ Add Panels to Frame
        add(logoPanel, BorderLayout.NORTH);
        add(welcomePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

