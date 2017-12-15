package com.recycleviewdemo.adapter;

/**
 * Created by Administrator on 2017/12/15.
 */

public class GroupContentAdapter /*extends RecyclerView.Adapter<RecyclerView.ViewHolder>*/ {

//    private static final int HEADER = 10000;
//    private static final int TYPE_HEADER = 1;
//    private static final int TYPE_CONTENT = 2;
//    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<>();//头部
//
//    //合并的情况
//    private Context context;
//    private List<ClassBean> datas;
//    private HashMap<Integer, Integer> headerIndex = new HashMap<>();
//
//    public GroupContentAdapter(Context context, List<ClassBean> datas) {
//        this.context = context;
//        this.datas = datas;
//    }
//
//
//    /**
//     * 是否为头布局
//     *
//     * @param position
//     * @return
//     */
//    protected boolean isHeaderView(int position) {
//        return headerIndex.containsValue(new Integer(position));
//
//    }
//
//
//    /**
//     * 获取头部的数量
//     *
//     * @return
//     */
//    private int getHeaderCount() {
//        return datas.size();
//    }
//
//
//    /**
//     * 获取item的数量
//     *
//     * @return
//     */
//
//    private int getContentCount() {
//        int itemCount = 0;
//        int studentSize = 0;
//        for (int i = 0; i < datas.size(); i++) {
//            if (i != 0) {
//                itemCount++;
//            }
//            headerIndex.put(i, new Integer(itemCount));
//            itemCount += getStudentOfClass(i);
//            studentSize += getStudentOfClass(i);
//        }
//        return studentSize;
//
//    }
//
//    /**
//     * 添加头部布局
//     *
//     * @param view
//     */
//    public void addHeaderView(View view) {
//        headerViews.put(headerViews.size() + HEADER, view);
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        if (viewType == TYPE_HEADER) {//头部布局
//            return new HeaderViewHolder(View.inflate(context, R.layout.group_item, null));
//
//        }
//        //内容布局
//        return new ContentViewHolder(View.inflate(context, R.layout.group_item, null));
//
//
//    }
//
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (isHeaderView(position)) {
//            ((HeaderViewHolder) holder).textView.setText(datas.get(getHeadRealCount(position)).getClassName());
//            return;
//        }
//
//
//        ((ContentViewHolder)holder).textView1.setText(
//                datas.get(getStudentOfClass(position))       //获取指定的班级
//                        .getClassStudents()                          //该班级下所有学生
//                        .get(position - headerIndex.get(getStudentOfClass(position)) - 1));    //具体的学生
//
//
//    }
//
//
//    /**
//     * 根据position获取当前是第几个班级
//     * @param position
//     * @return
//     */
//    private int getHeadRealCount(int position){
//        int result = 0;
//        Set<Map.Entry<Integer, Integer>> set = headerIndex.entrySet();
//        for(Map.Entry<Integer, Integer> entry : set){
//            if(entry.getValue().equals(position)){
//                result = entry.getKey();
//                break;
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 根据学生的position获取所属的班级
//     *
//     * @return
//     */
//    private int getStudentOfClass(int position) {
//
//        int tempSize = 0;
//
//        for (int i = 0; i < datas.size(); i++) {
//            tempSize += datas.get(i).getClassStudents().size() + 1;
//
//            if (position <= tempSize) {
//                return i;
//            }
//        }
//        return 0;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (isHeaderView(position)) {
//            return TYPE_HEADER;
//        } else {
//            return TYPE_CONTENT;
//        }
//    }
//
//    /**
//     * 为了动态的为不同的position设置布局
//     *
//     * @param recyclerView
//     */
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
//            GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {//getSpanSize方法，返回值就表示当前item占多少列
//                    int viewType = getItemViewType(position);
//                    if ((headerViews.get(viewType) != null)) {
//                        return ((GridLayoutManager) gridLayoutManager).getSpanCount();
//                    }
//                    return 1;
//                }
//            });
//        }
//
//    }
//
//    /**
//     * 即滑动离开了当前窗口界面就会被调用
//     *
//     * @param holder
//     */
//    @Override
//    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
//        int position = holder.getLayoutPosition();
//        if (isHeaderView(position)) {
//            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
//                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) params;
//                layoutParams.setFullSpan(true);
//            }
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return getHeaderCount() + getContentCount();
//    }
//
//    class HeaderViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView textView;
//
//        public HeaderViewHolder(View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.text3);
//        }
//    }
//
//
//    class ContentViewHolder extends RecyclerView.ViewHolder {
//        TextView textView1;
//
//        public ContentViewHolder(View itemView) {
//            super(itemView);
//            textView1 = itemView.findViewById(R.id.text3);
//        }
//    }
}
