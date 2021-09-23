package gst.trainingcourse.mocktest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.VehiclesViewHolder> {
    private List<Vehicles> listVehicles;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        this.listVehicles = new ArrayList<>();
    }

    @NonNull
    @Override
    public VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.vehicles_item,parent,false);
        return new VehiclesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiclesViewHolder holder, int position) {
        Vehicles o = listVehicles.get(position);
        holder.tvCardId.setText("Id: " + o.getId());
        holder.tvCardName.setText("Tên phương tiện: "+o.getName());
        holder.tvCardType.setText("Loại phương tiện: "+o.getType());
        holder.tvCardPrice.setText("Giá sản phẩm: "+o.getPrice()+"$");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditActivity.class);
                Vehicles o = listVehicles.get(position);
                i.putExtra("vehicles",o);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVehicles.size();
    }

    public void setListVehicles(List<Vehicles> list){
        this.listVehicles = list;
    }

    public class VehiclesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCardId, tvCardName, tvCardType, tvCardPrice;
        public VehiclesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardId = itemView.findViewById(R.id.txtid);
            tvCardName = itemView.findViewById(R.id.txtName);
            tvCardType = itemView.findViewById(R.id.txtType);
            tvCardPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}


