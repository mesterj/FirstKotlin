package tries.joco.kite.com.firstkotlin

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory


class PartnerService {

    companion object {

        fun create(): PartnerAPI {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    //.addConverterFactory(
                   //         GsonConverterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("http://192.168.3.90/NyomtServ3-1.0/webresources/")
                    .build()

            return retrofit.create(PartnerAPI::class.java)
        }
    }
}