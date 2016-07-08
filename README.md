# AndroidHeroApp
Android群英传，书中的全部例子写的App

### 以下是读书笔记

## Android Heroes 

#### 第一章  Android体系和系统架构

1.1、Android系统架构：大致分为四层，即Linux内核层、库和运行时、FrameWork层和应用层。Android系统体系架构鼓励系统组件重用，共享组件间的数据，并且定义组件的访问权限控制。可以说，这些层次组件结构即使相互独立，又是相互关联。 从Android设计者的角度来看整个Android之间的架构，设计者们希望Android架构能够起到承上启下的作用，让应用的各个组件之间结构，并且通过框架统一的调度、管理。

- Linux内核层：Linux内核层是Android系统最底层最核心的部分，可以在手机上查看我们使用的Linux内核版本。它包含了Android系统的核心服务，硬件驱动，进程管理，安全系统等等。
- Standard Libraries：
- FrameWork：
- Application：

Dalvik与Art：Android为每App分配了Dalvik虚拟机来保证了互相之间不受干扰，并保证独立。Dalvik虚拟机的特点是运行时编译，而Android5.0之后Art模式取代了Dalvik，它的特点是安装时就进行编译，执行效率更高。

2、Android系统源码目录

`Makefile` （描述Android各个组件间的联系并指导它们进行自动化编译）
`bionic` (bionic C库)
`bootable` (启动引导相关代码)
`build` (系统编译规则等基础开发包配置)
`cts` （Google兼容性测试标准）
`dalvik` （Dalvik虚拟机）
`development` （应用程序开发相关）
`external` （android使用的一些开源模块）
`frameworks` （Framework框架核心）
`hardware` （厂商硬件适配层HAL）
`out` （编译完成后的代码输出目录）
`packages` （应用程序包）
`prebuilt` （x86和arm架构下预编译资源）
`sdk` （sdk及模拟器）
`system` （底层文件系统库、应用及组件）
`vendor` （厂商定制代码）

3、Android系统目录

手机通过adb连接电脑后，可以通过`ls`命令来查看。

`/system`和`/app`是开发者最需要关注的两个目录。

system目录

主要包括:`system/app`主要存在系统的App，`system/bin`主要存放Linux自带组件，     `system/build.prop` 主要存放的是系统的属性信息。`system/fonts`存在字体文件目录， `/system/framework`系统核心文件，框架层。`system/lib`共享so库。

data目录主要包含了用户的数据信息。

主要包括：`/data/app`,`/data/data`,`data/system`,`data/misc`主要包含大部分Wifi、VPN信息。

#### 第二章 Android 开发工具

1、常用的ADB命令

`adb install  xxx.apk`安装应用，`adb uninstall xxx.apk`卸载应用。 
`adb push <local> <remote>` copy文件到手机，<local>为本地文件路经，<remote>手机端路径。`adb pull <remote> <local> `从手机端获取文件。

#### 第三章 Android控件架构与自定义控件详解

1.1、View的控件结构

View控件的树结构：Android整个界面的控件是一个树形结构，即控件树。每棵树的顶部都有一个ViewParent对象，所有的交互事件都由它来同一调度和分配。上层控件负责下层控件的测量和绘制，以及传递交互事件。`findViewById`就是通过遍历树来查找元素。

1.2、Android界面架构图

每个Acticity都包含一个Window对象，通常由他的子类PhoneWindow来实现。PhoneWindow将一个decorView作为整个应用窗口的根View，DecorView将屏幕分成两部分：TitleView和ContentView。ContentView实际上是一个FrameLayout(id为：android.R.id.content)，里面容纳的就是我们在xml布局文件中定义的布局。decorView里面所有的监听事件都由ActicityManagerServer来接受和回调分发。当程序执行setContentView后，ActivityManagerService会回调onResume()方法，此时系统会将整个DecorView添加到PhoneWindow中。

1.3、onMeasure()测量。

理解MeasureSpec：

MeasureSpec封装了测量的测量模式和测量的大小。它的数值是一个32位的int值，最高两位为测量的模式，低30位是测量的大小。使用位运算提高效率，节约空间。

- 测量规则MeasureSpec：
  - EXACTLY  ：view的宽高属性是一个精确的值，layout属性为match_parent时不一定是精确模式
  - AT_MOST：View的宽高属性为wrap_content，最大值不允许超过父控件指定的尺寸，其大小一般随子View(如果有)或者内容的变化而变化。
  - UNSPECIFIED：任意大小。在自定义View中才会使用到。

View和ViewGroup测量一致，如果需要支持Wrap_content属性需要重写onMeasure()方法。但是ViewGroup的一个职责就是管理子View的显示大小。一般需要遍历子View，并设置它们的大小。

1.4、onDraw()绘制。

重写父类的onDraw()方法，就可以在canvas利用canvas.drawXXX()Api来绘制我们想要的图形，不管多么复杂、精美的控件都可以拆分成一个个小的图像单元，我们要做的就是找到这些图形单元并将其绘制。

书中提到另一样绘制方式，我们创建自己的`mCanvas`对象，传入onDraw()方法中绘制的`bitmap`，对这个`bitmap`进行绘制，然后重绘view，也同样可以改变view的显示效果。代码类似下面：

```Java
canvas.drawBitmap(bitmap1,0,0,null)
canvas.drawBitmap(bitmap2,0,0,null);
Canvas mCanvas = new Canvas(bitmap2);
mCanvas.drawXXX()
```

View和ViewGroup的绘制：自定义View一般会重写onDraw()方法绘制自己样子。一般来说ViewGroup不需要重写onDraw()方法，如果不设置backgroud，但是会调用dispatchDraw来绘制子View。

1.5、自定义View

通常自定义View包含以下三种情况：

- 对现有控件进行拓展
- 通过组合来实现新的控件
- 重写View来实现全新的控件。

Canvas对象的各种draw方法的说明：

`canvas.drawRect()`：绘制矩形，需要确定四个点，即left，right，top，buttom。

`canvas.drawCricle()`：绘制圆形，需要确定中心点坐标，半径。即mCircleX，mCircleY，mRadio。

