package baseadapter.livesun.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：RecyclerView的Adapter基类
 * 创建人：livesun
 * 创建时间：2017/8/25
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private List<T> mDats=new ArrayList<>();
    private int mLayoutId;
    private LayoutInflater mInflater;
    private OnItemViewSupport mItemSupport;
    private OnItemClickListener<T> clickListener;

    //正常布局
    public BaseAdapter(Context context, int layoutId){
        mContext=context;
        mLayoutId= layoutId;
        mInflater = LayoutInflater.from(context);
    }

    public void addDatas(List<T> datas){
        mDats.addAll(datas);
    }

    public void setDatas(List<T> datas){
        mDats=datas;
    }

    //多布局调用这个
    public BaseAdapter(Context context, OnItemViewSupport<T> itemSupport){
        this(context ,0);
        mItemSupport=itemSupport;
    }

    //dia

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mItemSupport!=null){
            mLayoutId=viewType;
        }
        return new ViewHolder(mInflater.inflate(mLayoutId,parent,false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final T item = mDats.get(position);
        bindData(holder, item);
        if(clickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(item,position);
                }
            });

        }
    }

    protected abstract void bindData(ViewHolder holder, T item);


    @Override
    public int getItemCount() {
        return mDats.size();
    }



    @Override
    public int getItemViewType(int position) {
        //处理多布局
        if (mItemSupport!=null){
            return mItemSupport.getSupportItemView(mDats.get(position));
        }
        return super.getItemViewType(position);
    }

    public void setOnItemClickListener(OnItemClickListener<T> clickListener){

        this.clickListener = clickListener;
    }

}
