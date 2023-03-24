package com.example.tmdbclient.presentation.TvShow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: ActivityTvShowBinding
    private lateinit var adapter: TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tv_show)
        (application as Injector).createTvShowSubComponent()
            .inject(this)
        tvShowViewModel = ViewModelProvider(this,factory)[TvShowViewModel::class.java]
        initRecyclerView()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update->{
                updateTvShow()
                true
            }else-> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView(){
        binding.tvShowRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter()
        binding.tvShowRecyclerView.adapter = adapter
        displayTvShow()

    }
    private fun displayTvShow(){
        binding.tvProgressBar.visibility = View.VISIBLE
        val responseLiveData= tvShowViewModel.getTvShow()
        responseLiveData.observe(this, Observer {

            Log.e("MOVIE ACTIVITY", it.toString() )
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility = View.GONE

            }else{
                binding.tvProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun updateTvShow(){
        binding.tvProgressBar.visibility = View.VISIBLE
        val responseLiveData= tvShowViewModel.updateTvShow()
        responseLiveData.observe(this, Observer {

            Log.e("MOVIE ACTIVITY", it.toString() )
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility = View.GONE

            }else{
                binding.tvProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }

        })
    }
}