`canvas.drawArc`：绘制弧线，需要确定绘制的矩形，开始角度，当前角度，useCenter，传入false即可。

Paint画笔：

- 指定画笔的颜色`paint.setColor()`
- 指定画笔的样式`paint.setStyle()`
  - FILL，填充整个画笔经过的区域
  - STROKE，指定画笔只填充边框
  - FILL_AND_STROKE，这是啥，我不知道。
- 指定画笔的shader(着色器)：`paint.setShader(Shader)`
  - LinearShader：线性颜色渐变着色器。

1.6、自定义View/ViewGroup：看代码就好。

#### 第四章 ListView使用技巧 

1.1、设置技巧

- 设置ListView每个item的分割线：

  `ndroid:divider=""@android:color/white"`
  `android:dividerHeight="10dp"`
  ` android:divider = @null` （设置分隔线透明）

- 设置不显示滚动条：android:scrollbars="none"

- 取消点击效果：`android:listSelector="@android:color/transparent"`

- 取消滑动顶(低)部的半月牙阴影效果：android:overScrollMode="never"

- 平滑滚动：

  `listview.setSelection(n);` 这个方法类似scrollTo瞬间完成移动。
  `listview.smoothScrollBy(distance, duration);`
  `listview.smoothScrollByOffset(offset);`
  `listview.smoothScrollToPosition(index);`

- 设置没有数据的ListView显示内容`setEmptyView(view)`

1.2、弹性的ListView

ListView的一个回调方法`overScrollBy()`，改变maxOverScrollY就可以使ListView具有弹性的功能。

1.3、滚动隐藏和显示toolbar的ListView。

给ListView添加个Toolbar一样大小的headView保证ListView在Toolar下面，然后ListView滚动的时候动态的显示个隐藏Toolbar

- 获取TouchSlop：`touchSlop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();`
- 获取Toolbar的高度64dp：`getResources().getDimension(R.dimen.abc_action_bar_default_height_material)))`


- 触摸监听：`onTouchListener` 可以得道MotionEnevt对象。

1.4、ListView滚动状态监听：

- 滑动监听：`onScrollListener` 可以获取ListView的滚动状态。

1.5、ListView优化在面试中也常被提到，[facebookListView的优化。](http://blog.aaapei.com/article/2015/02/facebookxin-wen-ye-listviewyou-hua)

#### 第五章 Android Scroll分析

1.1、实现滑动的基本思想

当View触摸时，记下当前触摸点的坐标。在手指移动时，获取最新坐标，计算出偏移量。通过偏移量修改View的坐标，这样不断的重复，从而实现View的滑动过程。

> 实现滑动的方法

- 使用`layout()`方法。

  ```java
  layout(getLeft() + dx, getTop() + dy, getRight() + dx, getBottom() + dy);
  ```

- 使用`offsetLeftAndRight()`方法。

  ```java
   offsetLeftAndRight(dx);
   offsetTopAndBottom(dy);
  ```

- LayoutParams。或是使用ViewGroup.MarginLayoutParams

  ```Java
  ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)
  			getLayoutParams();
  layoutParams.leftMargin = dx;
  layoutParams.topMargin = dy;
  setLayoutParams(layoutParams);
  ```

- scrollTo和scrollBy。搭配Scroller对象实现平滑滚动。

scrollTo/ScrollBy移动的View的内容区域，View的BackGroud并不会跟随移动。

getScrollX/getScrollY值View内容的滑动距离，View的内容区域在View的边缘右边或下边是这个值为负。

Scroller的startScroll方法实现滚动，同样是移动的View的内容区域，方法本身只是记录了View的移动距离，需要调用View的invalidate()方法并搭配View的computeScroll方法。

- 属性动画

1.2、ViewDragHelper

对子View的拖拽进行处理。它主要封装了对View的触摸位置，触摸速度，移动距离等的检测和Scroller,通过接口回调的方式告诉我们;只需要我们指定是否需要移动，移动多少等; 本质是对触摸事件的解析类;

#### 第六章 Android绘图机制与处理技巧

1.1、2D绘图基础

(1)Canvas对象

- `drawPoint` ：绘制点
- `drawLine` ：绘制直线
- `drawLines` ：绘制多条直线
- `drawRect` ：绘制矩形
- `drawRoundRect(left,top,right,buttom,radiusX,radiusY,paint`)` ：绘制圆角矩形
- `drawCircle`：绘制圆形
- `drawArc()` ：绘制弧型
- `drawOval` ：绘制椭圆
- `drawText` ：绘制文本
- `drawPosText` ：在指定位置绘制文本
- `drawPath` ：绘制path路径

(2)Paint对象

- `setAntiAlias` ：设置画笔的锯齿效果


- `setColor` ：设置画笔的颜色


- `setARGB` ：设置画笔的A、R、G、B值


- `setAlpha` ：设置画笔的透明度值


- `setTextSize` ：设置字体大小


- `setStyle` ：设置画笔的效果（空心STROKE或者实心FILL）


- `setStrokeWidth` ：设置画笔的宽度

1.2、Android   Xml绘图

- bitmap：在drawable文件夹下定义bitmap，可以直接转化成bitmap在程序中使用。

```Java
<?xml version="1.0" encoding="utf-8"?>
<bitmap
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@[package:]drawable/drawable_resource"
    android:antialias=["true" | "false"]
    android:dither=["true" | "false"]
    android:filter=["true" | "false"]
    android:gravity=["top" | "bottom" | "left" | "right" | "center_vertical" |
                      "fill_vertical" | "center_horizontal" | "fill_horizontal" |
                      "center" | "fill" | "clip_vertical" | "clip_horizontal"]
    android:tileMode=["disabled" | "clamp" | "repeat" | "mirror"] />
