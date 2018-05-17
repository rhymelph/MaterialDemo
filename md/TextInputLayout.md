## 1. TextInputLayout
> 实际就是一个LinearLayout
> 实现了WithHint接口，目的是获取TextInputEditText实现动画效果

### 布局使用
```xml
    <android.support.design.widget.TextInputLayout
        android:id="@+id/til_user"
        app:counterEnabled="true"
        app:counterMaxLength="16"
        app:errorEnabled="true"
        app:counterTextAppearance="@style/Base.TextAppearance.AppCompat.Small"
        app:counterOverflowTextAppearance="@style/Base.TextAppearance.AppCompat"
        app:hintAnimationEnabled="false"
        app:hintEnabled="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.design.widget.TextInputEditText
            android:id="@+id/tiet_user"
            android:maxLength="16"
            android:drawableStart="@drawable/ic_account_box_black"
            android:layout_width="match_parent"
            android:hint="用户名"
            android:inputType="text"
            android:drawablePadding="8dp"
            android:layout_height="wrap_content"
            android:drawableLeft="@drawable/ic_account_box_black" />
    </android.support.design.widget.TextInputLayout>
    <android.support.design.widget.TextInputLayout
        android:id="@+id/til_pass"
        app:passwordToggleContentDescription="密码显示开关"
        app:passwordToggleEnabled="true"
        app:passwordToggleTint="@color/colorPrimary"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.design.widget.TextInputEditText
            android:id="@+id/tiet_pass"
            android:drawableStart="@drawable/ic_lock_black"
            android:layout_width="match_parent"
            android:hint="密码"
            android:inputType="textPassword"
            android:drawablePadding="8dp"
            android:layout_height="wrap_content"
            android:drawableLeft="@drawable/ic_lock_black" />
    </android.support.design.widget.TextInputLayout>
```

### 属性介绍
| 属性 | 介绍 |
| - | :-: |
| counterEnabled | 是否使用字数限制提示 |
| counterMaxLength | 字数限制最大值 |
| errorEnabled | 是否使用错误提示 |
| counterTextAppearance | 字数限制字体 |
| counterOverflowTextAppearance | 超过字数限制显示的字体 |
| hintAnimationEnabled | 提示动画是否使用 |
| hintEnabled | 提示是否使用 |
| passwordToggleContentDescription | 密码显示开关介绍 |
| passwordToggleEnabled | 密码显示开关是否显示 |
| passwordToggleTint | 密码显示开关颜色 |

## 2. TextInputEditText
> 跟一般的EditText没有什么区别，只是重写了onCreateInputConnection当失去焦点时拿到Hint