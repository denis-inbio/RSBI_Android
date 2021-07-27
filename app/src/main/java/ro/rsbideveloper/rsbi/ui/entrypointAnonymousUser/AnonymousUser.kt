package ro.rsbideveloper.rsbi.ui.entrypointAnonymousUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ro.rsbideveloper.rsbi.databinding.ArticleEventListBinding

// <TODO> change blue action button in keyboard (~ Imm ?)

class AnonymousUser : AppCompatActivity() {
    private lateinit var binding: ArticleEventListBinding
    private lateinit var viewModel: AnonymousUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArticleEventListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AnonymousUserViewModel::class.java)
        viewModel.liveState1.observe(this, Observer {
            binding.ArticleEventListRootScrollViewLinearLayoutTv1.text = it
        })
        viewModel.liveState2.observe(this, Observer {
            binding.ArticleEventListRootScrollViewLinearLayoutTv2.text = it.toString()
        })
        viewModel.liveState3.observe(this, Observer {
            binding.ArticleEventListRootScrollViewLinearLayoutTv3.text = it.toString()
        })


        binding.ArticleEventListRootScrollViewLinearLayoutBtn1.setOnClickListener {
            viewModel.changeState1()
        }
        binding.ArticleEventListRootScrollViewLinearLayoutBtn2.setOnClickListener {
            viewModel.changeState2()
        }
        binding.ArticleEventListRootScrollViewLinearLayoutBtn3.setOnClickListener {
            viewModel.changeState3()
        }
        

        SyncData()
        InitiateArticlesList()
    }

    private fun SyncData() {

    }

    private fun InitiateArticlesList() {

    }

}