```

- shape：

```java
<?xml version="1.0" encoding="utf-8"?>
<shape    
    xmlns:android="http://schemas.android.com/apk/res/android"    
    android:shape=["rectangle" | "oval" | "line" | "ring"] >    
    <corners   //当shape为rectangle时使用
        android:radius="integer" //半径值会被后面的单个半径属性覆盖，默认为1dp
        android:topLeftRadius="integer"        
        android:topRightRadius="integer"        
        android:bottomLeftRadius="integer"        
        android:bottomRightRadius="integer" />    
    <gradient       //渐变
        android:angle="integer"        
        android:centerX="integer"        
        android:centerY="integer"        
        android:centerColor="integer"        
        android:endColor="color"        
        android:gradientRadius="integer"        
        android:startColor="color"        
        android:type=["linear" | "radial" | "sweep"]        
        android:useLevel=["true" | "false"] />    
    <padding        //内边距
        android:left="integer"        
        android:top="integer"        
        android:right="integer"        
        android:bottom="integer" />    
    <size           //指定大小，一般用在imageview配合scaleType属性使用
        android:width="integer"        
        android:height="integer" />    
    <solid          //填充颜色
        android:color="color" />    
   	<stroke         //边框
      	android:width="integer"        
        android:color="color"        
        android:dashWidth="integer"        
        android:dashGap="integer" />
</shape>
```

- Layer

```Java
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@[package:]drawable/drawable_resource" />
    <item android:drawable="@[package:]drawable/drawable_resource" />
    ......
</layer-list>
```

- selector

selector的用法很多，一般是定义控件在不同状态下的显示形态，可以是图片drawable，也可以是形状shape，还可以只是颜色color！

```Java
<?xml version="1.0" encoding="utf-8" ?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 默认时的背景图片-->
    <item android:drawable="@drawable/pic1"/>
    <!-- 没有焦点时的背景图片 -->
    <item android:drawable="@drawable/pic1" android:state_window_focused="false"/>
    <!-- 非触摸模式下获得焦点并单击时的背景图片 -->
    <item android:drawable="@drawable/pic2" android:state_focused="true" android:state_pressed="true"/>
    <!-- 触摸模式下单击时的背景图片-->
    <item android:drawable="@drawable/pic3" android:state_focused="false" android:state_pressed="true"/>
    <!--选中时的图片背景-->
    <item android:drawable="@drawable/pic4" android:state_selected="true"/>
    <!--获得焦点时的图片背景-->
    <item android:drawable="@drawable/pic5" android:state_focused="true"/>
</selector>
```

selector可以用来指定不同状态下文本的颜色，例如按钮上的文本的颜色

```Java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="#999" android:state_selected="true"/>
    <item android:color="#666" android:state_focused="true"/>
    <item android:color="#333" android:state_pressed="true"/>
    <item android:color="#000"/>
</selector>
```

结合这篇博文[Android开发：shape和selector和layer-list](http://blog.csdn.net/brokge/article/details/9713041)以及博主的实现的[圆角镂空按钮](http://blog.csdn.net/brokge/article/details/41318117)例子(综合使用了Shape、Layer和Selector实现了圆角镂空按钮)一起看还是挺不错的。

1.3、Android 绘图技巧

- Canvas画布

`save`：保存画布，将之前绘制的内容保存起来，让后续的绘制操作像是在一个新的画布上绘制。
`restore`：合并画布，将save方法之后绘制的内容与之前绘制的内容合并起来；
`translate`：移动画布，其实是画布所在的坐标系的移动；调用之前先save较好。
`rotate`：旋转画布，其实是画布所在的坐标系的旋转。

- Layer 图层

在Android中图层是基于栈的结构来管理的，通过调用`saveLayer()`、`saveLayerAlpha()`方法来创建图层和创建一个透明的图层，即入栈创建一个图层栈，使用`restore`、`restoreToCount`方法合并一个或多个图层，即出栈，将图层上的内容合并到canvas上。入栈的时候，后面所有的操作都发生在这个图层上，而出栈的时候则会把图像绘制在上层Canvas上。

1.4、Android图像处理：色彩特效、图形特效、画笔特效。

1.5、SurfaceView

SurfaceView是View的子类。View的onDraw方法运行在主线程，同时View的绘制每隔16m绘制一次，如果在16m的时间内不能完成界面的绘制，那么在视觉上就会造成卡顿，通常在Log中出现The application may be doing must work on main thread就是View的绘制阻塞了主线程。而SurfaceView的绘制运行在子线程，他很好的代替了View在不断刷新状态下的不足。

SurfaceView适用于比较频繁刷新的界面，并且实现了双缓冲的机制。

- 使用

使用SurfaceView我们通常实现两个接口，SurfaceHolder.Callback和Runnable。

SurfaceHolder：管理SurfaceView的声明周期（回调方法）和获取canvas对象进行绘制。

通过`lockCanvas`方法获取Canvas对象进行绘制，并通过`unlockCanvasAndPost`方法对画布内容进行提交。
需要注意的是每次调用lockCanvas拿到的Canvas都是同一个Canvas对象，所以之前的操作都会保留，如果需要擦除，可以在绘制之前调用drawColor方法来进行清屏。

#### 第七章 Android动画机制与使用技巧

1.1、View动画 （视图动画）
视图动画(Animation)框架定义了透明度(AlphaAnimation)、旋转(RotateAnimation)、缩放(ScaleAnimation)和位移(TranslateAnimation)几种常见的动画，控制的是整个View，所以视图动画的缺陷就在于当某个元素发生视图动画后，其响应事件的位置还依然停留在原来的地方！

> 实现原理是每次绘制视图时View所在的ViewGroup中的drawChild方法获取该View的Animation的Transformation值，然后调用canvas.concat(transformationToApply.getMatrix())，通过矩阵运算完成动画帧。如果动画没有完成，就继续调用invalidate方法，启动下次绘制来驱动动画，从而完成整个动画的绘制。

- 动画集合(AnimationSet)：将多个视图动画组合起来


- 动画监听器(AnimationListener)：提供动画的监听回调方法


1.2、属性动画

Android 3.0之后添加了属性动画(Animator)框架，其中核心类ObjectAnimator能够自动驱动，在不影响动画效果的情况下减少CPU资源消耗。

**ObjectAnimator**
创建ObjectAnimator只需通过它的静态工厂方法直接返回一个ObjectAnimator对象，参数包括view对象，以及view的属性名字，这个属性必须要有get/set方法，因为ObjectAnimator内部会通过反射机制来修改属性值。常用的可以直接使用属性动画的属性包括：
(1)`translationX`和`translationY`：控制view从它布局容器左上角坐标偏移的位置；
(2)`rotation`、`rotationX`和`rotationY`：控制view围绕支点进行2D和3D旋转；
(3)`scaleX`和`scaleY`：控制view围绕着它的支点进行2D缩放；
(4)`pivotX`和`pivotY`：控制支点位置，围绕这个支点进行旋转和缩放处理。默认情况下，支点是view的中心点；
(5)`x`和`y`：控制view在它的容器中的最终位置，它是最初的左上角坐标和translationX、translationY的累计和；
(6)`alpha`：控制透明度，默认是1（不透明）。

ObjectAnimator的常见使用方式如下：

```java
ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 300);
animator.setDuration(1000);
animator.start();
```

属性动画集合AnimatorSet：控制多个动画的协同工作方式，常用方法`animatorSet.play().with().before().after()`、`playTogether`、`playSequentially`等方法来精确控制动画播放顺序。使用`PropertyValueHolder`也可以实现简单的动画集合效果。

**动画监听器**：监听动画事件可以使用`AnimatorListener`或者简易的适配器`AnimatorListenerAdapter`

**如果一个属性没有get/set方法怎么办？**
(1)自定义包装类，间接地给属性提供get/set方法，下面就是一个包装类的例子，为width属性提供了get/set方法

```Java
public class WrapperView {
    private View mView;
    public WrapperView(View mView){
        this.mView = mView;
    }
    public int getWidth(){
        return mView.getLayoutParams().width;
    }
    public void setWidth(int width){
        mView.getLayoutParams().width = width;
        mView.requestLayout();
    }
}
```

(2)使用`ValueAnimator`
ObjectAnimator就是继承自ValueAnimator的，它是属性动画的核心，ValueAnimator不提供任何动画效果，它就是一个数值产生器，用来产生具有一定规律的数字，从而让调用者来控制动画的实现过程，控制的方式是使用`AnimatorUpdateListener`来监听数值的变换。

```Java
ValueAnimator animator = ValueAnimator.ofFloat(0,100);
animator.setTarget(view);
animator.setDuration(1000);
animator.start();
animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Float value = (Float) animation.getAnimatedValue();
        //do the animation!
    }
});
```

**在XML中使用属性动画**
下面是一个简单例子：

```Java
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="4000"
    android:propertyName="rotation"
    android:valueFrom="0"
    android:valueTo="360" />
