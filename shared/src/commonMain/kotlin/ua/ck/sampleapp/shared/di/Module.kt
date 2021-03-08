package ua.ck.sampleapp.shared.di

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.kodein.di.*
import ua.ck.sampleapp.shared.utils.logger.HttpLogger
import ua.ck.sampleapp.shared.data.repository.UserRepository
import ua.ck.sampleapp.shared.network.api.UserApi

private const val NETWORK_MODULE_NAME = "Network Module"
private const val API_MODULE_NAME = "Api Module"
private const val REPOSITORY_MODULE_NAME = "Repository Module"

internal val networkModule = DI.Module(NETWORK_MODULE_NAME) {
    bind<HttpClient>() with singleton {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = HttpLogger()
                level = LogLevel.ALL
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                }
                host = "api.github.com"
            }
        }
    }
}

internal val apiModule = DI.Module(API_MODULE_NAME) {
    bind<UserApi>() with singleton { UserApi(instance()) }
}

internal val repositoryModule = DI.Module(REPOSITORY_MODULE_NAME) {
    bind<UserRepository>() with provider { UserRepository() }
}