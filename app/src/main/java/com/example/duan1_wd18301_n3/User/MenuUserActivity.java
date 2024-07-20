package com.example.duan1_wd18301_n3.User;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1_wd18301_n3.Activities.MainActivity;
import com.example.duan1_wd18301_n3.Adapters.MenuUserAdapter;
import com.example.duan1_wd18301_n3.DAO.CategoryDAO;
import com.example.duan1_wd18301_n3.Models.Category;
import com.example.duan1_wd18301_n3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MenuUserActivity extends AppCompatActivity {

    ListView lv;
   private BottomNavigationView bottomNavigationView;
    List<Category> categoryList;
    MenuUserAdapter menuUserAdapter;
    CategoryDAO categoryDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        lv = (ListView) findViewById(R.id.lv_user_category);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        //
        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

        imageList.add(new SlideModel(R.drawable.eredivisie3, "Đậm chất thể thao, bừng cá tính", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.eredivisie4, "Thể thao là sự cố gắng không ngừng nghỉ",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.eredivisie5, "Bùng cháy đam mê, thách thức rào cản",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.eredivisie6, "Tiếp nối ngọn lửa thể thao",ScaleTypes.CENTER_CROP));


        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);


         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.ac_trang_chu){
                    Intent intent = new Intent(bottomNavigationView.getContext(), MenuUserActivity.class);
                    startActivity(intent);
                }else if (id == R.id.ac_gio_hang){
                    Intent intent = new Intent(bottomNavigationView.getContext(), ShoppingCartActivity.class);
                    startActivity(intent);
                }else if (id == R.id.ac_hoa_don){
                    Intent intent = new Intent(bottomNavigationView.getContext(), BillStatisticUserActivity.class);
                    startActivity(intent);
                }else if (id == R.id.ac_dang_xuat){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });

        categoryList = new ArrayList<>();
        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), ProductListUserActivity.class);
                Category category = (Category) menuUserAdapter.getItem(i);
                intent.putExtra("category_id", category.getIdCategory());
                intent.putExtra("category_name", category.getNameCategory());
                startActivity(intent);
            }
        });
    }

    public void display() {
        categoryDAO = new CategoryDAO(this);
        categoryList = categoryDAO.getAllCategoriesWithIdCategory();
        menuUserAdapter = new MenuUserAdapter(this, R.layout.user_item_category, categoryList);
        lv.setAdapter(menuUserAdapter);
    }


}