```

在代码中使用方式如下： **[注：测试该代码的时候，上面的xml定义应该放在res的animator目录下，放在anim目录下不行]**

```Java
Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_rotation);
animator.setTarget(view);
animator.start();
```

**View的animate方法**
Android 3.0之后View新增了animate方法直接驱动属性动画，它其实是属性动画的一种简写方式

```Java
imageView.animate().alpha(0).y(100).setDuration(1000)
        .setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
```

3.布局动画

布局动画是作用在ViewGroup上的，给ViewGroup添加view时添加动画过渡效果。
(1)简易方式（但是没有什么效果）：在xml中添加如下属性 `android:animateLayoutChanges="true`
(2)通过LayoutAnimationController来自定义子view的过渡效果，下面是一个常见的使用例子：

```java
LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
scaleAnimation.setDuration(2000);
LayoutAnimationController controller = new LayoutAnimationController(scaleAnimation, 0.5f);
controller.setOrder(LayoutAnimationController.ORDER_NORMAL);//NORMAL 顺序 RANDOM 随机 REVERSE 反序
linearLayout.setLayoutAnimation(controller);
```

4.自定义动画
创建自定义动画就是要实现它的`applyTransformation`的逻辑，不过通常还需要覆盖父类的`initialize`方法来实现初始化工作。
下面是一个模拟电视机关闭的动画，

```java
public class CustomTV extends Animation {
    private int mCenterWidth;
    private int mCenterHeight;
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(1000);// 设置默认时长
        setFillAfter(true);// 动画结束后保留状态
        setInterpolator(new AccelerateInterpolator());// 设置默认插值器
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
    }
}
```

applyTransformation方法的第一个参数interpolatedTime是插值器的时间因子，取值在0到1之间；第二个参数Transformation是矩阵的封装类，一般使用它来获得当前的矩阵Matrix对象，然后对矩阵进行操作，就可以实现动画效果了。

**如何实现3D动画效果呢？**
使用`android.graphics.Camera`中的Camera类，它封装了OpenGL的3D动画。可以把Camera想象成一个真实的摄像机，当物体固定在某处时，只要移动摄像机就能拍摄到具有立体感的图像，因此通过它可以实现各种3D效果。
下面是一个3D动画效果的例子

```java
public class CustomAnim extends Animation {

    private int mCenterWidth;
    private int mCenterHeight;
    private Camera mCamera = new Camera();
    private float mRotateY = 0.0f;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(2000);// 设置默认时长
        setFillAfter(true);// 动画结束后保留状态
        setInterpolator(new BounceInterpolator());// 设置默认插值器
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }

    // 暴露接口-设置旋转角度
    public void setRotateY(float rotateY) {
        mRotateY = rotateY;
    }

    @Override
    protected void applyTransformation( float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        mCamera.rotateY(mRotateY * interpolatedTime);// 使用Camera设置旋转的角度
        mCamera.getMatrix(matrix);// 将旋转变换作用到matrix上
        mCamera.restore();
        // 通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(mCenterWidth, mCenterHeight);
        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }
}
```
#### 第八章 activity与activity调用栈分析

1.1、Activity生命周期注意点：

- 启动一个新的Activity，两个activity的生命周期
  - 启动后调用了`finish()`方法，先调用旧Activity的onPause方法，然后调用新的Activity的onCreate->onStart->onResume方法，最后调用旧Activity的onStop->onDestory方法。
  - 没有调用`finish()`方法，那么旧Activity会调用onPause->onSaveInstanceState->onStop方法，onDestory方法不会被调用。
  - 如果新的Activity为透明或则对话框的格式，那么旧`onPause`、`onSaveInstanceState`


