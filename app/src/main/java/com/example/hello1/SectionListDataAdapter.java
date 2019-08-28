package com.example.hello1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder singleItemRowHolder, int n) {

        SingleItemModel singleItemModel = (SingleItemModel)this.itemsList.get(n);
        singleItemRowHolder.tvTitle.setText((CharSequence)singleItemModel.getName());
        singleItemRowHolder.time.setText((CharSequence)singleItemModel.getTime());
        ((RequestBuilder)((RequestBuilder)((RequestBuilder)Glide.with((Context)this.mContext).load(singleItemModel.getBackground_url()).diskCacheStrategy(DiskCacheStrategy.ALL)).centerCrop()).error(2131165356)).into(singleItemRowHolder.itemImage);
        if (singleItemModel.isFree()) {
            singleItemRowHolder.lock.setImageResource(R.drawable.logo);
            return;
        }
        singleItemRowHolder.lock.setImageResource(R.drawable.logo);

    }

    @Override
    public int getItemCount() {
        ArrayList<SingleItemModel> arrayList = this.itemsList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public class SingleItemRowHolder
            extends RecyclerView.ViewHolder {
        protected ImageView itemImage;
        protected ImageView lock;
        protected TextView time;
        protected TextView tvTitle;

        public SingleItemRowHolder(View view) {
            super(view);
            this.tvTitle = (TextView)view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView)view.findViewById(R.id.itemImage);
            //this.lock = (ImageView)view.findViewById(2131230954);
            //this.time = (TextView)view.findViewById(2131230970);
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    Toast.makeText((Context)view.getContext(), (CharSequence)SingleItemRowHolder.this.tvTitle.getText(), (int)0).show();
                    //SectionListDataAdapter.this.mContext.startActivity(new Intent(SectionListDataAdapter.this.mContext, Video.class).setData(Uri.parse((String)((SingleItemModel)SectionListDataAdapter.this.itemsList.get(SingleItemRowHolder.this.getAdapterPosition())).getUrl())));
                }
            });
        }

    }
}
