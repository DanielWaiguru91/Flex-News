package tech.danielwaiguru.flexnews.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite_news.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.adapters.FavoriteNewsAdapter
import tech.danielwaiguru.flexnews.models.Article
import tech.danielwaiguru.flexnews.ui.viewmodels.FavoriteNewsViewModel
import timber.log.Timber

@AndroidEntryPoint
class FavoriteNewsFragment : Fragment(), FavoriteNewsAdapter.ArticleClickListener {
    private val favoriteNewsViewModel: FavoriteNewsViewModel by viewModels()
    private val favoriteNewsAdapter: FavoriteNewsAdapter by lazy { FavoriteNewsAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeToLiveData()
    }
    private fun subscribeToLiveData() {
        favoriteNewsViewModel.favArticles.observe(viewLifecycleOwner, {
            Timber.d(it.toString())
            favoriteNewsAdapter.setData(it)
        })
    }
    private fun setupRecyclerView() = favoriteNewsRecyclerView.apply {
        adapter = favoriteNewsAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onArticleItemClicked(article: Article) {
        val action = FavoriteNewsFragmentDirections
            .actionFavoriteNewsFragmentToArticleFragment(article)
        findNavController().navigate(action)
    }
}
