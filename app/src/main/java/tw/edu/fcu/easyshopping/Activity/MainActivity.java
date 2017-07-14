package tw.edu.fcu.easyshopping.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import tw.edu.fcu.easyshopping.R;

public class MainActivity extends AppCompatActivity {
    private Fragment mCurrentFrgment;
    int currentIndex;
    private ArrayList<Fragment> fragmentArrayList;
    private List<String> titles;
    private TabLayout mTabLayout;

    final Integer[] ICONS1 = new Integer[]{
            R.drawable.home,
            R.drawable.search,
            R.drawable.camera,
            R.drawable.message,
            R.drawable.profile
    };

    final Integer[] ICONS2 = new Integer[]{
            R.drawable.home_clicked,
            R.drawable.search_clicked,
            R.drawable.camera_clicked,
            R.drawable.message_clicked,
            R.drawable.profile_clicked
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
    }

    //初始化每個view
    public void initView(){
        titles = new ArrayList<>();//因為imageview不能比較，所以就用textview的方法來比較
        titles.add("One");
        titles.add("Two");
        titles.add("Three");
        titles.add("Four");
        titles.add("Five");

        mTabLayout = (TabLayout)findViewById(R.id.tabs);
        setupTabIcons();
        initTabLayoutEvent();
    }

    //初始化tab
    private void setupTabIcons() {
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(0)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(1)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(2)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(3)));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(4)));
    }

    //自定義tab
    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_main_tab, null);//自定義layout設定tab（主頁，搜尋，拍照，留言板，個人主頁）裡面的設計
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(titles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        Picasso.with(this).load(ICONS1[position]).into(img_title);

        if (position == 0) {
            Picasso.with(this).load(ICONS2[position]).into(img_title);
        } else {
            Picasso.with(this).load(ICONS1[position]).into(img_title);
        }
        return view;
    }

    //設定滑動另一頁後的事件
    private void initTabLayoutEvent() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    //選中以後的圖，設白色底
    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();//使用自定義view
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);

        if (txt_title.getText().toString().equals("One")) {
            Picasso.with(this).load(ICONS2[0]).into(img_title);
            changeTab(0);
        } else if (txt_title.getText().toString().equals("Two")) {
            Picasso.with(this).load(ICONS2[1]).into(img_title);
            changeTab(1);
        } else if (txt_title.getText().toString().equals("Three")){
            Picasso.with(this).load(ICONS2[2]).into(img_title);
            changeTab(2);
        }else if (txt_title.getText().toString().equals("Four")){
            Picasso.with(this).load(ICONS2[3]).into(img_title);
            changeTab(3);
        }else {
            Picasso.with(this).load(ICONS2[4]).into(img_title);
            changeTab(4);
        }
    }

    //沒被選中的圖，設黑色底
    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();//使用自定義view
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);

        if (txt_title.getText().toString().equals("One")) {
            Picasso.with(this).load(ICONS1[0]).into(img_title);
        } else if (txt_title.getText().toString().equals("Two")) {
            Picasso.with(this).load(ICONS1[1]).into(img_title);
        }else if (txt_title.getText().toString().equals("Three")) {
            Picasso.with(this).load(ICONS1[2]).into(img_title);
        }else if (txt_title.getText().toString().equals("Four")) {
            Picasso.with(this).load(ICONS1[3]).into(img_title);
        }else {
            Picasso.with(this).load(ICONS1[4]).into(img_title);
        }
    }

    //初始化每個頁面的fragment（主頁，搜尋，拍照，留言板，個人主頁）
    private void initFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new HomeFragment());//主頁
        fragmentArrayList.add(new SearchFragment());//搜尋
        fragmentArrayList.add(new CameraFragment());//拍照
        fragmentArrayList.add(new MessageFragment());//留言板
        fragmentArrayList.add(new ProfileFragment());//個人主頁

        changeTab(0);
    }

    public void changeTab(int index) {
        currentIndex = index;

        FragmentManager ft = getFragmentManager();
        android.app.FragmentTransaction fragTran = ft.beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (mCurrentFrgment != null) {
            fragTran.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        Fragment fragment = ft.findFragmentByTag(fragmentArrayList.get(currentIndex).getClass().getName());

        if (fragment == null) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = (Fragment) fragmentArrayList.get(index);
        }
        mCurrentFrgment = fragment;

        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            fragTran.add(R.id.classification_fragment, fragment, fragment.getClass().getName());
        } else {
            fragTran.show(fragment);
        }
        fragTran.commit();
    }
}
