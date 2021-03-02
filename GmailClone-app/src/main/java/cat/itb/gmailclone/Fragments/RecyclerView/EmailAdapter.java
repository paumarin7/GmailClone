package cat.itb.gmailclone.Fragments.RecyclerView;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.itb.gmailclone.Model.Email;
import cat.itb.gmailclone.R;


public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> implements View.OnClickListener {

    List<Email> emails = new ArrayList<>();

    public EmailAdapter(List<Email> emails ){
        this.emails = emails;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_basic_item,parent,false);

        v.setOnClickListener(this);

        return new EmailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        holder.bindData(emails.get(position));
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

    }

    @Override
    public void onClick(View v) {

    }



    class EmailViewHolder extends RecyclerView.ViewHolder{
        ImageView imageItem;
        TextView titleItem;
        TextView descriptionItem;
        TextView dateItem;
        CheckBox starItem;


        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            titleItem = itemView.findViewById(R.id.titleItem);
            descriptionItem = itemView.findViewById(R.id.descriptionItem);
            dateItem = itemView.findViewById(R.id.dateItem);
            starItem = itemView.findViewById(R.id.starItem);

        }

        public void bindData(final Email email){
            imageItem.setImageAlpha(email.getImage());
            titleItem.setText(email.getTitle());
            descriptionItem.setText(email.getDescription());
            dateItem.setText(email.getDate().toString());
            starItem.setEnabled(email.getStar());

        }
    }

}
