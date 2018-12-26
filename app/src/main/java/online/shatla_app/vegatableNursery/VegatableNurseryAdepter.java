package online.shatla_app.vegatableNursery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import online.shatla_app.R;
import online.shatla_app.fruitNursery.FruitNurseryItem;

public class VegatableNurseryAdepter extends RecyclerView.Adapter<VegatableNurseryAdepter.MyViewHolder> {

    private ArrayList<VegatableNurseryItem> dataSet;

    private Context mcontext;
    private onAdpterItemClick onclick;
    private CardView cardView;

    public interface  onAdpterItemClick{
        void onItemclick(int postion);
    }

    public  void newmethod(onAdpterItemClick onclick){
        this.onclick=onclick ;
    }
    //generate a constecture (right click)
    public VegatableNurseryAdepter(ArrayList<VegatableNurseryItem> dataSet, Context context) {
        this.dataSet =dataSet ;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use it to atach alayout using adapter
        LayoutInflater layoutInflater =(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.activity_vegatable_nursery_adepter,parent,false);
        cardView=view.findViewById(R.id.card_view);

        return new MyViewHolder(view);
    }
    //http://square.github.io/picasso/
    //Volley Library    https://developer.android.com/training/volley/
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        VegatableNurseryItem items =dataSet.get(position);
        holder.textView1.setText(items.getName());
        holder.textView2.setText(" "+items.getPrice());
        holder.textView3.setText(""+items.getQuantity());
        Picasso.with(mcontext).load(items.getImage()).into(holder.circularImageView);
    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //component in txt_img layout
        TextView textView1,textView2,textView3;
        ImageView circularImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.name);
            textView2=itemView.findViewById(R.id.price);
            textView3=itemView.findViewById(R.id.quantity);
            circularImageView=itemView.findViewById(R.id.item_info);
        }
    }




}
