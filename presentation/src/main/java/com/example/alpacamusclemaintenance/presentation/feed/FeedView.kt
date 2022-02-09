package com.example.alpacamusclemaintenance.presentation.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.alpacamusclemaintenance.domain.feed.Feed
import com.example.alpacamusclemaintenance.presentation.R
import com.example.alpacamusclemaintenance.presentation.linkToWebView
import org.apache.commons.lang3.time.DateFormatUtils

@ExperimentalCoilApi
@Composable
fun FeedView(
    navController: NavController,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val query = stringResource(R.string.qiita_tag_muscle_maintenance)
    val lazyFeedItems: LazyPagingItems<Feed> = viewModel.fetchFeed(query).collectAsLazyPagingItems()

    LazyColumn {
        items(items = lazyFeedItems) { feed ->
            if (feed != null) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navController.navigate(linkToWebView(feed.url)) }
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
                            val postedAt = DateFormatUtils
                                .format(feed.createdAt, "yyyy/MM/dd HH:mm")
                            Text(
                                // TODO: Move this to strings.xml
                                text = "${feed.user.id} が $postedAt に投稿",
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

                Divider()
            }
        }

        lazyFeedItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .height(56.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(32.dp))
                        }
                    }
                }
            }
        }
    }
}
