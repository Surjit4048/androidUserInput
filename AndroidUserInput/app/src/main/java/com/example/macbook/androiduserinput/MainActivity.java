package com.example.macbook.androiduserinput;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private CheckBox whippedCreamCheckBox;
    private CheckBox chocolateCheckBox;
    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        nameText = (EditText) findViewById(R.id.name_editText);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java for " + nameText.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Create summary of order for submission
     *
     * @param price Price of coffee
     * @return text summary
     */
    public String createOrderSummary(int price) {
        String personName = "Kaptain Kunal";

        return "Name: " + nameText.getText().toString() +
                "\nAdded whipped cream? " + whippedCreamCheckBox.isChecked() +
                "\nAdded chocolate cream? " + chocolateCheckBox.isChecked() +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price +
                "\nThank You!";
    }

    private int calculatePrice() {
        int basePrice = 5;
        if (whippedCreamCheckBox.isChecked()) {
            basePrice += 1;
        }
        if (chocolateCheckBox.isChecked()) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /**
     * This method increase value by one
     */
    public void increment(View view) {
        if (quantity <= 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }else {
            Toast.makeText(this, "You cannot order more than 100 cups", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method decrease value by one
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }else {
            Toast.makeText(this, "You cannot order less than 1 cups", Toast.LENGTH_SHORT).show();
        }
    }

}
