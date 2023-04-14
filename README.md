yurii.bezborodov@ucalgary.ca; tamerlan.ormanbayev@ucalgary.ca

Pecunia v1.0 is a financial expenses tracking application.

In order to compile the program from command line, please do:
**javac --module-path "javafx lib" --add-modules javafx.controls,javafx.fxml --source-path "src" src\*.java**

The application can store all of your expenses in accordance to the amount, category and recurrence that you set. 

Firstly, you are being greeted with a welcome screen. You can either choose an existing file, or create a new one. All of them are stored in a .csv format. Every wallet has their own name, and their own 5-digit ID. When a new wallet is created, an ID is created and assigned automatically.

After you enter the main menu, you can add a transaction. You only need to specify the amount, the category and whether or not the transaction is recurring. **Remember, you cannot include any letters in the amount text field.** After the transaction has been added, you can see it in the list view on the right.

The other option in main menu is to edit a transaction. Simply click on any transaction in the list view on the right, and you will be able to automatically change any of the 3 factors. You can also delete a transaction. As soon as you press 'Edit', you will return to menu immediately, and the transaction will change.

You can also view the statistics of your wallet. All of the transactions considered, you can see your most expensive transactions, most expensive categories, all recurring transactions, transactions with the same amount and transactions over the amount that you can choose yourself. 
**Remember, you cannot include any letters in the pop-up text field.** All of the statistics are shown in the same list view, sorted in accordance to your choice. 

There is also wallet settings. You can see the name of your wallet, or change it. It also shows your ID, shows you even more statistics of how many transactions you've made and how much are they in total. You can also choose the currency of your wallet, or you can load another one. **The ABOUT button on the bottom right shows what the program is and who made it.**

After you're done working with your Pecunia wallet, you are able to save it with the bottom right button in main menu. ID, name, and all transactions are going to be saved in the same .csv file for you to work with later on. **All of the transactions are saved in a format: 'NAME$12345.csv', so if you want to load your third-party file, please make sure to name it the proper way.**

IF YOU ARE RUNNING THE PROGRAM ON MACOS:
Sometimes, the OS may create a hidden file called .DS_Store in the data folder. You will see an error when running the program "For input string .DS_Store". To fix it, simply delete the data folder and create a new one with the same name in the same directory.
