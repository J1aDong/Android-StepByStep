package com.j1adong.androidstepbystep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.j1adong.androidstepbystep.httpservice.WeatherService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by J1aDong on 2016/11/17.
 */

public class RetrofitRxjavaActivity extends BaseActivity
{

	@BindView(R.id.tv_weather)
	TextView mTvWeather;
	private String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_retrofit_rxjava);
		ButterKnife.bind(this);

		// 初始化okHttp
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(interceptor).retryOnConnectionFailure(true)
				.connectTimeout(15, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://apis.baidu.com/apistore/").client(client)
				.addConverterFactory(ScalarsConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();

		WeatherService service = retrofit.create(WeatherService.class);

		service.getWeather("d0b8f3d75bfbb9aced61d8e47cf7c463", "杭州")
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(s -> mTvWeather
						.setText(s), Throwable::printStackTrace);
	}
}
