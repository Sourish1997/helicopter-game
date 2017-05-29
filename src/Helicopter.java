import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.*;
import javax.sound.sampled.*;

/*WARNING!! This code is some hardcore legacy shit with no care taken with regards to any
sort of coding convention. Continue reading at your own risk. Not suitable for minors and
the faint hearted. If you somehow manage to make sense out of it.......well lets say that
you're a jobless guy with some talent and keep it at that.*/

public class Helicopter extends JPanel implements MouseListener, ActionListener {
    String reso = "" + Helicopter.class.getResource("Helicopter.class");
    String reso2 = (((reso.replace("Helicopter.class", "file.txt")).replace("jar:", "")).replace("/", "\\")).replace("file:\\", "").replace("Helicopter.jar!\\", "").replace("%20", " ").replace("%5b", "[").replace("%5d", "]").replace("%7b", "{").replace("%7d", "}").replace("%3d", "=").replace("%5e", "^").replace("%23", "#").replace("%25", "%").replace("%60", "`").replace("Helicopter.exe!\\", "");
    ImageIcon a = new ImageIcon("data/images/a1.jpg");
    ImageIcon b = new ImageIcon("data/images/a2.jpg");
    ImageIcon c32 = new ImageIcon("data/images/a3.png");
    Clip clip1;
    Clip clip2;
    int x = 0, y = 230, vx = 0, vy = -0, score = 0;
    Rectangle2D obje[] = new Rectangle2D[5];
    Timer sd;
    Timer ad;
    int yuo;
    int y5[] = new int[]{320, 600, 880};
    int y6[] = new int[3];
    int y7[] = new int[3];
    boolean b1 = true, b2 = false, b3 = true, b4 = false, beginning = true, pause = true;
    Font font = new Font("Verdana", Font.BOLD, 15);
    static JMenuBar bar = new JMenuBar();
    JMenu menu = new JMenu("        File        ");
    JMenu menu2 = new JMenu("         Help        ");
    JMenuItem neu = new JMenuItem("    New        ");
    JMenuItem exit = new JMenuItem("    Exit        ");
    JMenuItem help = new JMenuItem("        Help        ");
    JMenuItem about = new JMenuItem("        About        ");
    JMenuItem sound = new JMenuItem("   Disable Sound");

