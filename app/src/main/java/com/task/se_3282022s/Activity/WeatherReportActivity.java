package com.task.se_3282022s.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.task.se_3282022s.weather.APIService;
import com.task.se_3282022s.Helper.HelperClass;
import com.task.se_3282022s.R;
import com.task.se_3282022s.weather.WeatherModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * The type Weather report activity.
 */
public class WeatherReportActivity extends AppCompatActivity {

    private TextView mainTextView, descriptionTextView, tempTextView, humidityTextView,
            minTextView, maxTextView, speedTextView, nameTextView, countryTextView, sunriseTextView, sunsetTextView;
    private ImageView imageView;

    private EditText cityEditText;

    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);
        getSupportActionBar().setTitle("Weather");

        mainTextView = findViewById(R.id.main);
        descriptionTextView = findViewById(R.id.description);
        tempTextView = findViewById(R.id.temp);
        humidityTextView = findViewById(R.id.humidity);
        minTextView = findViewById(R.id.min);
        maxTextView = findViewById(R.id.max);
        speedTextView = findViewById(R.id.speed);
        nameTextView = findViewById(R.id.name);
        countryTextView = findViewById(R.id.country);
        sunriseTextView = findViewById(R.id.sunrise);
        sunsetTextView = findViewById(R.id.sunset);

        imageView = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);
        ImageView image4 = findViewById(R.id.image4);
        ImageView image5 = findViewById(R.id.image5);
        ImageView image6 = findViewById(R.id.image6);
        ImageView image7 = findViewById(R.id.image7);

        image2.setImageResource(R.drawable.humidity);
        image3.setImageResource(R.drawable.temperature);
        image4.setImageResource(R.drawable.wind);
        image5.setImageResource(R.drawable.location);
        image6.setImageResource(R.drawable.sunrise);
        image7.setImageResource(R.drawable.sunset);
        getAPIData("Berlin");


        cityEditText = findViewById(R.id.et_city);
        searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(view -> {
            if (cityEditText.getText().toString().trim().isEmpty()) {
                HelperClass.warning(getApplicationContext(), "City name cannot be null");
                return;
            }
            getAPIData(cityEditText.getText().toString().trim());
        });
    }


    private void getAPIData(String city) {
        if (HelperClass.isNetworkAvailable(getApplicationContext())) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.client_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIService service = retrofit.create(APIService.class);

            Call<WeatherModel> listCall = service.getWeather(city,
                    "metric", getResources().getString(R.string.api_key));
            listCall.enqueue(new Callback<WeatherModel>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                    if (response.isSuccessful()) {
                        WeatherModel weatherList = response.body();

                        for (int z = 0; z < weatherList.getWeather().size(); z++) {
                            mainTextView.setText(String.valueOf(weatherList.getWeather().get(z).getMain()));
                            descriptionTextView.setText(String.valueOf(weatherList.getWeather().get(z).getDescription()));
                            tempTextView.setText(weatherList.getMain().getTemp()
                                    + getUnit(getApplication().getResources().getConfiguration().locale.getCountry()));
                            humidityTextView.setText(weatherList.getMain().getHumidity() + " per cent");
                            minTextView.setText(weatherList.getMain().getTempMin() + " min");
                            maxTextView.setText(weatherList.getMain().getTempMax() + " max");
                            speedTextView.setText(weatherList.getWind().getSpeed() + " miles/hour");
                            nameTextView.setText(String.valueOf(weatherList.getName()));
                            countryTextView.setText(String.valueOf(weatherList.getSys().getCountry()));
                            sunriseTextView.setText(unixTime(Long.parseLong(String.valueOf(weatherList.getSys().getSunrise()))));
                            sunsetTextView.setText(unixTime(Long.parseLong(String.valueOf(weatherList.getSys().getSunset()))));

                            switch (weatherList.getWeather().get(z).getIcon()) {
                                case "01d":
                                    imageView.setImageResource(R.drawable.sunny);
                                    break;
                                case "02d":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "03d":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "04d":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "04n":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "10d":
                                    imageView.setImageResource(R.drawable.rain);
                                    break;
                                case "11d":
                                    imageView.setImageResource(R.drawable.storm);
                                    break;
                                case "13d":
                                    imageView.setImageResource(R.drawable.snowflake);
                                    break;
                                case "01n":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "02n":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "03n":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "10n":
                                    imageView.setImageResource(R.drawable.cloud);
                                    break;
                                case "11n":
                                    imageView.setImageResource(R.drawable.rain);
                                    break;
                                case "13n":
                                    imageView.setImageResource(R.drawable.snowflake);
                                    break;

                            }
                        }
                    } else {
                        int sc = response.code();
                        switch (sc) {
                            case 400:
                                Toast.makeText(getApplicationContext(), "Bad request", Toast.LENGTH_SHORT).show();
                                break;
                            case 404:
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Generic error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<WeatherModel> call, Throwable t) {
                    t.getMessage();
                }
            });
        }
    }


    private String unixTime(long timex) {

        Date date = new Date(timex * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    private String getUnit(String value) {
        String val = "°C";

        if ("US".equals(value) || "LR".equals(value)) {
            val = "°F";
        }

        return val;
    }

}