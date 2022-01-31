package com.example.alpacamusclemaintenance.presentation.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.example.alpacamusclemaintenance.domain.feed.Feed
import com.example.alpacamusclemaintenance.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.time.DateFormatUtils

@AndroidEntryPoint
class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                Surface {
                    FeedList(
                        // Reference about the navigation:
                        //   https://developer.android.com/jetpack/compose/navigation#navigate-from-Compose
                        onNavigate = { feed ->
                            findNavController().navigate(
                                FeedFragmentDirections
                                    .actionFeedFragmentToWebViewFragment(url = feed.url)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FeedList(
    viewModel: FeedViewModel = viewModel(),
    onNavigate: (Feed) -> Unit
) {
    val query = stringResource(R.string.qiita_tag_muscle_maintenance)
    val lazyFeedItems: LazyPagingItems<Feed> = viewModel.fetchFeed(query).collectAsLazyPagingItems()

    LazyColumn {
        itemsIndexed(items = lazyFeedItems) { index, feed ->
            if (feed != null) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            // TODO: When coming back to this view from anywhere else, this LazyColumn
                            //   is initialized every time. Need to figure out how to persist state
                            //   between navigation.
                            onNavigate(feed)
                        }
                ) {
                    Image(
                        painter = rememberImagePainter(feed.user.profileImageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.height(40.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val postedAt = DateFormatUtils.format(feed.createdAt, "yyyy/MM/dd HH:mm")
                            Text(
                                // TODO: Move this to strings.xml
                                text = "${feed.user.id} が ${postedAt} に投稿",
                                fontSize = 12.sp
                            )

                            Spacer(Modifier.width(4.dp))
                            Row {
                                Icon(
                                    painter = painterResource(R.drawable.ic_thumb_up_gray_24dp),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.Gray
                                )

                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = feed.likesCount.toString(),
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Text(
                            text = feed.title,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }

                if (index < lazyFeedItems.itemCount - 1)
                    Divider()
            }
        }

        lazyFeedItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxWidth().height(56.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp).align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
