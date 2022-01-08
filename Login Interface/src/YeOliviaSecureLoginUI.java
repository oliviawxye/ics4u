/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File; // File input/output library
import java.io.FileNotFoundException; //Handle file not found
import java.io.IOException; // Handle file input/output exceptions
import java.io.PrintWriter; // Methods to write to the text file
import java.util.Scanner; // We'll be using scanner to read from the text file
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

/**
 *
 * @author oliviaye
 */
public class YeOliviaSecureLoginUI extends javax.swing.JFrame {

    String[][] accounts = new String[1000000][5]; // String array with all the accounts
    int numAccounts = 0; // Number of accounts
    boolean registered = false; // If at least one account has been registered

    /**
     * Method to check if the input is empty
     *
     * @param input input from the text field
     * @return if the input is empty (false) or not (true)
     */
    public boolean check(String input) {
        boolean full = false; // Whether or not the input is full
        // Try-catch statement to check if the string is empty
        try {
            input.substring(0, 1);
            full = true;
        } catch (StringIndexOutOfBoundsException e) {
            full = false;
        }
        return full;
    }

    /**
     * Method to write the accounts to the loginInformation.txt file
     *
     * @param firstName first name of the user [column 0] of the double array
     * @param lastName last name of the user [column 1 of the double array
     * @param userName username of the user [column 2] of the double array
     * @param email email of the user [column 3] of the double array
     * @param encryptedPassword encrypted password of the user [column 4] of the
     * double array
     * @return whether or not writing to the file was successful
     */
    public boolean writeToFile(String firstName, String lastName, String userName, String email, String encryptedPassword) {
        File file = new File("loginInformation.txt"); // Login information file
        boolean success = false; // Whether or not registration was successful
        // Try-catch statement to ALL the information to the file
        try {
            PrintWriter output = new PrintWriter(file);
            accounts[numAccounts][0] = firstName;
            accounts[numAccounts][1] = lastName;
            accounts[numAccounts][2] = userName;
            accounts[numAccounts][3] = email;
            accounts[numAccounts][4] = encryptedPassword;
            // For loop to print the user information to the txt file 
            for (int count = 0; count <= numAccounts; count++) {
                for (int count1 = 0; count1 < 5; count1++) {
                    output.print(accounts[count][count1] + ";");
                }
                output.println("");
            }
            success = true;
            output.close(); // Closes the file
        } catch (IOException ex) {
            success = false;
        }
        // Adds 1 to the number of accounts
        numAccounts++;
        return success;
    }

    /**
     * Method to check if the input in the email text field contains '@' and '.'
     *
     * @param email inputted email
     * @return if the email is an email (true) or not an email (false)
     */
    public boolean checkEmail(String email) {
        boolean isEmail = false; // If the input is an actual email or not
        boolean haveAt = false, havePeriod = false; // Whether the input has a '@' and '.'
        char at = '@'; // @ characterto check
        char period = '.'; // . character check
        int emailLength = email.length();
        // For loop to check if the email contains both a '@' and '.'
        for (int count = 0; count < emailLength; count++) {
            if (email.charAt(count) == at) {
                haveAt = true;
            } else if (email.charAt(count) == period) {
                havePeriod = true;
            }
        }
        // If conditional to set that the email is an email
        if (haveAt == true && havePeriod == true) {
            isEmail = true;
        } else {
            isEmail = false;
        }
        return isEmail;
    }

