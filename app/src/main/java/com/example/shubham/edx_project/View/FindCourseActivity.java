package com.example.shubham.edx_project.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
import com.example.shubham.edx_project.Utility.AppContext;
import com.example.shubham.edx_project.Utility.NetworkStateChecker;

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
    private ProgressDialog mProgressDialog;
    //id for page number of courses
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        id = 1;//default value for page of course
        initViews();
        //showing the progress dialog
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Downloading Course List");
        mProgressDialog.show();
        loadCourseList(id);
    }
    /**
     * for showing views and initialising views
     */
    private void initViews() {
        //for moving in courses
        mNextButton = (Button) findViewById(R.id.next_button);
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        //calling the listeners for button to perform the desired actions
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        //recyclerView Finding and ResultList initialising
        mResultList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.course_list);
        mRecyclerView.setHasFixedSize(true);
        //for recycler view management using
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mCourseAdapter = new CourseAdapter(mResultList, this);
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
                Log.d("coursePage", ":" + id);
        //calling the api
        if (NetworkStateChecker.isConnected()) {
            Call<CourseApiModel> courseApiModelCall = edxRequestInterface.getCourse(id);
            courseApiModelCall.enqueue(new Callback<CourseApiModel>() {
                @Override
                public void onResponse(Call<CourseApiModel> call, final Response<CourseApiModel> response) {
                    Log.d("Data", "" + response);
                    mCourseApiModel = response.body();
                    mResultList.clear();
                    mResultList.addAll(mCourseApiModel.getResults());
                    mCourseAdapter.notifyDataSetChanged();
                    mProgressDialog.dismiss();
                }
                @Override
                public void onFailure(Call<CourseApiModel> call, Throwable t) {
                    Log.d("Exception", t.getMessage());
                    t.printStackTrace();
                    mProgressDialog.dismiss();
                }
            });
        }
        else
        {
            Toast.makeText(AppContext.getAppContext(),"Network Connection Not Available",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_button:
            {
                int length = mCourseApiModel.getPagination().getNext().length();
                String nextUrl = mCourseApiModel.getPagination().getNext().substring(length - 1);
                id = Integer.parseInt(nextUrl);
                loadCourseList(id);
                Log.d("URl Course Id", nextUrl);
                break;
            }
            case R.id.previous_button:
            {
                String previousUrl = mCourseApiModel.getPagination().getPrevious();
                int nextUrlLength = mCourseApiModel.getPagination().getNext().length();
                String end = mCourseApiModel.getPagination().getNext().substring(nextUrlLength - 1);
                if (previousUrl == null) {
                    Toast.makeText(AppContext.getAppContext()," "+ R.string.first_page, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int previousUrlLength = mCourseApiModel.getPagination().getPrevious().length();
                    String previousURL = previousUrl.substring(previousUrlLength - 1);
                    id=end.equals("3")?1:Integer.parseInt(previousURL);
                    loadCourseList(id);
                    Log.d("URl Course Id",""+id);
                }
            }
            break;
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
        try
        {
            FavoriteListDB favoriteListDB = new FavoriteListDB(this);
            SQLiteDatabase mDatabase = favoriteListDB.getReadableDatabase();
            Cursor cursor = favoriteListDB.getDataFromCourseTable(mDatabase);
            int cursorLength = cursor.getCount();
            if (cursorLength == 0) {
                Toast.makeText(AppContext.getAppContext(), R.string.no_course_added, Toast.LENGTH_SHORT).show();
            } else {
                //showing favoriteListActivity
                launchIntent(FavoriteListActivity.class);
            }
        }
        catch (SQLiteException | CursorIndexOutOfBoundsException | NullPointerException e)
        {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * method for launching activity
     * @param iActivityClass instance of passed class name
     */
    private  void launchIntent(Class iActivityClass){
        //opening FavoriteListActivity
        Intent intent=new Intent(AppContext.getAppContext(),iActivityClass);
        startActivity(intent);
    }
}