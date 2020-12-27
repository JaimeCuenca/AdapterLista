package com.example.adapterlista

import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class StringAdapter(var listaString : MutableList<String>) : RecyclerView.Adapter<StringAdapter.StringViewHolder>()  {

    class StringViewHolder(var root: View, var textView: TextView, var chkBx: CheckBox, var item: LinearLayout) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val twTextView = view.findViewById<TextView>(R.id.textView)
        val chkBx = view.findViewById<CheckBox>(R.id.checkBox)
        val itemLayout = view.findViewById<LinearLayout>(R.id.item)
        return StringViewHolder(view, twTextView, chkBx, itemLayout)
    }

    override fun getItemCount(): Int {
        return listaString.size + 1
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {

        var contEncendidos=0

        if ( 0<position && position<itemCount-2){
            holder.textView.text=listaString[position]
            holder.chkBx.isEnabled = true
            holder.chkBx.isVisible = true

            if (holder.chkBx.isChecked) {
                holder.item.setBackgroundColor(holder.item.context.getColor(R.color.green))
                contEncendidos++
            }else {
                holder.item.setBackgroundColor(holder.item.context.getColor(R.color.red))
                contEncendidos--
            }

            holder.chkBx.setOnCheckedChangeListener{ _: CompoundButton, _: Boolean ->
                if (holder.chkBx.isChecked) {
                    holder.item.setBackgroundColor(holder.item.context.getColor(R.color.green))
                    contEncendidos++
                }else {
                    holder.item.setBackgroundColor(holder.item.context.getColor(R.color.red))
                    contEncendidos--
                }
            }

            return
        }else {
            if (position == 0) {
                holder.textView.text = "Borrar"
                holder.chkBx.isEnabled=false
                holder.chkBx.isVisible=false
                holder.item.setBackgroundColor(holder.item.context.getColor(R.color.white))
                holder.root.setOnClickListener {
                    if (listaString.size>1) {
                        val toast = Toast.makeText(it.context, "Borrando...", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                        val pcBorrado = Random.nextInt(1, itemCount - 2)
                        listaString.removeAt(pcBorrado)
                        notifyDataSetChanged()
                    }
                }
                return
            } else {
                if (position == itemCount-1){
                    holder.textView.text = "Insertar"
                    holder.chkBx.isEnabled=false
                    holder.chkBx.isVisible=false
                    holder.item.setBackgroundColor(holder.item.context.getColor(R.color.white))
                    holder.root.setOnClickListener {
                        val toast = Toast.makeText(it.context, "Insertando...", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                        var nom: Int
                        do {
                             nom = Random.nextInt(0,itemCount*10)
                        }while(listaString.contains("PC - $nom"))
                        listaString.add("PC - $nom")
                        notifyDataSetChanged()
                    }
                    return
                }else {
                    holder.textView.text = "PCs activos"
                    holder.chkBx.isEnabled=false
                    holder.chkBx.isVisible=false
                    holder.item.setBackgroundColor(holder.item.context.getColor(R.color.white))
                    holder.root.setOnClickListener {
                        val toast = Toast.makeText(it.context, "Hay ${contEncendidos} PCs activos", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                    return
                }
            }
        }
    }
}

