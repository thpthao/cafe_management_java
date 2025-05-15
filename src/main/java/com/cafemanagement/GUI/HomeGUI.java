package com.cafemanagement.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import com.cafemanagement.BLL.StaffBLL;
import com.cafemanagement.DTO.Account;
import com.cafemanagement.DTO.Staff;
import com.cafemanagement.custom.Button;
import com.cafemanagement.custom.Header;
import com.cafemanagement.custom.ImageAvatar;
import com.cafemanagement.custom.ProductPanel;
import com.cafemanagement.custom.RoundPanel;
import com.cafemanagement.main.CafeManagement;
import com.cafemanagement.utils.Day;
import com.cafemanagement.utils.Resource;
import com.cafemanagement.utils.Theme;

public class HomeGUI extends JFrame  {

    int currentPanel = 1;
    private Account account;
    private Staff staff;
    private int[] mang;
    private RoundPanel home;
    private Header north;
    private JPanel center;
    private RoundPanel east;
    private RoundPanel west;
    private RoundPanel info;
    private RoundPanel cate_frame;
    private RoundPanel cate;
    private RoundPanel function;
    private ProductPanel currentBtn;
    private JLabel currentLb;
    private Button exit;
    private Button minimize;
    private JLabel lbName;
    private JLabel lbRole;
    private JLabel lbTime;
    private JLabel[] jLabel = new JLabel[15];
    private RoundPanel[] rpContent = new RoundPanel[15];
    private int numberContent;
    private int addContent;
    private JLabel[] labelName = new JLabel[15];
    private ProductPanel[] roundPanel = new ProductPanel[15];
    private ImageAvatar[] imageAvatar = new ImageAvatar[15];
    private ImageAvatar[] imageIcon = new ImageAvatar[15];
    private int numberjpane;
    private String string1 = "img/icons/down.png";
    private int[] listCount = new int[3];
    private int totalHeight;
    private Color imageAvatarIcon;
    private boolean isListening;

    public HomeGUI(Account account) {
        this.account = account;
      getUser();
        initComponents();
        setTheme();
    }

    public static void main(String[] args) {
        CafeManagement.start();
        CafeManagement.loginGUI.dispose();
        CafeManagement.homeGUI.setVisible(true);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
       getUser();
        System.gc();
        initLeftMenu();
        selectRoundPanel(1);
        setTheme();
    }

    private void getUser() {
        staff = new StaffBLL().searchStaffs("STAFF_ID = '" + account.getStaffID() + "'").get(0);
    }

    public JPanel getCurrentGUI() {
        return (JPanel) function.getComponent(0);
    }

