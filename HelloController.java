package org.example.financecalcrestr;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class HelloController {
    @FXML
    private ArrayList<Purchase> listOfPurchases= new ArrayList<>();
    @FXML
    private ComboBox<String> allTypesComboBox = new ComboBox<>();


    @FXML
    private Text enterTheNewPurchase = new Text("Enter the new purchase");
    @FXML
    private Text warningMessage = new Text();
    @FXML
    private TextField addName = new TextField();
    @FXML
    private TextField addPrice = new TextField();
    @FXML
    private DatePicker datePicker = new DatePicker();
    @FXML
    private Text name = new Text("Name: ");
    @FXML
    private Text price = new Text("Price: ");
    @FXML
    private Text date = new Text("Date: ");



    @FXML
    protected void onButtonAddPurchaseClick(){
        if(addPrice.getText().isEmpty() || !checkDigitsInLine(addPrice.getText()) || addName.getText().isEmpty()) {
        warningMessage.setText("Please, enter the right name and price of purchase");
    }
    else {
        warningMessage.setText("");
        LocalDate date = datePicker.getValue();
        Purchase purchase = new Purchase(date, addName.getText(), Integer.parseInt(addPrice.getText()),
                (String) allTypesComboBox.getSelectionModel().getSelectedItem());


        listOfPurchases.add(purchase);
        addName.clear();
        addPrice.clear();
    }}

    public static boolean checkDigitsInLine(String str){
        boolean isOnlyDigits = true;
        for (int i = 0; i < str.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(str.charAt(i))){
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }






    @FXML
    private Text search = new Text("Search ");
    @FXML
    DatePicker fromDatePicker = new DatePicker();
    @FXML
    DatePicker toDatePicker = new DatePicker();

    @FXML
    private Text from = new Text("from:");
    @FXML
    private Text to = new Text("to:");
    @FXML
    ComboBox <String> comboBoxTypes = new ComboBox<>();

    @FXML
    Text dateInfo = new Text();
    @FXML
    TextArea allPurchasesInPeriod = new TextArea();
    @FXML
    ArrayList<String> allPurchasesList = new ArrayList<>();
    @FXML
    Text sumOut = new Text();


    @FXML
    protected void onButtonGetDateInfoClick(){
        refresh(allPurchasesList);

        LocalDate fromDate = fromDatePicker.getValue();//переменные для указания диапазона дат в поиске
        LocalDate toDate = toDatePicker.getValue();

        if(fromDate == null || toDate == null){
            dateInfo.setText("Enter the dates, please");
            return;
        } else {
            dateInfo.setText("Your purchases from " + fromDatePicker.getValue() + " to " + toDatePicker.getValue());
        }

        double sum = 0;
        for(Purchase purchase : listOfPurchases){
            if((purchase.getDate().isAfter(fromDate) || purchase.getDate().isEqual(fromDate))
                    && (purchase.getDate().isBefore(toDate) || purchase.getDate().isEqual(toDate))){

                if(comboBoxTypes.getValue().equals("All") || comboBoxTypes.getValue().equals(purchase.getType())){
                    allPurchasesList.add(purchase.getName() + " - " + purchase.getPrice());
                    sum = sum + purchase.getPrice();
                    sumOut.setText("Sum is: " + sum);
                }
            }
        }
        String separator = "\n";
        String res = String.join(separator, allPurchasesList);
        allPurchasesInPeriod.setText(res);
    }

    public static void refresh(ArrayList<String> stringArrayList){
        stringArrayList.clear();
    }

}