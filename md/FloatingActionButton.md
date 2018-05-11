## FloatingActionButton

1. 直接使用
```xml
    <android.support.design.widget.FloatingActionButton
        android:id="@+id/up_fab"
        app:rippleColor="@android:color/holo_blue_dark"
        app:fabSize="normal"
        app:borderWidth="0dp"
        app:elevation="5dp"
        android:src="@drawable/ic_arrow_upward_while"
        app:fabCustomSize="50dp"
        app:backgroundTint="@android:color/white"
        app:pressedTranslationZ="5dp"
        app:useCompatPadding="true"
        android:layout_gravity="bottom|end"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```

2. 属性介绍
| 属性 | 介绍 |
| - | :-: |
| rippleColor | 点击按钮要出现的渐变颜色 |
| fabSize | 按钮的大小规模（normal，mini，auto） |
| borderWidth | 边框宽度，为兼容5.0推荐设置为0 |
| elevation | 阴影高度 |
| src | 图片资源 |
| fabCustomSize | 按钮具体大小 |
| backgroundTint | 按钮背景 |
| pressedTranslationZ | 点击按钮时，按钮边缘的宽度 |
| useCompatPadding | 是否使用推荐的内边距 |

3. 然后像Button一样使用