    /**
     * Method to check whether or not the password is a bad password
     *
     * @param password inputted password to check
     * @return if the password is bad (false) or good (true)
     */
    public boolean badPassword(String password) {
        boolean isItGood = false; // Whether the password is good or not
        password = password.toLowerCase(); // Sets the password to lowercase to check all case variations of the bad passwords
        int passwordLength = password.length();
        // If conditional to check if the password is at least 4 characters
        if (passwordLength > 3) {
            isItGood = true;
            File file = new File("badPasswords.txt"); // File of bad passwords
            // Try-catch statement to print the bad passwords to the text file
            try {
                PrintWriter output = new PrintWriter(file);
                output.print("password;123456;abcdef;microsoft;ics4u;qwerty");
                output.close();
            } catch (IOException ex) {
            }
            // Try-catch statement to read the passwords from the file
            try {
                Scanner input = new Scanner(file);
                String badPasswords = input.nextLine(); // Reads the line of bad passwords
                StringTokenizer token = new StringTokenizer(badPasswords, ";", false); // Tokenizes the line of bad passwords
                // Checks the password
                while (token.hasMoreTokens()) {
                    // If conditional to check if the password and bad password match, and if so, sets the password as bad
                    if (0 == password.compareTo(token.nextToken())) {
                        isItGood = false;
                    }
                }
            } catch (FileNotFoundException ex) {
            }
        }
        return isItGood;
    }

