package tries.joco.kite.com.firstkotlin

import java.util.Date

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Unique

@Entity
class Partner (
    @Id
    var id: Long = 0,

    @Unique()
    var partnerKod: String? = " ",
    var partnerNev: String? = " ",
    var partnerTelepules: String? = " ",
    var partnerIrsz: String? = " ",
    var partnerCim: String? = " ",
    var partnerAdoszam: String? = " ",
    var partnerEmail: String? = " ",
    var status: String? = " ",
    var modifiedTime: Date? = Date() ,
    var baj: String? = " " ,
    var partnerAdoazonosito: String? = " "
)
