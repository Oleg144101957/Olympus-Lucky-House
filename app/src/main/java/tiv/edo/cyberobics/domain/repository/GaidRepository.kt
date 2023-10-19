package tiv.edo.cyberobics.domain.repository

interface GaidRepository {

    suspend fun provideGaid() : String

}