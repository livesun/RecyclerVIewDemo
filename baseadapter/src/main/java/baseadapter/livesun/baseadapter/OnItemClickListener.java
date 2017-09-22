package baseadapter.livesun.baseadapter;

/**
 * 类描述：条目点击事件
 * 创建人：livesun
 * 创建时间：2017/8/25
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */

public interface OnItemClickListener<T> {

    void onItemClick(T item, int position);
}
