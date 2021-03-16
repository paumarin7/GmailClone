package cat.itb.gmailclone.Fragments.RecyclerView;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cat.itb.gmailclone.Model.Email;
import cat.itb.gmailclone.R;


public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    List<Email> emails = new ArrayList<>();

    public EmailAdapter(List<Email> emails) {
        this.emails = emails;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view, parent, false);

        v.setOnClickListener(this);

        return new EmailViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        holder.bindData(emails.get(position));
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }


    class EmailViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView originItem;
        TextView titleItem;
        TextView descriptionItem;
        TextView dateItem;
        CheckBox starItem;


        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            originItem = itemView.findViewById(R.id.originItem);
            titleItem = itemView.findViewById(R.id.titleItem);
            descriptionItem = itemView.findViewById(R.id.descriptionItem);
            dateItem = itemView.findViewById(R.id.dateItem);
            starItem = itemView.findViewById(R.id.starItem);

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bindData(final Email email) {
            imageItem.setImageAlpha(email.getImage());
            originItem.setText(email.getOrigin());
            titleItem.setText(email.getTitle());
            descriptionItem.setText(email.getDescription());

            Date mailDate = email.getDate();
            String date;
            SimpleDateFormat today = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat notToday = new SimpleDateFormat("dd MMM", Locale.getDefault());
            SimpleDateFormat compareDay = new SimpleDateFormat("yyyyMMdd",Locale.getDefault());
            if (compareDay.format(mailDate).equals(compareDay.format(Calendar.getInstance().getTime()))) {
                date = today.format(mailDate);

            } else {
                date = notToday.format(mailDate);
            }
            dateItem.setText(date);

            starItem.setEnabled(email.isStar());

            if (email.isRead()) {
                originItem.setTextColor(Color.GRAY);
                titleItem.setTextColor(Color.GRAY);
                dateItem.setTextColor(Color.GRAY);
            } else {
                originItem.setTextColor(Color.WHITE);
                titleItem.setTextColor(Color.WHITE);
                dateItem.setTextColor(Color.WHITE);
            }
            descriptionItem.setTextColor(Color.GRAY);
        }
    }

}
