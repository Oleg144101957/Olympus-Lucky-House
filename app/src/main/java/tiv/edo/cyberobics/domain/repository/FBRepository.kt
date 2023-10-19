package tiv.edo.cyberobics.domain.repository

interface FBRepository {
    suspend fun provideFB() : String

}