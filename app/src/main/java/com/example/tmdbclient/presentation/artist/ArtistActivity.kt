package com.example.tmdbclient.presentation.artist

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
import com.example.tmdbclient.databinding.ActivityArtistBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private lateinit var  binding: ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_artist)
        (application as Injector).createArtistSubComponent()
            .inject(this)
        artistViewModel = ViewModelProvider(this,factory)[ArtistViewModel::class.java]
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
                updateArtist()
                true
            }else-> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView(){
        binding.artistsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.artistsRecyclerView.adapter = adapter
        displayArtist()

    }
    private fun displayArtist(){
        binding.artistsProgressBar.visibility = View.VISIBLE
        val responseLiveData= artistViewModel.getArtist()
        responseLiveData.observe(this, Observer {

            Log.e("MOVIE ACTIVITY", it.toString() )
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistsProgressBar.visibility = View.GONE

            }else{
                binding.artistsProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun updateArtist(){
        binding.artistsProgressBar.visibility = View.VISIBLE
        val responseLiveData= artistViewModel.updateArtist()
        responseLiveData.observe(this, Observer {

            Log.e("MOVIE ACTIVITY", it.toString() )
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistsProgressBar.visibility = View.GONE

            }else{
                binding.artistsProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }

        })
    }
}