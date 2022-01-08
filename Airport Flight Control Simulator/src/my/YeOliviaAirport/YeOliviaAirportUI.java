/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.YeOliviaAirport;

import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.Timer;

/**
 *
 * @author oliviaye
 */
public class YeOliviaAirportUI extends javax.swing.JFrame {

    Queue<Integer> arrivals = new LinkedList<>(); // Arrival plane queue
    Queue<Integer> takeoffs = new LinkedList<>(); // Takeoff plane queue
    Queue<Integer> backup = new LinkedList<>(); // Backup queue to use when deleting planes from the queue

    /**
     * Creates new form YeOliviaAirportUI
     */
    public YeOliviaAirportUI() {
        initComponents();
        // Loads in the arrival planes when the program is started
        File arrivalplanes = new File("arrivals.txt");
        boolean run = true;
        // Try-catch structure to catch potential exceptions
        try {
            Scanner input = new Scanner(arrivalplanes);
            while (run == true) {
                int add = Integer.parseInt(input.nextLine().trim());
                // Updates the arrival queue with the next plane
                updateQueue(add, "aa");
            }
            input.close();
        } catch (FileNotFoundException | NoSuchElementException ex) {
            run = false;
        }
        // Loads in the takeoff planes when the program is started
        File takeoffplanes = new File("takeoffs.txt");
        run = true;
        // Try-catch structure to catch potential exceptions
        try {
            Scanner input = new Scanner(takeoffplanes);
            while (run == true) {
                int add = Integer.parseInt(input.nextLine().trim());
                // Updates the takeoff queue with the next plane
                updateQueue(add, "ta");
            }
            input.close();
        } catch (FileNotFoundException | NoSuchElementException ex) {
            run = false;
        }
    }

    // Timer duration
    int tDuration = 600;
    // Timer
    Timer t = new Timer(tDuration, new TimerListener());
    // Number of timer cycles, number of rounds
    int cycle = 5, rounds = 12;
    // To indicate which sections (arrivals or takeoffs)
    char section = 'a', progress = 'a';
    // Indicate if initial conditions have been set
    boolean initial = true;

    /**
     * Timer Listener class to run the plane queues
     */
    public class TimerListener implements ActionListener {

        @Override
        // Method to execute every second
        public void actionPerformed(ActionEvent e) {
            // If conditional to cycle through the countdown + call on updateQueue method to remove a plane from the queue
            // Will execute if:
            //      There are planes to execute in the queue and the indicated section to execute is takeoff
            //      The rounds are more than 12 (starting conditions or a plane has just taken off)
            //      The takeoff queue is empty and the arrivals conditions have been initialized
            if (arrivals.isEmpty() == false && section == 'a' || rounds > 10 || (takeoffs.isEmpty() && initial == true)) {
                // If the rounds are 0 (a plane has just taken off), resets the arrival conditions
                if (rounds == 0) {
                    rounds = 12;
                    cycle = 5;
                }
                // If the arrivals queue is not empty, counts down and sets the current countdown as an arrival countdown
                if (arrivals.isEmpty() == false) {
                    initial = false;
                    cycle--;
                    ControlDisplay.setText("Plane " + arrivals.peek() + " is next to land.  " + cycle);
                    progress = 'a';
                }
                // If the cycles have reached 0 and the current countdown is for arrival, then indicates the plan has landed
                if (cycle == 0 && progress == 'a') {
                    ControlDisplay.setText("Plane " + arrivals.peek() + " has landed.");
                    // Calls the udpate queue method to update the queue
                    updateQueue(arrivals.peek(), "ar");
                    cycle = 5;
                    rounds--;
                }
            }

            // If conditional to cycle through the countdown + call on updateQueue method to remove a plane from the queue
            // Will execute if:
            //      There are planes to takeoff in the queue and the indicated section to execute is takeoff
            //      The rounds are less than 9 or greater than 0 (two planes have already arrived)
            //      The arrivals queue is empty and the takeoff conditions have been intialized
            if (takeoffs.isEmpty() == false && section == 't' || rounds > 0 && rounds < 9 || (arrivals.isEmpty() == true && initial == true)) {
                // If the rounds are 10 (plane has just landed) or the rounds or 0, then resets conditions
                if (rounds == 10 || rounds == 0) {
                    rounds = 1;
                    cycle = 3;
                }
                // If the takeoffs queue is not empty, counts down and sets the current coundown as a takeoff countdown
                if (takeoffs.isEmpty() == false) {
                    initial = false;
                    cycle--;
                    ControlDisplay.setText("Plane " + takeoffs.peek() + " is next to take off.  " + cycle);
                    progress = 't';
                }
                // If the cycles have reached 0 and the current countdown is for arrival, then indicates the plan has taken off
                if (cycle == 0 && progress == 't') {
                    ControlDisplay.setText("Plane " + takeoffs.peek() + " has taken off.");
                    // Calls the udpate queue method to update the queue
                    updateQueue(takeoffs.peek(), "tr");
                    cycle = 3;
                    rounds--;
                }
            }

            // If conditional to:
            //      Check if there are no planes to arrive and timer cycle has reached zero
            //      Else, check if there are no planes to lant and the time cycle has reached zero
            if (arrivals.isEmpty() == true && cycle == 0) {
                // Sets rounds to 10, which will set off the takeoff conditions
                rounds = 10;
            } else if (takeoffs.isEmpty() == true && cycle == 0) {
                // Sets rounds to 0, which will set off the arrival conditions
                rounds = 0;
            }

            // If conditional to set take off conditions (cycle of 2 seconds and 1 round) and indicates that conditions were set
            // Will execute if:
            //      The arrival queue is empty and the last plane was an arrival
            //      Two planes have arrived and the takeoff queue is not empty
            if (arrivals.isEmpty() == true && section != 't' || rounds == 10 && takeoffs.isEmpty() == false) {
                section = 't';
                rounds = 1;
                cycle = 3;
                initial = true;
            }

            // If conditional to set arrival conditions (cycle of 4 seconds and 2 rounds) and indicates that conditions were set
            // Will execute if:
            //      The takeoff cue is empty and the last plane was a takeoff
            //      A plane has just taken off and the arrival queue is not empty
            if (takeoffs.isEmpty() == true && section != 'a' || rounds == 0 && arrivals.isEmpty() == false) {
                section = 'a';
                rounds = 12;
                cycle = 5;
                initial = true;
            }

            // If conditional to stop both arrival & takeoff methods from executing
            if (arrivals.isEmpty() == true && takeoffs.isEmpty() == true) {
                initial = false;
            }

        }
    }

