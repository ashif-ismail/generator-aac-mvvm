package a.view.ui.home;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.firebase.crash.FirebaseCrash;
import java.io.FileNotFoundException;
import javax.inject.Inject;
import a.R;
import a.databinding.FragmentHomeBinding;
import a.di.components.Injectable;
import a.util.AppConstants.Status;
import a.util.DialogUtils;
import a.view.adapter.ContentAdapter;
import timber.log.Timber;

public class HomeFragment extends LifecycleFragment implements Injectable {

  String TAG = "logger";
  @Inject
  ViewModelProvider.Factory mViewModelFactory;
  @Inject
  DialogUtils mDialogUtils;
  private FragmentHomeBinding mBinding;


  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
    initViews();
    return mBinding.getRoot();
  }

  private void initViews() {
    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    mBinding.listContent.setLayoutManager(llm);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    Timber.d("inside activity created");
    HomeViewModel homeViewModel = ViewModelProviders.of(this, mViewModelFactory)
        .get(HomeViewModel.class);
    observeViewModel(homeViewModel);
    //faking a crash to firebase
    FirebaseCrash.report(new NullPointerException());
    FirebaseCrash.report(new ArithmeticException());
    FirebaseCrash.report(new ArrayIndexOutOfBoundsException());
    FirebaseCrash.report(new FileNotFoundException("Boom !!"));
  }

  private void observeViewModel(HomeViewModel homeViewModel) {
    mDialogUtils.showProgress(getContext(), getResources().getString(R.string.loading_message));
    homeViewModel.getContentListObservable().observe(this, content -> {
      if (content.data != null) {
        ContentAdapter contentAdapter = new ContentAdapter(content.data);
        mBinding.listContent.setAdapter(contentAdapter);
        mDialogUtils.hideProgress();
      }
      else if (content.status == Status.ERROR){
        Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT)
            .show();
        mDialogUtils.hideProgress();
      }
    });
  }
}
