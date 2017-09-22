package baseadapter.livesun.baseadapter;

/**
 * 类描述：RecyclerView 多条目支持接口
 * 创建人：livesun
 * 创建时间：2017/8/25
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public interface OnItemViewSupport<T> {

    int getSupportItemView(T item);
}
