# MultiOperationEditTextSample

Modify the TextInputLayout to support more operations to improve the usage of TextInputLayout.
Add the TextView and the Toggle to the right side.
修改TextInputLayout，添加文字和按钮在右侧，以优化TextInputLayout的使用。

!(https://github.com/wilin52/MultiOperationEditText/raw/master/screenshot/show_error.png)

!(https://github.com/wilin52/MultiOperationEditText/raw/master/screenshot/show_normal.png)

How to use: 

    <com.wilin.multioperationedittext.MultiOperationInputLayout
        android:id="@+id/email_input_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:operationErrorColor="@color/errorColor"
        app:operationTextSize="15"
        app:operationTextColor="@color/multi_operation_text_color"
        app:operationTextString="@string/forget_password"
        app:operationType="editTextWithTextView">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="@string/email_hint"
            android:singleLine="true"
            android:textSize="15sp" />
    </com.wilin.multioperationedittext.MultiOperationInputLayout>
    
    <com.wilin.multioperationedittext.MultiOperationInputLayout
        android:id="@+id/password_input_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:operationToggleDrawable="@drawable/design_password_eye_icon"
        app:operationToggleType="password"
        app:operationType="editTextWithToggle">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="@string/password_hint" />
    </com.wilin.multioperationedittext.MultiOperationInputLayout>
