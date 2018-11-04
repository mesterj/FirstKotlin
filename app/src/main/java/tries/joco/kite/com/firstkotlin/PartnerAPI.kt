package tries.joco.kite.com.firstkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PartnerAPI {


    @GET("entities.partner")
    fun partnerList() : Call<List<Partner>>

    //modifiedTime alapú szinkron frissítés a Partner táblán
    @GET("com.joco.nyomtserv3.sajatservices/partnerekupdatebytime/{d}")
    fun getPartnerByModiDate(@Path("d") d: Long?): Call<List<Partner>>

}