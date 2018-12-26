package online.shatla_app.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import online.shatla_app.R;

public class AdapterHomeFragment extends RecyclerView.Adapter<AdapterHomeFragment.MyViewHolder> {


    private ArrayList<SupportItems> dataSet;
    private Context mContext;
    private CardView cardView;



    private OnAdapterItemClick onClick;

    public interface OnAdapterItemClick{
        void onItemClick(int position);
    }
    public void newMethod(OnAdapterItemClick onClick){
        this.onClick=onClick;
    }

    public AdapterHomeFragment(ArrayList<SupportItems> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.activity_adapter_home_fragment,parent,false);
        cardView=view.findViewById(R.id.card_view);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        SupportItems items=dataSet.get(position);
        holder.textView.setText(items.getName());
        holder.circleImageView.setImageResource(items.getImageView());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CircleImageView circleImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.name);
            circleImageView=itemView.findViewById(R.id.item_info);
        }
    }

}
