package com.example.rxjavaretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.CovidVO

class CovidViewAdapter: RecyclerView.Adapter<CovidViewAdapter.CovidHolder?>() {

    private val covidList: ArrayList<CovidVO?> = arrayListOf()

    fun setList(covidList: ArrayList<CovidVO?>){
//        notifyDataSetChanged()
        this.covidList.addAll(covidList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_api,parent, false)
        return CovidHolder(view)
    }

    override fun onBindViewHolder(holder: CovidHolder, position: Int) {
        holder.txtCountry.text = covidList[position]?.countryName ?: "null"
        holder.txtNewCase.text = "신규확진자 수 : ${covidList[position]?.newCase}" ?: "null"
        holder.txtTotalCase.text = "확진자 수 : ${covidList[position]?.totalCase}"  ?: "null"
        holder.txtRecovered.text = "완치자 수 : ${covidList[position]?.recovered}" ?: "null"
        holder.txtDeath.text = "사망자 수 : ${covidList[position]?.death}" ?: "null"
        holder.txtPercent.text = "발생률 : ${covidList[position]?.percentage}%" ?: "null"
        holder.txtNewFCase.text = "전일대비증감-해외유입 : ${covidList[position]?.newFcase}"?: "null"
        holder.txtNewCCase.text = "전입대비증감-지역발생 : ${covidList[position]?.newCcase}" ?: "null"
    }

    override fun getItemCount(): Int = covidList.size

    class CovidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCountry: TextView = itemView.findViewById(R.id.tv_country)
        var txtRecovered: TextView = itemView.findViewById(R.id.tv_recovered)
        var txtNewCase: TextView = itemView.findViewById(R.id.tv_new)
        var txtTotalCase: TextView = itemView.findViewById(R.id.tv_total)
        var txtDeath: TextView = itemView.findViewById(R.id.tv_death)
        var txtPercent: TextView = itemView.findViewById(R.id.tv_percent)
        var txtNewFCase: TextView = itemView.findViewById(R.id.tv_newf)
        var txtNewCCase: TextView = itemView.findViewById(R.id.tv_newc)
    }
}