    /**
     * Method to encrypt the password using MD5
     *
     * @param password password to be encrypted
     * @return encrypted password using MD5
     * @throws NoSuchAlgorithmException
     */
    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        // Create MD5 encryption object
        MessageDigest md = MessageDigest.getInstance("MD5");
        // Takes the password as bytes and updates the digest
        md.update(password.getBytes());
        // Takes the digest and stores it as a byte array
        byte byteData[] = md.digest();
        String encryptedPassword = "";
        // Build a new string of the digested password
        for (int i = 0; i < byteData.length; ++i) {
            encryptedPassword += (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return encryptedPassword;
    }

    /**
     * Creates new form YeOliviaSecureLoginUI
     */
    public YeOliviaSecureLoginUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        uNameLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pWordLogin = new javax.swing.JTextField();
        login = new javax.swing.JButton();
        loginDisplay = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        uNameRegister = new javax.swing.JTextField();
        emailRegister = new javax.swing.JTextField();
        pWordRegister = new javax.swing.JTextField();
        register = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lNameRegister = new javax.swing.JTextField();
        fNameRegister = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        resetPassword = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        forgetUName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        forgetPassword = new javax.swing.JTextField();
        forgetDisplay = new javax.swing.JLabel();
        registerDisplay = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        forgetEmail = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Olivia Ye's Enterprise");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel5.setText("Register a New User Account");

        jLabel6.setText("Enter Your Username:");

        jLabel7.setText("Enter Your Email Address:");

        jLabel8.setText("Enter Your Password:");

        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        jLabel11.setText("Enter Your Last Name:");

        jLabel12.setText("Enter Your First Name:");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        resetPassword.setText("Reset Password");
        resetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPasswordActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel13.setText("Forgotten Your Password?");

        jLabel16.setText("Enter Your Account's Username:");

        jLabel17.setText("Enter Your New Password:");

        jLabel20.setText("Enter Your Account's Email:");

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel21.setText("Note: All fields except email are case-sensitive.");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel9.setText("Login");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(uNameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(pWordLogin))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(loginDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(login))
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(registerDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(register))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(48, 48, 48)
                                        .addComponent(pWordRegister))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(emailRegister))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(lNameRegister, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                                .addComponent(fNameRegister, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(uNameRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(forgetDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(147, 147, 147))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(resetPassword)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel17))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(forgetEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                        .addComponent(forgetPassword)
                                        .addComponent(forgetUName)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uNameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(pWordLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(login)
                            .addComponent(loginDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(fNameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lNameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(uNameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(emailRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(pWordRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(register)
                            .addComponent(registerDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(forgetUName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(forgetEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(forgetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resetPassword)
                    .addComponent(forgetDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordActionPerformed
        // Reset Password Button
        // If conditional to execute if an account has been registered
        if (registered == true) {
            // Resets the display of the other two sections
            loginDisplay.setText("");
            registerDisplay.setText("");
            boolean success = false; // Whether or not the password reset was a success
            boolean vUName, vEmail, vPassword; // If the username, email, and password are valid inputs
            int accountNum = 0; // Row of the account in the document
            // Gets the inputs from their respective text fields
            String username = forgetUName.getText();
            String email = forgetEmail.getText();
            String password = forgetPassword.getText();
            // Following lines check if the input fields fields are empty
            vUName = check(username);
            vEmail = check(email);
            vPassword = check(password);
            // If conditional to execute if the fields are not empty
            if (success == false && vUName == true && vEmail == true && vPassword == true) {
                // For loop to compare the username of each account to the inputted username
                for (int count = 0; count < numAccounts; count++) {
                    // If conditional to execute if a username matches
                    if (username.compareTo(accounts[count][2]) == 0) {
                        // If conditional to set the password reset to success (all the fields matched)
                        if ((email.toLowerCase()).compareTo(accounts[count][3]) == 0 && vEmail == true) {
                            success = true;
                        }
                    }
                    // Sets the account number to the current row of the array
                    accountNum = count;
                }
                // If conditional to ask the audience to try again if their username and email do not match
                if (success == false) {
                    forgetDisplay.setText("Username or email do not match our records. Please try again.");
                }
            } else { // Else conditional to as prompt for reinput if one or more of the fields are empty
                forgetDisplay.setText("One or more inputs is invalid. Please try again.");
            }
            // If conditional to execute if the username and password match
            if (success == true) {
                int lengthPassword = password.length();
                // If conditional to execute if the password is not empty and is at least 4 characters
                if (lengthPassword > 3) {
                    // Check if the password is considered a good password
                    vPassword = badPassword(password);
                    // If conditional to execute if the password is considered good
                    if (vPassword == true) {
                        // Sets the password to the new password
                        try {
                            accounts[accountNum][4] = encryptPassword(password);
                        } catch (NoSuchAlgorithmException ex) {
                        }
                        // Resets the fields and displays the success message
                        forgetUName.setText("");
                        forgetEmail.setText("");
                        forgetPassword.setText("");
                        forgetDisplay.setText("Success!");
                    } else { // Else conditional to execute if it is a bad password
                        // Resets the password field and displays the prompt to try again
                        forgetDisplay.setText("Bad password. Please try again.");
                        forgetPassword.setText("");
                    }
                } else { // Else conditional to execute if the input is empty or the password is less than 4 characters
                    forgetDisplay.setText("Bad password. Please try again.");
                    forgetPassword.setText("");
                }
            }
        } else { // Disables the login and reset password and prompts the player to register
            login.setEnabled(false);
            resetPassword.setEnabled(false);
            forgetDisplay.setText("Please register an account first.");
            forgetUName.setText("");
            forgetEmail.setText("");
            forgetPassword.setText("");
        }
    }//GEN-LAST:event_resetPasswordActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        // Register Button
        // Resets the display of the other two sections
        forgetDisplay.setText("");
        loginDisplay.setText("");
        boolean success; // Whether or not the registration was a success
        boolean vFName, vLName, vUName, vEmail, vPassword; // If the first name, last name, username, email, and password are valid inputs
        // Gets the inputs from their respective text fields
        String firstName = fNameRegister.getText();
        String lastName = lNameRegister.getText();
        String uName = uNameRegister.getText();
        String email = emailRegister.getText();
        email = email.toLowerCase();
        String password = pWordRegister.getText();
        // Checks if the fields are empty or not
        vFName = check(firstName);
        vLName = check(lastName);
        vUName = check(uName);
        vEmail = check(email);
        vPassword = check(password);
        // If conditional to execute if none of the fields are not empty
        if (vFName == true && vLName == true && vUName == true && vEmail == true && vPassword == true) {
            // Checks if the email is a valid email (contains '@' and '.')
            vEmail = checkEmail(email);
            // Checks if the password is a good password
            vPassword = badPassword(password);
            // If conditional to clear the field and prompt for reentry if the email does not contain the required characters
            if (vEmail != true) {
                emailRegister.setText("");
                registerDisplay.setText("Email is invalid. Please try again.");
            }
            // If conditional clear the field and prompt for reentry if the password is bad
            if (vPassword != true) {
                pWordRegister.setText("");
                registerDisplay.setText("Bad Password. Please enter another.");
            }
            // If conditional to execute if the password and email are valid and good, respectively
            if (vEmail == true && vPassword == true) {
                // Try-catch statement to encrypt the password using MD5
                try {
                    password = encryptPassword(password);
                } catch (NoSuchAlgorithmException ex) {
                }
                // Writes the inputs to the file and returns if it was successful
                success = writeToFile(firstName, lastName, uName, email, password);
                // If conditional to display the success message and clear the fields if writing to the file was successful
                if (success == true) {
                    registerDisplay.setText("Registration Successful!");
                    fNameRegister.setText("");
                    lNameRegister.setText("");
                    uNameRegister.setText("");
                    emailRegister.setText("");
                    pWordRegister.setText("");
                    registered = true; // Sets register to true
                    // Opens up the option to now login and reset the password
                    login.setEnabled(true);
                    resetPassword.setEnabled(true);
                } else { // Asks them to try again if it was successful when writing to the file
                    registerDisplay.setText("Please try again.");
                }
            }
        } else { // Displays to try again if one or more of the fields were empty
            registerDisplay.setText("One or more inputs are invalid. Please try again.");
        }
    }//GEN-LAST:event_registerActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // Login Button
        // If conditional to execute if an account has been registered
        if (registered == true) {
            // Resets the display of the other two sections
            registerDisplay.setText("");
            forgetDisplay.setText("");
            boolean login = false; // Sets the login to false
            boolean vUName, vPassword; // If the username and password are valid inputs
            // Gets the inputs from the respective fields
            String uName = uNameLogin.getText();
            String pWord = pWordLogin.getText();
            // Checks if the inputs are not empty
            vUName = check(uName);
            vPassword = check(pWord);
            // If conditional to execute if the inputs are not empty
            if (vUName == true && vPassword == true) {
                // Try-catch to encrpt the password, compare it to the password associated with the username, determine if login was sucessful 
                try {
                    String encryptPassword = encryptPassword(pWord);
                    // For loop to access each line of the login information
                    for (int count = 0; count < numAccounts && login == false; count++) {
                        // If conditional to execute if the username and an account match
                        if (uName.compareTo(accounts[count][2]) == 0) {
                            // If conditional to set the login to true if the password matches the account associated with the username 
                            if (encryptPassword.compareTo(accounts[count][4]) == 0) {
                                login = true;
                            }
                        }
                    }
                    // If conditional to reset the fields and set the display text if login was successful
                    if (login == true) {
                        loginDisplay.setText("Login successful!");
                        uNameLogin.setText("");
                        pWordLogin.setText("");
                    } else { // Displays prompt for user to try again
                        loginDisplay.setText("Username or Password Incorrect. Please Try Again.");
                    }
                } catch (NoSuchAlgorithmException ex) {
                }
            } else { // If one or more fields are empty, displays prompt for user to try again
                loginDisplay.setText("One or more inputs are invalid. Please try again.");
            }
        } else { // Disables the login and reset password and prompts the player to register
            login.setEnabled(false);
            resetPassword.setEnabled(false);
            loginDisplay.setText("Please register an account first.");
            uNameLogin.setText("");
            pWordLogin.setText("");
        }

    }//GEN-LAST:event_loginActionPerformed

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
            java.util.logging.Logger.getLogger(YeOliviaSecureLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YeOliviaSecureLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YeOliviaSecureLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YeOliviaSecureLoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new YeOliviaSecureLoginUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailRegister;
    private javax.swing.JTextField fNameRegister;
    private javax.swing.JLabel forgetDisplay;
    private javax.swing.JTextField forgetEmail;
    private javax.swing.JTextField forgetPassword;
    private javax.swing.JTextField forgetUName;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField lNameRegister;
    private javax.swing.JButton login;
    private javax.swing.JLabel loginDisplay;
    private javax.swing.JTextField pWordLogin;
    private javax.swing.JTextField pWordRegister;
    private javax.swing.JButton register;
    private javax.swing.JLabel registerDisplay;
    private javax.swing.JButton resetPassword;
    private javax.swing.JTextField uNameLogin;
    private javax.swing.JTextField uNameRegister;
    // End of variables declaration//GEN-END:variables
}
