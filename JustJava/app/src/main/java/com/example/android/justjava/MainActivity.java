package com.example.android.justjava;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    boolean hasWhippedCream = false, hasChocolate = false;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  initialise quantity display
        display(quantity);
        displayPrice(quantity * 5);
        Log.i("EnterpriseActivity.java", "Captain's Log, Stardate 43125.8. We have entered a spectacular binary star system in the Kavis Alpha sector on a most critical mission of astrophysical research.");
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = 0;

        // get user's name
        EditText etName = (EditText) findViewById(R.id.edit_text_name);
        String name = etName.getText().toString();

        // figure out if user wants whipped cream topping
        CheckBox cbWhippedCream = (CheckBox) findViewById(R.id.has_whipped_cream);
        hasWhippedCream = cbWhippedCream.isChecked();

        // figure out if user wants chocolate topping
        CheckBox cbChocolate = (CheckBox) findViewById(R.id.has_chocolate);
        hasChocolate = cbChocolate.isChecked();

        price = calculatePrice(hasWhippedCream, hasChocolate);

        displayMessage(createOrderSummary(name, price, hasWhippedCream, hasChocolate));
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        //composeEmail(new String[] {"harddisk@gmail.com"}, name, message);
    }

    private boolean checkIntent(Intent _intent) {
        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(_intent, PackageManager.MATCH_DEFAULT_ONLY);
        return activities.size() > 0;
    }

    private void composeEmail(String[] addresses, String name, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));   // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) return;
        quantity++;
        display(quantity);
    }

    /**
     * This method is called when - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) return;
        quantity--;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView =
                (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates price of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;  // price of 1 cup of coffee

        if (addWhippedCream) basePrice += 1;    // add $1 if user wants whipped cream
        if (addChocolate) basePrice += 2;       // add $2 if user wants chocolate

        return quantity * basePrice;    // calculate total order price by multiplying by quantity
    }

    /**
     * @param name            on the order
     * @param price           of order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        return getString(R.string.string_order_summary_name, name) + "\n" +
               getString(R.string.string_add_whipped_cream, addWhippedCream) + '\n' +
               getString(R.string.string_add_chocolate, addChocolate) + '\n' +
               getString(R.string.string_order_summary_quantity, quantity) + "\n" +
               getString(R.string.string_total, NumberFormat.getCurrencyInstance().format(price)) + "\n" +
               getString(R.string.string_thank_you);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given message on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}