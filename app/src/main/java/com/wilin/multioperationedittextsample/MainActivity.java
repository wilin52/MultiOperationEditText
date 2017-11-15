package com.wilin.multioperationedittextsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wilin.multioperationedittext.MultiOperationInputLayout;

public class MainActivity extends AppCompatActivity {
    private Button changeColorBtn;
    private MultiOperationInputLayout emailInputLayout;
    private MultiOperationInputLayout passwordInputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView(){
        changeColorBtn = (Button) findViewById(R.id.show_error_btn);
        passwordInputLayout = (MultiOperationInputLayout) findViewById(R.id.password_input_layout);
        emailInputLayout = (MultiOperationInputLayout) findViewById(R.id.email_input_layout);

        changeColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailInputLayout.setShowErrorWithoutErrorText(!emailInputLayout.isShowErrorWithoutErrorText());
            }
        });

        emailInputLayout.setOperationTextViewOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Operation TextView Onclick",Toast.LENGTH_SHORT).show();
            }
        });

        passwordInputLayout.setOperationToggleOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Operation Toggle Onclick",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
