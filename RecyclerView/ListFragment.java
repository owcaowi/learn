
public class ListFragment extends Fragment {

    private final static String SELECTED_ITEM = "SELECTED_ITEM";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinkedList<String> titles = getTitles();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MyAdapter(titles);
        mRecyclerView.setAdapter(mAdapter);

        /* Zaznaczanie ostatnio wybranego */
        if( savedInstanceState != null ) {
            int selectedPos = savedInstanceState.getInt(SELECTED_ITEM, RecyclerView.NO_POSITION);
            mAdapter.selectedPos = selectedPos;
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* Zaznaczanie ostatnio wybranego */
        outState.putInt( SELECTED_ITEM, mAdapter.selectedPos );
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        public final static int STANDARD_VIEW_TYPE = 0;
        public final static int SELECTED_VIEW_TYPE = 1;

        public int selectedPos = RecyclerView.NO_POSITION;
        private LinkedList<String> mTitles;

        public MyAdapter(LinkedList<String> titles) {
            mTitles = titles;
        }


        @Override
        public int getItemViewType(int position) {
            return (position == selectedPos)?MyAdapter.SELECTED_VIEW_TYPE:MyAdapter.SELECTED_VIEW_TYPE ;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch(viewType){
                case MyAdapter.SELECTED_VIEW_TYPE:
                    return new MySelectedHolder(layoutInflater, parent);

                case MyAdapter.STANDARD_VIEW_TYPE:
                    return new MyHolder(layoutInflater, parent);

                default:
                    return new MyHolder(layoutInflater, parent);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            String itemData = mTitles.get(position);
            int viewType = holder.getItemViewType();

            switch(viewType){
                case MyAdapter.SELECTED_VIEW_TYPE:
                    ((MySelectedHolder) holder).bind(itemData);
                    break;
                case MyAdapter.STANDARD_VIEW_TYPE:
                    ((MyHolder) holder).bind(itemData);
                    break;
            }

            holder.itemView.setSelected(selectedPos == position);
        }

        @Override
        public int getItemCount() {
            return mTitles.size();
        }



        /* Standard View Holder */
        public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

            public TextView mTitle;

            public MyHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.recycler_view_item, parent, false));

                itemView.setOnClickListener(this);
                mTitle = (TextView) itemView.findViewById(R.id.title);

            }

            public void bind(String data) {
                mTitle.setText(data);
            }

            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPos);
                selectedPos = getLayoutPosition();
                notifyItemChanged(selectedPos);
            }

        }

        /* Selected View Holder */
        public class MySelectedHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

            public TextView mTitle;

            public MySelectedHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.recycler_view_item_selected, parent, false));

                itemView.setOnClickListener(this);
                mTitle = (TextView) itemView.findViewById(R.id.title);
            }

            public void bind(String data) {
                mTitle.setText(data);
            }

            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPos);
                selectedPos = getLayoutPosition();
                notifyItemChanged(selectedPos);
            }

        }

    }


}
