package leapfrog_inc.putipro.Fragment.Offer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class OfferDetailFragment extends BaseFragment {

    private String mCategoryId = "";
    private String mDescription = "";
    private String mFee = "";

    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private int mSelectedHour;
    private int mSelectedMinute;

    public void setInfo(String categoryId, String description, String fee) {
        mCategoryId = categoryId;
        mDescription = description;
        mFee = fee;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_detail, null);

        initAction(view);

        Calendar calendar = Calendar.getInstance();
        mSelectedYear = calendar.get(Calendar.YEAR);
        mSelectedMonth = calendar.get(Calendar.MONTH);
        mSelectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        mSelectedHour = calendar.get(Calendar.HOUR_OF_DAY);
        mSelectedMinute = calendar.get(Calendar.MINUTE);
        setSelectedDate();

        return view;
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.dateButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mSelectedYear = year;
                        mSelectedMonth = month;
                        mSelectedDay = dayOfMonth;
                        setSelectedDate();
                    }
                }, mSelectedYear, mSelectedMonth, mSelectedDay);
                datePickerDialog.show();
            }
        });

        ((Button)view.findViewById(R.id.timeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mSelectedHour = hourOfDay;
                        mSelectedMinute = minute;
                        setSelectedDate();
                    }
                }, mSelectedHour, mSelectedMinute, true);
                timePickerDialog.show();
            }
        });

        ((Button)view.findViewById(R.id.nextButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTapNext();
            }
        });

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment(AnimationType.horizontal);
            }
        });
    }

    private void setSelectedDate() {

        View view = getView();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mSelectedYear);
        calendar.set(Calendar.MONTH, mSelectedMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mSelectedDay);
        calendar.set(Calendar.HOUR_OF_DAY, mSelectedHour);
        calendar.set(Calendar.MINUTE, mSelectedMinute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        ((Button)view.findViewById(R.id.dateButton)).setText(dateFormat.format(calendar.getTime()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        ((Button)view.findViewById(R.id.timeButton)).setText(timeFormat.format(calendar.getTime()));
    }

    private void onTapNext() {

        View view = getView();

        String name = ((EditText)view.findViewById(R.id.nameEditText)).getText().toString();
        String age = ((EditText)view.findViewById(R.id.ageEditText)).getText().toString();

        OfferCandidateFragment fragment = new OfferCandidateFragment();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mSelectedYear);
        calendar.set(Calendar.MONTH, mSelectedMonth);
        calendar.set(Calendar.DAY_OF_MONTH, mSelectedDay);
        calendar.set(Calendar.HOUR_OF_DAY, mSelectedHour);
        calendar.set(Calendar.MINUTE, mSelectedMinute);

        fragment.set(mCategoryId, mDescription, mFee, calendar, name, age);
        stackFragment(fragment, AnimationType.horizontal);
    }
}
