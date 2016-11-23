package com.example.shubham.edx_project.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shubham.edx_project.Adapter.CourseAdapter;
import com.example.shubham.edx_project.EdXModel.CourseModel.CourseApiModel;
import com.example.shubham.edx_project.EdXModel.CourseModel.Result;
import com.example.shubham.edx_project.EdxServerInstance.EdxRequestInterface;
import com.example.shubham.edx_project.EdxServerInstance.ServiceGenerator;
import com.example.shubham.edx_project.FavoriteListDatabase.FavoriteListDB;
import com.example.shubham.edx_project.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * showing courseList
 *
 * Created by shubham on 17/11/16.
 */
public class FindCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private CourseAdapter mCourseAdapter;
    private List<Result> mResultList;
    private CourseApiModel mCourseApiModel;

    private Button mNextButton;
    private Button mPreviousButton;

    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        id = 1;

        initViews();
        loadCourseList(id);

    }

    /**
     * for showing views and initialsing views
     */
    private void initViews() {
        //for moving courses
        mNextButton = (Button) findViewById(R.id.next_button);
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        //listeners for button
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        //recyclerView Finding and ResultList initialsing
        mResultList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.course_list);
        mRecyclerView.setHasFixedSize(true);

        //for recycler view management using
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mCourseAdapter = new CourseAdapter(mResultList, FindCourseActivity.this, getLayoutInflater());
        //setting adapter
        mRecyclerView.setAdapter(mCourseAdapter);

    }

    /**
     * for loading data and calling api
     *
     * @param id :id for page url
     */
    private void loadCourseList(int id) {
        EdxRequestInterface edxRequestInterface = ServiceGenerator.getRetrofitInstance().create(EdxRequestInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.download_data));
        progressDialog.show();

        Log.d("coursePage", "" + id);

        Call<CourseApiModel> courseApiModelCall = edxRequestInterface.getCourse(id);
        courseApiModelCall.enqueue(new Callback<CourseApiModel>() {
            @Override
            public void onResponse(Call<CourseApiModel> call, final Response<CourseApiModel> response) {

                Log.d("Data", "" + response);

                mCourseApiModel = response.body();
                mResultList.clear();
                mResultList.addAll(mCourseApiModel.getResults());

                mCourseAdapter.notifyDataSetChanged();

                Log.d("Data", "" + response);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CourseApiModel> call, Throwable t) {

                Log.d("Exception", t.getMessage());
                progressDialog.dismiss();

            }
        });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button:
                int length = mCourseApiModel.getPagination().getNext().length();
                String nextUrl = mCourseApiModel.getPagination().getNext().substring(length - 1);
                int courseId = Integer.parseInt(nextUrl);
                loadCourseList(courseId);
                Log.d("URl Length", nextUrl);
                break;

            case R.id.previous_button: {
                String previousUrl = mCourseApiModel.getPagination().getPrevious();

                int nextUrlLength = mCourseApiModel.getPagination().getNext().length();
                String end = mCourseApiModel.getPagination().getNext().substring(nextUrlLength - 1);

                Log.d("end", end);

                if (previousUrl == null) {
                    Toast.makeText(getApplicationContext(), R.string.first_page, Toast.LENGTH_SHORT).show();

                } else {
                    if (end.equals("3")) {
                        loadCourseList(1);
                    } else {
                        int previousUrlLength = mCourseApiModel.getPagination().getPrevious().length();
                        String previousURL = mCourseApiModel.getPagination().getPrevious().substring(previousUrlLength - 1);
                        int courseid = Integer.parseInt(previousURL);
                        loadCourseList(courseid);
                    }

                }

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.button, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FavoriteListDB favoriteListDB = new FavoriteListDB(this);
        SQLiteDatabase mDatabase = favoriteListDB.getReadableDatabase();
        Cursor cursor = favoriteListDB.getDataFromCourseTable(mDatabase);

        int cursorLength = cursor.getCount();
        if (cursorLength == 0) {
            Toast.makeText(this, R.string.no_course_added, Toast.LENGTH_SHORT).show();
        } else {
            //showing favoriteListActivity
            Intent favoriteListIntent = new Intent(this, FavoriteListActivity.class);
            startActivity(favoriteListIntent);
        }

        return true;
    }
}
