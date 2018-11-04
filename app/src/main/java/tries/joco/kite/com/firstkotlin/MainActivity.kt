package tries.joco.kite.com.firstkotlin

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var notesBox: Box<Note>
    private lateinit var partnerBox: Box<Partner>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showToast("Próba")
        notesBox = ObjectBox.boxStore.boxFor(Note::class.java)
        partnerBox = ObjectBox.boxStore.boxFor(Partner::class.java)
        btnSave.setOnClickListener{dbadd()}
        btnList.setOnClickListener{listofNotes()}
        btnDel.setOnClickListener{notesBox.removeAll()}
        btnPsDownload.setOnClickListener{psDownload()}
        btnPsList.setOnClickListener{psList()}
        btnSearchPsKod.setOnClickListener { onePsList() }
        btnCursor.setOnClickListener { startActivity<CursorActivity>()}
        btnRecPs.setOnClickListener { startActivity<RecycActivity>() }
    }

    private fun onePsList() {
        val partnerKodList = partnerBox.query().equal(Partner_.partnerKod,etPsKod.text.toString()).build().find()
        for (partner in partnerKodList) {
            Log.i("LOG","Talált ps: ${partner.partnerKod} nev: ${partner.partnerNev} ")
        }
    }

    private fun psDownload() {
        var psdownloadProgress = ProgressDialog(this)
        psdownloadProgress.setMessage("Partner letöltés")
        psdownloadProgress.setCancelable(false)
        psdownloadProgress.show()
        var success = true
            doAsync {
            val partnerletolto = PartnerService.create()
                try {
                val partnerList = partnerletolto.partnerList().execute().body()
                partnerBox.put(partnerList)
                } catch (ex: Exception) {
                    psdownloadProgress.dismiss()
                    success = false
                }
                uiThread {
                        psdownloadProgress.dismiss()
                    if (!success)
                        toast("Nincs kapcsolat a szerverrel")
                }
            }

           // psdownloadProgress.dismiss()
           // toast("Nincs kapcsolat a szerverrel")

    }

    private fun psList() {
        val partnerek = partnerBox.all
        for (partner in partnerek) Log.i("LOG",partner.partnerKod)
    }

    private fun listofNotes() {
        val notes = notesBox.all
        for (note in notes)  Log.i("LOG", note.toString())

        val partnerek = partnerBox.all
        for (partner in partnerek) Log.i("LOG",partner.toString())
    }

    private fun dbadd() {

        val note1 = Note()
        note1.text="Note1"
        note1.subtext="Sub1"
        val note2 = Note()
        note2.text="Note2"

        notesBox.put(note1,note2)

/*        val ps1 = Partner()
        ps1.partnerKod="000001"

        val ps2 = Partner()
        ps2.partnerKod="000001"
        ps2.partnerIrsz="4251"

        partnerBox.put(ps1)

        try {
            Log.i("LOG","Save ps")
           // partnerBox.put(ps2)
        } catch (ex : UniqueViolationException) {
        /*    Log.i("LOG","exception")
            val pskodquery  = partnerBox.query().equal(Partner_.partnerKod,ps2.partnerKod).build()
            var psold = pskodquery.findFirst()
            psold?.partnerIrsz=ps2?.partnerIrsz
            partnerBox.put(psold)*/
        }*/

    }

    fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
        // valami
    }


}

