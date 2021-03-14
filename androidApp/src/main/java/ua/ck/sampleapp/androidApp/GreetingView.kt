package ua.ck.sampleapp.androidApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import dev.chrisbanes.accompanist.coil.CoilImage
import oolong.Dispatch
import ua.ck.sampleapp.shared.network.entities.GithubUserResponse
import ua.ck.sampleapp.shared.store.user.UserFetchResult
import ua.ck.sampleapp.shared.store.user.UserStore

@Composable
fun GreetingView(props: UserStore.Props, dispatch: Dispatch<UserStore.Msg>) {
    when (val result = props.data) {
        is UserFetchResult.Loading -> LoadingView()
        is UserFetchResult.Content -> UserDetailsView(result.data)
        is UserFetchResult.Error -> ErrorLoading(message = result.message)
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorLoading(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}

@Composable
fun UserDetailsView(user: GithubUserResponse) {
    LazyColumn(content = {
        item { User(user = user) }
        item { ua.ck.sampleapp.androidApp.Divider() }
        item { UserInfo(user = user) }
        item { ua.ck.sampleapp.androidApp.Divider() }
        item { UserRepoGist(user = user) }
    })
}

@Composable
fun User(user: GithubUserResponse?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 24.dp, end = 18.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            data = user?.avatar.orEmpty(),
            contentDescription = "Profile image",
            modifier = Modifier.size(64.dp),
            fadeIn = true,
            requestBuilder = { transformations(CircleCropTransformation()) },
            error = {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.Blue)
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            Text(
                text = user?.name.orEmpty(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user?.login.orEmpty(),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun UserInfo(user: GithubUserResponse?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
    ) {
        Text(text = user?.location.orEmpty())
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = user?.followers.toString(),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "followers")
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = user?.following.toString(),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "following")
        }
    }
}

@Composable
fun UserRepoGist(user: GithubUserResponse?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color(0xFFEFF1F8),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 12.dp, top = 18.dp, bottom = 18.dp, end = 12.dp)
                    .background(color = Color(0xFFEFF1F8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Repositories:")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user?.publicRepos.toString())

                Row(modifier = Modifier.wrapContentSize()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .size(12.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color(0xFFEFF1F8),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 18.dp, bottom = 18.dp, end = 12.dp)
                    .background(color = Color(0xFFEFF1F8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Gists:")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user?.publicGists.toString())

                Row(modifier = Modifier.wrapContentSize()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Divider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 12.dp),
        color = Color(0xFFEFF1F8),
        thickness = 1.dp
    )
}