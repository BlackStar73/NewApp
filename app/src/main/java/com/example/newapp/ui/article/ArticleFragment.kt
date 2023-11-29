package com.example.newapp.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newapp.R
import com.example.newapp.data.model.Article
import com.example.newapp.data.model.Result
import com.example.newapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val article = arguments?.getSerializable("article") as? Result

            if (article?.posterPath != null) {
                Glide.with(requireContext()).load("https://image.tmdb.org/t/p/original" + article.posterPath).centerCrop()
                    .placeholder(R.drawable.logo_transformed).into(image)
            } else {
                image.setImageResource(R.drawable.logo_transformed)
            }

            movieName.text = article?.title
            overview.text = article?.overview
            date.text = article?.releaseDate?.substring(0, 10) ?: "Unknown"
            language.text = article?.originalLanguage
        }
    }
}