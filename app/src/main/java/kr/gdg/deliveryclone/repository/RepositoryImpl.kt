package kr.gdg.deliveryclone.repository

class RepositoryImpl : Repository{

    val firebaseRepo : Repository by lazy {
        FirebaseRepository()
    }

    var result = 0

    override fun addCount(count: Int) {
        result = count + 1

        firebaseRepo.addCount(result);

    }

    override fun getCount() : Int {
        return result
    }
}