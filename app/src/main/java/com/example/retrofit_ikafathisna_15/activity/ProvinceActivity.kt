package com.example.retrofit_ikafathisna_15.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_ikafathisna_15.adapter.ProvinceAdapter
import com.example.retrofit_ikafathisna_15.api.RetrofitClient
import com.example.retrofit_ikafathisna_15.databinding.ActivityProvinceBinding
import com.example.retrofit_ikafathisna_15.model.ProvinceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProvinceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProvince()
    }

    private fun showProvince() {
        binding.rvProvince.setHasFixedSize(true)
        binding.rvProvince.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getProvince().enqueue(object : Callback<ArrayList<ProvinceResponse>> {

            override fun onResponse(
                call: Call<ArrayList<ProvinceResponse>>,
                response: Response<ArrayList<ProvinceResponse>>
            ) {
                val list = response.body()
                val adapter = list?.let { ProvinceAdapter(it) }
                binding.rvProvince.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ProvinceResponse>>, t: Throwable) {
                Toast.makeText(this@ProvinceActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}