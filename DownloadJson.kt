package layout

import android.os.AsyncTask
import android.util.Log
import com.example.myapplication.Data
import com.example.myapplication.MyModelList
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class DownloadJson :AsyncTask<String,Void,String>() {

    override fun doInBackground(vararg url: String?): String {
        var json:String=""
        val url= URL(url[0])
        var connection: HttpURLConnection = url . openConnection () as HttpURLConnection
          connection.connect()
        try{

                json =
                    connection.inputStream.use { it.reader().use { reader -> reader.readText() } }

            Log.v("Downloaded json ","$json")
        }
        finally {
            connection.disconnect()
        }

        return json
    }

    override fun onPostExecute(result: String?) {
        val MyModelList = arrayListOf<MyModelList>()
      val jsonObj:JSONObject= JSONObject(result)
        for(i in 0..jsonObj.length()-1){
            val jsonArray: JSONArray =jsonObj.getJSONArray("stationBeanList")
            for (j in 0..jsonArray.length()-1){
              val obj:  JSONObject =jsonArray.getJSONObject(j)
            val   model: MyModelList  =MyModelList((obj.get("id") as Int),(obj.get("stationName") as String),
                (obj.get("lastCommunicationTime") as String),(obj.get("availableDocks") as Int),(obj.get("latitude") as Double),
                ( obj.get("longitude") as Double))

                MyModelList.add(model)
                Data().fff(MyModelList)

             //   MyAdapter(mcontext,MyModelList)
//                val bundle = Bundle()
//                bundle.putString("value", MyModelList.toString())
//                val intent= Intent(mcontext, MainActivity::class.java)
//                intent.putExtras(bundle)
//                startActivity(intent)
            }

        }
Log.d("iddddd","$MyModelList")
    }

}