    public Helicopter() {
        try {
            clip1 = AudioSystem.getClip();
            clip2 = AudioSystem.getClip();
        } catch (Exception e) {
        }
        musicStart();
        try {
            try {
                BufferedReader efd = new BufferedReader(new FileReader(reso2));
                yuo = Integer.parseInt(efd.readLine());
            } catch (Exception eff) {
            }
            addMouseListener(this);
            sd = new Timer(30, this);
            neu.addActionListener(this);
            exit.addActionListener(this);
            help.addActionListener(this);
            about.addActionListener(this);
            sound.addActionListener(this);
            bar.add(menu);
            bar.add(menu2);
            menu.add(neu);
            menu.add(new JSeparator());
            menu.add(sound);
            menu.add(new JSeparator());
            menu.add(exit);
            menu2.add(help);
            menu2.add(new JSeparator());
            menu2.add(about);
            ad = new Timer(5, this);
            ad.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public void paintComponent(Graphics g) {
        if (b4) {
            if ((sound.getText().trim()).equals("Disable Sound")) {
                loser();
            }
        }
        try {
            super.paintComponent(g);
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, 1000, 30);
            g.fillRect(0, 537, 1000, 30);
            obje[3] = new Rectangle2D.Double(0, 0, 1000, 30);
            obje[4] = new Rectangle2D.Double(0, 537, 1000, 30);
            g.setFont(font);
            if (b3 == true) {
                f();
                b3 = false;
            }
            for (int yui = 0; yui < 3; yui++) {
                g.fillRect(y5[yui], y6[yui], 70, y7[yui]);
                obje[yui] = new Rectangle2D.Double(y5[yui], y6[yui], 70, y7[yui]);
            }
            x += vx;
            y += vy;
            if (b4 == true) {
                c32.paintIcon(this, g, x, y);
            } else {
                if (b1 == true) {
                    a.paintIcon(this, g, x, y);
                }
                if (b2 == true) {
                    b.paintIcon(this, g, x, y);
                }
            }
            g.fillRect(0, 0, 1000, 30);
            g.fillRect(0, 537, 1000, 30);
            for (int yui = 0; yui < 3; yui++) {
                g.fillRect(y5[yui], y6[yui], 70, y7[yui]);
                obje[yui] = new Rectangle2D.Double(y5[yui], y6[yui], 70, y7[yui]);
            }
            g.setColor(Color.BLACK);
            g.drawString("Score : " + score, 480, 20);
            g.drawString("Best : " + "" + yuo, 480, 558);
            if (pause == false) {
                g.setColor(Color.YELLOW);
                g.fillRect(0, 0, 1000, 30);
                g.setColor(Color.BLACK);
                g.drawString("Game Paused. Press any mouse button to resume ", 305, 20);
            }
            if (b4 == true) {
                g.setColor(Color.YELLOW);
                g.fillRect(0, 0, 1000, 30);
                g.setColor(Color.BLACK);
                g.drawString("Game Over. Final Score : " + score, 415, 20);
                c32.paintIcon(this, g, x, y);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public void f() {
        try {
            for (int hg = 0; hg < 3; hg++) {
                y6[hg] = 50 + ((int) ((Math.random()) * 350));
                y7[hg] = 50 + ((int) ((Math.random()) * 200));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (beginning == false && b4 == false) {
                neu.setEnabled(false);
                help.setEnabled(false);
                about.setEnabled(false);
            } else {
                neu.setEnabled(true);
                help.setEnabled(true);
                about.setEnabled(true);
            }
            if (e.getSource() == sd) {
                if (x == 1000) {
                    x = 0;
                    b3 = true;
                }
                if (x == 392 || x == 672 || x == 952) {
                    score += 20;
                }
                if (b1) {
                    for (int tyu = 0; tyu < 5; tyu++) {
                        if (obje[tyu].contains(x + 38, y + 3) || obje[tyu].contains(x + 75, y + 44) || obje[tyu].contains(x, y + 88) || obje[tyu].contains(x + 4, y + 41)) {
                            sd.stop();
                            b4 = true;
                            try {
                                BufferedWriter dbv = new BufferedWriter(new FileWriter(reso2));
                                if (score > yuo) {
                                    dbv.write("" + score);
                                    dbv.close();
                                } else {
                                    dbv.write("" + yuo);
                                    dbv.close();
                                }
                            } catch (Exception eff) {
                                JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
                                System.exit(0);
                            }
                        }
                    }
                } else if (b2) {
                    for (int tyu = 0; tyu < 5; tyu++) {
                        if (obje[tyu].contains(x, y) || obje[tyu].contains(x + 87, y + 37) || obje[tyu].contains(x + 45, y + 73) || obje[tyu].contains(x + 50, y + 4)) {
                            sd.stop();
                            b4 = true;
                            try {
                                BufferedWriter dbv = new BufferedWriter(new FileWriter(reso2));
                                if (score > yuo) {
                                    dbv.write("" + score);
                                    dbv.close();
                                } else {
                                    dbv.write("" + yuo);
                                    dbv.close();
                                }
                            } catch (Exception eff) {
                                JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
                                System.exit(0);
                            }
                        }
                    }
                }
                repaint();
            } else if (e.getSource() == neu) {
                try {
                    BufferedReader efd = new BufferedReader(new FileReader(reso2));
                    yuo = Integer.parseInt(efd.readLine());
                } catch (Exception eff) {
                    JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
                    System.exit(0);
                }
                beginning = true;
                x = 0;
                y = 230;
                vx = 0;
                vy = -0;
                score = 0;
                b1 = true;
                b2 = false;
                b3 = true;
                b4 = false;
                pause = true;
                f();
                if ((sound.getText().trim()).equals("Disable Sound")) {
                    musicStart();
                }
                repaint();
            } else if (e.getSource() == exit) {
                System.exit(0);
            } else if (e.getSource() == about) {
                JOptionPane.showMessageDialog(null, "Created by Sourish Banerjee. " + "\n" + "        Achieved with java. ");
            } else if (e.getSource() == help) {
                JOptionPane.showMessageDialog(null, "                       Dodge the obstacles and score as high as you can." + "\n" + "Press and hold any mouse button to rise up. Release mouse button to fall." + "\n" + "                              Press the scroll button to pause the game.");
            } else if (e.getSource() == sound) {
                if ((sound.getText().trim()).equals("Disable Sound")) {
                    sound.setText("    Enable Sound    ");
                    clip1.stop();
                    clip2.stop();
                    clip1.close();
                    clip2.close();
                } else {
                    sound.setText("    Disable Sound    ");
                    if (b4) {
                        loser();
                    } else {
                        musicStart();
                    }
                }
            }
        } catch (Exception ef) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        try {
            JFrame w = new JFrame("Helicopter");
            Helicopter obj = new Helicopter();
            w.add(obj);
            obj.setBackground(Color.BLACK);
            w.setBackground(Color.BLACK);
            w.setSize(1000, 622);
            w.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 1000) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 622) / 2);
            w.setResizable(false);
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            w.setVisible(true);
            w.setJMenuBar(bar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        try {
            if (e.isAltDown()) {
                if (b4 != true) {
                    if (pause) {
                        sd.stop();
                        beginning = true;
                        pause = false;
                        repaint();
                    } else {
                        sd.start();
                        beginning = false;
                        pause = true;
                        repaint();
                    }
                }
            } else {
                if (b4 == false) {
                    sd.start();
                    pause = true;
                    beginning = false;
                    vx = 8;
                    vy = -8;
                    b1 = true;
                    b2 = false;
                }
            }
        } catch (Exception ef) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public void mouseReleased(MouseEvent e) {
        try {
            if (b4 == false) {
                vx = 8;
                vy = 8;
                b2 = true;
                b1 = false;
            }
        } catch (Exception ef) {
            JOptionPane.showMessageDialog(null, " Helicopter has encountered a problem and needs to shut down. ");
            System.exit(0);
        }
    }

    public void musicStart() {
        clip2.stop();
        clip2.close();
        try {
            AudioInputStream as1 = AudioSystem.getAudioInputStream(new FileInputStream("soul.wav"));
            AudioFormat af = as1.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            Line line1 = AudioSystem.getLine(info);
            if (!line1.isOpen()) {
                clip1.open(as1);
                clip1.loop(Clip.LOOP_CONTINUOUSLY);
                clip1.start();
            }
        } catch (Exception e) {
        }
    }

    public void loser() {
        clip1.stop();
        clip1.close();
        try {
            AudioInputStream as1 = AudioSystem.getAudioInputStream(new FileInputStream("lose.wav"));
            AudioFormat af = as1.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            Line line1 = AudioSystem.getLine(info);
            if (!line1.isOpen()) {
                clip2.open(as1);
                clip2.start();
            }
        } catch (Exception e) {
        }
    }
}