- 如果应用长时间处于stopped状态并且此时系统内存极为紧张的时候，系统就会回收Activity，此时系统在回收之前会回调`onSaveInstanceState`方法来保存应用的数据Bundle。当该Activity重新创建的时候，保存的Bundle数据就会传递到`onRestoreInstanceState`方法和`onCreate`方法中，这就是`onCreate`方法中`Bundle savedInstanceState`参数的来源。
- 用户点击back键不会调用`onSavaInstancestate()`方法，而点击Home键则会调用`onSavaInstanceState`方法，却别在于是不是应用主动的行为去停止当前的Activity。


- **onSaveInstanceState方法和onRestoreInstanceState方法“不一定”是成对的被调用的。**onSaveInstanceState的调用遵循一个重要原则，即当系统“未经你许可”时销毁了你的activity，则onSaveInstanceState会被系统调用，这是系统的责任，因为它必须要提供一个机会让你保存你的数据。onRestoreInstanceState被调用的前提是，activity“确实”被系统销毁了，而如果仅仅是停留在有这种可能性的情况下，则该方法不会被调用，例如，当正在显示activity的时候，用户按下HOME键回到主界面，然后用户紧接着又返回到activity，这种情况下activity一般不会因为内存的原因被系统销毁，故activity的onRestoreInstanceState方法不会被执行。

1.2、Activity的任务栈

应用内的Activity是被任务栈Task来管理的，一个Task中的Activity可以来自不同的应用，同一个应用的Activity也可能不在同一个Task中。默认情况下，任务栈依据栈的后进先出原则管理Activity，但是Activity可以设置一些“特权”打破默认的规则，主要是通过在AndroidManifest文件中的属性`android:launchMode`或者通过Intent的flag来设置。

合理的设置设置Acticity的启动模式会让程序的运行更加有效率用户体验更好。

**standard**：默认的启动模式，该模式下会生成一个新的Activity，同时将该Activity实例压入到栈中（不管该Activity是否已经存在在Task栈中，都是采用new操作）。例如： 栈中顺序是A B C D ，此时D通过Intent跳转到A，那么栈中结构就变成 A B C D A，点击返回按钮的 显示顺序是 D C B A，依次摧毁。

**singleTop**：在singleTop模式下，如果当前Activity D位于栈顶，此时通过Intent跳转到它本身的Activity（即D），那么不会重新创建一个新的D实例（走`onNewIntent()`），所以栈中的结构依旧为A B C D，如果跳转到B，那么由于B不处于栈顶，所以会新建一个B实例并压入到栈中，结构就变成了A B C D B。应用实例：三条推送，点进去都是一个activity。

**singleTask**：在singleTask模式下，Task栈中只能有一个对应Activity的实例。例如：现在栈的结构为A B C D，此时D通过Intent跳转到B（走`onNewIntent()`），则栈的结构变成了：A B。其中的C和D被栈弹出销毁了，也就是说位于B之上的实例都被销毁了。如果其他程序通过singleTask的模式来启动该Activity，那么他会创建一个新的任务栈（类是singleInStance），但是如果启动的acticity已经在后台的任务栈中了，那么这个任务栈也会一起被唤到前台。案例：1.通常应用于首页，首页肯定得在栈底部，也只能在栈底部。2.退出整个应用，将主Activity设置为SingTask的模式，然后在要退出的activity中转到主Activity，从而将主Activity上的所有Activity中全部清楚，然后重新`onNewIntent()`方法，在方法的最后加上一句`finish()`。	

**singleInstance**：singleInstance模式下会将打开的Activity压入一个新建的任务栈中。例如：Task栈1中结构为：A B C，C通过Intent跳转到了D（D的启动模式为singleInstance），那么则会新建一个Task 栈2，栈1中结构依旧为A B C，栈2中结构为D，此时屏幕中显示D，之后D通过Intent跳转到D，栈2中不会压入新的D，所以2个栈中的情况没发生改变。如果D跳转到了C，那么就会根据C对应的启动模式在栈1中进行对应的操作，C如果为standard，那么D跳转到C，栈1的结构为A B C C，此时点击返回按钮，还是在C，栈1的结构变为A B C，而不会回到D。

3.Intent Flag启动模式`

(1)`Intent.FLAG_ACTIVITY_NEW_TASK`：使用一个新的task来启动Activity，一般用在service中启动Activity的场景，因为service中并不存在Activity栈。
(2)`Intent.FLAG_ACTIVITY_SINGLE_TOP`：类似`andoid:launchMode="singleTop"`
(3)`Intent.FLAG_ACTIVITY_CLEAR_TOP`：类似`andoid:launchMode="singleTask"`
(4)`Intent.FLAG_ACTIVITY_NO_HISTORY`：使用这种模式启动Activity，当该Activity启动其他Activity后，该Activity就消失了，不会保留在task栈中。例如A B，在B中以这种模式启动C，C再启动D，则当前的task栈变成A B D。

4.清空任务栈
(1)`clearTaskOnLaunch`：每次返回该Activity时，都将该Activity之上的所有Activity都清除。通过这个属性可以让task每次在初始化的时候都只有这一个Activity。
(2)`finishOnTaskLaunch`：clearTaskOnLaunch作用在别的Activity身上，而finishOnTaskLaunch作用在自己身上。通过这个属性，当离开这个Activity所在的task，那么当用户再返回时，该Activity就会被finish掉。
(3)`alwaysRetainTaskState`：如果将Activity的这个属性设置为true，那么该Activity所在的task将不接受任何清理命令，一直保持当前task状态，相当于给了task一道”免死金牌”。

#### 第九章 Android系统信息与安全机制

1.获取系统信息：`android.os.Build`和`SystemProperty`

```java
//Build
String board = Build.BOARD;     //主板
String brand = Build.BRAND;		//系统定制商
String supported_abis = Build.SUPPORTED_ABIS[0];  //CPU指令集
String device = Build.DEVICE;   //设备参数
String display = Build.DISPLAY;	//显示屏参数
String fingerprint = Build.FINGERPRINT;  //唯一编号
String serial = Build.SERIAL;   //硬件序列号
String id = Build.ID;			//修订版本列表
String manufacturer = Build.MANUFACTURER;  //硬件制造商
String model = Build.MODEL;    //版本
String hardware = Build.HARDWARE;  //硬件名
String product = Build.PRODUCT;  //手机产品名
String tags = Build.TAGS;		//描述build的标签
String type = Build.TYPE;	   //Builder类型
String codename = Build.VERSION.CODENAME;  //当前开发代号
String incremental = Build.VERSION.INCREMENTAL;  //源码控制版本号
String release = Build.VERSION.RELEASE;  //版本字符串
String sdk_int = "" + Build.VERSION.SDK_INT; //版本号
String host = Build.HOST;  //host值
String user = Build.USER;  //user名
String time = "" + Build.TIME; //编译时间

