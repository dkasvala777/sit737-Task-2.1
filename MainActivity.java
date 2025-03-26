package com.macco.unitconvertor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerSourceUnit, spinnerDestinationUnit;
    private EditText editTextValue;
    private Button buttonConvert;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinnerSourceUnit = findViewById(R.id.spinnerSourceUnit);
        spinnerDestinationUnit = findViewById(R.id.spinnerDestinationUnit);
        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSourceUnit.setAdapter(adapter);
        spinnerDestinationUnit.setAdapter(adapter);

        // Initialize UI components

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConvertButtonClick();
            }
        });
    }

    // Handle conversion button click
    private void onConvertButtonClick() {
        String sourceUnit = spinnerSourceUnit.getSelectedItem().toString();
        String destUnit = spinnerDestinationUnit.getSelectedItem().toString();
        String valueStr = editTextValue.getText().toString();

        // Check if the input is empty
        if (valueStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse the entered value
        double value;
        try {
            value = Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the source unit is the same as the destination unit
        if (sourceUnit.equals(destUnit)) {
            Toast.makeText(this, "Source and destination units must be different", Toast.LENGTH_SHORT).show();
            return;
        }

        // Log the values being passed to the conversion function
        Log.d("UnitConverter", "Source Unit: " + sourceUnit);
        Log.d("UnitConverter", "Destination Unit: " + destUnit);
        Log.d("UnitConverter", "Input Value: " + value);

        // Perform the conversion
        double convertedValue = ConversionUtils.convert(sourceUnit, destUnit, value);

        // Log the result
        Log.d("UnitConverter", "Converted Value: " + convertedValue);

        // Display the result
        textViewResult.setText(String.valueOf(convertedValue));
    }
}