    /**
     * Method to check if the input is an integer
     *
     * @param input             input from the text field
     * @return                  if the input is an integer (true) or not an integer (false)
     */
    public boolean check(String input) {
        boolean number = false; // Whether or not the input is a number
        // Try catch structure to check if the input is an integer
        try {
            int check = Integer.parseInt(input);
            number = true;
        } catch (NumberFormatException e) {
            number = false;
        }
        return number;
    }

    /**
     * Method to update the queue
     * 
     * @param flightNum         number of the plane
     * @param type              type of update to execute 
     *                              ("aa" - add arrival)
     *                              ("ta" - add takeoff)
     *                              ("ar" - remove arrival)
     *                              ("tr" - remove takeoff)
     */
    public void updateQueue(int flightNum, String type) {
        // If conditional to execute based on the required type update
        if (type.compareTo("aa") == 0) {
            //Adds a flight to the arrivals queue
            arrivals.add(flightNum);
            // Grabs the current queue from the GUI and adds the new plane to the UI queue
            String textbox = ArrivalsDisplay.getText();
            textbox = textbox + flightNum + "\n";
            ArrivalsDisplay.setText(textbox);
        } else if (type.compareTo("ta") == 0) {
            // Adds a flight to the takeoffs queue
            takeoffs.add(flightNum);
            // Grabs the current queue from the GUI and adds the new plane to the UI queue
            String textbox = TakeoffsDisplay.getText();
            textbox = textbox + flightNum + "\n";
            TakeoffsDisplay.setText(textbox);
        }
        else if (type.compareTo("ar") == 0) {
            // Removes the first plane in the arrivals queue
            arrivals.remove();
            // Creates a string to store the queued planes
            String newQueue = "";
            // While loop to cycle through the updated queue, storing the removed planes in the backup queue, and creating a new string of planes
            while (arrivals.isEmpty() == false) {
                int flight = arrivals.remove();
                backup.add(flight);
                newQueue = newQueue + flight + "\n";
            }
            ArrivalsDisplay.setText(newQueue);
            // While loop to move the planes in the backup queue back to the main queue
            while (backup.isEmpty() == false) {
                arrivals.add(backup.remove());
            }
        } else if (type.compareTo("tr") == 0) {
            // Removes the first plane in the takeoffs queue
            takeoffs.remove();
            // Creates a string to store the queued planes
            String newQueue = "";
            // While loop to cycle through the updated queue, storing the removed planes in the backup queue, and creating a new string of planes
            while (takeoffs.isEmpty() == false) {
                int flight = takeoffs.remove();
                backup.add(flight);
                newQueue = newQueue + flight + "\n";
            }
            TakeoffsDisplay.setText(newQueue);
            // While loop to move the planes in the backup queue back to the main queue
            while (backup.isEmpty() == false) {
                takeoffs.add(backup.remove());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ControlDisplay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        StartButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ArrivalsEnter = new javax.swing.JTextField();
        TakeoffsEnter = new javax.swing.JTextField();
        ExitButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TakeoffsDisplay = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        ArrivalsDisplay = new javax.swing.JTextArea();
        ErrorLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setText("Airport Simulator (Olivia Ye)");

        ControlDisplay.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        ControlDisplay.setText("Press 'Start' to begin simulation.");

        jLabel2.setText("Arrivals");

        jLabel3.setText("Takeoffs");

        StartButton.setText("Start");
        StartButton.setFocusable(false);
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Arriving Flight:");

        jLabel6.setText("Takeoff Flight:");

        ArrivalsEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArrivalsEnterActionPerformed(evt);
            }
        });

        TakeoffsEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TakeoffsEnterActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.setFocusable(false);
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        TakeoffsDisplay.setEditable(false);
        TakeoffsDisplay.setColumns(20);
        TakeoffsDisplay.setRows(5);
        TakeoffsDisplay.setFocusable(false);
        jScrollPane3.setViewportView(TakeoffsDisplay);

        ArrivalsDisplay.setEditable(false);
        ArrivalsDisplay.setColumns(20);
        ArrivalsDisplay.setRows(5);
        ArrivalsDisplay.setFocusable(false);
        jScrollPane4.setViewportView(ArrivalsDisplay);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ControlDisplay)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ExitButton)
                            .addComponent(ErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(StartButton)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(ArrivalsEnter))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(TakeoffsEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addComponent(ControlDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(StartButton)
                    .addComponent(jScrollPane4))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ArrivalsEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TakeoffsEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ExitButton)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // Start button
        // Calls on the timer to start
        t.start();
    }//GEN-LAST:event_StartButtonActionPerformed

    private void ArrivalsEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArrivalsEnterActionPerformed
        // Arrivals Input Text Field
        // Creates a new scanner object to get the text from the text field
        Scanner input = new Scanner(ArrivalsEnter.getText());
        String flight = "";
        boolean valid;
        // Try-catch statement to try retrieving text from the text field
        try {
            flight = input.nextLine();
            valid = true;
        } catch (NoSuchElementException ex) {
            // Checks whether or not the input is empty
            valid = false;
        }
        // If conditonal to execute if the input was not empty
        if (valid == true) {
            // Calls on the valid method to check if the input is an integer
            valid = check(flight);
        }
        // If conditional to execute if the input is not empty and the input is an integer
        if (valid == true) {
            // Calls on the update queue method to add a plane to the arrivals queue
            updateQueue(Integer.parseInt(flight), "aa");
            ArrivalsEnter.setText("");
            ErrorLabel.setText("");
        } else {
            // Asks the person to re-enter their input
            ErrorLabel.setText("Input invalid. Please re-enter the flight number.");
            ArrivalsEnter.setText("");
        }
    }//GEN-LAST:event_ArrivalsEnterActionPerformed

    private void TakeoffsEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TakeoffsEnterActionPerformed
        // Takeoffs Input Text Field
        // Creates a new scanner object to get the text from the text field
        Scanner input = new Scanner(TakeoffsEnter.getText());
        String flight = "";
        boolean valid;
        // Try-catch statement to try retrieving text from the text field
        try {
            flight = input.nextLine();
            valid = true;
        } catch (NoSuchElementException ex) {
            // Checks whether or not the input is empty
            valid = false;
        }
        // If conditonal to execute if the input was not empty
        if (valid == true) {
            // Calls on the valid method to check if the input is an integer
            valid = check(flight);
        }
        // If conditional to execute if the input is not empty and the input is an integer
        if (valid == true) {
            // Calls on the update queue method to add a plane to the arrivals queue
            updateQueue(Integer.parseInt(flight), "ta");
            TakeoffsEnter.setText("");
            ErrorLabel.setText("");
        } else {
            // Asks the person to re-enter their input
            ErrorLabel.setText("Input invalid. Please re-enter the flight number.");
            TakeoffsEnter.setText("");
        }
    }//GEN-LAST:event_TakeoffsEnterActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // Exit Button
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(YeOliviaAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YeOliviaAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YeOliviaAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YeOliviaAirportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new YeOliviaAirportUI().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ArrivalsDisplay;
    private javax.swing.JTextField ArrivalsEnter;
    private javax.swing.JLabel ControlDisplay;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton StartButton;
    private javax.swing.JTextArea TakeoffsDisplay;
    private javax.swing.JTextField TakeoffsEnter;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
