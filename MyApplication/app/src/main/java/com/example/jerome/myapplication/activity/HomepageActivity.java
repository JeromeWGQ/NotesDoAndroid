package com.example.jerome.myapplication.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.jerome.myapplication.R;
import com.example.jerome.myapplication.fragment.NoteFragment;
import com.example.jerome.myapplication.fragment.RemindFragment;
import com.example.jerome.myapplication.fragment.SettingFragment;
import com.example.jerome.myapplication.fragment.SortFragment;
import com.example.jerome.myapplication.fragment.dummy.DummyContent;
import com.example.localalbum.ui.DynamicPost;

public class HomepageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NoteFragment.OnListFragmentInteractionListener {

    private final int SelectedNote = 0;
    private final int SelectedSort = 1;
    private final int SelectedRemind = 2;
    private final int SelectedSetting = 3;

    private int currentSel;

    private FrameLayout contentFrame;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocalAlbum();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        currentSel = SelectedNote;
        contentFrame = (FrameLayout) findViewById(R.id.fragment_content);

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        toolbar.setTitle("全部笔记");
        ft.replace(R.id.fragment_content, new NoteFragment());
        ft.commit();
    }

    private void showLocalAlbum() {
        Intent intent = new Intent(this, DynamicPost.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_note) {
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            toolbar.setTitle("全部笔记");
            ft.replace(R.id.fragment_content, new NoteFragment());
            ft.commit();
        } else if (id == R.id.nav_sort) {
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            toolbar.setTitle("分类");
            ft.replace(R.id.fragment_content, new SortFragment());
            ft.commit();
        } else if (id == R.id.nav_remind) {
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            toolbar.setTitle("事项");
            ft.replace(R.id.fragment_content, new RemindFragment());
            ft.commit();
        } else if (id == R.id.nav_setting) {
            fm = getFragmentManager();
            ft = fm.beginTransaction();
            toolbar.setTitle("设置");
            ft.replace(R.id.fragment_content, new SettingFragment());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
    }
}
