package github.dzc.apptemplate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.dzc.apptemplate.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String TITLE = "title";
    private static final String TAG = "tag";
    private static final String LOG_TAG = "BlankFragment";
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;

    private boolean isOnce = false;//true fragment一旦隐藏  下次要显示的话会重新执行onCreate方法

    public String getFTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // TODO: Rename and change types of parameters
    private String title;


    private OnFragmentInteractionListener mListener;
    private OnCurrentFragmentChangedListener onCurrentFragmentChangedListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    public boolean isOnce() {
        return isOnce;
    }

    public void setOnce(boolean once) {
        isOnce = once;
    }

    public static BlankFragment newInstance(String title, boolean isOnce) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        String mTag = fragment.hashCode() + "";
        args.putString(TAG, mTag);
        fragment.setTag(mTag);
        fragment.setTitle(title);
        fragment.setOnce(isOnce);
        fragment.setArguments(args);
        return fragment;
    }

    public static BlankFragment newInstance(String title) {
        return newInstance(title, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            tag = getArguments().getString(TAG);
        }
        if (savedInstanceState != null) {
            title = savedInstanceState.getString(TITLE, title);
        }
        Log.d(LOG_TAG, tag + "  onCreate   " + title);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TITLE, title);
        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        tv.setText(title);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("fragment2",false);
            }
        });
        tv2.setText("to fragment2");
        tv3.setText("to fragment3");
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("fragment3",true);
            }
        });
        Log.d(LOG_TAG, "onCreateView   " + title);

        return view;
    }

    public void onButtonPressed(String tag,boolean isOnce) {
        if (mListener != null) {
            mListener.onFragmentInteraction(tag,isOnce);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if (context instanceof OnCurrentFragmentChangedListener) {
            onCurrentFragmentChangedListener = (OnCurrentFragmentChangedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        onCurrentFragmentChangedListener = null;
        Log.d(LOG_TAG,title+"  onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Log.d(LOG_TAG,title+"  onDestroyView");
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String tag,boolean isOnce);
    }

    public interface OnCurrentFragmentChangedListener {
        void change(BlankFragment fragment);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(LOG_TAG, title + "   hidden=" + hidden);
        if (!hidden && onCurrentFragmentChangedListener != null) {
            onCurrentFragmentChangedListener.change(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,title+"  onDestroy");
    }


}