    private void initComponents() {
        home = new RoundPanel();
        north = new Header();
        lbTime = new JLabel();
        minimize = new Button();
        exit = new Button();

        center = new JPanel();
        west = new RoundPanel();
        east = new RoundPanel();

        info = new RoundPanel();
        lbName = new JLabel();
        lbRole = new JLabel();

        cate_frame = new RoundPanel();
        cate = new RoundPanel();
        function = new RoundPanel();
        mang = new int[15];
        for (int i = 1; i <=14; i++) {
            mang[i] = 1;
        }
        roundPanel = new ProductPanel[15]; 
        for (int i = 0; i < roundPanel.length; i++) {
            roundPanel[i] = new ProductPanel();
            imageAvatar[i] = new ImageAvatar();
            jLabel[i] = new JLabel();
        }

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        setUndecorated(true);
        home.setLayout(new BorderLayout(10, 10));
        setContentPane(home);

        // home.north
        north.setLayout(null);
        north.setPreferredSize(new Dimension(1300, 50));
        home.add(north, BorderLayout.NORTH);
            // set tg
        lbTime.setBounds(80, 10, 200, 30);
        lbTime.setFont(new Font("Public Sans", Font.PLAIN, 15));
        lbTime.setForeground(new Color(251, 252, 253));
        north.add(lbTime);
        BiConsumer<Button, java.util.List<Object>> configButton = (button, properties) -> {
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setFont(new Font("Public Sans", Font.BOLD, 15));
            button.setRadius(15);
            button.setBorderColor(Color.BLACK);
            button.setForeground(Color.BLACK);
            button.setText((String) properties.get(0));
            button.setBounds((Integer) properties.get(1), (Integer) properties.get(2), 50, 30);
            button.setColor((Color) properties.get(3));
            button.setColorOver((Color) properties.get(4));
            button.setColorClick((Color) properties.get(5));
            button.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    ((Runnable) properties.get(6)).run();
                }
            });
            north.add(button);
        };
        configButton.accept(minimize, java.util.List.of("-", 1235, 10, new Color(0xF3F0F0), new Color(0xC4BDBD),
                new Color(0x676161), (Runnable) this::minimize));
        
        configButton.accept(exit, java.util.List.of("X", 1290, 10, new Color(0xFD1111), new Color(0xB04848), new Color(0xE79292), (Runnable) this::exit));

        // home.center
        center.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        center.setPreferredSize(new Dimension(1350, 710));
        home.add(center, BorderLayout.CENTER);
        //Home.west
        west.setLayout(new BorderLayout(0, 10));
        west.setPreferredSize(new Dimension(300, 700));
        center.add(west);

        // home.center.east
        east.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        east.setPreferredSize(new Dimension(1020, 700));
        east.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(east);

        // home.center.west
        initLeftMenu();

        function.setPreferredSize(new Dimension(1005, 680));
        function.setBackground(new Color(255, 254, 254));
        pack();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15)); // arc là radius
        setLocationRelativeTo(null);
    }

    public void initLeftMenu() {
        listCount[0] = 0;
        numberContent = -1;
        isListening = true;

        west.removeAll();
        west.revalidate();
        west.repaint();
        cate_frame.removeAll();
        cate_frame.revalidate();
        cate_frame.repaint();
        cate.removeAll();
        cate.revalidate();
        cate.repaint();

        info.setLayout(null); 
        info.setPreferredSize(new Dimension(300, 80));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        west.add(info, BorderLayout.NORTH);

        roundPanel = new ProductPanel[15];
        for (int i = 0; i < roundPanel.length; ++i) {
            roundPanel[i] = new ProductPanel();
            imageAvatar[i] = new ImageAvatar();
            jLabel[i] = new JLabel();
        }

        imageAvatar[0].setForeground(new Color(255, 255, 255));
        imageAvatar[0].setBorderSize(2);
        imageAvatar[0].setBounds(20, 10, 60, 60);
        imageAvatar[0].setIcon(Resource.loadImageIcon("img/icons/working.png"));
        info.add(imageAvatar[0]);

        lbName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lbName.setForeground(new Color(251, 252, 253));
        lbName.setBounds(100, 15, 200, 20);
        lbName.setText("Tên: " + staff.getName());
        info.add(lbName);

        lbRole.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lbRole.setBounds(100, 45, 150, 20);
        lbRole.setForeground(new Color(251, 252, 253));
        lbRole.setText("Vai Trò: ADMIN" ); 
        info.add(lbRole);
        // done: info 

        cate_frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cate_frame.setPreferredSize(new Dimension(300, 600));
        cate_frame.setAlignmentX(Component.CENTER_ALIGNMENT);
        west.add(cate_frame, BorderLayout.CENTER);

        cate.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        cate.setPreferredSize(new Dimension(290, 600));
        cate.setAlignmentX(Component.CENTER_ALIGNMENT);
        cate_frame.add(cate);

        for (int i = 1; i < 3; i++) {
           if (mang[i] != 0) {
                roundPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 35, 7));
                roundPanel[i].setPreferredSize(new Dimension(280, 45));
                roundPanel[i].setAutoscrolls(true);
                roundPanel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                int index = i;
                roundPanel[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        selectRoundPanel(index);
                    }
                });
                cate.add(roundPanel[i]);
    }
        }

        for (int i =3 ; i <= 4; i++) {
                switch (i) {
                    case 3, 4 -> 
                        listCount[0]++;

                    default -> {
                    }
                }

        }

        rpContent = new RoundPanel[5];
        imageIcon = new ImageAvatar[5];
        for (int i = 0; i < rpContent.length; ++i) {
            rpContent[i] = new RoundPanel();
            labelName[i] = new JLabel();
            imageIcon[i] = new ImageAvatar();
        }

        if (listCount[0] != 0) {
            rpContent[0].setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            rpContent[0].setPreferredSize(new Dimension(280, 45));
            rpContent[0].setAutoscrolls(true);
            rpContent[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
            cate.add(rpContent[0]);

            rpContent[2].setLayout(new FlowLayout(FlowLayout.LEFT, 0, 7));
            rpContent[2].setPreferredSize(new Dimension(280, 45));
            rpContent[2].setAutoscrolls(true);
            rpContent[2].setCursor(new Cursor(Cursor.HAND_CURSOR));
            rpContent[2].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (isListening) {
                        isListening = false;
                        if (numberContent != -1 && numberjpane != 0) {
                            imageIcon[numberjpane].setIcon(null);
                            imageIcon[numberjpane].setIcon(Resource.loadImageIcon(string1));
                            pressDelay(numberContent, rpContent[numberContent].getHeight(), addContent);
                        }
                        totalHeight = (listCount[0] * 50) / 5;
                        if (rpContent[0].getHeight() == 45) {
                            numberjpane = 0;
                            pressDelay(0, rpContent[0].getHeight(), totalHeight);
                            numberContent = 0;
                            addContent = -totalHeight;
                        } else {
                            pressDelay(0, rpContent[0].getHeight(), -totalHeight);
                            numberContent = -1;
                        }
                        isListening = true;
                    }
                }
            });
            rpContent[0].add(rpContent[2]);

            labelName[0].setPreferredSize(new Dimension(230, 30));
            labelName[0].setFont(new Font("Times New Roman", Font.PLAIN, 18));
            labelName[0].setText("Quản lý sản phẩm");
            labelName[0].setHorizontalAlignment(SwingConstants.CENTER);
            labelName[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
            rpContent[2].add(labelName[0]);

            rpContent[3].setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
            rpContent[3].setPreferredSize(new Dimension(280, listCount[0] * 50));
            rpContent[3].setAutoscrolls(true);
            rpContent[3].setCursor(new Cursor(Cursor.HAND_CURSOR));
            rpContent[0].add(rpContent[3]);

            imageIcon[0].setForeground(new Color(255, 255, 255));
            imageIcon[0].setPreferredSize(new Dimension(30, 30));
            imageIcon[0].setBorderSize(2);
            imageIcon[0].setIcon(Resource.loadImageIcon(string1));
            rpContent[2].add(imageIcon[0], BorderLayout.EAST);

            for (int i = 3; i < 5; i++) {
                if (mang[i] != 0) {
                    roundPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 35, 7));
                    roundPanel[i].setPreferredSize(new Dimension(280, 45));
                    roundPanel[i].setAutoscrolls(true);
                    roundPanel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    int index = i;
                    roundPanel[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            selectRoundPanel(index);
                        }
                    });
                    rpContent[3].add(roundPanel[i]);
                }
            }
        }

        for (int i = 1; i < roundPanel.length; i++) {
            if (mang[i] != 0) {
                imageAvatar[i].setPreferredSize(new Dimension(30, 30));
                imageAvatar[i].setBorderSize(2);
                imageAvatar[i].setIcon(Resource.loadImageIcon(String.format("img/icons/%02d.png", i)));
                roundPanel[i].add(imageAvatar[i]);
                jLabel[i].setFont(new Font("Times New Roman", Font.PLAIN, 18));
                jLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
                jLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                roundPanel[i].add(jLabel[i]);
            }
        }

        jLabel[1].setText("Bán hàng");
        jLabel[2].setText("Hóa đơn");
        jLabel[3].setText("Sản phẩm"); 
        jLabel[4].setText("Loại sản phẩm");
    }

    private void setTheme() {
        String theme;
        Color rpContentBG, rpFolderBG, rpFolderFG, roundPanelBG, roundPanelColor, roundPanelColorOver, imageAvatarFG, labelBG, labelFG, currentBtnBG, currentLbFG;
        theme = "light";
        home.setBackground(new Color(240, 240, 240));
        north.setBackground(new Color(31, 57, 51));
        center.setBackground(new Color(240, 240, 240));
        west.setBackground(new Color(240, 240, 240));
        east.setBackground(new Color(248,247,248));
        info.setBackground(new Color(31, 57, 51));
        cate_frame.setBackground(new Color(248, 247, 248));
        cate.setBackground(new Color(248, 247, 248));
        rpContentBG = new Color(254,254,254);
        rpFolderBG = new Color(240, 240, 240);
        rpFolderFG = new Color(25, 25, 25);
        roundPanelBG = new Color(240, 240, 240);
        roundPanelColor = new Color(240, 240, 240);
        roundPanelColorOver = new Color(104, 162, 159);
        imageAvatarFG = new Color(225, 200, 73, 255);
        labelBG = new Color(0,0,0);
        labelFG = new Color(25, 25, 25);
        currentBtnBG = new Color(104, 162, 159);  
        currentLbFG = new Color(255,255,255);
        
        imageAvatarIcon = new Color(79, 194, 53);
        Theme.applyTheme(theme);
        selectRoundPanel(currentPanel);
        for (int i = 1; i < jLabel.length; i++) {
            roundPanel[i].setBackground(roundPanelBG);
            roundPanel[i].setColor(roundPanelColor);
            roundPanel[i].setColorOver(roundPanelColorOver);
            imageAvatar[i].setForeground(imageAvatarFG);
            jLabel[i].setBackground(labelBG);
            jLabel[i].setForeground(labelFG);
        }
        
        rpContent[0].setBackground(rpContentBG);
        rpContent[3].setBackground(rpContentBG);
        rpContent[2].setBackground(rpFolderBG);
        labelName[0].setForeground(rpFolderFG);
        labelName[1].setForeground(rpFolderFG);
       labelName[2].setForeground(rpFolderFG);
        if (currentBtn != null) {
            currentBtn.setBackground(currentBtnBG);
            currentLb.setForeground(currentLbFG);
        }
    }

    private void selectRoundPanel(int index) {
        Active(roundPanel[index], jLabel[index]);
        JPanel panel = switch (index) {
            case 1 ->
                new SaleGUI(account.getStaffID());
            case 2 ->
                new BillGUI();
            case 3 ->
                new ProductGUI();
            case 4 ->
              new CategoryGUI();
           
            default ->
                null;
        };
        OpenChildForm(panel);
        currentPanel = index;
    }

    private void Disable() {
        if (currentBtn != null) {
            currentBtn.setPressover(false);
            currentBtn.setBackground(new Color(240, 240, 240));
            currentLb.setForeground(new Color(0,0,0));
        }
    }

    private void Active(ProductPanel btn, JLabel lb) {
        Disable();
        currentBtn = btn;
        currentLb = lb;
        currentBtn.setPressover(true);
        currentBtn.setBackground(new Color(31, 57, 51));
        currentLb.setForeground(new Color(0xffffff));
       
        
    }

    private void OpenChildForm(JPanel panel) {
        function.removeAll();
        function.add(panel);
        function.repaint();
        function.revalidate();
        east.add(function);
    }

    private void minimize() {
        setState(HomeGUI.ICONIFIED);
    }

    private void exit() {
        int message = JOptionPane.showOptionDialog(null,
                "Bạn có chắc chắn muốn thoát?",
                "Thoát",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Thoát", "Huỷ"},
                "Thoát");
        if (message == JOptionPane.YES_OPTION) {

            exit.setColor(new Color(0xFD1111));
            this.dispose();
            CafeManagement.loginGUI.setVisible(true);
        }
    }

    public void setTime() {
        lbTime.setText(Day.now());
    }

    public void pressDelay(int index, int height, int add) {
        Timer timer = new Timer(1, new ActionListener() {
            private int counter = 0;
            private int angle = 0;
            private int coordinates = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                rpContent[index].setPreferredSize(new Dimension(280, height + counter * add));
                rpContent[index].revalidate();
                rpContent[index].repaint();
                if (add > 0) {
                    coordinates = 36; 
                }else {
                    coordinates = 72;
                }
                angle = angle + coordinates;
                imageIcon[numberjpane].setIcon(rotateIcon(Resource.loadImageIcon(string1), angle));
                if (counter == 5) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private ImageIcon rotateIcon(ImageIcon icon, int angle) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage img = new BufferedImage(w, h, type);
        Graphics2D g2 = img.createGraphics();
        g2.rotate(Math.toRadians(angle), (double) w / 2, (double) h / 2);
        icon.paintIcon(null, g2, 0, 0);
        g2.dispose();

        return new ImageIcon(img);
    }
}
