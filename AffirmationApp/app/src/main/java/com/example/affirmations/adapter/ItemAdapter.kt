package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

//The ItemAdapter needs information on how to resolve the string resources.
//This, and other information about the app, is stored in a Context object instance
// that you can pass into an ItemAdapter instance.
//In our case is MainActivity's context
//-----------------------------------------------
/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    //這是abstract class喔!
    /**
     * Modify the ItemAdapter class to extend from
     * the RecyclerView.Adapter class with the custom ViewHolder class.
     * (nested class ??)
     * */

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //takes 2 parameters and return a new ViewHolder
        //parent: which is the view group that the new list item view will be attached
        // to as a child.The parent is the RecyclerView.
        //-----------------------------------------------------
        //viewType : When there are multiple item view types in the same RecyclerView.
        //When you have different list item layouts displayed within the recyclerView,
        //there are different item view types.(每個view都有自己的layout，一個view都是不同的type)
        //-----------------------------------------------------------------
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        //obtain an instance of LayoutInflater
        //It knows how to inflate an XML layout into a hierarchy of view objects.
        //--------------------
        //Third argument:The third boolean argument is attachToRoot. This argument needs to be false,
        // because RecyclerView adds this item to the view hierarchy for you when it's time.
        //-----------------------------------------------------------
        //Now adapterLayout holds a reference to the list item view
        return ItemViewHolder(adapterLayout)
        //return a new ItemViewHolder instance where the root view is adapterLayout.

    }
    /**
     * Replace the contents of a list item view
     * (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //two parameters,
        // an ItemViewHolder previously created by the onCreateViewHolder() method
        // and an int that represents the current item position in the list.(就是index)
        val item = dataset[position]
        //先取得item，then update the item data

        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        //stringResourceId 和 imageResourceId都是Affirmation class的參數，在創建時就會傳入
        /**
        * holder就是onCreateViewHolder新建的holder(single item view)
        * and holder has one property:textView, and that's the single item we're looking for.
        */
        //context comes from adapter class.
        //resource 就是Resources class
        //getString()是 Resources class的method之一
        //可以取得該傳入id的item的string value

    }
    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
    //Defining a class inside another class is called creating a nested class.
    //Since ItemViewHolder is only used by ItemAdapter, creating it inside ItemAdapter shows this relationship.
    // This is not mandatory, but it helps other developers understand the structure of your program.
}