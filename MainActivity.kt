package com.example.myapplication

import android.content.Context
import android.os.AsyncTask.execute
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import layout.DownloadJson


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
DownloadJson.execute("https://feeds.citibikenyc.com/stations/stations.json")

        val list:ListView=findViewById(R.id.list)


        list.adapter=MyAdapter(this)
       // list.adapter = MyAdapter(this,dataModelList)
     //  list.setAdapter(adapter)
    }

}

class  MyAdapter(
     mcontext: Context
 ) :BaseAdapter() {
 val  mcontext:Context

init {
    this.mcontext = mcontext
}
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
val inflater:LayoutInflater= LayoutInflater.from(mcontext)
        val row=inflater.inflate(R.layout.texts,parent,false)
val text1=row.findViewById<TextView>(R.id.text1)

        val myModelList:MyModelList =Data().list[position]
        Log.d("fffff","$myModelList")
 text1.text= myModelList.i.toString()
        Log.d("ff222222","$myModelList.i")
        return row
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
return position.toLong()
    }

    override fun getCount(): Int {
return Data().list.size
    }





}




