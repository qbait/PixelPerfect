package eu.szwiec.pixelperfect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Item> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, score, alert;
        public ImageView star, image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            score = (TextView) view.findViewById(R.id.score);
            star = (ImageView) view.findViewById(R.id.star);
            image = (ImageView) view.findViewById(R.id.image);
            alert = (TextView) view.findViewById(R.id.alert);
        }
    }


    public MoviesAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.score.setText(item.getScore());
        holder.description.setText(item.getDescription());
        holder.star.setEnabled(item.isOrderFree());
        holder.image.setImageDrawable(item.getImage());
        holder.alert.setEnabled(item.isOrderFree());

        if(item.isOrderFree()) {
            holder.alert.setText("Order for free!");
        } else {
            holder.alert.setText("You need 40 points");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}