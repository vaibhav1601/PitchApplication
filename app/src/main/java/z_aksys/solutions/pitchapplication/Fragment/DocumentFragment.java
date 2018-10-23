package z_aksys.solutions.pitchapplication.Fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import z_aksys.solutions.pitchapplication.Adapter.DocumentAdapter;
import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.model.Document;

public class DocumentFragment extends Fragment {
    private RecyclerView recycler_view_document;
    private DocumentAdapter documentAdapter;
    private ArrayList<Document> documentArrayList;

    public DocumentFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_document, container, false);

        recycler_view_document=view.findViewById(R.id.recycler_view_document);
        documentArrayList= new ArrayList<>();
        Document document= new Document();
        document.setDocumentName("Vaibhav");
        document.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document);



        Document document1= new Document();
        document1.setDocumentName("Vaibhav");
        document1.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document1);



        Document document2= new Document();
        document2.setDocumentName("Vaibhav");
        document2.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document2);

        Document document3= new Document();
        document1.setDocumentName("Vaibhav");
        document1.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document1);



        Document document4= new Document();
        document2.setDocumentName("Vaibhav");
        document2.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document2);



        Document document5= new Document();
        document1.setDocumentName("Vaibhav");
        document1.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document1);



        Document document6= new Document();
        document6.setDocumentName("Vaibhav");
        document6.setDocumentSize("size:1.2 MB,Last modified 11/08/2018");
        documentArrayList.add(document6);


        documentAdapter= new DocumentAdapter(getContext(),documentArrayList);

        documentAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_document.setLayoutManager(mLayoutManager);
        recycler_view_document.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recycler_view_document.setItemAnimator(new DefaultItemAnimator());
        recycler_view_document.setAdapter(documentAdapter);
        return view;
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



}