//SystemProperty
String os_version = System.getProperty("os.version");    //os版本
String os_name = System.getProperty("os.name");			//os名称
String os_arch = System.getProperty("os.arch");			//os架构
String user_home = System.getProperty("user.home");		//home属性
String user_name = System.getProperty("user.name");		//name属性
String user_dir = System.getProperty("user.dir");		//dir属性
String user_timezone = System.getProperty("user.timezone");//时区
String path_separator = System.getProperty("path.separator");//路径分隔符
String line_separator = System.getProperty("line.separator");//行分隔符
String file_separator = System.getProperty("file.separator");//文件分隔符
String java_vendor_url = System.getProperty("java.vendor.url");//java verder URL属性
String java_class_path = System.getProperty("java.class.path");//java class路径
String java_class_version = System.getProperty("java.class.version");//java class版本
String java_vendor = System.getProperty("java.vendor");//java vender版本
String java_version = System.getProperty("java.version");//java版本
String java_home = System.getProperty("java_home"); //java home属性
```

2.Apk应用信息：`PackageManager`和`ActivityManager`

在AndroidManifest文件中，Activity的信息是通过`ActivityInfo`类来封装的；整个Manifest文件中节点的信息是通过`PackageInfo`类来进行封装的；此外还非有`ServiceInfo`、`ApplicationInfo`、`ResolveInfo`等。其中`ResolveInfo`封装的是包含信息的上一级信息，所以它可以返回ActivityInfo、ServiceInfo等包含的信息，它经常用来帮助我们找到那些包含特定Intent条件的信息，如带分享功能、播放功能的应用。

PackageManager侧重于获取应用的包信息，而ActivityManager侧重于获取运行的应用程序的信息。
PackageManager常用的方法：
`getPackageManger`、`getApplicationInfo`、`getApplicationIcon`、`getInstalledApplications`、`getInstalledPackages`、`queryIntentActivities`、`queryIntentServices`、`resolveActivity`、`resolveService`等。
ActivityManager封装了不少对象，每个对象都保存着一些重要信息。
`ActivityManager.MemoryInfo`：关于系统内存的信息，例如`availMem`(系统可用内存)、`totalMem`(总内存)等；
`Debug.MemoryInfo`：该MemoryInfo主要用于统计进程下的内存信息；
`RunningAppProceeInfo`：运行进程的信息，存储的是与进程相关的信息，例如`processName`、`pid`、`uid`等；
`RunningServiceInfo`：运行服务的信息，存储的是服务进程的信息，例如`activeSince`(第一次被激活时间)等。

1.3、packages.xml文件(位于`/data/system`目录下)
在系统初始化的时候，PackageManager的底层实现类PackageManagerService会去扫描系统中的一些特定的目录，并解析其中的apk文件，最后把它获得的应用信息保存到packages.xml文件中，当系统中的应用安装、删除或者升级时，它也会被更新。

1.4、Android安全机制
五道防线：
(1)代码安全机制——代码混淆proguard
(2)应用接入权限机制——AndroidManifest文件权限声明、权限检查机制
系统检查操作者权限的顺序：首先，判断permission名称，如果为空则直接返回PERMISSION_DENIED;其次，判断Uid，如果uid为0或者为System Service的uid，不做权限控制，如果uid与参数中的请求uid不同，那么返回PERMISSION_DENIED；最后，通过调用PackageManagerService.checkUidPermission方法判断该uid是否具有相应的权限，该方法会去xml的权限列表和系统级的platform.xml中进行查找。
(3)应用签名机制——数字证书：系统不会安装没有签名的app，只有拥有相同数字签名的app才会在升级时被认为是同一个app
(4)Linux内核层安全机制——Uid、访问权限控制
(5)Android虚拟机沙箱机制——沙箱隔离：每个app运行在单独的虚拟机中，与其他应用完全隔离

**apk反编译**
使用apktool、dex2jar、jd-gui三个工具反编译查看应用源码

**apk加密**
proguard不仅可以用来混淆代码（用无意义的字母来重命名类、方法和属性等），还可以删除无用的类、字段、方法和属性，以及删除无用的注释，最大限度地优化字节码文件。
下面是常见的proguard配置，其中`minifyEnabled`属性控制是否启动proguard；`proguardFiles`属性用于配置混淆文件，它分为两部分，一个是系统默认的混淆文件，它位于`/tools/proguard/proguard-android.txt`；另一个是自定义的混淆文件，可以在项目的app文件夹下找到该文件，在该文件中定义引入的第三方依赖包的混淆规则。

```Java
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 
          'proguard-rules.pro'
    }
}
```

#### 第十章 Android性能优化

1.1、布局优化

人眼感觉的流畅需要画面的帧数达到每秒40帧到60帧，那么差不多每16ms（1000/16）系统就要对UI进行渲染和重绘。如果在16m内不能完成绘制，那么就会就等待下次VSYNC信息发出界面重绘，这样就造成了界面的丢帧现象，即卡顿的原因。

(1)、AndroidUI渲染机制：android系统提供了检测UI渲染时间的工具，开发者选项->Profile GPU rendering(GPU呈现模式分析)->On screen as bars。中间的绿色横线代表VSYNC时间16ms，需要尽量将所有条形图都控制在这条绿线之下。

(2)、避免过度绘制：过度绘制（Overdraw）也是很浪费CPU/GPU资源的，系统也提供了检测工具Debug GPU Overdraw (调试界面过度绘制)，来查看界面overdraw的情况。该工具会使用不同的颜色绘制屏幕，来指示overdraw发生在哪里以及程度如何，其中：

```
没有颜色： 意味着没有overdraw。像素只画了一次。
蓝色： 意味着overdraw 1倍。像素绘制了两次。大片的蓝色还是可以接受的、
绿色： 意味着overdraw 2倍。像素绘制了三次。中等大小的绿色区域是可以接受的但你应该尝试优化、减少它们。
浅红： 意味着overdraw 3倍。像素绘制了四次，小范围可以接受。
暗红： 意味着overdraw 4倍。像素绘制了五次或者更多。这是错误的，要修复它们。
```

(3)、优化布局层级，在Android中系统对View的测量，布局，绘制都是通过遍历View树操作的。因此，View树的高度不宜太高。Google在文档中建议View树的高度不宜超过10层。

(4)、避免嵌套过多无用布局：

- 使用"<include>"标签重用layout
- 使用"<ViewStub>"实现view的延迟加载

```Java
ViewStub mViewStub = findViewById(R.id.not_often_use);
mViewStub.setVisible(View.VISIBLE);
//inflate 
View inflateView = mViewStub.inflate();
inflateView.findViewById();
....
```

ViewStub是一个非常轻量级的组件，它不仅不可见，而且大小为0。

ViewStub和View.GONE有啥区别？

它们的共同点是初始时都不会显示，但是前者只会在显示时才去渲染整个布局，而后者在初始化布局树的时候就已经添加到布局树上了，相比之下前者的布局具有更高的效率。

(5)、Hierarchy Viewer：查看视图树的工具，可以帮助我们找到布局冗余，优化布局。

1.2、内存优化

由于Android的沙箱机制，每个应用所分配的内存大小是有限制的，内存太低就会触发LMK - (Low Memory killer)机制。

通常情况下我们所说的内存是指手机的RAM，它包括以下几部分：

- 寄存器(Register)：速度最快的场所。寄存器处于CPU内部，在程序中无法控制；


- 栈(Stack)：存放基本数据类型和对象的引用；


- 堆(Heap)：存放new出来的对象和数组，由虚拟机GC来管理；在程序获取堆内存的大小。


- 静态存储区域(static field)：在固定的位置存放应用程序运行时一直存在的数据，Java在内存中专门划分了一个静态存储区域来管理一些特殊的数据变量，如静态的数据变量；


- 常量池(constant pool)：虚拟机必须为每个被装在的类型维护一个常量池，常量池就是这个类所用的常量的一个有序集合，包括直接常量（基本类型、string）和对其他类型、字段和方法的符号引用。

栈中的变量作用域结束后，这部分空间就会马上被用作新的控件进行分配。

堆中的对象作用域结束后，这部分内存不会马上被回收，而是等待系统GC来回收，堆内存的大小随着手机的不断发展而变大，可以通过以下代码回去手机堆内存的大小，所谓的内存分析就是对堆内存中的内存状态进行分析。

```Java
ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
int heapSize = am.getLargeMemoryClass();
```

1.3、内存优化实例
(1)Bitmap优化

- 使用适当分辨率和大小的图片，通过Options对象图片进行压缩。


- 及时回收内存：从Android 3.0开始，Bitmap被放置到了堆中，其内存由GC管理，所以不用手动调用bitmap.recycle()方法进行释放了；


- 使用图片缓存：设计内存缓存(LruCache)和磁盘缓存(DiskLruCache)可以更好地利用Bitmap。

(2)代码优化

- 对常量使用static修饰


- 使用静态方法，它比普通方法会提高15%左右的访问速度；
- 减少不必要的成员变量，这点Android lint工具已经集成检验啦。


- 尽量不要使用枚举（枚举逻辑更加清晰，相比静态常量效率更低），少用迭代器。


- 对Cursor、Receiver、Sensor、File等对象，要非常注意对它们的创建、回收与注册、解注册；
- 避免使用IOC框架，IOC框架使用注解反射来实现。Java反射效率已经有了很好的优化，但大量使用依然会使效率下降。
- 使用RenderScript，OpenGl来进行复杂的绘图操作。


- 使用SurfaceView来替代view进行大量的、频繁的绘图操作；


- 尽量使用视图缓存，而不是每次都执行inflate方法解析视图。

3.其他的辅助工具
(1)、Lint工具：代码提示工具，可以用来发现代码中隐藏的一些问题。

(2)、Memory Monitor工具：内存监视工具。突然走高可能发生内存泄漏，内存减少可能系统在GC。

(3)、TraceView工具：可视化性能调查工具，它用来分析TraceView日志

(4)、MAT工具：内存分析工具

(5)dumpsys命令：该命令可以列出android系统相关的信息和服务状态，可使用的配置参数很多，常见的有：
`activity`：显示所有Activity栈的信息；
`meminfo`：显示内存信息；
`battery`：显示电池信息；
`package`：显示包信息；
3.其他的辅助工具
(1)Lint工具：代码提示工具，可以用来发现代码中隐藏的一些问题
(2)Memory Monitor工具：内存监视工具
(3)TraceView工具：可视化性能调查工具，它用来分析TraceView日志
(4)MAT工具：内存分析工具
(5)dumpsys命令：该命令可以列出android系统相关的信息和服务状态，可使用的配置参数很多，常见的有：
`activity`：显示所有Activity栈的信息；
`meminfo`：显示内存信息；
`battery`：显示电池信息；
`package`：显示包信息；
`wifi`：显示wifi信息；
`alarm`：显示alarm信息；
`procstats`：显示内存状态

#### 第十一章 云端服务器

1.1、移动后端云：Backend as a service 即baas。

移动开发者不用在考虑如何租服务器、如何设计数据库、搭建服务器等，只要需调用api接口，就可以实现网络功能。后端云服务器一般包括数据存储、消息推送、文件服务、API分析、应用统计、移动官网。

#### 第十二章 Material Design

2014Google 携带 Android5.x重装归来。最为核心的就是Material  Design。

材料的形态模拟是Materila Design的设计核心思想所在。通过模拟自然界纸墨的变化、光线和阴影、纸与纸之间的空间层次关系，带来一种真实感。

Material  Design 大量引入了各种新的动画效果，让整个设计风格更加自然、和谐 。各种全新的转场动画指引用户的视觉焦点，使用户达到视觉的连贯性。

1.1、Palette

调色板,获取bitmap中的颜色信息，用以造成空指针，使用palette.getXXXColor(int defaltColot)获取颜色信息会更好。

```Java
//获取builder对象，又一个建造者模式
Palette.Builder builder = Palette.from(bitmap);
builder.generate(new Palette.PaletteAsyncListener() {
    @Override public void onGenerated(Palette palette) {
        //充满活力
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        int rgb = 0xff000000;
        if (vibrantSwatch != null) {
            rgb = vibrantSwatch.getRgb();
            setStatueBarColor(rgb);
            return;
        }
        //充满活力的亮
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        if (lightVibrantSwatch != null) {
            rgb = lightVibrantSwatch.getRgb();
            setStatueBarColor(rgb);
            return;
        }
        //充满活力的黑
        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        if (darkVibrantSwatch != null) {
            rgb = darkVibrantSwatch.getRgb();
            setStatueBarColor(rgb);
            return;
        }
        //柔和的
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        if (mutedSwatch != null) {
            rgb = mutedSwatch.getRgb();
            setStatueBarColor(rgb);
            return;
        }
        //柔和的亮
        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
        if (lightMutedSwatch != null) {
            rgb = lightMutedSwatch.getRgb();
            setStatueBarColor(rgb);
            return;
        }
        //柔和的黑
        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
        if (darkMutedSwatch != null) {
            rgb = darkMutedSwatch.getRgb();
            setStatueBarColor(rgb);
        }
    }
});
```

1.2、视图与阴影。

Google给View增加了一个全新的属性 z ，表示View在 z 方向上的高度，有以下公式来描述。

```
z = elevation + translationZ;
```

1.3、tint与clipping

1.4列表与卡片

主要介绍RecyclerView和CardView的简单使用。

1.5、Activity Transition

Android5.0提供了三种Transition类型

- 进入：决定Acitivity中的所有视图如何进入屏幕。
- 推出：决定Activity中的所有视图如何退出屏幕。
- 共享元素：决定两个Activity之间的多度，如何共享它们之间的视图。

其中进入和推出效果包括：

- explode：分解效果，Activity的进入或退出，从屏幕的的中间移动视图。
- slide：滑动效果，Activity的进入或退出，从屏幕的的边缘移动视图。
- fade：淡入淡出效果，通过改变Activity视图的不透明度达到添加或移除视图。

共享元素包括

- changeBounds：改变视图的边界。
- changeClipBounds：剪切视图的边界。
- changeTransform：改变目标视图的缩放比例和旋转角度。
- changeImageTransform：改变目标图片的大小和旋转角度。

只能兼容5.0以上，推荐使用AcitivityOptionsCompat下的几种转场动画。然后大部分Rom已经屏蔽了Activity的转场动画，设置并没有什么卵用。

```Java
//开启打开Activity的转场动画
startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//在打开一个Activity中设置
getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
int flag = getIntent().getExtras().getInt("flag");
// 设置不同的动画效果
switch (flag) {
    case 0:
        getWindow().setEnterTransition(new Explode());
        break;
    case 1:
        getWindow().setEnterTransition(new Slide());
        break;
    case 2:
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());
        break;
    case 3:
        break;
}
```

1.6、Material Design的动画效果。

- Ripple效果：5.0以上默认有ripple效果，可以通过一下方式设置不同的效果。但是不向下兼容。Github有兼容库，在任何的控件上都可以实现Ripple的点击效果。

  ```Java
  //设置有边界
  android:background="?android:attr/selectableItemBackground"
  //设置无边界 
  android:background="?android:attr/selectableItemBackgroundBorderless"
  //xml ripple效果
  <ripple xmlns:android="http://schemas.android.com/apk/res/android"
          android:color="@color/colorPrimary">
  </ripple>
  ```


- Circuler Reveal：具体表现为一个View以一个圆形的方式展开。

  ```java
  Animator animator = ViewAnimationUtils.createCircularReveal(
  		view,    //动画作用的View
  		view.getWidth()/2,   //开始圆心X
          view.getHeight()/2,	 //开始圆心Y
          0,                  //开始时半径的大小  
    		view.getWidth()/2); //结束时半径的大小
  animator.setDuration(1000);
  animator.start();
  ```


- 视图状态变化动画：

  - StateListAnim可以根据view的状态改变呈现不同的动画效果，通过XML构建不同的状态集合。

    ```Java
    //创建选择器动画
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:state_pressed="true">
            <set>
                <objectAnimator android:duration="500"
                                android:propertyName="rotationX"
                                android:valueTo="360"
                                android:valueType="floatType"/>
            </set>
        </item>
        <item android:state_pressed="false">
            <set>
                <objectAnimator android:duration="500"
                                android:propertyName="rotationX"
                                android:valueTo="0"
                                android:valueType="floatType"/>
            </set>
        </item>
    </selector>
    //给View设置动画
    <Button
        android:id="@+id/state_list_animation"
        android:stateListAnimator="@drawable/anim_change"
        android:layout_width="100dp"
        android:layout_height="100dp"/>

    //也通过代码的形式设置动画
    StateListAnimator animator = AnimatorInflater.loadStateListAnimator
          	(mContext,R.drawable.anim_change);
    mImageView.setStateListAnimator(animator);
    ```

  - animated-selector：动画状态选择器。具体表现为一个View的点击又多张图片组合而成的帧动画，典型的就是系统的chect-box。

#### 第十三章 实例提高





## 本笔记部分来自[hujiaweibujidao这位大神写的笔记](http://hujiaweibujidao.github.io/)













