# Online Banking System

Hello and Welcome to BCS the only bank you will ever need as a Computer Scientist.
This readme will tell you exactly how to use this application from setting up an account to making your first bank transfer.

Starting off to start the program you will need to run the BankSystem.java file the minimum screen resolution the program was designed for is 1920 x 1080. If you have correctly downloaded all the files the program will start onto the login page.

# SIGN UP
The next step is to make an account. This can be done by clicking the black Register button at the bottom of all the text boxes. When pressed a new page will appear called Sign Up.
On this new page you will be asked to fill out the following boxes:

First name: (Input your First name)
Last name: (Input your Last name)
Card Number: (This is a 16 digit number for testing purposes you may use one of these (4417123456789113, 3479965806744595, 4124172525029476)
Expiry Date: (Any date past 2023 will work)
CVV: (Any 3 digit number will work)
Date of Birth: (Enter your DOB)
Gender: (Select your Gender)
Email: (This accepts any valid email address eg. anything@gmail.com)
Password: (Accepts any combination of letters and numbers (please remember your chosen password))
Phone Number: (Accepts any 10 digit number)
Address: (Accepts any address eg 167 Church Street)

After all information is entered correctly you can click the black sign up after the address box. If the information passes all the validation checks your account will be created and will be redirected to the home page.

 ----------------------------------------------------------------------------------------------------------

# LOGIN
When you have created an account you now have the ability to login to the system. To do this open the program to the login screen. Next click the email text box and enter the email address you used to sign up. After clicking the password text box enter the password associated with the email you inputted. If you would like to see what password you are inputting select the “show password” checkbox under the password text box. Finally click the black sign in Button in the bottom left of the password. If the inputted email and password correctly corresponds to an email and password in the stored customers you will be granted access to your account.

 ----------------------------------------------------------------------------------------------------------

# HOME PAGE
Upon successful login you will gain access to your home page. Once logged in you will be given a random value of money between $0 - $10,000 in both chequing and savings accounts. (If in the extremely rare chance you get $0 in both accounts please make a new account)

On the Home page you will see your name at the top left side of the screen. Under your name you will see 6 options: Home, Transfer, Contact Us, Settings, Find Us and Logout. Under those options you will see A large box labeled Chequing with the amount of money you have on the right and below Savings with the amount of money in that account.

# TRANSFERRING MONEY 
To transfer money on the home page click the Transfer button. A pop up box will appear with 4 options: E-transfer, Bank Transfer, Transfer Funds and Cancel.

E-Transfer: If you wish to E-transfer another user select this option. You will then be redirected to a new E-transfer page. On this page you can enter the email address of the person you would like to transfer to. Below enter the amount you wish to transfer. Lastly select the account you would like to transfer the money from a drop down with the options Chequing and Savings will appear.
After all information is inputted into the correct text boxes click the black Complete Transaction button at the bottom of the screen. If all the information is inputted correctly then the E-transfer will successfully process the transfer and a success message will appear on the screen.
If you would like to not E-transfer you can exit this screen by clicking the Back to home button at the bottom of the screen.

Bank Transfer: If you wish to bank transfer another user select this option. You will then be redirected to a new bank transfer page. On this page you can enter the account number of the person you would like to transfer to. Below enter the amount you wish to transfer. Lastly select the account you would like to transfer the money from a drop down with the options Chequing and Savings will appear.
After all information is inputted into the correct text boxes click the black Complete Transaction button at the bottom of the screen. If all the information is inputted correctly then the bank transfer will successfully process the transfer and a success message will appear on the screen.
If you would like to not bank transfer you can exit this screen by clicking the Back to home button at the bottom of the screen.

Transfer Funds: If you wish to transfer funds between your accounts (Chequing and Saving) select this option. You will be redirected to a new transfer funds screen. On this page you will be asked to input the amount of money you wish to move. Followed by where the money will be coming from and to where it would be transferred to. After you have imputed all information correctly you can press the black Complete Transaction button below the From, To drop down box. If all the information is inputted correctly then transfer funds will successfully process the transfer and a success message will appear on the screen.
If you would like to not transfer funds you can exit this screen by clicking the Back to home button at the bottom of the screen.
 
 ----------------------------------------------------------------------------------------------------------
 
# LOGOUT
If you would like to exit the program you can select the Logout button on the centre right under your name. A popup will appear asking you if you would like to logout with 2 options: ok and cancel. To logout click ok and you will be redirected to the login screen. To stop the program click the X on the top right of the program window. All accounts will be saved upon program closure if you would like to clear all customers/ reset the system go to the BankSystem.java file and uncomment line 15 run the program then close the program and finally comment the line again.
