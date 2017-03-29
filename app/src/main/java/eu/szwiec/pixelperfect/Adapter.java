package eu.szwiec.pixelperfect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_DRINK = 0;
    public static final int ITEM_TYPE_SECTION = 1;
    public static final int ITEM_TYPE_HEADER = 2;

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public SectionViewHolder(View view){
            super(view);
            text = (TextView) view.findViewById(R.id.text);
        }

        public void setText(String text) {
            this.text.setText(text);
        }
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, score, alert;
        public ImageView star, image;

        public DrinkViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            score = (TextView) view.findViewById(R.id.score);
            star = (ImageView) view.findViewById(R.id.star);
            image = (ImageView) view.findViewById(R.id.image);
            alert = (TextView) view.findViewById(R.id.alert);
        }

        public void bindData(Drink drink) {
            title.setText(drink.getTitle());
            score.setText(drink.getScore());
            description.setText(drink.getDescription());
            star.setEnabled(drink.isOrderFree());
            image.setImageDrawable(drink.getImage());
            alert.setEnabled(drink.isOrderFree());

            if(drink.isOrderFree()) {
                alert.setText("Order for free!");
            } else {
                alert.setText("You need 40 points");
            }
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public HeaderViewHolder(View view){
            super(view);
            text = (TextView) view.findViewById(R.id.text);
        }

        public void setText(String text) {
            this.text.setText(text);
        }
    }

    private List<Object> items;


    public Adapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_DRINK) {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_drink, null);
            return new DrinkViewHolder(normalView);
        } else if (viewType == ITEM_TYPE_SECTION) {
            View headerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_section, null);
            return new SectionViewHolder(headerRow);
        } else if (viewType == ITEM_TYPE_HEADER) {
            View headerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header, null);
            return new HeaderViewHolder(headerRow);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);

        if (itemType == ITEM_TYPE_DRINK) {
            ((DrinkViewHolder)holder).bindData((Drink) items.get(position));
        } else if (itemType == ITEM_TYPE_SECTION) {
            ((SectionViewHolder)holder).setText((String)items.get(position));
        } else if (itemType == ITEM_TYPE_HEADER) {
            ((HeaderViewHolder)holder).setText((String)items.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof String) {
            return ITEM_TYPE_SECTION;
        } else {
            return ITEM_TYPE_DRINK;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}