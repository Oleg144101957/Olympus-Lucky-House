package tiv.edo.cyberobics.domain.repository

interface AppsRepository {
    suspend fun provideAppsflyer(): MutableMap<String, Any>?

}