package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Restaurant.DeliveryUI;
import Restaurant.RestaurantUI;
import Server.ServerView;
import Pedido.Pedido;   

public class ProyectoRestaurante extends JFrame{
    
    public ProyectoRestaurante(){
        setTitle("Bite & Enjoy");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(45, 45, 45));
        header.setBorder(
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
        );
        
        JLabel title = new JLabel("Bite & Enjoy");
        title.setForeground(Color.WHITE);
        
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        
        JPanel main = new JPanel(new BorderLayout());
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(2, 2, 20, 20));
        grid.setBackground(Color.WHITE);
        
        JLabel res = new JLabel("Restaurante");
        res.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topR = new JPanel(new BorderLayout());
        topR.setBackground(new Color(150, 150, 150));
        topR.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topR.add(res, BorderLayout.CENTER);
        
        ImageIcon rawRes = new ImageIcon(getClass().getResource("/restaurante.png"));
        
        Image scaledRes = rawRes.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        JLabel lblIRes = new JLabel(new ImageIcon(scaledRes));
        lblIRes.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnRes = new JPanel(new BorderLayout());
        btnRes.setBackground(new Color(150, 150, 150));
        btnRes.add(topR, BorderLayout.NORTH);
        btnRes.add(lblIRes, BorderLayout.CENTER);
        btnRes.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnRes.setBackground(new Color(98, 98, 98));
                    btnRes.repaint();
                    topR.setBackground(new Color(98, 98, 98));
                    topR.repaint();
                    lblIRes.setBackground(new Color(98, 98, 98));
                    lblIRes.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnRes.setBackground(new Color(150, 150, 150));
                    btnRes.repaint();
                    topR.setBackground(new Color(150, 150, 150));
                    topR.repaint();
                    lblIRes.setBackground(new Color(150, 150, 150));
                    lblIRes.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    RestaurantUI res = new RestaurantUI();
                    res.setVisible(true);
                    setVisible(false);
                }
        });
        
        JLabel del = new JLabel("Delivery");
        del.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topD = new JPanel(new BorderLayout());
        topD.setBackground(new Color(150, 150, 150));
        topD.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topD.add(del, BorderLayout.CENTER);
        
        ImageIcon rawDel = new ImageIcon(getClass().getResource("/delivery.png"));
        
        Image scaledDel = rawDel.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        JLabel lblIDel = new JLabel(new ImageIcon(scaledDel));
        lblIDel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnDel = new JPanel(new BorderLayout());
        btnDel.setBackground(new Color(150, 150, 150));
        btnDel.add(topD, BorderLayout.NORTH);
        btnDel.add(lblIDel, BorderLayout.CENTER);
        btnDel.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnDel.setBackground(new Color(98, 98, 98));
                    btnDel.repaint();
                    topD.setBackground(new Color(98, 98, 98));
                    topD.repaint();
                    lblIDel.setBackground(new Color(98, 98, 98));
                    lblIDel.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnDel.setBackground(new Color(150, 150, 150));
                    btnDel.repaint();
                    topD.setBackground(new Color(150, 150, 150));
                    topD.repaint();
                    lblIDel.setBackground(new Color(150, 150, 150));
                    lblIDel.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    DeliveryUI del = new DeliveryUI();
                    del.setVisible(true);
                    setVisible(false);
                }
        });
        
        JLabel ser = new JLabel("Server");
        ser.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topS = new JPanel(new BorderLayout());
        topS.setBackground(new Color(150, 150, 150));
        topS.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topS.add(ser, BorderLayout.CENTER);
        
        ImageIcon rawSer = new ImageIcon(getClass().getResource("/server.png"));
        
        Image scaledSer = rawSer.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        JLabel lblISer = new JLabel(new ImageIcon(scaledSer));
        lblISer.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnSer = new JPanel(new BorderLayout());
        btnSer.setBackground(new Color(150, 150, 150));
        btnSer.add(topS, BorderLayout.NORTH);
        btnSer.add(lblISer, BorderLayout.CENTER);
        btnSer.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnSer.setBackground(new Color(98, 98, 98));
                    btnSer.repaint();
                    topS.setBackground(new Color(98, 98, 98));
                    topS.repaint();
                    lblISer.setBackground(new Color(98, 98, 98));
                    lblISer.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnSer.setBackground(new Color(150, 150, 150));
                    btnSer.repaint();
                    topS.setBackground(new Color(150, 150, 150));
                    topS.repaint();
                    lblISer.setBackground(new Color(150, 150, 150));
                    lblISer.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    ServerView ser = new ServerView();
                    ser.setVisible(true);
                    setVisible(false);
                }
        });
        
        JLabel ped = new JLabel("Pedidos");
        ser.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel topP = new JPanel(new BorderLayout());
        topP.setBackground(new Color(150, 150, 150));
        topP.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        topP.add(ped, BorderLayout.CENTER);
        
        ImageIcon rawPed = new ImageIcon(getClass().getResource("/pedidos.png"));
        
        Image scaledPed = rawPed.getImage().getScaledInstance(64, 64,Image.SCALE_SMOOTH);
        JLabel lblIPed = new JLabel(new ImageIcon(scaledPed));
        lblISer.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel btnPed = new JPanel(new BorderLayout());
        btnPed.setBackground(new Color(150, 150, 150));
        btnPed.add(topP, BorderLayout.NORTH);
        btnPed.add(lblIPed, BorderLayout.CENTER);
        btnPed.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    btnPed.setBackground(new Color(98, 98, 98));
                    btnPed.repaint();
                    topP.setBackground(new Color(98, 98, 98));
                    topP.repaint();
                    lblIPed.setBackground(new Color(98, 98, 98));
                    lblIPed.repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    btnPed.setBackground(new Color(150, 150, 150));
                    btnPed.repaint();
                    topP.setBackground(new Color(150, 150, 150));
                    topP.repaint();
                    lblIPed.setBackground(new Color(150, 150, 150));
                    lblIPed.repaint();
                }
                @Override
                public void mouseClicked(MouseEvent e){
                    Pedido ped = new Pedido();
                    ped.setVisible(true);
                    setVisible(false);
                }
        });
        
        grid.add(btnDel);
        grid.add(btnRes);
        grid.add(btnSer);
        grid.add(btnPed);
        main.add(grid, BorderLayout.CENTER);
        
        JPanel west = new JPanel(new BorderLayout());
        west.setBackground(Color.WHITE);
        west.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(west, BorderLayout.WEST);
        
        JPanel east = new JPanel(new BorderLayout());
        east.setBackground(Color.WHITE);
        east.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(east, BorderLayout.EAST);
        
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(Color.WHITE);
        north.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(north, BorderLayout.NORTH);
        
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(Color.WHITE);
        south.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        main.add(south, BorderLayout.SOUTH);
        
        add(main, BorderLayout.CENTER);
        
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProyectoRestaurante ui = new ProyectoRestaurante();
        });
    }
}
